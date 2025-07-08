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

import org.allbinary.logic.string.regex.replace.Replace;

import org.allbinary.business.context.modules.storefront.StoreFrontData;


import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoData;

import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfig;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigData;

import org.allbinary.logic.visual.transform.template.util.TransformTemplateCustomizerUtil;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogFactory;

public class NoTemplateTransformInfoObjectConfig extends TransformInfoObjectConfig
{
    protected final LogUtil logUtil = LogUtil.getInstance();


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

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("Initial ObjectConfig: " + docString, this, "generate()");
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

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("Final ObjectConfig: " +
            DomDocumentHelper.toString(newObjectConfigDocument), this, "generate()");
      }

      return newObjectConfigDocument;
   }
}
