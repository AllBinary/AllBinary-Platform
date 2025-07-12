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
package org.allbinary.logic.io;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;

public class BufferedWriterUtil {

    private static final BufferedWriterUtil instance = new BufferedWriterUtil();

    /**
     * @return the instance
     */
    public static BufferedWriterUtil getInstance() {
        return instance;
    }

    public void overwrite(final String path, final String data) throws Exception
    {
        final AbFile abFile = new AbFile(path);
        if(abFile.exists()) {
            abFile.delete();
        }

        this.write(abFile, data);
    }
    
    public void overwrite(final AbFile abFile, final String data) throws Exception
    {
        if(abFile.exists()) {
            abFile.delete();
        }

        this.write(abFile, data);
    }
    
    public void write(final AbFile abFile, final String data) throws Exception
    {
         final BufferedWriter fileOut = new BufferedWriter(
             new FileWriter(AbFileNativeUtil.get(abFile)));

         fileOut.write(data, 0, data.length());
         fileOut.newLine();
         fileOut.flush();
    }
}
