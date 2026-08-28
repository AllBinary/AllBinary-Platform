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

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType
public class LicenseLoadingTypeFactory
{
    private static final LicenseLoadingTypeFactory instance = new LicenseLoadingTypeFactory();
    
    @JsMethod
    public static LicenseLoadingTypeFactory getIntance()
    {
        return LicenseLoadingTypeFactory.instance;
    }
    
    @JsProperty
    public final LicenseLoadingType INITIAL_LOADING = new LicenseLoadingType("Initial Loading");
    @JsProperty
    public final LicenseLoadingType GAME_START = new LicenseLoadingType("Game Start");
    @JsProperty
    public final LicenseLoadingType LOGIN = new LicenseLoadingType("Login");
    @JsProperty
    public final LicenseLoadingType OTHER = new LicenseLoadingType("Other/Usually The First Non Demo Level");
}
