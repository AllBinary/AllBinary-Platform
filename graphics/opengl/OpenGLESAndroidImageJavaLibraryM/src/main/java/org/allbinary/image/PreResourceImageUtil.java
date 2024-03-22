package org.allbinary.image;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.OpenGLBitmapFactory;
import org.allbinary.android.device.OpenGLESGraphics;
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.image.opengles.OpenGLImageFactory;
import org.allbinary.image.opengles.OpenGLImageSpecificFactory;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.feature.Features;
import org.platform.opengl.OpenGLTextureFactory;

public class PreResourceImageUtil
{
    private static final PreResourceImageUtil instance = new PreResourceImageUtil();

    public static PreResourceImageUtil getInstance()
    {
        return instance;
    }

    private final Features features = Features.getInstance();
    private final OpenGLFeatureFactory openGLFeatureFactory = OpenGLFeatureFactory.getInstance();

    public Image encapsulate(final Image image)
    {
        //PreLogUtil.put((image != null) + " && " + !(image instanceof OpenGLESImage) + " && " + Features.getInstance().isDefault(OpenGLFeatureFactory.getInstance().OPENGL), this, "encapsulate");
        if (image != null && !(image instanceof OpenGLESImage) && 
                (features.isDefault(openGLFeatureFactory.OPENGL) || features.isDefault(openGLFeatureFactory.OPENGL_SURFACE_VIEW))
                )
        {
            final OpenGLImageFactory imageFactory = 
                    OpenGLImageSpecificFactory.getInstance().getImageFactory();

            return imageFactory.getInstance(image,
                OpenGLBitmapFactory.getInstance(),
                OpenGLTextureFactory.getInstance());
        }
        else
        {
            return image;
        }
    }

    public void update(final Graphics graphics, final Image image)
    {
        try
        {
            if (this.features.isDefault(this.openGLFeatureFactory.OPENGL))
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
