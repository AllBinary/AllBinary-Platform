/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.AbFileNativeUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author user
 */
public class AbFileOutputStream
    extends FileOutputStream
{
    public AbFileOutputStream(String name)
        throws FileNotFoundException
    {
        super(name);
    }

    public AbFileOutputStream(String name, boolean append)
        throws FileNotFoundException
    {
        super(name, append);
    }

    public AbFileOutputStream(AbFile file) throws FileNotFoundException {
        super(AbFileNativeUtil.get(file));
    }

    public AbFileOutputStream(AbFile file, boolean append)
        throws FileNotFoundException
    {
        super(AbFileNativeUtil.get(file), append);
    }
}
