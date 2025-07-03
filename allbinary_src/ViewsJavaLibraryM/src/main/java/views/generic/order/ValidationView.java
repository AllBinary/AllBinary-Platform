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
package views.generic.order;




import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.commerce.inventory.order.OrderHistoryFactory;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.data.tables.user.commerce.inventory.order.OrderItemsEntity;

public class ValidationView extends OrderView implements ValidationComponentInterface
{
   public ValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
      
   public Boolean isValid()
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         if(this.order.isIdValid() == Boolean.FALSE)
         {
            return Boolean.FALSE;
         }
         else
         {
            this.orderHistory = OrderHistoryFactory.getInstance(this.order.getId());

            if(this.orderHistory == null  || this.orderHistory.isValid() == Boolean.FALSE)
            {
               return Boolean.FALSE;
            }
            
            OrderItemsEntity orderItems = new OrderItemsEntity();
            this.basketReview = orderItems.getBasketReview(this.order.getId());
            
            if(this.basketReview == null  || this.basketReview.isValid() == Boolean.FALSE)
            {
               return Boolean.FALSE;
            }            
         }
         
         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,commonStrings.IS_VALID,e));
         }
         return Boolean.FALSE;
      }
   }

   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(this.order.isIdValid() == Boolean.FALSE)
         {
            stringBuffer.append(this.order.getIdValidationInfo());
         }
         else
         {
            this.orderHistory = OrderHistoryFactory.getInstance(this.order.getId());

            if(this.orderHistory == null  || this.orderHistory.isValid() == Boolean.FALSE)
            {
               stringBuffer.append("Order History data error for: ");
               stringBuffer.append(this.order.getId());
               stringBuffer.append("<br />");
            }
            
            OrderItemsEntity orderItems = new OrderItemsEntity();
            this.basketReview = orderItems.getBasketReview(this.order.getId());
            
            if(this.basketReview == null)
            {
            	stringBuffer.append("Order Items data error - does not exist for: ");
            	stringBuffer.append(this.order.getId());
            	stringBuffer.append("<br />");
            }
            else
            	if(this.basketReview.isValid() == Boolean.FALSE)
            {
            	stringBuffer.append("Order Items data error - not valid for: ");
            	stringBuffer.append(this.order.getId());
            	stringBuffer.append("<br />");
            }
         }

         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info", this, "validationInfo()", e));
         }
         return "Error Validating Form";
      }
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
}
