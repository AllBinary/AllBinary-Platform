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
package org.allbinary.logic.system.security;


import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.AbeLicenseInterfaceFactory;
import org.allbinary.logic.system.security.licensing.LicensingException;

public class AbKeys
{
    private static final AbKeys instance = new AbKeys();

    public static AbKeys getInstance() {
        return instance;
    }

   private AbKeys()
   {
   }

   public synchronized String getKey(final AbeClientInformationInterface abeClientInformation, final String keyName) throws LicensingException
   {
      try
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LOADER))
         //{
            LogUtil.put(LogFactory.getInstance("Getting Key: " + keyName, this, "getKey"));
         //}

         //TWB - Replace with key from server
         if(keyName.compareTo("DirectX 8") == 0 ||
             keyName.compareTo("DirectX") == 0 ||
             keyName.compareTo("Low Level") == 0 ||
             keyName.compareTo("System Drivers") == 0)
         {
             return "Temp For Input Library";
         }
         
         return AbeLicenseInterfaceFactory.getInstance().getLicenseInstance(abeClientInformation).getKey(keyName);
      }
      catch (LicensingException e)
      {
         throw e;
      }
      catch (Exception e)
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
         //{
            LogUtil.put(LogFactory.getInstance("Licensing Failure", this, "getKey()", e));
         //}
         throw new LicensingException("Unknown License Failure");
      }
   }
}
