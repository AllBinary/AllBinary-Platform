/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *@author Travis Berthelot
 *Date: April 27, 2007, 2:32 AM
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.logic.util.cache;

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
       StringBuffer stringBuffer = new StringBuffer();
       
       stringBuffer.append("CacheType: Vendor: ");
       stringBuffer.append(this.getVendor());
       stringBuffer.append(" Type: ");
       stringBuffer.append(this.getType());
       
       return stringBuffer.toString();
    }
}
