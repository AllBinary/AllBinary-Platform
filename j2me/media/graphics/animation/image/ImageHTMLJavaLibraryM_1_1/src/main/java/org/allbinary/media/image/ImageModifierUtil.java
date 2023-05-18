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

import javax.microedition.lcdui.Image;
import org.allbinary.graphics.Anchor;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.microemu.device.playn.PlaynImage;
import org.microemu.device.playn.PlaynImmutableImage;
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
    
    public void setAlpha(final Image originalImage, final Image alphaImage, final int alpha) {
        final float alphaF = alpha;
        final float alphaFloat = alphaF / 255;
        //final float alphaFloat = alpha / 100;
        //final int alphaInt = alpha * 100 / 255;
        this.setAlpha(originalImage, alphaImage, alphaFloat);
    }
    
    public void setAlpha(final Image originalImage, final Image alphaImage,  final float alpha) {
        
        //LogUtil.put(LogFactory.getInstance("image: " + image, this, "setAlpha"));
        
        CanvasImage canvasImage;
        //if(image.isMutable()) {
            final PlaynMutableImage htmlImage = (PlaynMutableImage) alphaImage;
            canvasImage = (CanvasImage) htmlImage.getImage();
        //} else {
            //final PlaynImmutableImage htmlImage = (PlaynImmutableImage) image;
            //canvasImage = (CanvasImage) htmlImage.getImage();
        //}
        
        //LogUtil.put(LogFactory.getInstance("htmlImage: " + htmlImage, this, "setAlpha"));
        
        if(canvasImage != null) {
            //LogUtil.put(LogFactory.getInstance("alpha: " + alpha, this, "setAlpha"));
            final playn.core.Image image = ((PlaynImage) originalImage).getImage();
            final Canvas canvas = canvasImage.canvas();
            canvas.clear();
            canvas.setAlpha(alpha);
            canvas.drawImage(image, 0, 0);
        } //else {
            //LogUtil.put(LogFactory.getInstance("alpha: null", this, "setAlpha"));
        //}
        //canvasImage.canvas().s
        //final CanvasSurface canvasSurface = htmlImage.getCanvasSurface(canvasImage);
    }
    
    public Image[] getImageArray(final Image[] originalImageArray, final Image[] imageArray) {
        
        final int size = imageArray.length;
        for(int index = 0; index < size; index++) {
            imageArray[index] = originalImageArray[index];
        }
        return imageArray;
    }

    public void handleImage(final Image[] imageArray, final int index, final Image image) {
        
        final playn.core.Image image3 = ((PlaynImmutableImage) image).getImage();
        if (image3 != null) {
            final ResourceCallback callback = new ResourceCallback() {
                @Override
                public void done(Object resource) {
                    try {
                        LogUtil.put(LogFactory.getInstance("ResourceCallback:done", this, "handleImage"));
                        //final javax.microedition.lcdui.Image image2 = javax.microedition.lcdui.Image.createImage(image.getWidth(), image.getHeight());
                        final javax.microedition.lcdui.Image image2 = javax.microedition.lcdui.Image.createImage(64, 64);
                        final javax.microedition.lcdui.Graphics graphics = image2.getGraphics();
                        graphics.drawImage(image, 0, 0, Anchor.TOP_LEFT);
                        imageArray[index] = image2;
                    } catch (Exception e) {
                        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION_LABEL + "ResourceCallback:done", this, "handleImage"));
                    }
                }

                @Override
                public void error(Throwable e) {
                    LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION_LABEL + "ResourceCallback:error", this, "handleImage"));
                }
            };

            if (image3.isReady()) {
                callback.done(this);
            } else {
                image3.addCallback(callback);
            }

        } else {
            LogUtil.put(LogFactory.getInstance("ResourceCallback:null", this, "handleImage"));
        }
        
    }
}
