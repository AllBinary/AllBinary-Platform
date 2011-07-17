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
package abcs.logic.system.security.licensing;

import org.allbinary.game.testgamedemo.canvas.TestGameDemoSoftwareInfo;

import abcs.logic.basic.string.CommonStrings;

public class TestDemoGamePCClientInformation
extends AbeClientInformation
{
    private final static String PC_DESC = "PC";
    public TestDemoGamePCClientInformation()
    {
        super(
                TestGameDemoSoftwareInfo.getInstance().getName() + PC_DESC,
                TestGameDemoSoftwareInfo.getInstance().getVersion(),
                TestGameDemoSoftwareInfo.getInstance().getName() + PC_DESC +
                CommonStrings.getInstance().SPACE + 
                TestGameDemoSoftwareInfo.getInstance().getVersion()
                );
    }
}
