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

    protected AbFile(String filePath, boolean unknown) throws Exception
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

    public String getParent()
    {
        return this.file.getParent();
    }

    public File getParentFile()
    {
        return this.file.getParentFile();
    }

    public String getPath()
    {
        return this.file.getPath();
    }

    public boolean isAbsolute()
    {
        return this.file.isAbsolute();
    }

    public String getAbsolutePath()
    {
        return this.file.getAbsolutePath();
    }

    public File getAbsoluteFile()
    {
        return this.file.getAbsoluteFile();
    }

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

    public URI toURI()
    {
        return this.file.toURI();
    }

    public boolean canRead()
    {
        return this.file.canRead();
    }

    public boolean canWrite()
    {
        return this.file.canWrite();
    }

    public boolean exists()
    {
        return this.file.exists();
    }

    public boolean isDirectory()
    {
        return this.file.isDirectory();
    }

    public boolean isFile()
    {
        return this.file.isFile();
    }

    public boolean isHidden()
    {
        return this.file.isHidden();
    }

    public long lastModified()
    {
        return this.file.lastModified();
    }

    public long length()
    {
        return this.file.length();
    }

    public boolean createNewFile() throws IOException
    {
        return this.file.createNewFile();
    }

    //TWB - GAE throws exception but java.io does not
    public boolean delete() throws IOException
    {
        return this.file.delete();
    }

    public void deleteOnExit()
    {
        this.file.deleteOnExit();
    }

    public String[] list()
    {
        return this.file.list();
    }

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

    public boolean mkdir()
    {
        return this.file.mkdir();
    }

    public boolean mkdirs()
    {
        return this.file.mkdirs();
    }

    public boolean renameTo(AbFile dest)
    {
        return this.file.renameTo(dest.getFile());
    }

    public boolean setLastModified(long time)
    {
        return this.file.setLastModified(time);
    }

    public boolean setReadOnly()
    {
        return this.file.setReadOnly();
    }

    public int compareTo(File pathname)
    {
        return this.file.compareTo(pathname);
    }

    public boolean equals(Object obj)
    {
        return this.file.equals(obj);
    }

    public int hashCode()
    {
        return this.file.hashCode();
    }

    public String toString()
    {
        return this.file.toString();
    }
}
