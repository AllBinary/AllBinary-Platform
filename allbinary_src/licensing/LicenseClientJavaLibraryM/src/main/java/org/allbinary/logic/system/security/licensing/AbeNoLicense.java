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


import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class AbeNoLicense
    implements AbeLicenseInterface
{
    private static AbeNoLicense abeNoLicense = new AbeNoLicense();
    
    public BasicArrayList serverVector = new BasicArrayList();

    private AbeNoLicense()
    {
    }
    
    public static AbeNoLicense getInstance()
    {
        return abeNoLicense;
    }
    
    public boolean hasKey()
    {
        return false;
    }

    public String getKey(String keyName)
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
    
    public String getLicenseId()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }

    public String getSpecial()
    {
        return StringUtil.getInstance().EMPTY_STRING;
    }
    
    public BasicArrayList getServers()
    {
        return serverVector;
    }
    
    public boolean isValid()
    {
        return false;
    }
    
    public LicenseType getLicenseType()
    {
        return LicenseTypeFactory.getInstance().UNKNOWN;
    }
}
