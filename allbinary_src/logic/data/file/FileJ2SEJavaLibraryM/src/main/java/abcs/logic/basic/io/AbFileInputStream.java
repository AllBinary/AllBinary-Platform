/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.AbFileNativeUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 * @author user
 */
public class AbFileInputStream
    extends FileInputStream
{
    public AbFileInputStream(String name) throws FileNotFoundException {
        super(name);
    }

    public AbFileInputStream(AbFile file) throws FileNotFoundException {
        super(AbFileNativeUtil.get(file));
    }
    
    protected AbFileInputStream(File file) throws FileNotFoundException {
        super(file);
    }
}
