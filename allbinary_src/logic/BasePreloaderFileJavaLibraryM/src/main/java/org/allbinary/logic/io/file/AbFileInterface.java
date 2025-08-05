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

import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author user
 */
public interface AbFileInterface {

    //boolean canExecute();

    boolean canRead();

    boolean canWrite();

    boolean createNewFile() throws IOException;

    boolean delete() throws IOException;

    void deleteOnExit();

    //boolean equals(Object obj);

    boolean exists();

    String getAbsolutePath();

    String getCanonicalPath() throws IOException;

    //long getFreeSpace();

    //String getName();

    String getParent();

    String getPath();

    //long getTotalSpace();

    //long getUsableSpace();

    //int hashCode();

    boolean isAbsolute();

    boolean isDirectory();

    boolean isFile();

    boolean isHidden();

    long lastModified();

    long length();

    String[] list();

    String[] list(FilenameFilter filter);

    boolean mkdir();

    boolean mkdirs();
        
    //boolean setExecutable(boolean executable, boolean ownerOnly);

    //boolean setExecutable(boolean executable);

    boolean setLastModified(long time);

    boolean setReadOnly();

    //boolean setReadable(boolean readable, boolean ownerOnly);

    //boolean setReadable(boolean readable);

    //boolean setWritable(boolean writable, boolean ownerOnly);

    //boolean setWritable(boolean writable);

    String toString();

    URI toURI();
}
