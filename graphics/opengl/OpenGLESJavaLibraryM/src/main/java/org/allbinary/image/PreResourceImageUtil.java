package org.allbinary.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.android.device.OpenGLESGraphics;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.image.opengles.OpenGLImageFactory;
import org.allbinary.image.opengles.OpenGLImageSpecificFactory;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.feature.Features;

public class PreResourceImageUtil
{
    private static final PreResourceImageUtil instance = new PreResourceImageUtil();

    public static PreResourceImageUtil getInstance()
    {
        return instance;
    }

    public Image encapsulate(Image image)
    {
        if (image != null && !(image instanceof OpenGLESImage)
                && Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL))
        {
            OpenGLImageFactory imageFactory = 
                    OpenGLImageSpecificFactory.getInstance().getImageFactory();

            return imageFactory.getInstance(image);
        }
        else
        {
            return image;
        }
    }

    public void update(Graphics graphics, Image image)
    {
        try
        {
            if (Features.getInstance().isDefault(
                    OpenGLFeatureFactory.getInstance().OPENGL))
            {
                ((OpenGLESImage) image).set(
                        ((OpenGLESGraphics) graphics).getGl());
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }
}
