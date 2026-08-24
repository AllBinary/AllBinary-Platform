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
package org.allbinary.thirdparty.store;

import jsinterop.annotations.JsType;

import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;

/**
 *
 * @author user
 */

@JsType
public class ThirdPartyStore {

    private boolean authorized;

    private final String name;

    @JsConstructor
    public ThirdPartyStore(String name)
    {
        this.name = name;
    }

    @JsMethod
    public void authorize(
        AbeClientInformationInterface clientInformationInterface)
    {
    }

    /**
     * @return the authorized
     */
    @JsMethod
    public boolean isAuthorized()
    {
        return this.authorized;
    }

    /**
     * @param authorized the authorized to set
     */
    @JsMethod
    protected void setAuthorized(boolean authorized)
    {
        this.authorized = authorized;
    }

    /**
     * @return the name
     */
    @JsMethod
    public String getName()
    {
        return this.name;
    }


}
