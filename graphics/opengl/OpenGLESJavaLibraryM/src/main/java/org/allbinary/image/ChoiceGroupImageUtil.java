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

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.opengles.OpenGLFeatureFactory;

import org.allbinary.game.configuration.feature.Features;

public class ChoiceGroupImageUtil
{
    public static void init()
    {
        Image[] imageArray = ChoiceGroup.getImageArray();
        
        if(Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
        {
            final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();
            
            int size = imageArray.length;
            for(int index = 0; index < size; index++)
            {
                imageArray[index] = preResourceImageUtil.encapsulate(imageArray[index]);
            }
            ChoiceGroup.init(imageArray);
        }
    }

    public static void update(Graphics graphics)
    {
        Image[] imageArray = ChoiceGroup.getImageArray();
        
        if(Features.getInstance().isDefault(
                OpenGLFeatureFactory.getInstance().OPENGL))
        {
            int size = imageArray.length;
            for(int index = 0; index < size; index++)
            {
                PreResourceImageUtil.getInstance().update(
                        graphics, imageArray[index]);
            }
        }
        
        ChoiceGroup.init(imageArray);
    }
}
