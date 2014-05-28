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

public class TouchButtonTurnRightResource extends TouchButtonResource
{
    private static final TouchButtonResource instance = new TouchButtonTurnRightResource();

    /**
     * @return the instance
     */
    public static TouchButtonResource getInstance() {
        return instance;
    }
    
    private TouchButtonTurnRightResource()
    {
        super("/touch_button_turn_right_arrow_64_by_64.png", "/touch_button_turn_right_arrow_hint_64_by_64.png");
    }      
}
