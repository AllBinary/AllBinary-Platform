/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.AbFileNativeUtil;
import abcs.logic.basic.string.StringUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author user
 */
public class BufferedIOUtil
{
    public static void copy(AbFile backupFile, AbFile backupFileBak)
        throws Exception
    {
        String line = StringUtil.getInstance().EMPTY_STRING;

        BufferedWriter tmpOut = new BufferedWriter(
            new FileWriter(AbFileNativeUtil.get(backupFileBak)));

        BufferedReader tmpIn = new BufferedReader(
            new FileReader(AbFileNativeUtil.get(backupFile)));

        while ((line = tmpIn.readLine()) != null)
        {
            tmpOut.write(line, 0, line.length());
            tmpOut.newLine();
        }
        tmpOut.flush();
    }
}
