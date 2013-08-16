package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;

import abcs.logic.basic.path.AbPath;

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