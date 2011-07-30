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
package allbinary.game.midlet;

import abcs.logic.system.security.licensing.LicenseServerInitFileUtil;
import allbinary.thread.RunnableInterface;

public class SpecialDemoGameMidlet
extends DemoGameMidlet
{    
    public SpecialDemoGameMidlet(LicenseLoadingType licenseLoadingType)
    {
        new LicenseServerInitFileUtil().init();
    }

    public SpecialDemoGameMidlet(LicenseLoadingType licenseLoadingType, DemoSetup demoSetup, RunnableInterface licenseCheckRunnable)
    {
        new LicenseServerInitFileUtil().init();
    }
}
