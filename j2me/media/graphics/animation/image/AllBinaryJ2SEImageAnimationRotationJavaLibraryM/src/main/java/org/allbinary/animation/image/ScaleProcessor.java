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
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.media.image.ImageScaleUtil;

/**
 *
 * @author User
 */
public class ScaleProcessor extends ScaleBaseProcessor {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final ScaleProcessor instance = new ScaleProcessor();

    /**
     * @return the instance
     */
    public static ScaleBaseProcessor getInstance() {
        return instance;
    }
    
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private final ImageScaleUtil imageScaleUtil = ImageScaleUtil.getInstance();
    
    @Override
    public void update(final Image originalImage, final Image[] originalImageArray, final Image[] ximageToShowArray, final int unused, final float scaleX, final float scaleY, final float maxScaleX, final float maxScaleY) {
        try {

            imageScaleUtil.scale(originalImage, originalImageArray, ximageToShowArray, unused, scaleX, scaleY, maxScaleX, maxScaleY);

        } catch(Exception e) {
            logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.UPDATE);
        }
    }
    
}
