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

import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

/**
 *
 * @author user
 */
public class ThirdPartyStore {

    private boolean authorized;

    private final String name;

    public ThirdPartyStore(String name)
    {
        this.name = name;
    }

    public void authorize(
        AbeClientInformationInterface clientInformationInterface)
    {
    }

    /**
     * @return the authorized
     */
    public boolean isAuthorized()
    {
        return authorized;
    }

    /**
     * @param authorized the authorized to set
     */
    protected void setAuthorized(boolean authorized)
    {
        this.authorized = authorized;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }


}
