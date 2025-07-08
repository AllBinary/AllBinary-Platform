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
package views.business.context.modules.storefront.customizer.includes.globals;

import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.visual.transform.template.customizer.includes.globals.GlobalsValidation;

import org.allbinary.logic.communication.log.LogUtil;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class GlobalsView extends HttpStoreComponentView implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected GlobalsValidation globalsValidation;
   
   public GlobalsView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      this.globalsValidation = 
         new GlobalsValidation(this.getWeblisketSession().getStoreName());
   }
   
   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this.globalsValidation);
   }
   
   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         return this.globalsValidation.toXmlNode(document);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            logUtil.put(this.commonStrings.FAILURE,this,"toXmlNode",e);
         }
         throw e;
      }
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         throw e;
      }
   }
}
