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
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.StoreTransformer;
import org.allbinary.logic.visual.transform.data.TransformStoreDocumentFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class HttpStoreComponentView extends HttpComponentView
{
    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public HttpStoreComponentView(TransformInfoInterface transformInfoInterface)
       throws Exception
   {
      super(transformInfoInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Start", this, "view"));
         }

         this.toXmlDoc();
         String success = DomDocumentHelper.toString(this.getDoc());
         
         String result = new StoreTransformer(
            this.abeClientInformation, this.getTransformInfoInterface()).translate(success);
         
         return result;
      }
      catch(Exception e)
      {
          if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
          {
              String error = "Failed to view";
              LogUtil.put(LogFactory.getInstance(error, this, "view", e));
          }
         throw e;
      }
   }
}
