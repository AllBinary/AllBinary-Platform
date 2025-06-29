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

import org.allbinary.business.init.LicenseInitInfo;
import org.allbinary.business.init.LicenseInitInfoUtil;
import org.allbinary.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.os.OperatingSystemFactory;

// This is the information sent to the license server
public class AbeClientInformation extends ClientInformation
{
    private final String NONE = "No License Id";
    public AbeClientInformation(final String name, final String version, final String specialName, final String shortName)
    {
        super(name, version, specialName, shortName);
    }

    public void init()
    {
        try
        {
            this.setOperatingSystemInterface(
                OperatingSystemFactory.getInstance().getOperatingSystemInstance());

            LicenseInitInfo licenseInitInfo = 
                LicenseInitInfoUtil.getInstance().read();
            this.setLicenseId(licenseInitInfo.getLicenseId());
            this.setLicenseServers(licenseInitInfo.getServerList());

            final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
            
            if (stringValidationUtil.isEmpty(this.getLicenseId()))
            {
                this.setLicenseId(NONE);
            }

            final CommonStrings commonStrings = CommonStrings.getInstance();
            PreLogUtil.put(new StringMaker().append("Special Name: ").append(this.getSpecialName()).toString(), this, commonStrings.CONSTRUCTOR);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.CONSTRUCTOR, e));
        }
    }
}