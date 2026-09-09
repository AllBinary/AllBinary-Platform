import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RemoveJsInteropAnnotations {

    private static final Pattern IMPORT = Pattern.compile(
            "(?m)^[\\t ]*import[\\t ]+(?:static[\\t ]+)?jsinterop\\.annotations\\.([A-Za-z][A-Za-z0-9]*|\\*)[\\t ]*;[\\t ]*(?:\\r?\\n|$)");
    private static final Pattern ANNOTATION = Pattern.compile(
            "(?m)^[\\t ]*@(?:jsinterop\\.annotations\\.)?(JsType|JsMethod|JsProperty|JsConstructor)(?:[\\t ]*\\([^\\r\\n]*\\))?[\\t ]*(?:\\r?\\n|$)");
    private static final Pattern SUSPICIOUS_ANNOTATION = Pattern.compile(
            "(?m)^[\\t ]*@(?:jsinterop\\.annotations\\.)?(JsType|JsMethod|JsProperty|JsConstructor)\\b");
    private static final Set<String> ANNOTATION_TYPES = new HashSet<>(Arrays.asList(
            "JsType", "JsMethod", "JsProperty", "JsConstructor"));

    private RemoveJsInteropAnnotations() {
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1 || args.length > 2) {
            throw new IllegalArgumentException("Usage: RemoveJsInteropAnnotations <git-root> [pathspec]");
        }

        Path root = Path.of(args[0]).toAbsolutePath().normalize();
        String pathspec = args.length == 2 ? args[1] : "*.java";
        List<String> trackedFiles = trackedFiles(root, pathspec);
        int changedFiles = 0;
        int removedImports = 0;
        int removedAnnotations = 0;

        for (String relativePath : trackedFiles) {
            Path file = root.resolve(relativePath);
            byte[] originalBytes = Files.readAllBytes(file);
            boolean hasBom = originalBytes.length >= 3
                    && originalBytes[0] == (byte) 0xEF
                    && originalBytes[1] == (byte) 0xBB
                    && originalBytes[2] == (byte) 0xBF;
            int textOffset = hasBom ? 3 : 0;
            String source = new String(originalBytes, textOffset, originalBytes.length - textOffset,
                    StandardCharsets.UTF_8);

            Matcher importMatcher = IMPORT.matcher(source);
            Set<String> importedTypes = new HashSet<>();
            int fileImports = 0;
            while (importMatcher.find()) {
                importedTypes.add(importMatcher.group(1));
                fileImports++;
            }

            boolean hasWildcardImport = importedTypes.contains("*");
            Matcher annotationMatcher = ANNOTATION.matcher(source);
            StringBuffer withoutAnnotations = new StringBuffer();
            int fileAnnotations = 0;
            while (annotationMatcher.find()) {
                String annotationType = annotationMatcher.group(1);
                boolean fullyQualified = annotationMatcher.group().contains("jsinterop.annotations.");
                if (fullyQualified || hasWildcardImport || importedTypes.contains(annotationType)) {
                    annotationMatcher.appendReplacement(withoutAnnotations, "");
                    fileAnnotations++;
                }
            }
            annotationMatcher.appendTail(withoutAnnotations);

            String updated = IMPORT.matcher(withoutAnnotations.toString()).replaceAll("");
            if (fileImports > 0) {
                Matcher suspiciousMatcher = SUSPICIOUS_ANNOTATION.matcher(updated);
                while (suspiciousMatcher.find()) {
                    String annotationType = suspiciousMatcher.group(1);
                    if (hasWildcardImport || importedTypes.contains(annotationType)
                            || ANNOTATION_TYPES.contains(annotationType)
                                    && suspiciousMatcher.group().contains("jsinterop.annotations.")) {
                        throw new IllegalStateException("Unsupported annotation layout in " + file + ": "
                                + lineAt(updated, suspiciousMatcher.start()));
                    }
                }
            }

            if (!updated.equals(source)) {
                ByteArrayOutputStream output = new ByteArrayOutputStream(originalBytes.length);
                if (hasBom) {
                    output.write(0xEF);
                    output.write(0xBB);
                    output.write(0xBF);
                }
                output.write(updated.getBytes(StandardCharsets.UTF_8));
                Files.write(file, output.toByteArray());
                changedFiles++;
                removedImports += fileImports;
                removedAnnotations += fileAnnotations;
            }
        }

        System.out.printf("root=%s pathspec=%s tracked=%d changed=%d imports=%d annotations=%d%n",
                root, pathspec, trackedFiles.size(), changedFiles, removedImports, removedAnnotations);
    }

    private static List<String> trackedFiles(Path root, String pathspec) throws IOException, InterruptedException {
        Process process = new ProcessBuilder("git", "-C", root.toString(), "ls-files", "-z", "--", pathspec)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();
        byte[] output = process.getInputStream().readAllBytes();
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IllegalStateException("git ls-files failed for " + root + " with exit code " + exitCode);
        }

        List<String> files = new ArrayList<>();
        int start = 0;
        for (int index = 0; index < output.length; index++) {
            if (output[index] == 0) {
                String relativePath = new String(output, start, index - start, StandardCharsets.UTF_8);
                if (relativePath.endsWith(".java")) {
                    files.add(relativePath);
                }
                start = index + 1;
            }
        }
        return files;
    }

    private static String lineAt(String source, int offset) {
        int start = source.lastIndexOf('\n', Math.max(0, offset - 1)) + 1;
        int end = source.indexOf('\n', offset);
        return source.substring(start, end < 0 ? source.length() : end).trim();
    }
}
