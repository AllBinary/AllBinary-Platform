/*
 * AllBinary Open License Version 1
 * Copyright (c) 2026 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 * 
 */
package org.allbinary.java.runtime.process.git;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;
import org.allbinary.logic.io.file.directory.TrackedStrings;
import org.allbinary.logic.string.StringMaker;

/**
 *
 * @author User
 */
public class GitProcessHelper {
    
    private static final GitProcessHelper instance = new GitProcessHelper();

    /**
     * @return the instance
     */
    public static GitProcessHelper getInstance() {
        return instance;
    }

    private final TrackedStrings trackedStrings = TrackedStrings.getInstance();
    
    public List<String> trackedFiles(final String rootAsString, final String pathspec) throws IOException, InterruptedException {
        return this.trackedFiles(rootAsString, pathspec, ".java");
    }

    public List<String> trackedFiles(final String rootAsString, final String pathspec, final String suffix) throws IOException, InterruptedException {
        final Process process = new ProcessBuilder(
            this.trackedStrings.GIT_COMMAND, this.trackedStrings.CHANGE_DIRECTORY_OPTION, rootAsString, 
            this.trackedStrings.LIST_FILES_COMMAND, this.trackedStrings.SEP_BY_NULL_CHAR_INSTEAD_OF_NEW_LINE, this.trackedStrings.PATHSPEC_SEPARATOR, pathspec)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();
        final byte[] output = process.getInputStream().readAllBytes();
        final int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IllegalStateException(new StringMaker().append("git ls-files failed for ").append(rootAsString).append(" with exit code ").appendint(exitCode).toString());
        }

        final List<String> files = new ArrayList<>();
        int start = 0;
        String relativePath;
        for (int index = 0; index < output.length; index++) {
            if (output[index] == 0) {
                relativePath = new String(output, start, index - start, StandardCharsets.UTF_8);
                if (relativePath.endsWith(suffix)) {
                    files.add(relativePath);
                }
                start = index + 1;
            }
        }
        return files;
    }

    public boolean isGitTracked(final AbFile file) {
        try {
            final File nativeFile = AbFileNativeUtil.get(file);
            final File parentFile = nativeFile.getParentFile();
            final Process process = new ProcessBuilder(
                this.trackedStrings.GIT_COMMAND, this.trackedStrings.CHANGE_DIRECTORY_OPTION, parentFile.getPath(),
                this.trackedStrings.LIST_FILES_COMMAND, this.trackedStrings.ERROR_UNMATCH_OPTION, this.trackedStrings.PATHSPEC_SEPARATOR, nativeFile.getName())
                .redirectErrorStream(true)
                .start();
            process.getInputStream().readAllBytes();
            return process.waitFor() == 0;
        } catch (Exception e) {
            return false;
        }
    }
    
}
