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
package org.allbinary.game.testgamedemo.canvas;

import abcs.logic.system.SoftwareInformation;

public class TestGameDemoSoftwareInfo
{
    //This should change in next Android Release by moving AndroidMobile info only the client info
    private static final SoftwareInformation SINGLETON = 
        new SoftwareInformation("TestDemoGame", "1.2.0");
    
    public static SoftwareInformation getInstance()
    {
        return SINGLETON;
    }
}
