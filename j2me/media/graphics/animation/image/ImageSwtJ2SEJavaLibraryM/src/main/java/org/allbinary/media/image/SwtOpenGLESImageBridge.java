package org.allbinary.media.image;

import javax.microedition.lcdui.Image;
import org.allbinary.graphics.opengles.OpenGLUtil;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.image.opengles.OpenGLESImageExclusionUtil;

/** Shares image operations between the SWT and OpenGL implementations. */
final class SwtOpenGLESImageBridge
{
    private SwtOpenGLESImageBridge()
    {
    }

    static Image addImage(final OpenGLUtil util, final Image image)
    {
        return util.addImage(image);
    }

    static boolean isCustomScaling(final OpenGLESImageExclusionUtil util, final Image image)
    {
        return util.isCustomScaling(image);
    }

    static OpenGLESImage getOpenGLESImage(final Image image)
    {
        return (OpenGLESImage) image;
    }

    static Image getBitmapImage(final OpenGLESImage image)
    {
        return image.openGLBitmap.getImage();
    }

    static Image asImage(final OpenGLESImage image)
    {
        return image;
    }
}
