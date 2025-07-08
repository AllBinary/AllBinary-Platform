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
package views.admin.storefront;

import org.allbinary.logic.communication.log.LogFactory;
import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.context.modules.storefront.StoreFront;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class StoreFrontComponent extends HttpStoreComponentView
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   protected StoreFrontInterface newStoreFrontInterface;
   
   public StoreFrontComponent(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      this.newStoreFrontInterface = (StoreFrontInterface) 
         new StoreFront((HttpServletRequest) this.getPageContext().getRequest());

      //the storeName should always be in the session since only a store manager 
      //or higher can manage a store
      if(this.newStoreFrontInterface.getName()==null)
      {
         this.newStoreFrontInterface.setName(
           this.getWeblisketSession().getStoreName());
      }
   }

   public String view() throws Exception
   {
      try
      {
         //Store is already included in store view
         return super.view();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "view", e);
         }
         throw e;
      }
   }   
}
