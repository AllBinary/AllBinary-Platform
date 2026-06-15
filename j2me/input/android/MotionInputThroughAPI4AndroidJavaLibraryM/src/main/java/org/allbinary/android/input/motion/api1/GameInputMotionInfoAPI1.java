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
package org.allbinary.android.input.motion.api1;

import org.allbinary.android.input.motion.GameInputMotionInfo;

public class GameInputMotionInfoAPI1 extends GameInputMotionInfo
{
    private static final GameInputMotionInfoAPI1 instance = new GameInputMotionInfoAPI1();

    public static GameInputMotionInfoAPI1 getInstance()
    {
        return GameInputMotionInfoAPI1.instance;
    }

    private GameInputMotionInfoAPI1()
    {
        super(4, 1);
    }
}
