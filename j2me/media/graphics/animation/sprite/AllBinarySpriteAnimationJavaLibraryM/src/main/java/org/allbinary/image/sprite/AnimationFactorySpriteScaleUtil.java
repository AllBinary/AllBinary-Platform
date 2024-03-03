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
package org.allbinary.image.sprite;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.game.layer.SpriteFactory;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;

import org.allbinary.media.image.ImageCopyUtil;
import org.allbinary.media.image.ImageScaleUtil;

/**
 *
 * @author User
 */
public class AnimationFactorySpriteScaleUtil {
    
    private static final AnimationFactorySpriteScaleUtil instance = new AnimationFactorySpriteScaleUtil();

    /**
     * @return the instance
     */
    public static AnimationFactorySpriteScaleUtil getInstance() {
        return instance;
    }
    
    private final ImageCache imageCache = ImageCacheFactory.getInstance();
    private final ImageScaleUtil imageScaleUtil = ImageScaleUtil.getInstance();
    private final ImageCopyUtil imageCopyUtil = ImageCopyUtil.getInstance();
    
    public Sprite createImage(final Image image, final int width, final int height, final int scaleWidth, final int scaleHeight) throws Exception {
//       final CommonStrings commonStrings = CommonStrings.getInstance();
//       final StringMaker stringMaker = new StringMaker();
//       LogUtil.put(LogFactory.getInstance(stringMaker.append("scaleWidth: ").append(scaleWidth).append(" scaleHeight: ").append(scaleHeight).toString(), this, commonStrings.PROCESS));
       Sprite sprite;

       if(scaleWidth != 0 && scaleHeight != 0) {
           final float scaleX = ((float) scaleWidth) / ((float) width);
           final float scaleY = ((float) scaleHeight) / ((float) height);
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append("width: ").append(width).append(" height: ").append(height).toString(), this, commonStrings.PROCESS));
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append("0scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
           Image scaledImage;
           if ((scaleX == 1 && scaleY == 1) || (scaleX == 0 || scaleY == 0)) {
               scaledImage = image;
               sprite = SpriteFactory.getInstance().create(image, width, height);
           } else {
//               stringMaker.delete(0, stringMaker.length());
//               LogUtil.put(LogFactory.getInstance(stringMaker.append("scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
               scaledImage = imageScaleUtil.createImage(imageCache, image, scaleX, 1, scaleY, 1, true);
//               stringMaker.delete(0, stringMaker.length());
//               LogUtil.put(LogFactory.getInstance(stringMaker.append("scaledImage.getHeight(): ").append(scaledImage.getHeight()).append(" this.height * scaleY: ").append(this.height * scaleY).toString(), this, commonStrings.PROCESS));
               sprite = SpriteFactory.getInstance().create(scaledImage, (int) (width * scaleX), (int) (height * scaleY));
           }
           
       } else {
           sprite = SpriteFactory.getInstance().create(image, width, height);
       }
        
        return sprite;
    }
}
