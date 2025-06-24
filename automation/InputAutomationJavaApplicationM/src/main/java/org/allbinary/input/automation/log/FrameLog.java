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
package org.allbinary.input.automation.log;

import org.allbinary.logic.java.number.LongUtil;
import org.allbinary.input.automation.ImageOutputData;
import java.io.File;
import java.io.FileOutputStream;

import java.util.Vector;

public class FrameLog
{
    private static byte[] frameBytes = new String("Frame: ").getBytes();
    private static byte[] info = new String("Info: \n").getBytes();
    private static byte[] actions = new String("Actions: \n").getBytes();
    
    private final Long frame;

    private StringBuffer infoStringBuffer = new StringBuffer();
    private StringBuffer actionsStringBuffer = new StringBuffer();
    
    public FrameLog(Long frame)
    throws Exception
    {
        this.frame = frame;
    }

    public void addInfo(String infoString)
    {
        this.infoStringBuffer.append(infoString);
    }

    public void addActions(Vector<String> vectorOfStrings)
    {
        final int size = vectorOfStrings.size();
        for (int index = 0; index < size; index++)
        {
            this.addAction((String) vectorOfStrings.get(index));
        }
    }
    
    public void addAction(String action)
    {
        this.actionsStringBuffer.append(action);
        this.actionsStringBuffer.append("\n");
    }

    public void write()
    throws Exception
    {
        StringBuffer filePathStringBuffer = new StringBuffer();

        filePathStringBuffer.append(ImageOutputData.SAVE_PATH);
        filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
        filePathStringBuffer.append(".txt");
        
        String filePath = filePathStringBuffer.toString();

        FileOutputStream fileOutputStream = 
            new FileOutputStream(new File(filePath));
        
        fileOutputStream.write(frameBytes);
        fileOutputStream.write(getFrame().toString().getBytes());
        fileOutputStream.write('\n');
        fileOutputStream.write(info);
        fileOutputStream.write(infoStringBuffer.toString().getBytes());
        fileOutputStream.write('\n');
        fileOutputStream.write(actions);
        fileOutputStream.write(actionsStringBuffer.toString().getBytes());
        fileOutputStream.close();
    }   

    public Long getFrame()
    {
        return frame;
    }
}
