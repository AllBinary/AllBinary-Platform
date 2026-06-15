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

package org.allbinary.android.input.motion;

public class GameInputMotionInfo
{
    public final int MAX_API;
    public final int MAX_POINTERS;
    
    protected GameInputMotionInfo(final int maxApi, final int maxPointers)
    {
        this.MAX_API = maxApi;
        this.MAX_POINTERS = maxPointers;
    }
}
