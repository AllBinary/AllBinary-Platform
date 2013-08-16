/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abcs.logic.basic.io;

import abcs.logic.basic.io.file.AbFile;
import abcs.logic.basic.io.file.AbFileNativeUtil;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author user
 */
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
