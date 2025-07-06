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

import org.allbinary.input.motion.button.TouchButtonBlankResource;
import org.allbinary.input.motion.button.TouchButtonResource;

/**
 *
 * @author user
 */
public class TouchButtonDowngradeResource extends TouchButtonResource
{
    private static final TouchButtonResource instance = new TouchButtonDowngradeResource();

    /**
     * @return the instance
     */
    public static TouchButtonResource getInstance() {
        return instance;
    }
    
    private TouchButtonDowngradeResource()
    {
        super("/touch_button_downgrade_64_by_64.png", "/touch_button_downgrade_hint_64_by_64.png");
    }    
}
