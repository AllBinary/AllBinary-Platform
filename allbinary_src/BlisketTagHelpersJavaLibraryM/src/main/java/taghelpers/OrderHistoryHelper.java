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
package taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpServletRequest;

import admin.taghelpers.TagHelper;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.address.StreetAddress;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityInterface;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryFactory;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryInterface;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.business.user.commerce.shipping.ShippingMethodData;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;


public class OrderHistoryHelper extends TagHelper
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private WeblisketSession weblisketSession;
   
   private StoreFrontInterface storeFrontInterface;   
   private final HashMap propertiesHashMap;
   private final PageContext pageContext;
   
   private final HttpServletRequest request;
   
   private StreetAddress streetAddress;
   
   private String id;
   private String groupId;
   private String status;
   private String newStatus;
   
   public OrderHistoryHelper(HashMap propertiesHashMap, PageContext pageContext) throws Exception
   {
      this.propertiesHashMap = propertiesHashMap;
      this.pageContext = pageContext;
      
      this.request = (HttpServletRequest) pageContext.getRequest();      
      
      String storeName = (String) propertiesHashMap.get(StoreFrontData.getInstance().NAME);
      
      this.newStatus = (String) propertiesHashMap.get(OrderHistoryData.STATUS);
      
      if(storeName!=null)
      {       
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      }
      this.getFormData();
   }
   
   private void getFormData()
   {
      this.id = request.getParameter(OrderData.ID);
      this.groupId = request.getParameter(ShippingMethodData.GROUP);
      if(this.newStatus==null) 
      {
         this.newStatus = request.getParameter(OrderHistoryData.STATUS);
      }
   }
     
   public String setOrderStatus()
   {
      try
      {
         String success = "Status successfully set to: " + this.status;
         OrderHistoryInterface orderReviewInterface = OrderHistoryFactory.getInstance(id);
         String status = orderReviewInterface.getStatus();
         
         if(status.compareTo(OrderHistoryData.CANCELLED)==0)
         {
            return "Order " + id + " Already Cancelled<br/>";
         }
         else
            if(status.compareTo(OrderHistoryData.SHIPPED)==0)
            {
               return "Order " + id + " Already Shipped. You can't cancel a shipped order.<br/>";
            }
            else
               if(status.compareTo(OrderHistoryData.PREPROCESSING)==0)
               {
                  return "Order " + id + " has been cancelled.<br/>";
               }
               else
                  if(status.compareTo(OrderHistoryData.PARTIALLYSHIPPED)==0)
                  {
                     return "Order " + id + " Partially Shipped. You can cancel unshipped items, but not the whole order.<br/>";
                  }
                  else
                     if(status.compareTo(OrderHistoryData.PROCESSING)==0)
                     {
                        OrderHistoryEntityInterface orderHistoryEntityInterface 
                           = OrderHistoryEntityFactory.getInstance();
                        orderHistoryEntityInterface.setStatus(id,this.newStatus);
                        return "Order " + id + " has been cancelled.<br/>";
                     }
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put(success,this,"setOrderStatus()");
         }
         return "Error Setting Order Status";
      }
      catch(Exception e)
      {
         String error = "Failed to set order status";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"setOrderStatus()",e);
         }
         return error + "<br/>" + "Exception: " + e + "<br/>";
      }
   }   
   
}
