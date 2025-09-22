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
package org.allbinary.game.layer;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

import org.allbinary.logic.communication.log.LogUtil;

/**
 *
 * @author User
 */
public class SpriteFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    private static final SpriteFactory instance = new SpriteFactory();
    

    /**
     * @return the instance
     */
    public static SpriteFactory getInstance() {
        return instance;
    }

    //private final CommonStrings commonStrings = CommonStrings.getInstance();
    //private final CommonSeps commonSeps = CommonSeps.getInstance();
    
    public Sprite create(final Image image, final int frameWidth, final int frameHeight) {
        
        //logUtil.put(new StringMaker().append(image.getWidth()).append(commonSeps.COLON).append(image.getHeight()).append(commonSeps.FORWARD_SLASH).append(frameWidth).append(frameHeight).toString(), this, commonStrings.PROCESS);
        return new Sprite(image, frameWidth, frameHeight);
    } 

}
