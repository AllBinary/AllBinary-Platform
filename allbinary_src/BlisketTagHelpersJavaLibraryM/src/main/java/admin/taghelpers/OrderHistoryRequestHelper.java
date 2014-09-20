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

import org.allbinary.logic.communication.log.LogFactory;
import java.util.*;

import javax.servlet.jsp.PageContext;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.user.commerce.shipping.ShippingMethodData;

import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.business.user.commerce.inventory.order.status.OrderStatusEmail;

import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntityFactory;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderItemsEntityFactory;


import org.allbinary.logic.communication.log.LogUtil;

public class OrderHistoryRequestHelper
    implements TagHelperInterface
{
   private HttpServletRequest request;
   
   private String id;

   private String groupId;
   private String status;
   
   //private String paymentMethod;
   
   //private String xslFile;
   //private String path;
   
   public OrderHistoryRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      /*
      String storeName = (String) hashMap.get(StoreFrontData.NAME);
      if(storeName!=null)
      {
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      }
      */
 //     this.pageContext = pageContext;
      
      this.request = (HttpServletRequest) pageContext.getRequest();

      //this.weblisketSession =
      //new WeblisketSession(hashMap, pageContext);
      
      this.getFormData();
   }
   
   private void getFormData()
   {
      this.id = request.getParameter(OrderData.ID);
      this.groupId = request.getParameter(ShippingMethodData.GROUP);
      this.status = request.getParameter(OrderHistoryData.STATUS);
      
   //   this.paymentMethod = this.weblisketSession.getPaymentMethod();
   }
   
   public String setOrderStatus()
   {
      try
      {
         //order item status change may cause whole order status change
         if(this.groupId.compareTo("0")!=0)
         {
            if(OrderItemsEntityFactory.getInstance().isEverythingShipped(id))
            {
               this.status = OrderHistoryData.SHIPPED;
            }
            else
            {
               this.status = OrderHistoryData.PARTIALLYSHIPPED;
            }
         }
         
         String success = "Status successfully set to: " + this.status;
         
         OrderHistoryEntityFactory.getInstance().setStatus(id,this.status);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Success",this,"setOrderStatus()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to set order status";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setOrderStatus()",e));
         }
         return error;
      }
   }
   
   public String setOrderStatus(String newStatus)
   {
      try
      {
         if(newStatus == null) newStatus = this.status;
         String success = "Status successfully set to: " + newStatus;
         
         OrderHistoryEntityFactory.getInstance().setStatus(id,newStatus);
         
         OrderHistory orderHistory = 
            OrderHistoryEntityFactory.getInstance().getOrder(id);
         
         new OrderStatusEmail(orderHistory).process();

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Success",this,"setOrderStatus()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to view order table";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setOrderStatus(newStatus)",e));
         }
         return error;
      }
   }
   
   /*
   public String setPaymentMethod()
   {
      try
      {
         String success = "Payment Method successfully set: " + this.paymentMethod;
    
         OrderHistoryEntityFactory.getInstance().setPaymentMethod(id,this.paymentMethod);
    
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance("Success",this,"setPaymentMethod()");
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to set Payment Method";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"setPaymentMethod()",e);
         }
         return error;
      }
   }
    */
   
}
