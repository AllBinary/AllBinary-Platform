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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.allbinary.logic.io.file.AbFile;
import org.allbinary.logic.io.file.AbFileNativeUtil;

public class AbFileInputStream
    extends FileInputStream
{
    public AbFileInputStream(String name) throws FileNotFoundException {
        super(name);
    }

    public AbFileInputStream(AbFile file) throws FileNotFoundException {
        super(AbFileNativeUtil.get(file));
    }
    
    protected AbFileInputStream(File file) throws FileNotFoundException {
        super(file);
    }
}