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

import javax.microedition.lcdui.ChoiceGroupImageFactory;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.opengles.OpenGLFeatureFactory;

import org.allbinary.game.configuration.feature.Features;

public class ChoiceGroupImageUtil
{
    private static final ChoiceGroupImageUtil instance = new ChoiceGroupImageUtil();

    /**
     * @return the instance
     */
    public static ChoiceGroupImageUtil getInstance() {
        return instance;
    }
    
    public void init()
    {
        final Features features = Features.getInstance();

        final ChoiceGroupImageFactory choiceGroupFactory = ChoiceGroupImageFactory.getInstance();
        
        final Image[] imageArray = choiceGroupFactory.getImageArray();
        
        if(features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
        {
            final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();
            
            final int size = imageArray.length;
            for(int index = 0; index < size; index++)
            {
                imageArray[index] = preResourceImageUtil.encapsulate(imageArray[index]);
            }
            choiceGroupFactory.init(imageArray);
        }
    }

    public void update(Graphics graphics)
    {
        final Features features = Features.getInstance();
        
        final ChoiceGroupImageFactory choiceGroupFactory = ChoiceGroupImageFactory.getInstance();
        
        final Image[] imageArray = choiceGroupFactory.getImageArray();
        
        if(features.isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
        {
            final PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();
            
            final int size = imageArray.length;
            for(int index = 0; index < size; index++)
            {
                preResourceImageUtil.update(graphics, imageArray[index]);
            }
        }
        
        choiceGroupFactory.init(imageArray);
    }
}
