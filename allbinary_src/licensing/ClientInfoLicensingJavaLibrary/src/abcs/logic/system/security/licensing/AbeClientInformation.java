/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/19/02
 *
 *
 *Modified By         When       ?
 *
 */
package abcs.logic.system.security.licensing;

import abcs.business.init.LicenseInitInfo;
import abcs.business.init.LicenseInitInfoUtil;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import abcs.logic.system.os.OperatingSystemFactory;

// This is the information sent to the license server
public class AbeClientInformation extends ClientInformation
{
    private final String NONE = "No License Id";
    public AbeClientInformation(String name, String version, String specialName)
    {
        super(name, version, specialName);
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

            PreLogUtil.put("Special Name: " + this.getSpecialName(), this, CommonStrings.getInstance().CONSTRUCTOR);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
        }
    }
}