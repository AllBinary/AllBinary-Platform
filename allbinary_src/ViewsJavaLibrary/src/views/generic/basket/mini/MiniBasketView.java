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
package views.generic.basket.mini;

import abcs.logic.communication.log.LogFactory;
import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import abcs.logic.communication.log.LogUtil;



import allbinary.logic.control.search.SearchData;

import allbinary.business.user.commerce.inventory.basket.BasketData;
import allbinary.business.user.commerce.inventory.basket.BasketInterface;

import allbinary.logic.visual.transform.info.TransformInfoInterface;
import allbinary.data.tree.dom.DomNodeInterface;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class MiniBasketView 
extends HttpStoreComponentView 
implements DomNodeInterface
{
//   private HttpServletRequest request;
   
   public MiniBasketView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      //this.request = (HttpServletRequest) this.getPageContext().getRequest();
   }
      
   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
         BasketInterface basketInterface = 
            this.getWeblisketSession().getOrder().getBasket();
         Node basketNode = document.createElement(BasketData.BASKET);
         
         HashMap itemsAndNumberInBasket = basketInterface.getItems();
         int numberOfResults = 1;
         Set items = itemsAndNumberInBasket.keySet();
         Iterator iter = items.iterator();
                  
         Node totalNumberNode = document.createElement(SearchData.TOTAL_NUMBER_ITEMS);
         Node totalNumberTextNode = document.createTextNode(basketInterface.getNumberOfItems().toString());
         totalNumberNode.appendChild(totalNumberTextNode);
         
         Node totalWeightNode = document.createElement(BasketData.TOTALWEIGHT);
         Node totalWeightTextNode = document.createTextNode(basketInterface.getTotalWeight().toString());
         totalWeightNode.appendChild(totalWeightTextNode);
         
         Node subTotalNode = document.createElement(BasketData.SUBTOTAL);
         Node subTotalTextNode = document.createTextNode(basketInterface.getSubTotal().toString());
         subTotalNode.appendChild(subTotalTextNode);
         
         basketNode.appendChild(totalNumberNode);
         basketNode.appendChild(totalWeightNode);
         basketNode.appendChild(subTotalNode);
         
         return basketNode;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.XSLLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed",this,"toXmlNode",e));
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
         String error = "Failed to view Mini Basket";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
   
}
