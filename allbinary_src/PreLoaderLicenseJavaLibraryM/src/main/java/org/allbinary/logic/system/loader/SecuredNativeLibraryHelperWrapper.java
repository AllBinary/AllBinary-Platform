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
package org.allbinary.logic.system.loader;

import org.allbinary.logic.system.security.AbKeys;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.LicensingException;

public class SecuredNativeLibraryHelperWrapper extends NativeLibraryHelperWrapper
{
    private SecuredNativeLibraryInterface securedNativeLibraryInterface;
    
    public SecuredNativeLibraryHelperWrapper(
        final SecuredNativeLibraryInterface securedNativeLibraryInterface)
    {
        super(securedNativeLibraryInterface.getLibraryName());
        this.securedNativeLibraryInterface = securedNativeLibraryInterface;
    }
    
    public boolean unlock(final AbeClientInformationInterface abeClientInformation)
    throws LicensingException
    {
        return securedNativeLibraryInterface.unlock(
            AbKeys.getKey(abeClientInformation, securedNativeLibraryInterface.getName()));
    }
    
}
