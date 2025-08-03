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
package org.allbinary.game.midlet;

import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.logic.system.security.licensing.LicenseServerInitFileUtil;
import org.allbinary.thread.RunnableInterface;

public class SpecialDemoGameMidlet
extends DemoGameMidlet
{    
    public SpecialDemoGameMidlet(final ClientInformationFactory clientInformationFactory, final LicenseLoadingType licenseLoadingType)
    {
        super(clientInformationFactory);
    }

    public SpecialDemoGameMidlet(final ClientInformationFactory clientInformationFactory, final LicenseLoadingType licenseLoadingType, final DemoSetup demoSetup, final RunnableInterface licenseCheckRunnable)
    {
        super(clientInformationFactory);
    }

    public void initView() {
        
    }
    
    @Override
    public void preInit() {
        new LicenseServerInitFileUtil().init();
    }
}
