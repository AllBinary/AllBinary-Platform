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
package allbinary.game.input;

//import org.allbinary.input.gyro.OrientationMotionGestureFactory;

import allbinary.game.input.mapping.InputToGameKeyMapping;

public class DesktopGameInputMapping  extends PCGameInputMapping
{
    protected InputToGameKeyMapping getDefault()
    {
        //LogUtil.put(LogFactory.getInstance("Use Default GameKey Mappings", this, "addDefault"));

        return new DesktopDefaultGameInputMapping().getInputMapping();
    }
}
