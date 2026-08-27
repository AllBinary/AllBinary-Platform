/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
package org.allbinary.logic.io.file.directory;

import java.io.File;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;
import org.allbinary.logic.io.file.visitor.IncludeFileExtensionsBooleanFileVisitor;
import org.allbinary.util.BasicArrayList;

public class DirectoryOrIncludeFileExtensionAndTrackedBooleanFileVisitor
    extends IncludeFileExtensionsBooleanFileVisitor {

    private final TrackedStrings trackedStrings = TrackedStrings.getInstance();
    
    private final String includesString;

    public DirectoryOrIncludeFileExtensionAndTrackedBooleanFileVisitor(final BasicArrayList filterStringBasicArrayList) {
        super(filterStringBasicArrayList);

        this.includesString = null;
    }

    public DirectoryOrIncludeFileExtensionAndTrackedBooleanFileVisitor(final BasicArrayList filterStringBasicArrayList, final String includesString) {
        super(filterStringBasicArrayList);

        this.includesString = includesString;
    }

    public Boolean visit(AbFile file) {
        if (file.isDirectory()) {
            return Boolean.TRUE;
        }

        final String filePath = file.getAbsolutePath();
        if (this.includesString == null || (filePath.indexOf(this.includesString) >= 0 && (filePath.contains(trackedStrings.TARGET_PATH_WINDOWS) || filePath.contains(trackedStrings.TARGET_PATH_UNIX)) && this.isGitTracked(file))) {
            return super.visit(file);
        } else {
            return Boolean.FALSE;
        }

    }

    private boolean isGitTracked(final AbFile file) {
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
