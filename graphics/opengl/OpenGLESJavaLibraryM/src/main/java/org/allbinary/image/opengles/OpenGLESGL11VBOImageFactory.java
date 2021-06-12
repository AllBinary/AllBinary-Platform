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
package org.allbinary.image.opengles;

import javax.microedition.lcdui.Image;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.logic.communication.log.PreLogUtil;

public class OpenGLESGL11VBOImageFactory extends OpenGLImageFactory
{
    public Image getInstance(Image image)
    {
        //PreLogUtil.put(StringUtil.getInstance().EMPTY_STRING, this, CommonStrings.getInstance().GET_INSTANCE);
        return new OpenGLESGL10Image(image);
    }
    
}
