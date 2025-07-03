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
package views.admin.orderhistory;

import org.allbinary.logic.communication.log.LogFactory;
import java.util.Vector;


import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.data.tree.dom.ModDomHelper;

import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity;

import org.allbinary.business.user.UserData;

import org.allbinary.business.user.username.UserName;

import org.allbinary.business.user.commerce.inventory.order.OrderData;

import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;


import org.allbinary.globals.GLOBALS2;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class UserNameOrderHistoryView extends HttpStoreComponentView implements ValidationComponentInterface, DomNodeInterface
{
   private HttpServletRequest request;
   
   private String userName;
   
   private String shipped;
   private String partiallyShipped;
   private String processing;
   private String preprocessing;
   private String cancelled;
   
   private final String ON = "on";
   
   public UserNameOrderHistoryView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      
      this.request = (HttpServletRequest) this.getPageContext().getRequest();
      
      this.userName = request.getParameter(UserData.USERNAME);
      
      this.preprocessing = request.getParameter(OrderHistoryData.PREPROCESSINGNAME);
      this.shipped = request.getParameter(OrderHistoryData.SHIPPEDNAME);
      this.partiallyShipped = request.getParameter(OrderHistoryData.PARTIALLYSHIPPEDNAME);
      this.processing = request.getParameter(OrderHistoryData.PROCESSINGNAME);
      this.cancelled = request.getParameter(OrderHistoryData.CANCELLEDNAME);
      
   }

   public void addDomNodeInterfaces()
   {
      this.addDomNodeInterface((DomNodeInterface) this);
   }
   
   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
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
   
   public Node toXmlNode(Document document)
   {
      try
      {
         Node node = document.createElement(OrderData.ORDERS);

         OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
         
         //Note all generic views should be removed from admin pages because a user
         //could pass in false hidden data
         Vector orderReviewVector = orderHistoryEntity.getOrders(this.userName);
         int size = orderReviewVector.size();
         for (int index = 0; index < size; index++)
         {
            OrderHistory orderHistory = (OrderHistory) orderReviewVector.get(index);
            Node orderHistoryNode = orderHistory.toXmlNode(document);
            Node orderNode = document.createElement(orderHistory.getPaymentMethod());
            node.appendChild(orderHistory.toXmlNode(document));
         }
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Attempt to View a users order history",this,"view"));
         }
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.PREPROCESSINGNAME,
         OrderHistoryData.PREPROCESSING));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.PROCESSINGNAME,
         OrderHistoryData.PROCESSING));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.CANCELLEDNAME,
         OrderHistoryData.CANCELLED));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.PARTIALLYSHIPPEDNAME,
         OrderHistoryData.PARTIALLYSHIPPED));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         OrderHistoryData.SHIPPEDNAME,
         OrderHistoryData.SHIPPED));
         
         node.appendChild(ModDomHelper.createNameValueNodes(document,
         GLOBALS2.VIEWNAME,
         GLOBALS2.VIEW));
         
         return node;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance(this.commonStrings.FAILURE,this,"toXmlNode",e));
         }
         return null;
      }
      
   }
   
   public Boolean isValid()
   {      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Started",this,commonStrings.IS_VALID));
      }      
      
      if(UserName.getInstance().isValid(this.userName) == Boolean.TRUE)
      {
         return Boolean.TRUE;
      }      
            
      return Boolean.FALSE;
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String validationInfo()
   {      
      if(this.userName==null) return "No User Name Specified<br />";
      
      if(UserName.getInstance().isValid(this.userName) == Boolean.FALSE)
      {
         return "Invalid User Name<br />";
      }
      return "Unknown Error<br />";
   }
   
}
