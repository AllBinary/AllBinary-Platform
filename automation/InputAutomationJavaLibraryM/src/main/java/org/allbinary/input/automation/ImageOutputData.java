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
package org.allbinary.input.automation;

import org.allbinary.logic.io.file.FilePathData;
import org.allbinary.logic.string.StringMaker;

public class ImageOutputData
{
    private ImageOutputData()
    {
    }
 
    public final static String SAVE_PATH = 
        new StringMaker().append(System.getProperty("user.dir")).appendchar(FilePathData.getInstance().SEPARATORCHAR).append("saves").appendchar(FilePathData.getInstance().SEPARATORCHAR).toString();
}
