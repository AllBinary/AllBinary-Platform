/*
* AllBinary Open License Version 1
* Copyright (c) 2009 AllBinary
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
package org.allbinary.android.input.motion.api5;

import org.allbinary.android.input.motion.GameInputMotionInfo;

public class GameInputMotionInfoAPI5 extends GameInputMotionInfo
{
    private static final GameInputMotionInfoAPI5 instance = new GameInputMotionInfoAPI5();

    public static GameInputMotionInfoAPI5 getInstance()
    {
        return GameInputMotionInfoAPI5.instance;
    }

    private GameInputMotionInfoAPI5()
    {
        super(Integer.MAX_VALUE, 10);
    }
}
