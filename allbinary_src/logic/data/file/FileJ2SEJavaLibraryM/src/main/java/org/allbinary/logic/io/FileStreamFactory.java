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

import org.allbinary.logic.NullUtil;
import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.path.AbPath;

public class FileStreamFactory
{
    private static Object SINGLETON = NullUtil.getInstance().NULL_OBJECT;

    public static FileStreamFactory getInstance()
    {
        if(FileStreamFactory.SINGLETON == NullUtil.getInstance().NULL_OBJECT) {
            FileStreamFactory.SINGLETON = new FileStreamFactory();
        }

        return (FileStreamFactory) FileStreamFactory.SINGLETON;
    }

    private FileStreamFactory()
    {
    }

    public AbFileInputStream getFileInputStreamInstance(final String path, final String fileName)
        throws Exception
    {
        final AbPath FILEABPATH = new AbPath(path, fileName);
        final AbFile file = AbFile.createAbFileFromAbPath(FILEABPATH);
        return new AbFileInputStream(file);
    }

    public AbFileOutputStream getFileOutputStreamInstance(final String path, final String fileName)
        throws Exception
    {
        final AbPath FILEABPATH = new AbPath(path, fileName);
        final AbFile file = AbFile.createAbFileFromAbPath(FILEABPATH);

        if (file.exists())
        {
            file.delete();
            file.createNewFile();
        }

        return AbFileOutputStream.createFromAbFile(file);
    }

    public void delete(final String path, final String fileName)
        throws Exception
    {
        final AbPath FILEABPATH = new AbPath(path, fileName);
        final AbFile file = AbFile.createAbFileFromAbPath(FILEABPATH);
        file.delete();
    }
}