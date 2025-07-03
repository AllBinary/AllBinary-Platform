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
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, this.commonStrings.CONSTRUCTOR));
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,"toXmlNode",e);
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"view()",e));
         }
         throw e;
      }
   }
}
