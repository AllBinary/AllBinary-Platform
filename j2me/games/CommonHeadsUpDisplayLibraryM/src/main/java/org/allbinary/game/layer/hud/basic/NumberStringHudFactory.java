/*
 * AllBinary Open License Version 1
 * Copyright (c) 2025 AllBinary
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
package org.allbinary.game.layer.hud.basic;

import org.allbinary.game.graphics.hud.BasicHudFactory;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class NumberStringHudFactory {
    
    private static Object instance = NullUtil.getInstance();
    
    public static NumberStringHudFactory getInstance() {
        
        if(NumberStringHudFactory.instance == NullUtil.getInstance()) {
            NumberStringHudFactory.instance = new NumberStringHudFactory();
        }

        return (NumberStringHudFactory) NumberStringHudFactory.instance;
    }
    
    public NumberStringHud createHud() {

        try {
            return new NumberStringHud(
                StringUtil.getInstance().EMPTY_STRING, 9,
                BasicHudFactory.getInstance().ABSOLUTE, 1,
                0, 0, 0, BasicColorFactory.getInstance().NULL_COLOR);
        } catch (Exception e) {
            final LogUtil logUtil = LogUtil.getInstance();
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, "NumberStringHud", commonStrings.CONSTRUCTOR, e);
            throw new RuntimeException();
        }
    }

    public final NumberStringHud NULL_NUMBER_STRING_HUD = this.createHud();
    
}
