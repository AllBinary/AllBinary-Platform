/*
* AllBinary Open License Version 1
* Copyright (c) 2023 AllBinary
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
package org.allbinary.media.image;

import javax.microedition.lcdui.Image;

import org.allbinary.logic.communication.log.LogUtil;
import org.microemu.device.playn.PlaynImmutableImage;
import org.microemu.device.playn.PlaynMutableImage;

import playn.core.Canvas;
import playn.core.CoreImage;
import playn.core.ImageImpl;
import playn.core.PlayN;
import playn.html.HtmlGraphics;
import playn.html.HtmlImage;

/**
 *
 * @author user
 */
public class ImageJ2MEScaleUtil {

    private static final ImageJ2MEScaleUtil instance = new ImageJ2MEScaleUtil();

    /**
     * @return the instance
     */
    public static ImageJ2MEScaleUtil getInstance() {
        return ImageJ2MEScaleUtil.instance;
    }

    protected final LogUtil logUtil = LogUtil.getInstance();
        
    private ImageJ2MEScaleUtil() {

    }

    public Image[] scaleArray(Image[] images, int width, int height)
        throws Exception {
        Image[] scaledImages = new Image[images.length];

        for (int index = images.length; --index >= 0;) {
            scaledImages[index] = this.scale(images[index], width, height);
        }

        return scaledImages;
    }

    public Image scale(final Image image, final int width, final int height)
        throws Exception {

        CoreImage originalPlayNImage = null;
        if (image.isMutable()) {
            //PreLogUtil.put("3a", this, "createImage");
            final PlaynMutableImage originalHTMLImage = (PlaynMutableImage) image;
            originalPlayNImage = (CoreImage) originalHTMLImage.getImage();
            //PreLogUtil.put(CommonPhoneStrings.getInstance().FOUR, this, "createImage");
        } else {
            //PreLogUtil.put("3b", this, "createImage");
            final PlaynImmutableImage originalHTMLImage = (PlaynImmutableImage) image;
            originalPlayNImage = (CoreImage) originalHTMLImage.getImage();
            //PreLogUtil.put("4b", this, "createImage");
        }

        final Image scaledImage = Image.createImageWH(width, height);
        final PlaynMutableImage htmlImage = (PlaynMutableImage) scaledImage;
        final ImageImpl scaledPlayNImage = (ImageImpl) htmlImage.getImage();

        final Canvas canvas = ((HtmlGraphics) PlayN.getInstance().graphics()).get((HtmlImage) scaledPlayNImage);
        ((ImageImpl) originalPlayNImage).draw(canvas.gc(), 0, 0, scaledImage.getWidth(), scaledImage.getHeight(), 0, 0, image.getWidth(), image.getHeight());

        return scaledImage;

    }
}
