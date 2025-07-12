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

import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.io.AbFileOutputStream;
import org.allbinary.logic.io.StreamUtil;

public class FileLockUtil
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final FileLockUtil instance = new FileLockUtil();

    public static FileLockUtil getInstance() {
        return instance;
    }
    
    private FileLockUtil()
    {
    }
    
    public Vector getAll(Vector vector, boolean isReturnOnFailure)
    throws Exception
    {
        Vector fileLockVector = new Vector();
        
        final int size = vector.size();
        for (int index = 0; index < size; index++)
        {
            AbFile file = (AbFile) vector.get(index);

            FileLock fileLock = getLock(file);
            if(fileLock != null)
            {
                logUtil.put("File Lock Obtained: " + file.getAbsolutePath(), this, "getAll");
                fileLockVector.add(fileLock);
            }
            else
            if(isReturnOnFailure)
            {
                logUtil.put("Total Locks Obtained: " + fileLockVector.size(), this, "getAll");
                return fileLockVector;
            }
        }
        logUtil.put("Total Locks Obtained: " + fileLockVector.size(), this, "getAll");
        return fileLockVector;
    }

    public Vector getAllPossible(Vector vector)
    throws Exception
    {
        return getAll(vector, false);
    }

    public Vector getAllOrNone(Vector vector)
    throws Exception
    {
        Vector fileLockVector = getAll(vector, true);
        if(vector.size() != fileLockVector.size())
        {
            return new Vector();
        }
        else
        {
            return fileLockVector;
        }
    }
    
    public FileLock getLock(AbFile file)
    throws Exception
    {
        try
        {
            return getLock(new AbFileOutputStream(file, true));
        }
        catch(Exception e)
        {
            logUtil.put("Exception returns null", this, "getLock", e );
            return null;
        }
        finally
        {
        }
    }

    public FileLock getLock(AbFileOutputStream fileOutputStream)
    throws Exception
    {
        try
        {
            FileLock fileLock = getLock(fileOutputStream.getChannel());
            return fileLock;
        }
        catch(Exception e)
        {
            logUtil.put("Exception returns null", this, "getLock", e);
            return null;
        }
        finally
        {
            logUtil.put("Finally - Closing FileOutputStream", this, "getLock");
            StreamUtil.getInstance().close(fileOutputStream);
        }
    }
    
    public FileLock getLock(FileChannel fileChannel)
    throws Exception
    {
        try
        {
            FileLock fileLock = fileChannel.tryLock();
            //fileLock.release();
            return fileLock;
        }
        catch(Exception e)
        {
            logUtil.put("Exception returns null", this, "getLock", e);
            return null;
        }
        finally
        {
            logUtil.put("Finally - Closing FileChannel", this, "getLock");
            fileChannel.close();
        }
    }
}
