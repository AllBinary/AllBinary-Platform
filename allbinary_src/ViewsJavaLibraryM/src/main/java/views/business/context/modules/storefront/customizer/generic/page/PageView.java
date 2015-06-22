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
package views.business.context.modules.storefront.customizer.generic.page;




import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.communication.log.LogUtil;

import views.business.context.modules.storefront.HttpStoreComponentView;

//implements DomNodeInterface
public class PageView extends HttpStoreComponentView 
{     
   public PageView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "Constructor"));
      }
   }
   
   /*
   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
   }
   */
   
   /*
   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         return this.pageValidation.toXmlNode(document);
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.sqlStrings.COMMAND_FAILED,this,"toXmlNode",e);
         }
         throw e;
      }
   }
   */
   
   public String view() throws Exception
   {
      try
      {
         //this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         String error = "Failed to view TopBar";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
}
