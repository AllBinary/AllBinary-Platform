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
package org.allbinary.business.init;

import org.allbinary.debug.DebugFactory;
import org.allbinary.debug.NoDebug;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;

public class LicenseInitInfo
{
    private String id = StringUtil.getInstance().EMPTY_STRING;
    private BasicArrayList serverList = new BasicArrayList();

    LicenseInitInfo()
    {
    }

    public String getLicenseId()
    {
        return this.id;
    }

    public String getServer(int index)
    {
        return (String) this.serverList.get(index);
    }

    public int getNumberOfServers()
    {
        return this.serverList.size();
    }

    public void setLicenseId(String value)
    {
        this.id = value;
    }

    public void clearServers()
    {
        this.serverList.clear();
    }

    public void setServer(String value, int index)
    {
        this.serverList.add(index, value);
    }

    public void addServer(String value)
    {
        this.serverList.add(value);
    }

    public BasicArrayList getServerList()
    {
        return this.serverList;
    }

    public void setServerList(BasicArrayList servers)
    {
        if(DebugFactory.getInstance() == NoDebug.getInstance())
        {
            this.serverList = servers;
        }
    }
}