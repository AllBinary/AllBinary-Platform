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

import org.allbinary.animation.Animation;

public class AnimationFrameToImageUtil
{

    private static final AnimationFrameToImageUtil instance = new AnimationFrameToImageUtil();

    public static AnimationFrameToImageUtil getInstance()
    {
        return AnimationFrameToImageUtil.instance;
    }

    public Image getInstanceTranslate(int width, int height,
            Animation animationInterface) throws Exception
    {

        Image image = GameFeatureImageCacheFactory.getInstance().get(
                AnimationFrameToImageUtil.instance.getClass().getName(), ((width * 3) >> 1),
                ((height * 3) >> 1));

        Graphics graphics = image.getGraphics();

        int tranlateX = (width >> 2);
        int tranlateY = (height >> 2);

        graphics.translate(tranlateX, tranlateY);

        animationInterface.paintXY(graphics, 0, 0);

        graphics.translate(-tranlateX, -tranlateY);

        return image;
    }

    public Image getInstanceWH(int width, int height,
                               Animation animationInterface) throws Exception
    {

        Image image = GameFeatureImageCacheFactory.getInstance().get(
                AnimationFrameToImageUtil.instance.getClass().getName(), width, height);

        Graphics graphics = image.getGraphics();

        animationInterface.paintXY(graphics, 0, 0);

        return image;
    }
}
