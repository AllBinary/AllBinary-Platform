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

import jsinterop.annotations.JsType;

import org.allbinary.logic.system.security.licensing.ClientInformationFactory;
import org.allbinary.logic.system.security.licensing.LicenseServerInitFileUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class SpecialDemoGameMidlet
extends DemoGameMidlet
{

    @JsConstructor
    public SpecialDemoGameMidlet(final ClientInformationFactory clientInformationFactory, final LicenseLoadingType licenseLoadingType,
            final LicensedDemoSetupFactory demoSetupFactory, final LicenseCheckRunnableFactory licenseCheckRunnableFactory)
    {
        super(clientInformationFactory);
    }

    @JsMethod
    public void initView() {
        
    }
    
    @Override
    @JsMethod
    public void preInit() {
        new LicenseServerInitFileUtil().init();
    }
}
