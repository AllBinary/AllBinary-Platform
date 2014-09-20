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
package org.allbinary.logic.basic.io;

import org.allbinary.logic.basic.io.file.AbFile;

/**
 *
 * @author user
 */
public class DataOutputStreamFactory
{
    private static final DataOutputStreamFactory instance =
        new DataOutputStreamFactory();

    /**
     * @return the instance
     */
    public static DataOutputStreamFactory getInstance()
    {
        return instance;
    }

    public AbDataOutputStream getInstance(AbFile file) throws Exception
    {
        AbFileOutputStream idFileOutputStream = new AbFileOutputStream(file);
        AbDataOutputStream idOutData = new AbDataOutputStream(idFileOutputStream);

        return idOutData;
    }

    public AbDataOutputStream getInstance(String filePath, String fileName) throws Exception
    {
        FileStreamFactory fileStreamFactory =
            FileStreamFactory.getInstance();

        AbFileOutputStream fileOutputStream =
            fileStreamFactory.getFileOutputStreamInstance(
            filePath, fileName);

        AbDataOutputStream idOutData =
            new AbDataOutputStream(fileOutputStream);

        return idOutData;
    }
}
