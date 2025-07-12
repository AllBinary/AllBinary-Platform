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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;

public class AbFileOutputStream
    extends FileOutputStream
{
    public AbFileOutputStream(String name)
        throws FileNotFoundException
    {
        super(name);
    }

    public AbFileOutputStream(String name, boolean append)
        throws FileNotFoundException
    {
        super(name, append);
    }

    public AbFileOutputStream(AbFile file) throws FileNotFoundException {
        super(AbFileNativeUtil.get(file));
    }

    public AbFileOutputStream(AbFile file, boolean append)
        throws FileNotFoundException
    {
        super(AbFileNativeUtil.get(file), append);
    }
}
