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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.log.LogUtil;

public class ShippingHelper
    extends TagHelper
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private WeblisketSession weblisketSession;
   
   private StoreFrontInterface storeFrontInterface;   
   private HashMap propertiesHashMap;
   private PageContext pageContext;
   
   private HttpServletRequest request;
      
   private String shippingType;
   
   public ShippingHelper(HashMap hashMap, PageContext pageContext)
   {
      this.propertiesHashMap = propertiesHashMap;
      this.pageContext = pageContext;

      this.request = (HttpServletRequest) pageContext.getRequest();
      
      String storeName = (String) hashMap.get(StoreFrontData.getInstance().NAME);
      
      if(storeName!=null)
      {
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      }

      this.weblisketSession = 
         new WeblisketSession(hashMap, pageContext);
      
      this.getFormData();
   }
   
   private void getFormData()
   {      
      this.shippingType
         = request.getParameter(ShippingMethodData.NAME);
   }      
         
   public String setShippingType()
   {
      try
      {
         String success = "Successfully Set Shipping Type";
         OrderInterface order = this.weblisketSession.getOrder();
         order.setShippingMethod(shippingType);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {            
            logUtil.put(success,this,"setShippingType()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to view Shipping Type";
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"setShippingType()",e);
         }
         return error;
      }
   }
   
}
