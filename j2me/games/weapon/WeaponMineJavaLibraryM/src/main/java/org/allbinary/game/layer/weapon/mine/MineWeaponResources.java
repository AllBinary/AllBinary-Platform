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
package org.allbinary.game.layer.weapon.mine;

import org.allbinary.game.layer.resources.BasicGameResources;
import org.allbinary.logic.NullUtil;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class MineWeaponResources
extends BasicGameResources
{
    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    public static MineWeaponResources getInstance()
    {
        if(MineWeaponResources.instance == NullUtil.getInstance().NULL_OBJECT) {
            MineWeaponResources.instance = new MineWeaponResources();
        }
        
    	return (MineWeaponResources) MineWeaponResources.instance;
    }

    public final String DROP_TEXT_RESOURCE = "/mine_drop_text_20_by_20.png";
    public final String DROP_RESOURCE = "/mine_drop_20_by_20.png";
    public final String DROP_ICON_RESOURCE = "/mine_drop_icon_20_by_20.png";
    
    protected MineWeaponResources()
    {
        final String ROOT = "/mine";
        final String SMALL = "_20_by_20.png";
        final String MEDIUM = SMALL;
        final String SIZE_FOUR = SMALL;
        final String SIZE_FIVE = SMALL;
        final String SIZE_SIX = SMALL;
        
        final String[] SIZE = { SMALL, MEDIUM, SIZE_FOUR, SIZE_FIVE, SIZE_SIX };
        
        super.init(ROOT, SIZE);
    }
}
