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
package allbinary.logic.communication.smtp.info;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.configuration.ContextConfigurationInterface;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.logic.communication.smtp.configuration.server.EmailServerConfigurationInterface;

public class StoreEmailInfo extends BasicEmailInfo
{
   private StoreFrontInterface storeFrontInterface;

   public StoreEmailInfo(
      StoreFrontInterface storeFrontInterface,
      String subject, String textBody) 
      throws Exception
   {
      super(subject, textBody);
    
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "StoreEmailInfo"));
      }

      this.storeFrontInterface = storeFrontInterface;
      this.init();
   }
   
   protected void init() throws Exception
   {
      ContextConfigurationInterface contextConfigurationInterface = 
         storeFrontInterface.getContextConfigurationInterface();

      EmailServerConfigurationInterface emailServerConfigurationInterface =
         contextConfigurationInterface.getEmailServerConfigurationInterface();

      this.setEmailServerConfigurationInterface(emailServerConfigurationInterface);
   }
}
