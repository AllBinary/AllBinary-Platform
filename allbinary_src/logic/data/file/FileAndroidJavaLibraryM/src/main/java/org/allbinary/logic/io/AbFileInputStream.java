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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/**
 *
 * @author user
 */
public class AbFileInputStream
    //extends FileInputStream
    extends InputStream
{
    /*
    public AbFileInputStream(String name) throws FileNotFoundException {
        super(name);
    }

    public AbFileInputStream(AbFile file) throws FileNotFoundException {
        super(AbFileNativeUtil.get(file));
    }
    
    protected AbFileInputStream(File file) throws FileNotFoundException {
        super(file);
    }
    */
    
    private FileInputStream fileInputStream;
    
    protected AbFileInputStream(FileInputStream fileInputStream)
        throws FileNotFoundException
    {
        //super(fileInputStream.toString());

        this.fileInputStream = fileInputStream;
    }

    public void mark(int readlimit) 
    {
        this.fileInputStream.mark(readlimit);
    }

    public boolean markSupported() 
    {
        return this.fileInputStream.markSupported();
    }

    public synchronized void reset()
    throws IOException
    {
        this.fileInputStream.reset();
    }

    public int available()
    throws IOException
    {
        return this.fileInputStream.available();
    }
    
    public void close()
    throws IOException
    {
        this.fileInputStream.close();
    }
        
    public FileChannel getChannel()
    {
        return this.fileInputStream.getChannel();
    }
    
    //public FileDescriptor getFD()
    //{
        //return this.fileInputStream.getFD();
    //}
    
    public int read()
    throws IOException
    {
        return this.fileInputStream.read();
    }
    
    public int read(byte[] b) 
    throws IOException
    {
        return this.fileInputStream.read(b);
    }
    
    public int read(byte[] buffer, int offset, int count)
    throws IOException
    {
        return this.fileInputStream.read(buffer, offset, count);
    }
    
    public long skip(long n)
    throws IOException
    {
        return this.fileInputStream.skip(n);
    }    
}
