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
package org.allbinary.logic.system.security.licensing.registration;

import java.util.Hashtable;

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterfaceFactory;
import org.allbinary.canvas.SpecialMessageUtil;

public class LicenseRegistrationUtil
{
    private LicenseRegistrationUtil()
    {
    }

    public synchronized static void process(String registrationId)
    {
        try
        {
            PreLogUtil.put(CommonStrings.getInstance().START_LABEL + "License Registration", "LicenseRegistrationUtil", CommonStrings.getInstance().PROCESS);
            
            RegistrationConfiguration.getInstance().setRegistrationCode(registrationId);
            RegistrationConfiguration.getInstance().write();

            // System.out.println(message);
            AbeClientInformationInterface abeClientInformation = 
                AbeClientInformationInterfaceFactory.getInstance();

            Hashtable hashtable = abeClientInformation.toHashtable();

            hashtable.put(RegistrationConfiguration.getInstance().NAME, registrationId);
            hashtable.put("message", SpecialMessageUtil.getInstance().get());
            new XmlRpcRemoteLicenseRegistrationClient(abeClientInformation).get(hashtable);
        }
        catch (Exception e)
        {
            PreLogUtil.put("License Registration Exception", "LicenseRegistrationUtil", "License Registration", e);
        }
    }
}
