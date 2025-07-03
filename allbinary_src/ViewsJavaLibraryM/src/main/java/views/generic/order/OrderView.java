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

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import views.business.context.modules.storefront.HttpStoreComponentView;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.commerce.inventory.basket.Basket;
import org.allbinary.business.user.commerce.inventory.basket.BasketReview;
import org.allbinary.business.user.commerce.inventory.order.Order;
import org.allbinary.business.user.commerce.inventory.order.OrderData;
import org.allbinary.business.user.commerce.inventory.order.OrderHistory;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class OrderView
extends HttpStoreComponentView
implements DomNodeInterface
{
   private HttpServletRequest request;
   
   protected Order order;
   protected OrderHistory orderHistory;
   
   protected BasketReview basketReview;
   
   public OrderView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      this.request = (HttpServletRequest) this.getPageContext().getRequest();
      this.getFormData();
   }
   
   private void getFormData() throws Exception
   {
      String id = request.getParameter(OrderData.ID);
      if(id==null)
      {
         id = this.getWeblisketSession().getOrder().getId();
      }
      
      this.order = new Order(new Basket());
      this.order.setId(id);
   }
   
   public Node toXmlNode(Document document)
   {
      try
      {
         Node orderHistoryNode = orderHistory.toXmlNode(document);
         
         Node node = document.createElement(orderHistory.getPaymentMethod());
         
         orderHistoryNode.appendChild(basketReview.toXmlNode(document));
         
         node.appendChild(orderHistoryNode);
         
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
   
}
