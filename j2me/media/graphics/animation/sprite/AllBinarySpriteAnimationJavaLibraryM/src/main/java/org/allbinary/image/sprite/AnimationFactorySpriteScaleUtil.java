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
import org.allbinary.graphics.SpacialStrings;
import org.allbinary.graphics.opengles.OpenGLUtil;
import org.allbinary.image.ImageCache;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.image.opengles.OpenGLESImageExclusionUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

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
    //private final ImageCopyUtil imageCopyUtil = ImageCopyUtil.getInstance();
    private final OpenGLUtil openGLUtil = OpenGLUtil.getInstance();
    
    private final OpenGLESImageExclusionUtil openGLESImageExclusionUtil = OpenGLESImageExclusionUtil.getInstance();
    
    public Sprite createImage(final Image image, final int width, final int height, final int scaleWidth, final int scaleHeight) throws Exception {
//       final CommonStrings commonStrings = CommonStrings.getInstance();
//       final SpacialStrings spacialStrings = SpacialStrings.getInstance();
//       final StringMaker stringMaker = new StringMaker();
//       .append(image.getName())
//       LogUtil.put(LogFactory.getInstance(stringMaker.append("width: ").append(width).append(" height: ").append(height).append("scaleWidth: ").append(scaleWidth).append(" scaleHeight: ").append(scaleHeight).toString(), this, commonStrings.PROCESS));

      final SpriteFactory spriteFactory = SpriteFactory.getInstance();

       Sprite sprite;
       Image scaledImage;
       if(scaleWidth != 0 && scaleHeight != 0) {
           float scaleX = ((float) scaleWidth) / ((float) width);
           float scaleY = ((float) scaleHeight) / ((float) height);
           
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append(spacialStrings.WIDTH_LABEL).append(width).append(spacialStrings.HEIGHT_LABEL).append(height).toString(), this, commonStrings.PROCESS));
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append("0scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));

           if ((scaleX == 1 && scaleY == 1) || (scaleX == 0 || scaleY == 0)) {
//               stringMaker.delete(0, stringMaker.length());
//               LogUtil.put(LogFactory.getInstance(stringMaker.append("noscale ").append(spacialStrings.WIDTH_LABEL).append(width).append(spacialStrings.HEIGHT_LABEL).append(height).toString(), this, commonStrings.PROCESS));
               scaledImage = openGLUtil.add(image);
               sprite = spriteFactory.create(scaledImage, width, height);
           } else {
//               stringMaker.delete(0, stringMaker.length());
//               LogUtil.put(LogFactory.getInstance(stringMaker.append("scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
               if(openGLESImageExclusionUtil.isCustomScaling(image)) {
                   //This does not work for J2ME.
                   final int width2 =  Math.round((scaleWidth) - 0.5f);
                   final int height2 =  Math.round((scaleHeight) - 0.5f);

                   final int multiplesOf16Width = width2 / 16;
                   final int by16Width = multiplesOf16Width * 16;
                   scaleX = ((float) by16Width) / width;

                   final int multiplesOf16Height = height2 / 16;
                   final int by16Height = multiplesOf16Height * 16;
                   scaleY = ((float) by16Height) / height;
                   
//                   scaleX = 1.0f;
//                   scaleY = 1.0f;

                    if(scaleWidth < width) {
                        //information_blue.png
//                        stringMaker.delete(0, stringMaker.length());
//                        LogUtil.put(LogFactory.getInstance(stringMaker
//                            .append(image.getName())
//                            .append(" 2.35f").toString(), this, commonStrings.PROCESS));
                        
                        scaleX = scaleX * 2.35f;
                    }
                    if(scaleHeight < height) {
                        //information_blue.png
//                        stringMaker.delete(0, stringMaker.length());
//                        LogUtil.put(LogFactory.getInstance(stringMaker
//                            .append(image.getName())
//                            .append(" 2.35f").toString(), this, commonStrings.PROCESS));

                        scaleY = scaleY * 2.35f;
                    }

//                   stringMaker.delete(0, stringMaker.length());
//                   LogUtil.put(LogFactory.getInstance(stringMaker
//                       //.append(image.getName())
//                       .append(" scale set to 1 - scaleX: ").append(scaleX).append(" scaleY: ").append(scaleY).toString(), this, commonStrings.PROCESS));
               }

               scaledImage = imageScaleUtil.createImage(imageCache, image, scaleX, 1, scaleY, 1, true);
//               stringMaker.delete(0, stringMaker.length());
//               LogUtil.put(LogFactory.getInstance(stringMaker.append("scaledImage.getHeight(): ").append(scaledImage.getHeight()).append(" height * scaleY: ").append(height * scaleY).toString(), this, commonStrings.PROCESS));
               sprite = spriteFactory.create(scaledImage, (int) (width * scaleX), (int) (height * scaleY));
           }
           
       } else {
//           stringMaker.delete(0, stringMaker.length());
//           LogUtil.put(LogFactory.getInstance(stringMaker.append("noscale2").append(spacialStrings.WIDTH_LABEL).append(width).append(spacialStrings.HEIGHT_LABEL).append(height).toString(), this, commonStrings.PROCESS));
           scaledImage = openGLUtil.add(image);
           sprite = spriteFactory.create(scaledImage, width, height);
       }
        
        return sprite;
    }
}
