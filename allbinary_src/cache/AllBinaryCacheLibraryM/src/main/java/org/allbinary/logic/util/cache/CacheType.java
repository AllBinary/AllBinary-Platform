/*
 * AllBinary Open License Version 1
 * Copyright (c) 2007 AllBinary
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

package org.allbinary.logic.util.cache;

import org.allbinary.logic.string.StringMaker;

public class CacheType
{
    private String vendor;
    private String type;
    
    public CacheType(String vendor, String type)
    {
        this.setVendor(vendor);
        this.setType(type);
    }

    public String getVendor()
    {
        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
   @Override
    public String toString()
    {
       StringMaker stringBuffer = new StringMaker();
       
       stringBuffer.append("CacheType: Vendor: ");
       stringBuffer.append(this.getVendor());
       stringBuffer.append(" Type: ");
       stringBuffer.append(this.getType());
       
       return stringBuffer.toString();
    }
}
