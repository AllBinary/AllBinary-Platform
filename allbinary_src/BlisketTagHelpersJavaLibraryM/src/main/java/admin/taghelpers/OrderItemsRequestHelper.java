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

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;

import org.allbinary.business.user.commerce.shipping.ShippingMethodData;

import org.allbinary.data.tables.user.commerce.inventory.order.OrderItemsEntityFactory;

public class OrderItemsRequestHelper
    extends TagHelper
{

   private HttpServletRequest request;
   
   private String id;
   private String groupId;
   private String status;
   
   public OrderItemsRequestHelper(HashMap hashMap, PageContext pageContext)
   {
      this.request = (HttpServletRequest) pageContext.getRequest();
      this.getFormData();
   }
   
   private void getFormData()
   {
      this.id = request.getParameter(OrderData.ID);
      this.groupId = request.getParameter(ShippingMethodData.GROUP);
      this.status = request.getParameter(OrderHistoryData.STATUS);
   }      
      
   public String setOrderStatus(String newStatus)
   {
      try
      {
         if(newStatus==null) newStatus= this.status;
         String success = "Status successfully set to: " + newStatus;
         OrderItemsEntityFactory.getInstance().setStatus(id,groupId,newStatus);                  
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"setOrderStatus(newStatus)"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to view order table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"setOrderStatus(newStatus)",e));
         }
         return error;         
      }
   }
   
   public String setOrderStatus()
   {
      try
      {         
         String success = "Status successfully set to: " + this.status;
         OrderItemsEntityFactory.getInstance().setStatus(id, groupId, this.status);
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success,this,"setOrderStatus(newStatus)"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to view order table";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"setOrderStatus(newStatus)",e));
         }
         return error;         
      }
   }      
}
