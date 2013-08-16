/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.AbFileNativeUtil;
import java.io.FileNotFoundException;

/**
 *
 * @author user
 */
public class AbFileLocalInputStream
    extends AbFileInputStream
{
    public AbFileLocalInputStream(AbFile file) throws FileNotFoundException {
        super(AbFileNativeUtil.get(file));
    }
}
