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
package org.allbinary.logic.io.file;

import java.io.File;

import org.allbinary.logic.communication.log.PreLogUtil;

public class FileWrapperUtil {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    public static AbFile[] wrapFiles(Object[] files)
    {
        try
        {
            final AbFile[] abFileArray = new AbFile[files.length];

            for (int index = files.length - 1; index >= 0; index--)
            {
                abFileArray[index] = new AbFile((File) files[index]);
            }

            return abFileArray;
        } catch (Exception e)
        {
            PreLogUtil.put("Exception Wrapping Files", "FileWrapperUtil", "wrapFiles", e);
            return new AbFile[0];
        }
    }

    public static AbFile wrapFile(File file)
    {
        try
        {
            return new AbFile(file);
        } catch (Exception e)
        {
            PreLogUtil.put("Exception Wrapping File", "FileWrapperUtil", "wrapFile", e);
            return AbFile.NULL_FILE;
        }
    }
}
