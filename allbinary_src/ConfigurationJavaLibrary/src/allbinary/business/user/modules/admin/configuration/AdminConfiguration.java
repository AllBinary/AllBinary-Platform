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
package allbinary.business.user.modules.admin.configuration;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.configuration.ContextConfiguration;
import allbinary.business.context.configuration.ContextConfigurationData;
import allbinary.business.context.configuration.ContextConfigurationDomDocumentMapping;
import allbinary.business.context.configuration.ContextConfigurationInterface;
import allbinary.business.context.configuration.ContextConfigurationInterfaceFactory;
import allbinary.business.context.configuration.ContextConfigurationPathUtil;
import allbinary.logic.communication.http.request.RequestParams;
import allbinary.logic.control.crypt.file.CryptFileWriter;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class AdminConfiguration implements AdminConfigurationInterface
{
   private ContextConfigurationInterface contextConfigurationInterface;

   public AdminConfiguration() throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "AdminConfiguration"));
      }

      this.contextConfigurationInterface = 
         ContextConfigurationInterfaceFactory.getInstance(AdminConfigurationData.CONTEXTNAME);
   }

   public AdminConfiguration(HttpServletRequest request) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "AdminConfiguration"));
      }

      this.getFormData(new RequestParams(request).toHashMap());
   }
   
   public AdminConfiguration(HashMap storeHashMap) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "AdminConfiguration"));
      }

      this.getFormData(storeHashMap);
   }

   private void getFormData(HashMap storeHashMap) throws Exception
   {
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGING))
      {
         LogUtil.put(LogFactory.getInstance("Constructing", this, "getFormData"));
      }

      this.setContextConfigurationInterface(
         (ContextConfigurationInterface) 
            new ContextConfiguration(storeHashMap));
   }

   public synchronized void write() throws Exception
   {
      ContextConfigurationDomDocumentMapping
         contextConfigurationDomDocumentMapping = 
            new ContextConfigurationDomDocumentMapping(
               this.getContextConfigurationInterface());

      CryptFileWriter cryptFileWriter = new CryptFileWriter(
         ContextConfigurationData.getInstance().UNCRYPTED_EXTENSION,
         ContextConfigurationData.getInstance().ENCRYPTED_EXTENSION);

      cryptFileWriter.write(
         ContextConfigurationPathUtil.getAbPath(
         AdminConfigurationData.CONTEXTNAME),
         contextConfigurationDomDocumentMapping.toXmlDoc());
   }

   public ContextConfigurationInterface getContextConfigurationInterface()
   {
      return contextConfigurationInterface;
   }

   public void setContextConfigurationInterface(ContextConfigurationInterface contextConfigurationInterface)
   {
      this.contextConfigurationInterface = contextConfigurationInterface;
   }
}
