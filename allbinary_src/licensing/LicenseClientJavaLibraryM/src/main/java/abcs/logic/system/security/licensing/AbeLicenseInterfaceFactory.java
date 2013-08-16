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
package abcs.logic.system.security.licensing;

import java.io.IOException;
import java.util.Calendar;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.system.security.licensing.client.AbeLicenseClient;

public class AbeLicenseInterfaceFactory
{
    private static final AbeLicenseInterfaceFactory SINGLETON = new AbeLicenseInterfaceFactory();
    
    private long time = 0;
    private boolean check = false; //100000;
    private int checkPeriod = 36000000; //100000;
    private AbeLicenseInterface abeLicenseInterface = null;
    
    private AbeLicenseInterfaceFactory()
    {
    }
    
    public static AbeLicenseInterfaceFactory getInstance()
    {
        return SINGLETON;
    }

    public AbeLicenseInterface getLicenseInstance()
    throws LicensingException
    {
        if(isTimeToGetKey())
        {
            return get();
        }
        else
        {
            return abeLicenseInterface;
        }
    }
    
    private AbeLicenseInterface get()
    throws LicensingException
    {
        try
        {
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
            //{
                LogUtil.put(LogFactory.getInstance("Getting Keys", this, CommonStrings.getInstance().GET));
            //}
            
            abeLicenseInterface = AbeNoLicense.getInstance();
            AbeLicenseClient licenseClient = new AbeLicenseClient();
            abeLicenseInterface = licenseClient.get();

         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
         //{
            if(abeLicenseInterface!=null)
            {
                LogUtil.put(LogFactory.getInstance(
                        "Default Key: " + abeLicenseInterface.getKey(AbeClientInformationData.getInstance().KEY), 
                        this, CommonStrings.getInstance().GET));
            }
         //}
 
            return abeLicenseInterface;
        }
        catch (IOException e)
        {
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
            //{
                LogUtil.put(LogFactory.getInstance("Licensing IO Error", this, CommonStrings.getInstance().GET, e));
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
            //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
            //{
                LogUtil.put(LogFactory.getInstance("Licensing Failure", this, CommonStrings.getInstance().GET, e));
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
