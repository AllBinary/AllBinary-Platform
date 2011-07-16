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
package allbinary.input.automation;

import java.io.File;

public class ImageOutputData
{
    private ImageOutputData()
    {
    }
 
    public final static String SAVE_PATH = 
        System.getProperty("user.dir") + File.separator + 
        "saves" + File.separator;
}
