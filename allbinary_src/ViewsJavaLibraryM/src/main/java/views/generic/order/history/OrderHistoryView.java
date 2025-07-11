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
package views.generic.order.history;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.business.user.commerce.inventory.order.OrderHistoryData;
import org.allbinary.data.tables.user.commerce.inventory.order.OrderHistoryEntity;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.globals.GLOBALS2;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class OrderHistoryView 
extends HttpStoreComponentView 
implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private HttpServletRequest request;
   
   private String shipped;
   private String partiallyShipped;
   private String processing;
   private String preprocessing;
   private String cancelled;
   
   public OrderHistoryView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      this.request = (HttpServletRequest) this.getPageContext().getRequest();
      
      this.preprocessing = request.getParameter(OrderHistoryData.PREPROCESSINGNAME);
      this.shipped = request.getParameter(OrderHistoryData.SHIPPEDNAME);
      this.partiallyShipped = request.getParameter(OrderHistoryData.PARTIALLYSHIPPEDNAME);
      this.processing = request.getParameter(OrderHistoryData.PROCESSINGNAME);
      this.cancelled = request.getParameter(OrderHistoryData.CANCELLEDNAME);
   }
   
   public Node toXmlNode(final Document document) throws Exception
   {
      try
      {
         final Node node = document.createElement(OrderData.ORDERS);
         
         final OrderHistoryEntity orderHistoryEntity = new OrderHistoryEntity();
         
         //Note all generic views should be removed from admin pages because a user
         //could pass in false hidden data
         final Vector orderReviewVector = 
            orderHistoryEntity.getOrders(
               this.getWeblisketSession().getUserName());
         
         final int size = orderReviewVector.size();
         for (int index = 0; index < size; index++)
         {
            OrderHistory orderHistory = (OrderHistory) orderReviewVector.get(index);
            Node orderHistoryNode = orderHistory.toXmlNode(document);
            Node orderNode = document.createElement(orderHistory.getPaymentMethod());
            node.appendChild(orderHistory.toXmlNode(document));
         }
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("Attempt to View a users order history",this,"view");
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
            logUtil.put(this.commonStrings.FAILURE,this,"toXmlNode",e);
         }
         throw e;
      }
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
            logUtil.put(commonStrings.EXCEPTION,this,"view()",e);
         }
         throw e;
      }
   }
}
