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
package org.allbinary.media.audio;

import org.allbinary.logic.string.StringUtil;

/**
 *
 * @author User
 */
public class NoSound extends Sound {

    /**
     * @return the instance
     */
    public static NoSound getInstance() {
        return instance;
    }
 
    private static final NoSound instance = new NoSound();
    
    private NoSound() {
        super(StringUtil.getInstance().EMPTY_STRING);
        
        this.setPlayerP(NoPlayer.NO_PLAYER);
    }
}
