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

import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.NullUtil;

public class FileStreamFactory
{
    private static final FileStreamFactory SINGLETON = new FileStreamFactory(
            ResourceUtil.getInstance().getContext());

    private Object context;

    private FileStreamFactory(final Context context)
    {
        this.context = context;
    }

    public static FileStreamFactory getInstance()
    {
        return SINGLETON;
    }

    public AbFileInputStream getFileInputStreamInstance(final String path, final String fileName) throws Exception
    {
        return new AbFileInputStream(this.getContext().openFileInput(fileName));
    }

    public AbFileOutputStream getFileOutputStreamInstance(final String path, final String fileName) throws Exception
    {
        final Context context = this.getContext();
        context.deleteFile(fileName);
        return new AbFileOutputStream(context.openFileOutput(fileName, 0));
    }

    public void delete(final String path, final String fileName) throws Exception
    {
        this.getContext().deleteFile(fileName);
    }

    private Context getContext()
    {
        return (Context) context;
    }
}
