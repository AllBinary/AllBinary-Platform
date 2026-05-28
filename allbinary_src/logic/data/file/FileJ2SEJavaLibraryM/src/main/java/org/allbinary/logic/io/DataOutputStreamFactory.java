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

public class DataOutputStreamFactory
{
    private static final DataOutputStreamFactory instance =
        new DataOutputStreamFactory();

    /**
     * @return the instance
     */
    public static DataOutputStreamFactory getInstance()
    {
        return DataOutputStreamFactory.instance;
    }

    public AbDataOutputStream getInstanceForAbFile(final AbFile file) throws Exception
    {
        final AbFileOutputStream idFileOutputStream = AbFileOutputStream.createFromAbFile(file);
        final AbDataOutputStream idOutData = new AbDataOutputStream(idFileOutputStream);

        return idOutData;
    }

    public AbDataOutputStream getInstance(final String filePath, final String fileName) throws Exception
    {
        final FileStreamFactory fileStreamFactory =
            FileStreamFactory.getInstance();

        final AbFileOutputStream fileOutputStream =
            fileStreamFactory.getFileOutputStreamInstance(
            filePath, fileName);

        AbDataOutputStream idOutData =
            new AbDataOutputStream(fileOutputStream);

        return idOutData;
    }
}
