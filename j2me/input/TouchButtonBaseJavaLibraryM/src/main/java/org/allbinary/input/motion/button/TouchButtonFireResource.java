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
package org.allbinary.input.motion.button;

public class TouchButtonFireResource extends TouchButtonResource
{
    private static final TouchButtonResource instance = new TouchButtonFireResource();

    /**
     * @return the instance
     */
    public static TouchButtonResource getInstance() {
        return instance;
    }
    
    private TouchButtonFireResource()
    {
        super("/touch_button_fire_64_by_64.png", "/touch_button_fire_hint_64_by_64.png");
    }
}
