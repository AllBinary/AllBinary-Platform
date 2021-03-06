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
import org.allbinary.logic.basic.io.file.AbFileNativeUtil;
import java.io.BufferedWriter;
import java.io.FileWriter;

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
