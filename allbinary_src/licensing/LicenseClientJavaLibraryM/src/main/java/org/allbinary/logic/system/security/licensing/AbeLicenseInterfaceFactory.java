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

import java.io.IOException;
import java.util.Calendar;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.client.AbeLicenseClient;
import org.allbinary.string.CommonStrings;

public class AbeLicenseInterfaceFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final AbeLicenseInterfaceFactory SINGLETON = new AbeLicenseInterfaceFactory();
    
    public static AbeLicenseInterfaceFactory getInstance()
    {
        return SINGLETON;
    }

    private final CommonStrings commonStrings = CommonStrings.getInstance();

    private long time = 0;
    private boolean check = false; //100000;
    private int checkPeriod = 36000000; //100000;
    private AbeLicenseInterface abeLicenseInterface = null;
    
    private AbeLicenseInterfaceFactory()
    {
    }

    public AbeLicenseInterface getLicenseInstance(final AbeClientInformationInterface abeClientInformation)
    throws LicensingException
    {
        if(isTimeToGetKey())
        {
            return get(abeClientInformation);
        }
        else
        {
            return abeLicenseInterface;
        }
    }
    
    private AbeLicenseInterface get(final AbeClientInformationInterface abeClientInformation)
    throws LicensingException
    {
        try
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
            //{
                logUtil.put("Getting Keys", this, commonStrings.GET);
            //}
            
            abeLicenseInterface = AbeNoLicense.getInstance();
            AbeLicenseClient licenseClient = new AbeLicenseClient();
            abeLicenseInterface = licenseClient.get(abeClientInformation);

         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
         //{
            if(abeLicenseInterface!=null)
            {
                logUtil.put("Default Key: " + abeLicenseInterface.getKey(AbeClientInformationData.getInstance().KEY), this, commonStrings.GET);
            }
         //}
 
            return abeLicenseInterface;
        }
        catch (IOException e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            //{
                logUtil.put("Licensing IO Error", this, commonStrings.GET, e);
            //}
            throw new LicensingException("License Server Connection Error");
        }
      /*
      catch (LicensingException e)
      {
         throw e;
      }
       */
        catch (Exception e)
        {
            //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
            //{
                logUtil.put("Licensing Failure", this, commonStrings.GET, e);
            //}
                throw new LicensingException("Unknown License Failure: " + this.getClass().getName());
        }
    }
    
    private boolean isTimeToGetKey()
    {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();
        if(abeLicenseInterface == null || 
           abeLicenseInterface == AbeNoLicense.getInstance() || 
           !abeLicenseInterface.hasKey() || 
           isCheck() ||
           currentTime - checkPeriod > time)
        {
            abeLicenseInterface = null;
            time = currentTime;
            return true;
        }
        else return false;
    }

    public void setCheck(boolean check)
    {
        this.check = check;
    }

    private boolean isCheck()
    {
        return check;
    }
    
}
