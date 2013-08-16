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
package views.business.context.modules.storefront.customizer.template.objectConfig;

import java.util.HashMap;

import org.w3c.dom.Document;

import abcs.logic.basic.string.regex.replace.Replace;

import allbinary.business.context.modules.storefront.StoreFrontData;


import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.logic.visual.transform.info.TransformInfoData;

import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfig;
import allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;

import allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;

import abcs.logic.communication.log.LogUtil;
import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;

public class NoTemplateTransformInfoObjectConfig extends TransformInfoObjectConfig
{

   public NoTemplateTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      //this.setDocument(this.generate(this.getDocument()));
   }

   public NoTemplateTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface, Document document) throws Exception
   {
      super(transformInfoInterface, document);
      this.setDocument(this.generate(this.toXmlDoc()));
   }

   public NoTemplateTransformInfoObjectConfig(TransformInfoInterface transformInfoInterface, String name, String type) throws Exception
   {
      super(transformInfoInterface, name, type);
      this.setDocument(this.generate(this.toXmlDoc()));
   }

   protected Document generate(Document objectConfigDocument) throws Exception
   {
       String docString = DomDocumentHelper.toString(objectConfigDocument);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Initial ObjectConfig: " + docString, this, "generate()"));
      }

      String storeName = this.getTransformInfoInterface().getStoreName();
      String viewName = this.getTransformInfoInterface().getName();

      HashMap hashMap = new HashMap();
      
      final String VARKEY = TransformInfoObjectConfigData.getInstance().VARKEY;
      
      hashMap.put(VARKEY + StoreFrontData.getInstance().NAME, storeName);
      hashMap.put(VARKEY + TransformInfoData.getInstance().OWNER, viewName);

      //for customizer form object config - major hack should be in seperate class 
      //created by objectconfigfactory or better yet use multiple objectconfig files

      String pageName = 
         TransformTemplateCustomizerUtil.getInstance().getPageNameHack(
            this.getTransformInfoInterface().getName(),
            this.getTransformInfoInterface().getStoreName());

      hashMap.put(VARKEY + TransformInfoData.getInstance().PARTIAL, pageName);

      Replace replace = new Replace(hashMap);

      Document newObjectConfigDocument = 
         DomDocumentHelper.create(
            replace.all(docString ));

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Final ObjectConfig: " +
            DomDocumentHelper.toString(newObjectConfigDocument), this, "generate()"));
      }

      return newObjectConfigDocument;
   }
}
