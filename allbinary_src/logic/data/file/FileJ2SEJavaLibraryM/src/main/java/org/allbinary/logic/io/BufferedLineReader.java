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

import java.io.BufferedReader;
import java.io.FileReader;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;

public class BufferedLineReader
{
    private final BufferedReader tmpIn;

    public BufferedLineReader(AbFile file) 
        throws Exception
    {
        this.tmpIn = new BufferedReader(
            new FileReader(AbFileNativeUtil.get(file)));
    }

    //If the readahead is less than 2 lines then this will only work some of the time
    public void readUpToLines(long readAheadIndex)
    {
        /*
        if (getCurrent() < getSize())
        {
        	//Read ahead to a line
            while(readAheadIndex >= this.getCurrent())
            {
                this.tmpIn.readLine();
            }
        }
        */
    }

    public String readLine()
        throws Exception
    {
        return this.tmpIn.readLine();
    }

	public long getSize() {
		return 0;
	}

	public int getCurrent() {
		return 0;
	}

}
