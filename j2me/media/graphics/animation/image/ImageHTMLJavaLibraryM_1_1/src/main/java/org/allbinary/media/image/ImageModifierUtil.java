/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.string.CommonStrings;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.microemu.device.playn.PlaynImage;
import org.microemu.device.ResourceCallbackStrings;

import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.ResourceCallback;

/**
 *
 * @author User
 */
public class ImageModifierUtil {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    public static ImageModifierUtil getInstanceOrCreate() {
        return new ImageModifierUtil();
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    private final ResourceCallbackStrings resourceCallbackStrings = ResourceCallbackStrings.getInstance();
    
    private boolean[] alphaArray;
    
    public void setColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {
        
    }

    public void changeColor(final Image unusedOriginalImage, final Image image, final int imageIndex, final BasicColor basicColor) {
        
    }

    public void setAlpha(final Image originalImage, final Image image, final int imageIndex, final int alpha) {
        final float alphaF = alpha;
        final float alphaFloat = alphaF / 255;
        //final float alphaFloat = alpha / 100;
        //final int alphaInt = alpha * 100 / 255;
        this.setAlpha(originalImage, image, imageIndex, alphaFloat);
    }
    
    public void setAlpha(final Image originalImage, final Image image, final int imageIndex, final float alpha) {
        
        if(this.alphaArray[imageIndex]) {
            
            this.alphaArray[imageIndex] = false;
            
            this.setAlpha2(originalImage, image, imageIndex, alpha);
        }
    }

    public void setAlpha2(final Image originalImage, final Image image, final int imageIndex, final float alpha) {
        
            //logUtil.put("image: " + image, this, "setAlpha");

//            if(originalImage != null) {
                final PlaynImage htmlImage = (PlaynImage) image;

//                if (htmlImage != null) {
                    final CanvasImage canvasImage = (CanvasImage) htmlImage.getImage();

                    //logUtil.put("htmlImage: " + htmlImage, this, "setAlpha");
//                    if (canvasImage != null) {
                        //logUtil.put("alpha: " + alpha, this, "setAlpha");
                        final playn.core.Image originalPlaynImage = (playn.core.Image) ((PlaynImage) originalImage).getImage();
//                        if(originalPlaynImage != null) {
                            final Canvas canvas = canvasImage.canvas();
                            canvas.clear();
                            canvas.setAlpha(alpha);
                            canvas.drawImage(originalPlaynImage, 0, 0);
//                        } else {
//                            logUtil.put("originalPlaynImage: null", this, "setAlpha");
//                        }
//                    } else {
//                        logUtil.put("canvasImage: null", this, "setAlpha");
//                    }
//                } else {
//                    logUtil.put("htmlImage: null", this, "setAlpha");
//                }
//            } else {
//                logUtil.put("originalImage: null", this, "setAlpha");
//            }


            //canvasImage.canvas().s
            //final CanvasSurface canvasSurface = htmlImage.getCanvasSurface(canvasImage);
    }

    public void setAlpha3(final Image image, final float alpha) {
        final float alphaF = alpha;
        final float alphaFloat = alphaF / 255;
        final PlaynImage htmlImage = (PlaynImage) image;
        final CanvasImage canvasImage = (CanvasImage) htmlImage.getImage();
        final Canvas canvas = canvasImage.canvas();
        canvas.setAlpha(alphaFloat);
    }
    
    public Image[] getImageArray(final Image[] originalImageArray) {
        
        final int size = originalImageArray.length;
        
        this.alphaArray = new boolean[size];
        final Image[] imageArray = new Image[size];
        
        for(int index = 0; index < size; index++) {
            imageArray[index] = originalImageArray[index];
            this.handleImage(imageArray, index, originalImageArray[index]);
        }
        return imageArray;
    }

    public void handleImage(final Image[] imageArray, final int index, final Image image) {

        final playn.core.Image image3 = (playn.core.Image) ((PlaynImage) image).getImage();
        
        if (image3 != null) {

            if (image3.isReady() || image3.width() + image3.height() <= 0 || image.getName() == resourceCallbackStrings.FROM_DATA) {
                copy(imageArray, index, image, image3);
            } else {

                final ResourceCallback callback = new ResourceCallback() {
                    @Override
                    public void done(Object resource) {
                        logUtil.put(resourceCallbackStrings.DONE + image.getName(), this, resourceCallbackStrings.HANDLE_IMAGE);
                        copy(imageArray, index, image, image3);
                    }

                    @Override
                    public void error(Throwable e) {
                        logUtil.put(new StringMaker().append(commonStrings.EXCEPTION_LABEL).append(resourceCallbackStrings.ERROR).append(image.getName()).toString(), this, resourceCallbackStrings.HANDLE_IMAGE);
                    }
                };
                
                image3.addCallback(callback);

            }

        } else {
            logUtil.put(resourceCallbackStrings.NULL + image.isMutable(), this, resourceCallbackStrings.HANDLE_IMAGE);
        }
        
    }
    
    public void copy(final Image[] imageArray, final int index, final Image image, final playn.core.Image image3) {
        try {
            //final Image image2 = Image.createImage(image.getWidth(), image.getHeight());
            //logUtil.put(DONE + image3.width() + ", " + image3.height(), this, HANDLE_IMAGE);
            final Image image2 = Image.createImage(image3.width(), image3.height());
            final Graphics graphics = image2.getGraphics();
            graphics.drawImage(image, 0, 0, Anchor.TOP_LEFT);
            imageArray[index] = image2;

        } catch (Exception e) {
            logUtil.put(commonStrings.EXCEPTION_LABEL + resourceCallbackStrings.DONE, this, resourceCallbackStrings.HANDLE_IMAGE);
        }
    }
    
    public void reset() {
        final int size = this.alphaArray.length;
        for(int index = 0; index < size; index++) {
            this.alphaArray[index] = true;
        }
    }
}
