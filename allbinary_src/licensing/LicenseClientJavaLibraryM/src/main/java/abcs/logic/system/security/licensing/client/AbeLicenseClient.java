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
package abcs.logic.system.security.licensing.client;


import org.allbinary.util.BasicArrayList;

import abcs.business.init.LicenseInitInfo;
import abcs.business.init.LicenseInitInfoUtil;
import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.xmlrpc.XmlRpcAbeClient;
import abcs.logic.system.security.licensing.AbeClientInformationInterface;
import abcs.logic.system.security.licensing.AbeClientInformationInterfaceFactory;
import abcs.logic.system.security.licensing.AbeLicenseInterface;

public class AbeLicenseClient
{
   private final int MINSERVERS = 3;
   
   public AbeLicenseClient()
   {
      
   }
    
   public synchronized AbeLicenseInterface get() throws Exception
   {
      try
      {
         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
         //{
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this,CommonStrings.getInstance().GET));
         //}

         AbeClientInformationInterface abeClientInformation = 
        	 AbeClientInformationInterfaceFactory.getInstance();
         XmlRpcAbeClient xmlRpcAbeLicenseClient =
             new XmlRpcAbeLicenseRetrievalClient(abeClientInformation);
         
         AbeLicenseInterface abeLicenseInterface = (AbeLicenseInterface) xmlRpcAbeLicenseClient.get(null);
         
         //Save license id and server list to file
         //
         
         String licenseId = abeLicenseInterface.getLicenseId();
         BasicArrayList servers = abeLicenseInterface.getServers();
         
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
            LicenseInitInfo initInfo = 
                LicenseInitInfoUtil.getInstance().read();
            initInfo.setLicenseId(licenseId);
            initInfo.setServerList(servers);
            LicenseInitInfoUtil.getInstance().write(initInfo);
            abeClientInformation.init();
         }                  

         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSING))
         //{
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().END, this, CommonStrings.getInstance().GET));
         //}
         
         return abeLicenseInterface;
      }         
      catch(Exception e)
      {     
         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.LICENSINGERROR))
         //{
            //LogUtil.put(LogFactory.getInstance("Unknown License Retrieval Failure", this, CommonStrings.getInstance().GET, e));
         //}
         throw e;
      }
   }   
}
