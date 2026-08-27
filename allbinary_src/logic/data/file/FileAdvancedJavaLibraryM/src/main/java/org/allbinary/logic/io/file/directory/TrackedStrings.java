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
package org.allbinary.logic.io.file.directory;

/**
 *
 * @author User
 */
public class TrackedStrings {
 
    private static final TrackedStrings instance = new TrackedStrings();

    /**
     * @return the instance
     */
    public static TrackedStrings getInstance() {
        return instance;
    }
    
    public final String TARGET_PATH_WINDOWS = "\\target\\";
    public final String TARGET_PATH_UNIX = "/target/";
    
    public final String GIT_COMMAND = "git";
    public final String CHANGE_DIRECTORY_OPTION = "-C";
    public final String LIST_FILES_COMMAND = "ls-files";
    public final String ERROR_UNMATCH_OPTION = "--error-unmatch";
    public final String PATHSPEC_SEPARATOR = "--";

    
}
