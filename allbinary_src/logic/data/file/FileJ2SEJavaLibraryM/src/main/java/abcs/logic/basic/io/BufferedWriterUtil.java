/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.AbFileNativeUtil;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author user
 */
public class BufferedWriterUtil {

    public static void write(AbFile abFile, String data) throws Exception
    {
         BufferedWriter fileOut = new BufferedWriter(
             new FileWriter(AbFileNativeUtil.get(abFile)));

         fileOut.write(data, 0, data.length());
         fileOut.newLine();
         fileOut.flush();
    }
}
