/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.basic.io.file;

/**
 *
 * @author user
 */
public class FileFactory {

    private static final FileFactory instance = new FileFactory();

    /**
     * @return the instance
     */
    public static FileFactory getInstance()
    {
        return instance;
    }

    public AbFile getInstance(String filePath) throws Exception
    {
        return new AbFile(filePath, false);
    }

    public AbFile getInstance(AbFile file, String childFilePath) throws Exception
    {
        return new AbFile(file, childFilePath);
    }

    public boolean isFile(String path) throws Exception
    {
        AbFile file = new AbFile(path);
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
