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

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

/**
 *
 * @author user
 */
public class NoThirdPartyStore extends ThirdPartyStore
{
    NoThirdPartyStore()
    {
        super(StringUtil.getInstance().EMPTY_STRING);
    }

    @Override
    public void authorize(
        AbeClientInformationInterface clientInformationInterface)
    {
        this.setAuthorized(true);
    }
}
