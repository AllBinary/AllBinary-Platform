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
package org.allbinary.logic.io.file;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;

import org.allbinary.logic.io.path.AbPath;

public class AbFile implements AbFileInterface
{
    private final File file;

    AbFile(String filePath, boolean unknown) throws Exception
    {
        this.file = new File(filePath);
    }

    protected AbFile(File file) throws Exception
    {
        this.file = file;
    }

    public AbFile(AbFile file, String childPathName) throws Exception
    {
        this.file = new File(file.getFile(), childPathName);
    }

    public AbFile(String filePath) throws Exception
    {
        this.file = new File(new AbPath(filePath).toFileSystemString());
    }

    public AbFile(String filePath, String fileName) throws Exception
    {
        this.file = new File(new AbPath(filePath).toFileSystemString(), fileName);
    }

    public AbFile(AbPath abPath)
    {
        this.file = new File(abPath.toFileSystemString());
    }

    /**
     * @return the file
     */
    protected File getFile()
    {
        return file;
    }

    public String getName()
    {
        return this.file.getName();
    }

    @Override
    public String getParent()
    {
        return this.file.getParent();
    }

    public File getParentFile()
    {
        return this.file.getParentFile();
    }

    @Override
    public String getPath()
    {
        return this.file.getPath();
    }

    @Override
    public boolean isAbsolute()
    {
        return this.file.isAbsolute();
    }

    @Override
    public String getAbsolutePath()
    {
        return this.file.getAbsolutePath();
    }
    
    public File getAbsoluteFile()
    {
        return this.file.getAbsoluteFile();
    }

    @Override
    public String getCanonicalPath() throws IOException
    {
        return this.file.getCanonicalPath();
    }

    public File getCanonicalFile() throws IOException
    {
        return this.file.getCanonicalFile();
    }

    /*
    public URL toURL() throws MalformedURLException
    {
        return this.file.toURL();
    }
    */

    @Override
    public URI toURI()
    {
        return this.file.toURI();
    }

    @Override
    public boolean canRead()
    {
        return this.file.canRead();
    }

    @Override
    public boolean canWrite()
    {
        return this.file.canWrite();
    }

    @Override
    public boolean exists()
    {
        return this.file.exists();
    }

    @Override
    public boolean isDirectory()
    {
        return this.file.isDirectory();
    }

    @Override
    public boolean isFile()
    {
        return this.file.isFile();
    }

    @Override
    public boolean isHidden()
    {
        return this.file.isHidden();
    }

    @Override
    public long lastModified()
    {
        return this.file.lastModified();
    }

    @Override
    public long length()
    {
        return this.file.length();
    }

    @Override
    public boolean createNewFile() throws IOException
    {
        return this.file.createNewFile();
    }

    //TWB - GAE throws exception but java.io does not
    @Override
    public boolean delete() throws IOException
    {
        return this.file.delete();
    }

    @Override
    public void deleteOnExit()
    {
        this.file.deleteOnExit();
    }

    @Override
    public String[] list()
    {
        return this.file.list();
    }

    @Override
    public String[] list(FilenameFilter filter)
    {
        return this.file.list(filter);
    }

    public File[] listFiles()
    {
        return this.file.listFiles();
    }

    public File[] listFiles(FilenameFilter filter)
    {
        return this.file.listFiles(filter);
    }

    public File[] listFiles(FileFilter filter)
    {
        return this.file.listFiles(filter);
    }

    @Override
    public boolean mkdir()
    {
        return this.file.mkdir();
    }

    @Override
    public boolean mkdirs()
    {
        return this.file.mkdirs();
    }

    public boolean renameTo(AbFile dest)
    {
        return this.file.renameTo(dest.getFile());
    }

    @Override
    public boolean setLastModified(long time)
    {
        return this.file.setLastModified(time);
    }

    @Override
    public boolean setReadOnly()
    {
        return this.file.setReadOnly();
    }

    public int compareTo(File pathname)
    {
        return this.file.compareTo(pathname);
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.file.equals(obj);
    }

    @Override
    public int hashCode()
    {
        return this.file.hashCode();
    }

    public String toString()
    {
        return this.file.toString();
    }
}
