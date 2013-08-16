/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.basic.io.file;

import abcs.logic.communication.log.PreLogUtil;
import java.io.File;

/**
 *
 * @author user
 */
public class FileWrapperUtil {

    public static AbFile[] wrapFiles(File[] files)
    {
        try
        {
            AbFile[] abFileArray = new AbFile[files.length];

            for (int index = files.length - 1; index >= 0; index--)
            {
                abFileArray[index] = new AbFile(files[index]);
            }

            return abFileArray;
        } catch (Exception e)
        {
            PreLogUtil.put("Exception Wrapping Files", "FileWrapperUtil", "wrapFiles", e);
            return new AbFile[0];
        }
    }

    public static AbFile wrapFile(File file)
    {
        try
        {
            return new AbFile(file);
        } catch (Exception e)
        {
            PreLogUtil.put("Exception Wrapping File", "FileWrapperUtil", "wrapFile", e);
            return null;
        }
    }
}
