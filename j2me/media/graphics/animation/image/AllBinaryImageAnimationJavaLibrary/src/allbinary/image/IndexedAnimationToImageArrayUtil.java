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
package allbinary.image;

import javax.microedition.lcdui.Image;

import allbinary.animation.IndexedAnimation;

public class IndexedAnimationToImageArrayUtil
{
    public static Image[] getInstance(int width, int height,
            IndexedAnimation sequentialAnimationInterface) throws Exception
    {
        int size = sequentialAnimationInterface.getAnimationSize();
        Image[] imageArray = new Image[size];

        for (int index = 0; index < size; index++)
        {
            sequentialAnimationInterface.setFrame(index);
            imageArray[index] = AnimationFrameToImageUtil.getInstance()
                    .getInstance(width, height, sequentialAnimationInterface);
        }

        return imageArray;
    }
}
