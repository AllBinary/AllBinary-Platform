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
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.string.CommonStrings;

public class BufferedWriterUtil {

    private static final BufferedWriterUtil instance = new BufferedWriterUtil();

    /**
     * @return the instance
     */
    public static BufferedWriterUtil getInstance() {
        return BufferedWriterUtil.instance;
    }

    private final LogUtil logUtil = LogUtil.getInstance();
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public void overwrite(final String path, final String data) throws Exception
    {
        final AbFile abFile = AbFile.createAbFile(path);
        if(abFile.exists()) {
            abFile.delete();
        } else {
            //Create all of the directories that the file needs when it does not exist already.
            final String name = AbPathData.getInstance().removeNameFromPath(path);
            final AbFile abFileDirectory = AbFile.createAbFile(name);
            if(abFileDirectory.exists()) {
                
            } else {
                this.logUtil.putF(name, this, this.commonStrings.CREATE);
                abFileDirectory.mkdirs();
            }            
        }

        this.write(abFile, data);
    }
    
    public void overwrite(final AbFile abFile, final String data) throws Exception
    {
        if(abFile.exists()) {
            abFile.delete();
        } else {
            //Create all of the directories that the file needs when it does not exist already.
            final String name = AbPathData.getInstance().removeNameFromPath(abFile.getAbsolutePath());
            final AbFile abFileDirectory = AbFile.createAbFile(name);
            if(abFileDirectory.exists()) {
                
            } else {
                this.logUtil.putF(name, this, this.commonStrings.CREATE);
                abFileDirectory.mkdirs();
            }            
        }

        this.write(abFile, data);
    }
    
    public void write(final AbFile abFile, final String data) throws Exception {

        final BufferedWriter fileOut = new BufferedWriter(
            new FileWriter(AbFileNativeUtil.get(abFile)));

        fileOut.write(data, 0, data.length());
        fileOut.newLine();
        fileOut.flush();
    }

}
