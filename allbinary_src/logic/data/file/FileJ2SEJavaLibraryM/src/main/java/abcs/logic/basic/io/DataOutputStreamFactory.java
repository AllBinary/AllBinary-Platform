/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;

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
