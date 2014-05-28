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
package allbinary.input.motion.button;

public class TouchButtonGenericActionResource extends TouchButtonResource
{
    private static final TouchButtonResource instance = new TouchButtonGenericActionResource();

    /**
     * @return the instance
     */
    public static TouchButtonResource getInstance() {
        return instance;
    }
    
    private TouchButtonGenericActionResource()
    {
        super("/touch_button_generic_action_64_by_64.png", "/touch_button_generic_action_hint_64_by_64.png");
    }
}
