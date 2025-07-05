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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Transform;
import org.microemu.device.swt.SwtImmutableImage;
import org.microemu.device.swt.SwtMutableImage;

public class ImageSwtRotationUtil {

    private static final ImageSwtRotationUtil instance = new ImageSwtRotationUtil();

    /**
     * @return the instance
     */
    public static ImageSwtRotationUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final ImageUtil imageUtil = ImageUtil.getInstance();
    //private final ImageSwtUtil imageJ2SEUtil = ImageSwtUtil.getInstance();

    private final GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();
    private final Features features = Features.getInstance();
    private final String NO_COPY = "SWT should not copy images after initial loading as the alpha is not honored";
    
    private ImageSwtRotationUtil() {
    }

    public org.eclipse.swt.graphics.Image getRotatedImage(final org.eclipse.swt.graphics.Image bufferedImage, final int totalAngle) {

        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            LogUtil.put(LogFactory.getInstance(NO_COPY, this, commonStrings.CONSTRUCTOR));
            return bufferedImage;
        }
        
        //final org.eclipse.swt.graphics.Image newBufferedImage = this.imageUtil.create(
            //bufferedImage.getBounds().width, bufferedImage.getBounds().height);
        //final Image image = Image.createImage(bufferedImage.getBounds().width, bufferedImage.getBounds().height);

//        GC newGC = null;
//        org.eclipse.swt.graphics.Image copySwtImage = null;
//        if (image.isMutable()) {
//            //PreLogUtil.put("3a", this, "createRotatedImage");
//            final SwtMutableImage swtImage = (SwtMutableImage) image;
//            copySwtImage = swtImage.getImage();
//            newGC = swtImage.getGc();
//            //final org.eclipse.swt.graphics.Image newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
//            //PreLogUtil.put("4", this, "createRotatedImage");
//        } else {
//            final SwtImmutableImage swtImage = (SwtImmutableImage) image;
//            copySwtImage = swtImage.getImage();
//            newGC = swtImage.getGc();
//        }
//
//        return this.getRotatedImage(bufferedImage, copySwtImage, newGC, totalAngle);
          return bufferedImage;
    }

    private org.eclipse.swt.graphics.Image getRotatedImage2(final org.eclipse.swt.graphics.Image bufferedImage, final org.eclipse.swt.graphics.Image newBufferedImage, final GC gc, final float angle) {
        
        if(!features.isFeature(gameFeatureFactory.POST_IMAGE_LOADING_MODIFICATION)) {
            LogUtil.put(LogFactory.getInstance(NO_COPY, this, commonStrings.CONSTRUCTOR));
            return bufferedImage;
        }

        //LogUtil.put(LogFactory.getInstance("angle: " + angle, this, "getRotatedImage2"));
        
//        final Transform affineTransform = new Transform(gc.getDevice());
//        final int width = bufferedImage.getBounds().width;
//        final int height = bufferedImage.getBounds().height;
//        final int x = (newBufferedImage.getBounds().width - width) / 2;
//        final int y = (newBufferedImage.getBounds().height - height) / 2;
//        affineTransform.translate((x + (width / 2)), (y + (height / 2)));
//        affineTransform.rotate(angle);
//        affineTransform.translate(-(x + (width / 2)), -(y + (height / 2)));
//        gc.setTransform(affineTransform);
//        //gc.setBackground(imageJ2SEUtil.TRANSPARENT_COLOR);
//        //g.clearRect(0, 0, newBufferedImage.getWidth(), newBufferedImage.getHeight());
//        gc.setAntialias(SWT.ON);
//        gc.setInterpolation(SWT.HIGH);
//        gc.drawImage(bufferedImage, 0, 0, width, height, x, y, width, height);
//        //gc.dispose();
//
//        return newBufferedImage;

return bufferedImage;
    }

    public org.eclipse.swt.graphics.Image rotateImage(final org.eclipse.swt.graphics.Image originalSwtImage, final org.eclipse.swt.graphics.Image newSwtImage, final GC gc, final int totalAngle) {
        //g.setBackground(imageJ2SEUtil.TRANSPARENT_COLOR);
        //g.clearRect(0, 0, newBufferedImage.getWidth(), newBufferedImage.getHeight());
        //final org.eclipse.swt.graphics.Image bufferedImage
        
        return this.getRotatedImage2(originalSwtImage, newSwtImage, gc, totalAngle);
    }

    public org.eclipse.swt.graphics.Image getRotatedImage(final org.eclipse.swt.graphics.Image bufferedImage, final org.eclipse.swt.graphics.Image newSwtImage, final GC gc, final int totalAngle) {
        return this.getRotatedImage2(bufferedImage, newSwtImage, gc, totalAngle);
    }

    public org.eclipse.swt.graphics.Image[] getRotatedImages(final org.eclipse.swt.graphics.Image bufferedImage, final int numberOfFrames, final int totalAngle) {
        final org.eclipse.swt.graphics.Image[] bufferedImageArray = new org.eclipse.swt.graphics.Image[numberOfFrames];

        int angle;
        final int size = bufferedImageArray.length;
        for (int index = 0; index < size; index++) {
            angle = (totalAngle / size) * index;
            bufferedImageArray[index] = this.getRotatedImage(bufferedImage, angle);
        }
        return bufferedImageArray;
    }

    public org.eclipse.swt.graphics.Image createSpriteImage(final org.eclipse.swt.graphics.Image[] bufferedImageArray) {
        int columns = 9;
        int rows = 0;

        final int size = bufferedImageArray.length;
        if (size < columns) {
            columns = size;
        }

        rows = (size / columns);

        //Extra row for incomplete but needed row
        if (size % columns != 0) {
            rows++;
        }

        final org.eclipse.swt.graphics.Image firstBufferedImage = bufferedImageArray[0];
        ImageData imageData = firstBufferedImage.getImageData();
        final org.eclipse.swt.graphics.Image bufferedImage = 
            this.imageUtil.create(imageData.width * columns, imageData.height * rows);

        final GC gc = new GC(bufferedImage);
        //gc.setBackground(imageJ2SEUtil.TRANSPARENT_COLOR);
        //g.clearRect(0, 0, newBufferedImage.getWidth(), newBufferedImage.getHeight());
        gc.setAntialias(SWT.ON);
        gc.setInterpolation(SWT.HIGH);

        int columnIndex = 0;
        int rowIndex = 0;

        org.eclipse.swt.graphics.Image nextBufferedImage;
        for (int index = 0; index < size; index++) {
            if (index / 9 != 0 && index % 9 == 0) {
                rowIndex++;
                columnIndex = 0;
            }

            nextBufferedImage = bufferedImageArray[index];
            imageData = nextBufferedImage.getImageData();

            gc.drawImage(bufferedImage, 0, 0, bufferedImage.getBounds().width, bufferedImage.getBounds().height,
                imageData.width * columnIndex,
                imageData.height * rowIndex,
                imageData.width,
                imageData.height);

            columnIndex++;
        }

        gc.dispose();

        return bufferedImage;
    }
}
