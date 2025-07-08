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




import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.control.validate.ValidationInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;



import views.business.context.modules.storefront.HttpStoreComponentView;

public class CssCustomizerView extends HttpStoreComponentView //implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

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
         String pageName = TemplateCustomizerUtil.getInstance().getPageNameHack(
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
         //String error = "Failed to view Css Retail Template";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         throw e;
      }
   }
}
