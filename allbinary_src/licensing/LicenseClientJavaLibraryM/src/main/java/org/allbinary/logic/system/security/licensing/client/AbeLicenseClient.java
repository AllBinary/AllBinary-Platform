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
package org.allbinary.logic.system.security.licensing.client;


import org.allbinary.business.init.LicenseInitInfo;
import org.allbinary.business.init.LicenseInitInfoUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.xmlrpc.XmlRpcAbeClient;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.AbeLicenseInterface;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;

public class AbeLicenseClient
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
   private final int MINSERVERS = 3;
   
   public AbeLicenseClient()
   {
      
   }
    
   public synchronized AbeLicenseInterface get(final AbeClientInformationInterface abeClientInformation) throws Exception
   {
      try
      {
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
         //{
            logUtil.put(commonStrings.START, this,commonStrings.GET);
         //}

         final XmlRpcAbeClient xmlRpcAbeLicenseClient =
             new XmlRpcAbeLicenseRetrievalClient(abeClientInformation);
         
         final AbeLicenseInterface abeLicenseInterface = (AbeLicenseInterface) xmlRpcAbeLicenseClient.get(null);
         
         //Save license id and server list to file
         //
         
         final String licenseId = abeLicenseInterface.getLicenseId();
         final BasicArrayList servers = abeLicenseInterface.getServers();
         
         boolean isNewLicenseId = false;
         boolean isBetterServerList = false;
         
         final StringValidationUtil stringValidationUtil = StringValidationUtil.getInstance();
         
         if(stringValidationUtil.isEmpty(abeClientInformation.getLicenseId()) &&
            !abeClientInformation.isSameId(licenseId))
         {
             isNewLicenseId = true;
         }
         
         if(servers.size() >= MINSERVERS && abeClientInformation.isLargerOrDifferentServerList(servers))
         {
             isBetterServerList = true;
         }
         
         if(isBetterServerList || isNewLicenseId)
         {
            final LicenseInitInfo initInfo = 
                LicenseInitInfoUtil.getInstance().read();
            initInfo.setLicenseId(licenseId);
            initInfo.setServerList(servers);
            LicenseInitInfoUtil.getInstance().write(initInfo);
            abeClientInformation.init();
         }                  

         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSING))
         //{
            logUtil.put(commonStrings.END, this, commonStrings.GET);
         //}
         
         return abeLicenseInterface;
      }         
      catch(Exception e)
      {     
         //if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().LICENSINGERROR))
         //{
            //logUtil.put("Unknown License Retrieval Failure", this, commonStrings.GET, e);
         //}
         throw e;
      }
   }   
}
