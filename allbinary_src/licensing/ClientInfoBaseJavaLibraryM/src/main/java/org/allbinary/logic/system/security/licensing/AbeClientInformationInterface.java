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

import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.util.ABHashtable;
import org.allbinary.util.BasicArrayList;

@JsType
public interface AbeClientInformationInterface
{
    @JsMethod
    void init();

    @JsMethod
    GenericOperatingSystem getOperatingSystemInterface();

    @JsMethod
    String getName();

    @JsMethod
    String getSpecialName();

    @JsMethod
    String getVersion();

    @JsMethod
    String getLicenseId();

    @JsMethod
    BasicArrayList getLicenseServers();

    @JsMethod
    String getLicenseServer(int index);

    @JsMethod
    int getNumberOfLicenseServers();

    @JsMethod
    ABHashtable toHashtable();

    @JsMethod
    boolean isSameId(String alicenseId);

    @JsMethod
    boolean isLargerOrDifferentServerList(BasicArrayList vector);

    @JsMethod
    boolean isHardSale();
    
    @JsMethod
    String toShortString();
}