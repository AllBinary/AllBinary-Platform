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
package org.allbinary.animation.image;

import javax.microedition.lcdui.Image;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.image.ImageCreationUtil;
import org.allbinary.media.image.ImageScaleUtil;

/**
 *
 * @author User
 */
public class ScaleProcessor extends ScaleBaseProcessor {

    private static final ScaleProcessor instance = new ScaleProcessor();

    /**
     * @return the instance
     */
    public static ScaleBaseProcessor getInstance() {
        return instance;
    }
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final ImageCreationUtil imageCreationUtil = ImageCreationUtil.getInstance();
    private final ImageScaleUtil imageScaleUtil = ImageScaleUtil.getInstance();
    
    @Override
    public void update(final Image originalImage, final Image[] originalImageArray, final Image[] ximageToShowArray, final int unused, final float scaleX, final float scaleY, final float maxScaleX, final float maxScaleY) {
        try {

            //Set the max image size needed.
            if (maxScaleX * originalImage.getWidth() > originalImageArray[0].getWidth()
                    || maxScaleY * originalImage.getHeight() > originalImageArray[0].getHeight()) {
                //LogUtil.put(LogFactory.getInstance(new StringMaker().append("scale canvas: ").append(maxScaleX).toString(), this, this.commonStrings.UPDATE));
                originalImageArray[0] = this.imageCreationUtil.createImage(originalImage.getWidth(), originalImage.getHeight(), maxScaleX, maxScaleY);
            }

            //Set the new original image to the current scale
            //LogUtil.put(LogFactory.getInstance(new StringMaker().append("scaleX: ").append(scaleX).append("scaleY: ").append(scaleY).toString(), this, this.commonStrings.UPDATE));
            imageScaleUtil.scale(originalImage, originalImageArray[0], scaleX, scaleY);

        } catch(Exception e) {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.EXCEPTION, this, this.commonStrings.UPDATE));
        }
    }
    
}
