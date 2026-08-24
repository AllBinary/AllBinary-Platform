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
package org.allbinary.logic.system.security.licensing;

import jsinterop.annotations.JsType;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class AbeClientInformationData
{

    private static final AbeClientInformationData instance
            = new AbeClientInformationData();

    @JsMethod
    public static AbeClientInformationData getInstance()
    {
        return AbeClientInformationData.instance;
    }

    @JsConstructor
    private AbeClientInformationData()
    {
    }

    @JsProperty
    public final String KEY = "KEY";

    @JsProperty
    public final String NAME = "NAME";
    @JsProperty
    public final String VERSION = "VERSION";
    @JsProperty
    public final String SPECIALNAME = "SPECIALNAME";

    @JsProperty
    public final String LICENSEID = "LICENSEID";
    @JsProperty
    public final String LICENSE_TYPE = "LICENSETYPE";
    @JsProperty
    public final String PREVIOUSLICENSEID = "PREVIOUSLICENSEID";

    @JsProperty
    public final String OSNAME = "OSNAME";
    @JsProperty
    public final String OSARCH = "OSARCH";
    @JsProperty
    public final String OSVERSION = "OSVERSION";
    @JsProperty
    public final String OS = "OS";
    @JsProperty
    public final String HARDWARE = "HARDWARE";

    @JsProperty
    public final String LICENSESERVERS = "LICENSESERVERS";
    @JsProperty
    public final String NEWLICENSE = "NEWLICENSE";
    @JsProperty
    public final String ISNEW = "ISNEW";

    @JsProperty
    public final String SPECIAL = "SPECIAL";
}
