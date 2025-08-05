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
    
    public AbFileInputStream(FileInputStream fileInputStream)
        throws FileNotFoundException
    {
        //super(fileInputStream.toString());

        this.fileInputStream = fileInputStream;
    }

    @Override
    public void mark(int readlimit) 
    {
        this.fileInputStream.mark(readlimit);
    }

    @Override
    public boolean markSupported() 
    {
        return this.fileInputStream.markSupported();
    }

    @Override
    public synchronized void reset()
    throws IOException
    {
        this.fileInputStream.reset();
    }

    @Override
    public int available()
    throws IOException
    {
        return this.fileInputStream.available();
    }
    
    @Override
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

    @Override    
    public int read()
    throws IOException
    {
        return this.fileInputStream.read();
    }
    
    @Override
    public int read(byte[] b) 
    throws IOException
    {
        return this.fileInputStream.read(b);
    }
    
    @Override
    public int read(byte[] buffer, int offset, int count)
    throws IOException
    {
        return this.fileInputStream.read(buffer, offset, count);
    }
    
    @Override
    public long skip(long n)
    throws IOException
    {
        return this.fileInputStream.skip(n);
    }    
}
