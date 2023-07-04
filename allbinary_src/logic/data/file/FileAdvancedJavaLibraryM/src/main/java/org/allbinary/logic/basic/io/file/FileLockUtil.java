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
package org.allbinary.logic.basic.io.file;

import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Iterator;
import java.util.Vector;

import org.allbinary.logic.basic.io.AbFileOutputStream;
import org.allbinary.logic.basic.io.StreamUtil;
import org.allbinary.logic.basic.io.file.AbFile;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class FileLockUtil
{
	private static final FileLockUtil instance = new FileLockUtil();
	
    private FileLockUtil()
    {
    }
    
    public static Vector getAll(Vector vector, boolean isReturnOnFailure)
    throws Exception
    {
        Vector fileLockVector = new Vector();
        Iterator iterator = vector.iterator();
        while(iterator.hasNext())
        {
            AbFile file = (AbFile) iterator.next();

            FileLock fileLock = getLock(file);
            if(fileLock != null)
            {
                LogUtil.put(LogFactory.getInstance("File Lock Obtained: " + file.getAbsolutePath(), instance, "getAll"));
                fileLockVector.add(fileLock);
            }
            else
            if(isReturnOnFailure)
            {
                LogUtil.put(LogFactory.getInstance("Total Locks Obtained: " + fileLockVector.size(), instance, "getAll"));
                return fileLockVector;
            }
        }
        LogUtil.put(LogFactory.getInstance("Total Locks Obtained: " + fileLockVector.size(), instance, "getAll"));
        return fileLockVector;
    }

    public static Vector getAllPossible(Vector vector)
    throws Exception
    {
        return getAll(vector, false);
    }

    public static Vector getAllOrNone(Vector vector)
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
    
    public static FileLock getLock(AbFile file)
    throws Exception
    {
        try
        {
            return getLock(new AbFileOutputStream(file, true));
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception returns null", instance, "getLock", e ));
            return null;
        }
        finally
        {
        }
    }

    public static FileLock getLock(AbFileOutputStream fileOutputStream)
    throws Exception
    {
        try
        {
            FileLock fileLock = getLock(fileOutputStream.getChannel());
            return fileLock;
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception returns null", instance, "getLock", e));
            return null;
        }
        finally
        {
            LogUtil.put(LogFactory.getInstance("Finally - Closing FileOutputStream", instance, "getLock"));
            StreamUtil.getInstance().close(fileOutputStream);
        }
    }
    
    public static FileLock getLock(FileChannel fileChannel)
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
            LogUtil.put(LogFactory.getInstance("Exception returns null", instance, "getLock", e));
            return null;
        }
        finally
        {
            LogUtil.put(LogFactory.getInstance("Finally - Closing FileChannel", instance, "getLock"));
            fileChannel.close();
        }
    }
}
