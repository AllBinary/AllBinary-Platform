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
import org.allbinary.graphics.opengles.OpenGLFeatureFactory;
import org.allbinary.image.opengles.OpenGLESImage;
import org.allbinary.logic.communication.log.PreLogUtil;

import org.eclipse.swt.graphics.GC;

import org.microemu.device.swt.SwtImmutableImage;
import org.microemu.device.swt.SwtMutableImage;

public class ImageRotationUtil {
    //protected final LogUtil logUtil = LogUtil.getInstance();


    private static final ImageRotationUtil instance = new ImageRotationUtil();

    public static ImageRotationUtil getInstance() {
        return instance;
    }

    private final ImageSwtRotationUtil imageSwtRotationUtil = ImageSwtRotationUtil.getInstance();

    private ImageRotationUtil() {
    }

    private final Features features = Features.getInstance();
    
    public void rotateImage(final Image originalImage, final Image image, final int totalAngle) {

        Image originalImage2 = originalImage;
        if(features.isFeature(OpenGLFeatureFactory.getInstance().OPENGL)) {
            
            if (originalImage.getType() >= OpenGLESImage.TYPE) {
                final OpenGLESImage openGLESImage = ((OpenGLESImage) originalImage);
                //originalImage2 = openGLESImage.openGLBitmap.getImage();
                openGLESImage.openGLESImageProperties.angle = totalAngle;
//                if(originalImage != image) {
//                    throw new RuntimeException();
//                }
                return;
            } else {
                PreLogUtil.put("OpenGL but image is: " + originalImage + " type: " + originalImage.getType(), this, "rotateImage");
            }
        }
        
        final org.eclipse.swt.graphics.Image originalSwtImage = ((org.eclipse.swt.graphics.Image) originalImage2.getImage());

        GC gc = null;
        org.eclipse.swt.graphics.Image copySwtImage = null;
        if (image.isMutable()) {
            //PreLogUtil.put("3a", this, "createRotatedImage");
            final SwtMutableImage swtImage = (SwtMutableImage) image;
            copySwtImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
            gc = swtImage.getGc();
            //final org.eclipse.swt.graphics.Image newBufferedImage = (org.eclipse.swt.graphics.Image) swtImage.getImage();
            //PreLogUtil.put(CommonPhoneStrings.getInstance().FOUR, this, "createRotatedImage");
            imageSwtRotationUtil.rotateImage(originalSwtImage, copySwtImage, gc, totalAngle);
        } else {
            //final SwtImmutableImage swtImage = (SwtImmutableImage) image;
            PreLogUtil.put("No rotation for: " + image.toString(), this, "rotateImage");
        }

    }

    public Image createRotatedImage(Image originalImage, int rotationInDegrees)
        throws Exception {
        //PreLogUtil.put("originalImage: " + originalImage + " rotationInDegrees: " + rotationInDegrees, this, "createRotatedImage");
        //final Image image = ImageCreationUtil.getInstance().getInstance(
            //originalImage.getWidth(), originalImage.getHeight());

        //final org.eclipse.swt.graphics.Image originalSwtImage = ((org.eclipse.swt.graphics.Image) originalImage.getImage());
        
//        if (image.isMutable()) {
//            org.eclipse.swt.graphics.Image newSwtImage = null;
//            GC newGC = null;
//            if (image.isMutable()) {
//                //PreLogUtil.put("3a", this, "createRotatedImage");
//                SwtMutableImage newJ2SEImage = (SwtMutableImage) image;
//                newSwtImage = newJ2SEImage.getImage();
//                newGC = newJ2SEImage.getGc();
//                //PreLogUtil.put(CommonPhoneStrings.getInstance().FOUR, this, "createRotatedImage");
//            } else {
//                //PreLogUtil.put("3b", this, "createRotatedImage");
//                final SwtImmutableImage newJ2SEImage = (SwtImmutableImage) image;
//                newSwtImage = newJ2SEImage.getImage();
//                newGC = newJ2SEImage.getGc();
//                //PreLogUtil.put("4b", this, "createRotatedImage");
//            }
//
//            org.eclipse.swt.graphics.Image bufferedImage = 
//                imageSwtRotationUtil.getRotatedImage(originalSwtImage, newSwtImage, newGC, rotationInDegrees);
//
//            return image;
//        } else {
//            throw new Exception("Not Mutable");
//        }

return originalImage;

    }
}
