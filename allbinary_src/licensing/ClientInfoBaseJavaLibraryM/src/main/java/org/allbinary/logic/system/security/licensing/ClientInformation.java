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

import java.util.Hashtable;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.system.hardware.HardwareInterface;
import org.allbinary.logic.system.hardware.SystemHardwareFactory;
import org.allbinary.logic.system.os.GenericOperatingSystem;

// This is the information sent to the license server
public class ClientInformation implements AbeClientInformationInterface
{
    private GenericOperatingSystem operatingSystemInterface;
    private final String name;
    private final String shortName;
    private final String version;
    private final String specialName;
    
    private boolean hardSale = true;
    private String licenseId;
    private BasicArrayList licenseServers;

    public ClientInformation(final String name, final String version, final String specialName, final String shortName)
    {
        this.name = name;
        this.version = version;
        this.specialName = specialName;
        this.shortName = new StringMaker().append(shortName).append(CommonSeps.getInstance().UNDERSCORE).append(version).toString();

        this.init();
    }

    public void init()
    {
        
    }

    /*
     * (non-Javadoc)
     * 
     * @seeabcs.logic.system.security.licensing.AbeClientInformationInterface#
     * getOperatingSystemInterface()
     */
    public GenericOperatingSystem getOperatingSystemInterface()
    {
        return operatingSystemInterface;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * abcs.logic.system.security.licensing.AbeClientInformationInterface#getName
     * ()
     */
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
    public Hashtable toHashtable()
    {
        AbeClientInformationData abeClientInformationData = 
            AbeClientInformationData.getInstance();
        
        Hashtable clientInfoHashtable = new Hashtable();
        
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
                this.getOperatingSystemInterface().getName());
        
        clientInfoHashtable.put(abeClientInformationData.OSARCH, 
                this.getOperatingSystemInterface().getArch());
        
        clientInfoHashtable.put(abeClientInformationData.OSVERSION, 
                this.getOperatingSystemInterface().getVersion());
        
        clientInfoHashtable.put(
                abeClientInformationData.OS, 
                this.getOperatingSystemInterface().toString());

        HardwareInterface hardwareInterface = 
            SystemHardwareFactory.getInstance().getInstance(getOperatingSystemInterface());
        
        String hardwareString = hardwareInterface.toString();

        clientInfoHashtable.put(
                abeClientInformationData.HARDWARE,
                hardwareString);

        return clientInfoHashtable;
    }

    public boolean isSameId(String alicenseId)
    {
        if (this.getLicenseId().compareTo(alicenseId) == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isLargerOrDifferentServerList(BasicArrayList list)
    {
        int size = list.size() - 1;
        int currentSize = this.licenseServers.size() - 1;

        if (size > currentSize)
        {
            return true;
        }

        for (int index = size; index >= 0; index--)
        {
            String newServerName = (String) list.objectArray[index];
            for (int index2 = currentSize; index2 >= 0; index2--)
            {
                String nextOldServerName = (String) 
                    this.licenseServers.objectArray[index];

                if (newServerName.compareTo(nextOldServerName) != 0)
                {
                    return true;
                }
            }
        }
        return false;
    }

    protected void setHardSale(boolean hardSale)
    {
        this.hardSale = hardSale;
    }

    public boolean isHardSale()
    {
        return hardSale;
    }

    protected void setOperatingSystemInterface(GenericOperatingSystem operatingSystemInterface)
    {
        this.operatingSystemInterface = operatingSystemInterface;
    }

    protected void setLicenseId(String licenseId)
    {
        this.licenseId = licenseId;
    }

    protected void setLicenseServers(BasicArrayList licenseServers)
    {
        this.licenseServers = licenseServers;
    }

    public String toShortString() {
        return this.shortName;
    }

}