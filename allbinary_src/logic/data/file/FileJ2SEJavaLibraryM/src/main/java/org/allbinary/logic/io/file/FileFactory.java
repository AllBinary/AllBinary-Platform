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

public class FileFactory {

    private static final FileFactory instance = new FileFactory();

    /**
     * @return the instance
     */
    public static FileFactory getInstance()
    {
        return FileFactory.instance;
    }

    public AbFile getInstance(String filePath) throws Exception
    {
        return AbFile.createAbFile(filePath, false);
    }

    public AbFile getInstance(AbFile file, String childFilePath) throws Exception
    {
        return AbFile.createAbFile(file, childFilePath);
    }

    public boolean isFile(String path) throws Exception
    {
        AbFile file = AbFile.createAbFile(path);
        if (file.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
