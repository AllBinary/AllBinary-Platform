/*
 * AllBinary Open License Version 1
 * Copyright (c) 2007 AllBinary
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

package org.allbinary.game.input.resource;

import org.allbinary.input.motion.button.TouchButtonResource;

/**
 *
 * @author user
 */
public class TouchButtonUpgradeResource extends TouchButtonResource
{
    private static final TouchButtonResource instance = new TouchButtonUpgradeResource();

    /**
     * @return the instance
     */
    public static TouchButtonResource getInstance() {
        return instance;
    }
    
    private TouchButtonUpgradeResource()
    {
        super("/touch_button_upgrade_64_by_64.png", "/touch_button_upgrade_hint_64_by_64.png");
    }       
}
