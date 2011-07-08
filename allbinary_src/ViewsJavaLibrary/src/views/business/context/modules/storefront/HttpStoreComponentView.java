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
package views.business.context.modules.storefront;

import views.HttpComponentView;
import abcs.data.tree.dom.document.DomDocumentHelper;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.logic.visual.transform.StoreTransformer;
import allbinary.logic.visual.transform.data.TransformStoreDocumentFactory;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

public class HttpStoreComponentView extends HttpComponentView
{
   public HttpStoreComponentView(TransformInfoInterface transformInfoInterface)
       throws Exception
   {
      super(transformInfoInterface);
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "Constructor"));
      }
      
      this.setTransformDocumentInterface(
         TransformStoreDocumentFactory.getInstance(
         this.getPageContext(), this.getWeblisketSession()));
   }

   public String view() throws Exception
   {
      try
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Start", this, "view"));
         }

         this.toXmlDoc();
         String success = DomDocumentHelper.toString(this.getDoc());
         
         String result = new StoreTransformer(
            this.getTransformInfoInterface()).translate(success);
         
         return result;
      }
      catch(Exception e)
      {
          if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
          {
              String error = "Failed to view";
              LogUtil.put(LogFactory.getInstance(error, this, "view", e));
          }
         throw e;
      }
   }
}
