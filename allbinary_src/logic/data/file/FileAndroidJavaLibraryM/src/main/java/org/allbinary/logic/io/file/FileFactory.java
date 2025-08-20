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
import java.io.FileNotFoundException;

import android.content.Context;

import org.allbinary.data.resource.ResourceUtil;

/**
 *
 * @author user
 */
public class FileFactory {

    private static final FileFactory SINGLETON = new FileFactory(
            ResourceUtil.getInstance().getContext());
    
    private Context context;

    private FileFactory(Context context)
    {
        this.context = context;
    }
    
    public static FileFactory getInstance()
    {
        return SINGLETON;
    }

    public boolean isFile(String path) throws Exception
    {
        try
        {
//            final File file = new File(path);
//            if (file.exists() && file.isFile()) {
//                return true;
//            } else {
//                //Some early Android devices do not give true for File checking even when file does exist
              context.openFileInput(path);
//            }
            return true;
        }
        catch (FileNotFoundException e)
        {
            return false;
        }
    }
    
    /*
    public boolean isFile(AbPath abPath) throws Exception
    {
        try
        {
            context.openFileInput(abPath.toFileSystemString());
            return true;
        }
        catch (FileNotFoundException e)
        {
            return false;
        }
    }

    public boolean isFile(String path) throws Exception
    {
        AbFile file = new AbFile(path);
        if (file.exists())
        {
            return true;
        }
        else
        {
            return false;
        }
    }    
    */

    public Context getContext()
    {
        return context;
    }

    public AbFile getFile(String filePath) throws Exception
    {
        return new AbFile(filePath, false);
    }

    public AbFile getInstance(AbFile file, String childFilePath) throws Exception
    {
        return new AbFile(file, childFilePath);
    }
}
