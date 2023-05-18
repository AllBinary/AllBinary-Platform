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
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.microemu.device.playn.PlaynImage;
import org.microemu.device.playn.PlaynMutableImage;
import playn.core.Canvas;
import playn.core.CanvasImage;
import playn.core.ResourceCallback;

/**
 *
 * @author User
 */
public class ImageModifierUtil {
    
    public static ImageModifierUtil getInstanceOrCreate() {
        return new ImageModifierUtil();
    }
    
    private final String HANDLE_IMAGE = "handleImage";
    private final String DONE = "ResourceCallback:done";
    private final String ERROR = "ResourceCallback:error";
    private final String NULL = "ResourceCallback:null isMutable: ";
    
    private boolean[] alphaArray;
    
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
            //LogUtil.put(LogFactory.getInstance("image: " + image, this, "setAlpha"));

//            if(originalImage != null) {
                final PlaynImage htmlImage = (PlaynImage) image;

//                if (htmlImage != null) {
                    final CanvasImage canvasImage = (CanvasImage) htmlImage.getImage();

                    //LogUtil.put(LogFactory.getInstance("htmlImage: " + htmlImage, this, "setAlpha"));
//                    if (canvasImage != null) {
                        //LogUtil.put(LogFactory.getInstance("alpha: " + alpha, this, "setAlpha"));
                        final playn.core.Image originalPlaynImage = ((PlaynImage) originalImage).getImage();
//                        if(originalPlaynImage != null) {
                            final Canvas canvas = canvasImage.canvas();
                            canvas.clear();
                            canvas.setAlpha(alpha);
                            canvas.drawImage(originalPlaynImage, 0, 0);
//                        } else {
//                            LogUtil.put(LogFactory.getInstance("originalPlaynImage: null", this, "setAlpha"));
//                        }
//                    } else {
//                        LogUtil.put(LogFactory.getInstance("canvasImage: null", this, "setAlpha"));
//                    }
//                } else {
//                    LogUtil.put(LogFactory.getInstance("htmlImage: null", this, "setAlpha"));
//                }
//            } else {
//                LogUtil.put(LogFactory.getInstance("originalImage: null", this, "setAlpha"));
//            }


            //canvasImage.canvas().s
            //final CanvasSurface canvasSurface = htmlImage.getCanvasSurface(canvasImage);
        }
    }
    
    public Image[] getImageArray(final Image[] originalImageArray, Image[] imageArray) {
        
        final int size = originalImageArray.length;
        
        this.alphaArray = new boolean[size];
        imageArray = new Image[size];
        
        for(int index = 0; index < size; index++) {
            imageArray[index] = originalImageArray[index];
            this.handleImage(imageArray, index, originalImageArray[index]);
        }
        return imageArray;
    }

    public void handleImage(final Image[] imageArray, final int index, final Image image) {

        final playn.core.Image image3 = ((PlaynImage) image).getImage();
        
        if (image3 != null) {

            if (image3.isReady()) {
                copy(imageArray, index, image, image3);
            } else {
                final ResourceCallback callback = new ResourceCallback() {
                    @Override
                    public void done(Object resource) {
                        LogUtil.put(LogFactory.getInstance(DONE, this, HANDLE_IMAGE));
                        copy(imageArray, index, image, image3);
                    }

                    @Override
                    public void error(Throwable e) {
                        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION_LABEL + ERROR, this, HANDLE_IMAGE));
                    }
                };
                
                image3.addCallback(callback);
            }

        } else {
            LogUtil.put(LogFactory.getInstance(NULL + image.isMutable(), this, HANDLE_IMAGE));
        }
        
    }
    
    public void copy(final Image[] imageArray, final int index, final Image image, final playn.core.Image image3) {
        try {
            //final Image image2 = Image.createImage(image.getWidth(), image.getHeight());
            //LogUtil.put(LogFactory.getInstance(DONE + image3.width() + ", " + image3.height(), this, HANDLE_IMAGE));
            final Image image2 = Image.createImage(image3.width(), image3.height());
            final Graphics graphics = image2.getGraphics();
            graphics.drawImage(image, 0, 0, Anchor.TOP_LEFT);
            imageArray[index] = image2;

        } catch (Exception e) {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION_LABEL + DONE, this, HANDLE_IMAGE));
        }
    }
    
    public void reset() {
        final int size = this.alphaArray.length;
        for(int index = 0; index < size; index++) {
            this.alphaArray[index] = true;
        }
    }
}
