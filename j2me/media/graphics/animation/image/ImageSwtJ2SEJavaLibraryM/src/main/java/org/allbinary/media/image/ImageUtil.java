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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.ImageData;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

import org.microemu.app.BareMain;

public class ImageUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final ImageUtil instance = new ImageUtil();

    /**
     * @return the instance
     */
    public static ImageUtil getInstance() {
        return instance;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final CommonSeps commonSeps = CommonSeps.getInstance();

    private String IIOIMAGE_POOL_NAME = "IIOIMAGE_POOL_NAME";
    //public static PoolInterface poolInterface = null;

    private final String CREATE_BUFFERED_IMAGE = "createBufferedImage";

    private ImageUtil() {
        try {
            logUtil.put(commonStrings.START, this, commonStrings.CONSTRUCTOR);

            /*
         poolInterface =
         PoolInterfaceFactory.getInstance(
         new IIOImageCacheableFactory(),
         PoolType.SHIFT_ONE_VECTOR_POOL,
         CachePolicy.MAX_TIME_THOUSAND_MAX);
             */
        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e);
        }
    }

    public org.eclipse.swt.graphics.Image create(final int width, final int height) {
        final Display display = BareMain.shell.getDisplay();
        return new org.eclipse.swt.graphics.Image(display, width, height);
    }

    public org.eclipse.swt.graphics.Image[] createBufferedImage(final org.eclipse.swt.graphics.Image[] bufferedImageArray, final int percent, final boolean scale)
        throws Exception {
        final int size = bufferedImageArray.length;
        final org.eclipse.swt.graphics.Image[] scaledBufferedImageArray = new org.eclipse.swt.graphics.Image[size];

        org.eclipse.swt.graphics.Image bufferedImage;
        ImageData imageData;
        for (int index = 0; index < size; index++) {
            bufferedImage = bufferedImageArray[index];
            imageData = bufferedImage.getImageData();
            final int newWidth = (int) (imageData.width * percent / 100);
            final int newHeight = (int) (imageData.height * percent / 100);

            scaledBufferedImageArray[index]
                = this.createBufferedImage(bufferedImage, newWidth, newHeight, scale);
        }

        return scaledBufferedImageArray;
    }

    public org.eclipse.swt.graphics.Image[] createBufferedImage(final org.eclipse.swt.graphics.Image[] bufferedImageArray, final float percent, final boolean scale)
        throws Exception {
        final int size = bufferedImageArray.length;
        final org.eclipse.swt.graphics.Image[] scaledBufferedImageArray = new org.eclipse.swt.graphics.Image[size];

        org.eclipse.swt.graphics.Image bufferedImage;
        ImageData imageData;
        for (int index = 0; index < size; index++) {
            bufferedImage = bufferedImageArray[index];
            imageData = bufferedImage.getImageData();
            final int newWidth = (int) (imageData.width * percent);
            final int newHeight = (int) (imageData.height * percent);

            scaledBufferedImageArray[index]
                = this.createBufferedImage(bufferedImage, newWidth, newHeight, scale);
        }

        return scaledBufferedImageArray;
    }

    public org.eclipse.swt.graphics.Image[] createBufferedImage(final org.eclipse.swt.graphics.Image[] bufferedImageArray, final int width, final int height, final boolean scale)
        throws Exception {
        final int size = bufferedImageArray.length;
        final org.eclipse.swt.graphics.Image[] scaledBufferedImageArray = new org.eclipse.swt.graphics.Image[size];

        for (int index = 0; index < size; index++) {
            scaledBufferedImageArray[index]
                = this.createBufferedImage(bufferedImageArray[index], width, height, scale);
        }

        return scaledBufferedImageArray;
    }

    public org.eclipse.swt.graphics.Image createBufferedImage(final org.eclipse.swt.graphics.Image bufferedImage, final int newWidth, int newHeight)
        throws Exception {
        return this.createBufferedImage(bufferedImage, newWidth, newHeight, true);
    }

    public org.eclipse.swt.graphics.Image createBufferedImage(final org.eclipse.swt.graphics.Image bufferedImage, final int newWidth, int newHeight, final boolean scale) throws Exception {
        return this.createBufferedImage(bufferedImage, newWidth, newHeight, scale, false);
    }

    public org.eclipse.swt.graphics.Image createBufferedImage(final org.eclipse.swt.graphics.Image bufferedImage, final int newWidth, int newHeight, final boolean scale, final boolean allowTranslate)
        throws Exception {
        final ImageData imageData = bufferedImage.getImageData();
        final float width = imageData.width;
        final float height = imageData.height;
        final float d_newWidth = newWidth;
        final float d_newHeight = newHeight;
        final float widthRatio = d_newWidth / width;
        final float heightRatio = d_newHeight / height;

        float ratioX = 1.0f;
        float ratioY = 1.0f;
        if (scale) {
            ratioX = widthRatio;
            ratioY = heightRatio;
        }

        logUtil.put(new StringMaker().append(width).append(this.commonSeps.FORWARD_SLASH).append(height)
            .append(this.commonSeps.COLON).append(newWidth).append(this.commonSeps.FORWARD_SLASH).append(newHeight).append(this.commonSeps.COLON)
            .append(widthRatio).append(this.commonSeps.FORWARD_SLASH).append(heightRatio).toString(), this, CREATE_BUFFERED_IMAGE);

        float dx = 0;
        float dy = 0;
        if (!scale && allowTranslate) {
            dx = (newWidth - width) / 2;
            dy = (newHeight - height) / 2;
            logUtil.put(new StringMaker().append("Translate dx: ").append(dx).append(" dy: ").append(dy).toString(), this, CREATE_BUFFERED_IMAGE);

//          if (newWidth < width) {
//              final double translate = -(width - newWidth);
//              logUtil.put("Translating to keep image centered x3: " + translate, this, CREATE_BUFFERED_IMAGE);
//              affineTransform.translate(translate, 0);
//          }
//          if (newHeight < height) {
//              //final double translate = -(height - newHeight) / 2;
//              final double translate = -(height - newHeight);
//              logUtil.put("Translating to keep image centered y0: " + translate, this, CREATE_BUFFERED_IMAGE);
//              affineTransform.translate(0, translate);
//          }
//
//          //if(newHeight > height && widthRatio <= 1) {
//          if (newHeight > height) {
//              final double translate = (newHeight - height) / 2;
//              logUtil.put("Translating to keep image centered y1: " + translate, this, CREATE_BUFFERED_IMAGE);
//              affineTransform.translate(0, translate);
//          }
//          //if(newWidth > width && heightRatio <= 1) {
//          if (newWidth > width) {
//              final double translate = (newWidth - width) / 2;
//              logUtil.put("Translating to keep image centered x2: " + translate, this, CREATE_BUFFERED_IMAGE);
//              affineTransform.translate(translate, 0);
//          }
        }

        final org.eclipse.swt.graphics.Image newBufferedImage = new org.eclipse.swt.graphics.Image(BareMain.shell.getDisplay(),
            newWidth, newHeight); //org.eclipse.swt.graphics.Image.TYPE_INT_ARGB_PRE

        //bufferedImage.getImageData().scaledTo(newWidth, newHeight);
        final GC gc = new GC(newBufferedImage);

        final Transform affineTransform = new Transform(gc.getDevice());
        affineTransform.scale(ratioX, ratioY);
        affineTransform.translate(dx, dy);
        gc.setTransform(affineTransform);

        gc.setAntialias(SWT.ON);
        gc.setInterpolation(SWT.HIGH);
        gc.drawImage(bufferedImage, 0, 0, bufferedImage.getBounds().width, bufferedImage.getBounds().height, 0, 0, newWidth, newHeight);
        //gc.drawImage(bufferedImage, 0, 0, bufferedImage.getBounds().width, bufferedImage.getBounds().height, (int) dx, (int) dy, (int) ((width * ratioX) + dx), (int) ((height * ratioY) + dy));
        gc.dispose();

        return newBufferedImage;
    }

    public Canvas drawTransformedImage(final org.eclipse.swt.graphics.Image image, final Composite parent, final int dx, final int dy, final int width, final int height) {
        final Canvas imageCanvas = new Canvas(parent, SWT.NONE);
        imageCanvas.addPaintListener(new PaintListener() {

            public void paintControl(PaintEvent event) {
                final GC gc = event.gc;

                final Transform affineTransform = new Transform(gc.getDevice());
                affineTransform.translate(dx, dy);

                gc.setAntialias(SWT.ON);
                gc.setInterpolation(SWT.HIGH);
                gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, 0, 0, width, height);
                //gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, dx, dx, width + dx, height + dx);
            }

        });

        return imageCanvas;
    }

    public String toString(org.eclipse.swt.graphics.Image bufferedImage) {
        final CommonLabels commonLabels = CommonLabels.getInstance();
        final StringBuffer stringBuffer = new StringBuffer();

        final ImageData imageData = bufferedImage.getImageData();

        stringBuffer.append(" org.eclipse.swt.graphics.Image: ");
        stringBuffer.append(commonLabels.WIDTH_LABEL);
        stringBuffer.append(imageData.width);
        stringBuffer.append(commonLabels.HEIGHT_LABEL);
        stringBuffer.append(imageData.height);
        stringBuffer.append(" Type: ");
        stringBuffer.append(imageData.type);

        return stringBuffer.toString();
    }
}
