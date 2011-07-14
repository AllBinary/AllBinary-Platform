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
package org.allbinary.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class PreResourceImageUtil
{
    private static final PreResourceImageUtil instance = new PreResourceImageUtil();

    /**
     * @return the instance
     */
    public static PreResourceImageUtil getInstance()
    {
        return instance;
    }

    public Image encapsulate(Image image)
    {
        return image;
    }

    public void update(Graphics graphics, Image image)
    {
    }
}
