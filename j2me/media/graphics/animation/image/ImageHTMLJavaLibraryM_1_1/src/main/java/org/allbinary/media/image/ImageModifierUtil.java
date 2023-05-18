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
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.microemu.device.playn.PlaynImage;
import org.microemu.device.playn.PlaynMutableImage;
import playn.core.Canvas;
import playn.core.CanvasImage;

/**
 *
 * @author User
 */
public class ImageModifierUtil {
    
    private static final ImageModifierUtil instance = new ImageModifierUtil();

    /**
     * @return the instance
     */
    public static ImageModifierUtil getInstance() {
        return instance;
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
            LogUtil.put(LogFactory.getInstance("alpha: " + alpha, this, "setAlpha"));
            final playn.core.Image image = ((PlaynImage) originalImage).getImage();
            final Canvas canvas = canvasImage.canvas();
            canvas.clear();
            canvas.setAlpha(alpha);
            canvas.drawImage(image, 0, 0);
        } else {
            LogUtil.put(LogFactory.getInstance("alpha: null", this, "setAlpha"));
        }
        //canvasImage.canvas().s
        //final CanvasSurface canvasSurface = htmlImage.getCanvasSurface(canvasImage);
    }
}
