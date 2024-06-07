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
package org.allbinary.image;

import javax.microedition.lcdui.Image;
import org.allbinary.animation.image.BaseImageAnimationFactory;
import org.allbinary.media.ScaleProperties;

import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.media.image.ImageJ2MEScaleUtil;

/**
 *
 * @author User
 */
public class AnimationFactoryImageScaleUtil {
    
    private static final AnimationFactoryImageScaleUtil instance = new AnimationFactoryImageScaleUtil();

    /**
     * @return the instance
     */
    public static AnimationFactoryImageScaleUtil getInstance() {
        return instance;
    }
    
    //private final ImageCache imageCache = ImageCacheFactory.getInstance();
    //private final ImageScaleUtil imageScaleUtil = ImageScaleUtil.getInstance();
    private final ImageJ2MEScaleUtil imageScaleUtil = ImageJ2MEScaleUtil.getInstance();
    //private final ImageCopyUtil imageCopyUtil = ImageCopyUtil.getInstance();
    
    public Image createImage(final Image image, final int width, final int height, final int scaleWidth, final int scaleHeight) throws Exception {
        Image scaledImage;

//        final CommonStrings commonStrings = CommonStrings.getInstance();
//        final StringMaker stringMaker = new StringMaker();
//        stringMaker.delete(0, stringMaker.length());
//        LogUtil.put(LogFactory.getInstance(stringMaker.append("before scale width: ").append(width).append(" height: ").append(height).toString(), this, commonStrings.PROCESS));

        if (scaleWidth != 0 && scaleHeight != 0) {
            final float scaleX = ((float) scaleWidth) / ((float) width);
            final float scaleY = ((float) scaleHeight) / ((float) height);
//            stringMaker.delete(0, stringMaker.length());
//            LogUtil.put(LogFactory.getInstance(stringMaker.append("0scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
            if ((scaleX == 1 && scaleY == 1) || (scaleX == 0 || scaleY == 0)) {
                //scaledImage = this.imageCopyUtil.createImage(image);
                scaledImage = image;
            } else {
//                stringMaker.delete(0, stringMaker.length());
//                LogUtil.put(LogFactory.getInstance(stringMaker.append("scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
                //scaledImage = this.imageScaleUtil.createImage(this.imageCache, image, scaleX, 1, scaleY, 1, true);
                scaledImage = this.imageScaleUtil.scale(image, (int) (scaleX * width), (int) (scaleY * height));
//                stringMaker.delete(0, stringMaker.length());
//                LogUtil.put(LogFactory.getInstance(stringMaker.append("scaledImage.getHeight(): ").append(scaledImage.getHeight()).append(" this.height * scaleY: ").append((height * scaleY)).toString(), this, commonStrings.PROCESS));
            }

        } else {
           //scaledImage = this.imageCopyUtil.createImage(image);
           scaledImage = image;
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append("unscaledImage.getHeight(): ").append(scaledImage.getHeight()).append(" this.height: ").append(height).toString(), this, commonStrings.PROCESS));
        }
        
        return scaledImage;
    }

    public void processAdjust(final BaseImageAnimationFactory baseImageAnimationFactory) throws Exception {
        
        final ScaleProperties scaleProperties = baseImageAnimationFactory.scaleProperties;
        if (scaleProperties.scaleWidth != 0 && scaleProperties.scaleHeight != 0) {
            final float scaleX = ((float) scaleProperties.scaleWidth) / ((float) baseImageAnimationFactory.width);
            final float scaleY = ((float) scaleProperties.scaleHeight) / ((float) baseImageAnimationFactory.height);
            if ((scaleX == 1 && scaleY == 1) || (scaleX == 0 || scaleY == 0)) {
            } else {
                baseImageAnimationFactory.dx = (int) (baseImageAnimationFactory.dx * scaleX);
                baseImageAnimationFactory.dy = (int) (baseImageAnimationFactory.dy * scaleY);
            }

        } else {
        }
    }
}
