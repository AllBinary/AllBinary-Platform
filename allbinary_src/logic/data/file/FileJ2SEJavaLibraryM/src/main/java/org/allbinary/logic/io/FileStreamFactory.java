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
package org.allbinary.logic.io;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPath;

public class FileStreamFactory
{
    private static final FileStreamFactory SINGLETON =
        new FileStreamFactory();

    private FileStreamFactory()
    {
    }

    public static FileStreamFactory getInstance()
    {
        return SINGLETON;
    }

    public AbFileInputStream getFileInputStreamInstance(String path, String fileName)
        throws Exception
    {
        AbPath FILEABPATH = new AbPath(path, fileName);
        AbFile file = new AbFile(FILEABPATH);
        return new AbFileInputStream(file);
    }

    public AbFileOutputStream getFileOutputStreamInstance(String path, String fileName)
        throws Exception
    {
        AbPath FILEABPATH = new AbPath(path, fileName);
        AbFile file = new AbFile(FILEABPATH);

        if (file.exists())
        {
            file.delete();
            file.createNewFile();
        }

        return new AbFileOutputStream(file);
    }

    public void delete(String path, String fileName)
        throws Exception
    {
        AbPath FILEABPATH = new AbPath(path, fileName);
        AbFile file = new AbFile(FILEABPATH);
        file.delete();
    }
}