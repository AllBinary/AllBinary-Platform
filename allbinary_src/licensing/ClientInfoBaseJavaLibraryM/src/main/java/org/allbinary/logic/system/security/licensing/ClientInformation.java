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

import java.util.Hashtable;

import org.allbinary.TsUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.system.hardware.HardwareInterface;
import org.allbinary.logic.system.hardware.SystemHardwareFactory;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.NoOperatingSystem;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import org.allbinary.logic.StdUtil;

// This is the information sent to the license server

@JsType
public class ClientInformation implements AbeClientInformationInterface
{
    private GenericOperatingSystem operatingSystemInterface = NoOperatingSystem.NO_OPERATING_SYSTEM;

    private final TsUtil tsUtil = TsUtil.getInstance();
    
    private final String name;
    private final String shortName;
    private final String version;
    private final String specialName;
    
    private boolean hardSale = true;
    private String licenseId = StringUtil.getInstance().EMPTY_STRING;
    private BasicArrayList licenseServers = BasicArrayListUtil.getInstance().getImmutableInstance();

    @JsConstructor
    public ClientInformation(final String name, final String version, final String specialName, final String shortName)
    {
        this.name = name;
        this.version = version;
        this.specialName = specialName;
        this.shortName = new StringMaker().append(shortName).append(CommonSeps.getInstance().UNDERSCORE).append(version).toString();

        this.init();
    }

    @Override
    @JsMethod
    public void init()
    {
        
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * getOperatingSystemInterface()
     */
    @Override
    @JsMethod
    public GenericOperatingSystem getOperatingSystemInterface()
    {
        return this.operatingSystemInterface;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * abcs.logic.system.security.licensing.AbeClientInformationInterface#getName
     * ()
     */
    @Override
    @JsMethod
    public String getName()
    {
        return this.name;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * getSpecialName()
     */
    @Override
    @JsMethod
    public String getSpecialName()
    {
        return this.specialName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * abcs.logic.system.security.licensing.AbeClientInformationInterface#getVersion
     * ()
     */
    @Override
    @JsMethod
    public String getVersion()
    {
        return this.version;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * getLicenseId()
     */
    @Override
    @JsMethod
    public String getLicenseId()
    {
        return this.licenseId;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * getLicenseServers()
     */
    @Override
    @JsMethod
    public BasicArrayList getLicenseServers()
    {
        return this.licenseServers;
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * getLicenseServer(int)
     */
    @Override
    @JsMethod
    public String getLicenseServer(int index)
    {
        return (String) this.licenseServers.objectArray[index];
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * getNumberOfLicenseServers()
     */
    @Override
    @JsMethod
    public int getNumberOfLicenseServers()
    {
        return this.licenseServers.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * toHashtable()
     */
    @Override
    @JsMethod
    public Hashtable toHashtable()
    {
        final AbeClientInformationData abeClientInformationData = 
            AbeClientInformationData.getInstance();
        
        final GenericOperatingSystem genericOperatingSystem = this.getOperatingSystemInterface();

        final Hashtable clientInfoHashtable = StdUtil.getInstance().createHashtable();
        
        clientInfoHashtable.put(
                abeClientInformationData.NAME, this.name);
        clientInfoHashtable.put(
                abeClientInformationData.VERSION, this.version);
        clientInfoHashtable.put(
                abeClientInformationData.SPECIALNAME, this.specialName);
        clientInfoHashtable.put(
                abeClientInformationData.LICENSEID, this.getLicenseId());
        clientInfoHashtable.put(
                abeClientInformationData.OSNAME, 
                genericOperatingSystem.getName());
        
        clientInfoHashtable.put(abeClientInformationData.OSARCH, 
                genericOperatingSystem.getArch());
        
        clientInfoHashtable.put(abeClientInformationData.OSVERSION, 
                genericOperatingSystem.getVersion());
        
        clientInfoHashtable.put(
                abeClientInformationData.OS, 
                genericOperatingSystem.toString());

        final HardwareInterface hardwareInterface = 
            SystemHardwareFactory.getInstance().getInstance(genericOperatingSystem);
        
        String hardwareString = hardwareInterface.toString();

        clientInfoHashtable.put(
                abeClientInformationData.HARDWARE,
                hardwareString);

        return clientInfoHashtable;
    }

    @Override
    @JsMethod
    public boolean isSameId(String alicenseId)
    {
        if (this.tsUtil.compareTo(this.getLicenseId(), alicenseId) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    @JsMethod
    public boolean isLargerOrDifferentServerList(BasicArrayList list)
    {
        final int size = list.size() - 1;
        final int currentSize = this.licenseServers.size() - 1;

        if (size > currentSize)
        {
            return true;
        }

        for (int index = size; index >= 0; index--)
        {
            final String newServerName = (String) list.objectArray[index];
            for (int index2 = currentSize; index2 >= 0; index2--)
            {
                final String nextOldServerName = (String) 
                    this.licenseServers.objectArray[index];

                if (this.tsUtil.compareTo(newServerName, nextOldServerName) != 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @JsMethod
    protected void setHardSale(boolean hardSale)
    {
        this.hardSale = hardSale;
    }

    @Override
    @JsMethod
    public boolean isHardSale()
    {
        return this.hardSale;
    }

    @JsMethod
    protected void setOperatingSystemInterface(GenericOperatingSystem operatingSystemInterface)
    {
        this.operatingSystemInterface = operatingSystemInterface;
    }

    @JsMethod
    protected void setLicenseId(String licenseId)
    {
        this.licenseId = licenseId;
    }

    @JsMethod
    protected void setLicenseServers(BasicArrayList licenseServers)
    {
        this.licenseServers = licenseServers;
    }

    @Override
    @JsMethod
    public String toShortString() {
        return this.shortName;
    }

}