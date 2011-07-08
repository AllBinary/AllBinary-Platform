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

import abcs.logic.communication.log.LogFactory;
import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import abcs.logic.communication.log.LogUtil;
import admin.taghelpers.TagHelperInterface;

import allbinary.business.user.address.StreetAddress;

import allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityInterface;

import allbinary.business.user.commerce.inventory.order.OrderData;
import allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import allbinary.business.user.commerce.inventory.order.OrderHistoryFactory;
import allbinary.business.user.commerce.inventory.order.OrderHistoryInterface;

import allbinary.logic.communication.http.request.session.WeblisketSession;

import allbinary.business.user.commerce.shipping.ShippingMethodData;

import allbinary.business.context.modules.storefront.StoreFrontData;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import java.util.HashMap;

public class OrderHistoryHelper implements TagHelperInterface
{
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"setOrderStatus()"));
         }
         return "Error Setting Order Status";
      }
      catch(Exception e)
      {
         String error = "Failed to set order status";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setOrderStatus()",e));
         }
         return error + "<br/>" + "Exception: " + e + "<br/>";
      }
   }   
   
}
