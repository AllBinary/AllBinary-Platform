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

import jsinterop.annotations.JsType;

import org.allbinary.debug.DebugFactory;
import org.allbinary.debug.NoDebug;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class LicenseInitInfo
{
    private String id = StringUtil.getInstance().EMPTY_STRING;
    private BasicArrayList serverList = new BasicArrayListD();

    @JsConstructor
    LicenseInitInfo()
    {
    }

    @JsMethod
    public String getLicenseId()
    {
        return this.id;
    }

    @JsMethod
    public String getServer(int index)
    {
        return (String) this.serverList.get(index);
    }

    @JsMethod
    public int getNumberOfServers()
    {
        return this.serverList.size();
    }

    @JsMethod
    public void setLicenseId(String value)
    {
        this.id = value;
    }

    @JsMethod
    public void clearServers()
    {
        this.serverList.clear();
    }

    @JsMethod
    public void setServer(String value, int index)
    {
        this.serverList.addAt(index, value);
    }

    @JsMethod
    public void addServer(String value)
    {
        this.serverList.add(value);
    }

    @JsMethod
    public BasicArrayList getServerList()
    {
        return this.serverList;
    }

    @JsMethod
    public void setServerList(BasicArrayList servers)
    {
        if(DebugFactory.getInstance() == NoDebug.getInstance())
        {
            this.serverList = servers;
        }
    }
}