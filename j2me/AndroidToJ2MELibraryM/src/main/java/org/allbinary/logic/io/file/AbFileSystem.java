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
package org.allbinary.logic.io.file;

import java.io.File;

import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class AbFileSystem {
    
    public static String[] getFilesAsStringArrayForPath(final String currentDirPath) {
        final File file = new File(currentDirPath);
        if(file.exists()) {
            return file.list();
        } else {
            return StringUtil.getInstance().getArrayInstance();
        }
    }

}
