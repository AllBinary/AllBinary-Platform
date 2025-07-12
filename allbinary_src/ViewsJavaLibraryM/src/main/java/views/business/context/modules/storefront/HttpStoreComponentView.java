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

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.StoreTransformer;
import org.allbinary.logic.visual.transform.data.TransformStoreDocumentFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.string.CommonStrings;
import views.HttpComponentView;

public class HttpStoreComponentView extends HttpComponentView
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
   public HttpStoreComponentView(TransformInfoInterface transformInfoInterface)
       throws Exception
   {
      super(transformInfoInterface);
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("View Name: " + transformInfoInterface.getName(), this, this.commonStrings.CONSTRUCTOR);
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
            logUtil.put(this.commonStrings.START, this, "view");
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
              logUtil.put(commonStrings.EXCEPTION, this, "view", e);
          }
         throw e;
      }
   }
}
