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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.basic.io.file.AbFileNativeUtil;

/**
 * 
 * @author user
 */
public class AbFileOutputStream 
    //extends FileOutputStream
    extends OutputStream
{
    public AbFileOutputStream(String name) throws FileNotFoundException
    {
        //super(name);
    }

    /*
    public AbFileOutputStream(String name, boolean append) throws FileNotFoundException
    {
        super(name, append);
    }

    public AbFileOutputStream(AbFile file) throws FileNotFoundException
    {
        super(AbFileNativeUtil.get(file));
    }

    public AbFileOutputStream(AbFile file, boolean append) throws FileNotFoundException
    {
        super(AbFileNativeUtil.get(file), append);
    }
    */
    
    private FileOutputStream fileOutputStream;

    public AbFileOutputStream(AbFile file) throws FileNotFoundException
    {
        this.fileOutputStream = 
            new FileOutputStream(AbFileNativeUtil.get(file));
    }
    
    protected AbFileOutputStream(FileOutputStream fileOutputStream)
    throws FileNotFoundException
    {
        //super(fileOutputStream.toString());
        
        this.fileOutputStream = fileOutputStream;
    }

    public void close()
    throws IOException
    {
        this.fileOutputStream.close();
    }

    public FileChannel getChannel() 
    {
        return this.fileOutputStream.getChannel();
    }

    /*
    public FileDescriptor getFD() 
    {
        return this.fileOutputStream.getFD();
    }
    */

    public void write(byte[] buffer)
    throws IOException
    {
        this.fileOutputStream.write(buffer);
    }

    public void write(byte[] buffer, int offset, int count)
    throws IOException
    {
        this.fileOutputStream.write(buffer, offset, count);
    }

    public void write(int b)
    throws IOException
    {
        this.fileOutputStream.write(b);
    }
    
}
