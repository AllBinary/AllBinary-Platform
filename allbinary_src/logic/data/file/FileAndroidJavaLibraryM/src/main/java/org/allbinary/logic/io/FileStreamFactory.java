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

import android.content.Context;
import org.allbinary.data.resource.NullAndroidContextFactory;

import org.allbinary.data.resource.ResourceUtil;

public class FileStreamFactory
{
    private static final FileStreamFactory SINGLETON = new FileStreamFactory(
            ResourceUtil.getInstance().getContext());

    private Context context = NullAndroidContextFactory.getInstance();

    private FileStreamFactory(Context context)
    {
        this.setContext(context);
    }

    public static FileStreamFactory getInstance()
    {
        return SINGLETON;
    }

    public AbFileInputStream getFileInputStreamInstance(String path,
            String fileName) throws Exception
    {
        return new AbFileInputStream(this.getContext().openFileInput(fileName));
    }

    public AbFileOutputStream getFileOutputStreamInstance(String path,
            String fileName) throws Exception
    {
        this.getContext().deleteFile(fileName);
        return new AbFileOutputStream(this.getContext().openFileOutput(fileName, 0));
    }

    public void delete(String path, String fileName) throws Exception
    {
        this.getContext().deleteFile(fileName);
    }

    private void setContext(Context context)
    {
        this.context = context;
    }

    private Context getContext()
    {
        return context;
    }
}
