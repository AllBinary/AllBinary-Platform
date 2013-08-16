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
package views.business.context.modules.storefront.customizer.includes.style.css;




import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

import allbinary.data.tree.dom.DomNodeInterface;

import allbinary.logic.control.validate.ValidationInterface;

import allbinary.logic.visual.transform.info.TransformInfoInterface;



import views.business.context.modules.storefront.HttpStoreComponentView;

public class CssCustomizerView extends HttpStoreComponentView //implements DomNodeInterface
{
   protected ValidationInterface styleValidationInterface;
   
   public CssCustomizerView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public void addDomNodeInterfaces() throws Exception
   {
      this.addDomNodeInterface((DomNodeInterface) this.styleValidationInterface);
      //this.addDomNodeInterface((DomNodeInterface) this);
   }      
   
   /*
   public Node toXmlNode(Document document) throws Exception
   {
         String pageName = TemplateCustomizerUtil.getPageNameHack(
            this.getTransformInfoInterface().getName(),
            this.getWeblisketSession().getStoreName());

         return ModDomHelper.createNameValueNodes(document, PageData.NAME, pageName);
   }
   */
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view Css Retail Template";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
}
