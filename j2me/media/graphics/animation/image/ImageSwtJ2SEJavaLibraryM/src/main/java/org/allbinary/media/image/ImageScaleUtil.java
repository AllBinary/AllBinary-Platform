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
package org.allbinary.media.image;

import javax.microedition.lcdui.Image;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;

import org.allbinary.image.ImageCache;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Transform;
import org.microemu.device.swt.SwtImmutableImage;
import org.microemu.device.swt.SwtMutableImage;

public class ImageScaleUtil {

    private static final ImageScaleUtil instance = new ImageScaleUtil();

    public static ImageScaleUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    //private final ImageSwtUtil imageJ2SEUtil = ImageSwtUtil.getInstance();
    //private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();

    private final ImageCopyUtil imageCopyUtil = ImageCopyUtil.getInstance();
    
    private final GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();
    private final Features features = Features.getInstance();
    private final String NO_COPY = "SWT should not copy images after initial loading as the alpha is not honored";
    
    private ImageScaleUtil() {
    }

    public Image createImage(final ImageCache imageCache, final Image originalImage,
        final float scaleNominatorX, final float scaleDenominatorX,
        final float scaleNominatorY, final float scaleDenominatorY, final boolean cached)
        throws Exception {

        return this.createImage(imageCache, originalImage, scaleNominatorX, scaleDenominatorX, scaleNominatorY, scaleDenominatorY, cached, true);

    }
    
    //private int anchor = Anchor.TOP_LEFT;
    public Image createImage(final ImageCache imageCache, final Image originalImage,
        final float scaleNominatorX, final float scaleDenominatorX,
        final float scaleNominatorY, final float scaleDenominatorY, final boolean cached, final boolean mutable)
        throws Exception {
        
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            LogUtil.put(LogFactory.getInstance(NO_COPY, this, commonStrings.CONSTRUCTOR));
            return originalImage;
        }
        
        final int width = originalImage.getWidth();
        final int height = originalImage.getHeight();

        final float scaleX = scaleNominatorX / scaleDenominatorX;
        final float scaleY = scaleNominatorY / scaleDenominatorY;

//        Image image;
//        if (cached) {
//            image = imageCache.get(this.getClass().getName(), (int) (width * scaleX), (int) (height * scaleY));
//        } else {
//            //TWB - Image Create
//            //image = Image.createImage(width, height);
//            image = imageCache.get("createImage", (int) (width * scaleX), (int) (height * scaleY));
//        }
//
//        this.scale(originalImage, image, scaleX, scaleY);

        final Image scaledImage = this.imageCopyUtil.createImage(originalImage, (int) (scaleX * width), (int) (scaleY * height), mutable);

        return scaledImage;
        //throw new RuntimeException("Image Scaling is not supported by J2SE with this call yet");
    }

    public void scale(final Image originalImage, final Image[] originalImageArray, final Image[] ximageToShowArray, final int unused, final float scaleX, final float scaleY, final float maxScaleX, final float maxScaleY) throws Exception {
        this.scale(originalImage, originalImageArray, ximageToShowArray, unused, scaleX, scaleY, maxScaleX, maxScaleY, true);
    }
    
    public void scale(final Image originalImage, final Image[] originalImageArray, final Image[] ximageToShowArray, final int unused, final float scaleX, final float scaleY, final float maxScaleX, final float maxScaleY, final boolean mutable) throws Exception {

        final int width = originalImage.getWidth();
        final int height = originalImage.getHeight();
        
        final Image scaledImage = this.imageCopyUtil.createImage(originalImage, (int) (scaleX * width), (int) (scaleY * height), mutable);
        originalImageArray[0] = scaledImage;
    }    

//    private void scale(final Image originalImage, final Image newMaxSizeImage, final float scaleX, final float scaleY) {
//
//        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
//            LogUtil.put(LogFactory.getInstance(NO_COPY, this, commonStrings.CONSTRUCTOR));
//            return;
//        }

//        org.eclipse.swt.graphics.Image bufferedImage;
//        if (originalImage.isMutable()) {
//            final SwtMutableImage swtImage = (SwtMutableImage) originalImage;
//            bufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
//        } else {
//            final SwtImmutableImage swtImage = (SwtImmutableImage) originalImage;
//            //X cannot be cast to class java.awt.image.org.eclipse.swt.graphics.Image
//            bufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
//        }
//
//        org.eclipse.swt.graphics.Image newBufferedImage;
//        GC gc = null;
//        if (newMaxSizeImage.isMutable()) {
//            final SwtMutableImage swtImage = (SwtMutableImage) newMaxSizeImage;
//            gc = swtImage.getGc();
//            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
//        } else {
//            final SwtImmutableImage swtImage = (SwtImmutableImage) newMaxSizeImage;
//            //X cannot be cast to class java.awt.image.org.eclipse.swt.graphics.Image
//            newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
//            gc = new GC(newBufferedImage);
//        }
//
//        final Transform affineTransform = new Transform(gc.getDevice());
//        affineTransform.scale(scaleX, scaleY);
//        gc.setTransform(affineTransform);
//        //gc.setBackground(imageJ2SEUtil.TRANSPARENT_COLOR);
//        //g.clearRect(0, 0, newBufferedImage.getWidth(), newBufferedImage.getHeight());
//        gc.setAntialias(SWT.ON);
//        gc.setInterpolation(SWT.HIGH);
//        gc.drawImage(bufferedImage, 0, 0, bufferedImage.getBounds().width, bufferedImage.getBounds().height, 0, 0, newBufferedImage.getBounds().width, newBufferedImage.getBounds().height);
//        //gc.drawImage(bufferedImage, 0, 0, bufferedImage.getBounds().width, bufferedImage.getBounds().height, 0, 0, (int) (imageData.width * scaleX), (int) (imageData.height * scaleY));
//        if (!newMaxSizeImage.isMutable()) {
//            gc.dispose();
//        }

//    }

}
