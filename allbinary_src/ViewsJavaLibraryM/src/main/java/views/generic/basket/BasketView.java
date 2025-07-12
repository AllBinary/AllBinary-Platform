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
package views.generic.basket;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.user.commerce.inventory.basket.BasketData;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;
import org.allbinary.business.user.commerce.inventory.item.BasicItemView;
import org.allbinary.business.user.commerce.inventory.item.ItemInterface;
import org.allbinary.business.user.commerce.money.Money;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntity;
import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;
import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.control.search.SearchData;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class BasketView extends HttpStoreComponentView implements DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   //private HashMap propertiesHashMap;
   private final HttpServletRequest request;

   public BasketView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
      //this.propertiesHashMap = hashMap;
      
      this.request = (HttpServletRequest) this.getPageContext().getRequest();
   }

   public Node toXmlNode(Document document) throws Exception
   {
      try
      {
    	  InventoryEntity inventoryEntity = 
    		  InventoryEntityFactory.getInstance().getInventoryEntityInstance();
    	  
         BasketInterface basketInterface = this.getWeblisketSession().getOrder().getBasket();
         Node basketNode = document.createElement(BasketData.BASKET);
         
         HashMap itemsAndNumberInBasket = basketInterface.getItems();
         int numberOfResults = 1;
         Set items = itemsAndNumberInBasket.keySet();
         
         final Object[] productArray = items.toArray();
         final int size = productArray.length;
         for(int index = 0; index < size; index++)
         {
            String product = new String((String) productArray[index]);
            
            ItemInterface itemInterface = inventoryEntity.getItem(product);

            if(itemInterface!=null)
            {
            	BasicItemView basicItemView = 
            		new BasicItemView(itemInterface, new Vector());
            	
               Node node = basicItemView.toXmlNode(document);
               
               String numberInBasket = basketInterface.getNumberOf(product).toString();
               
               node.appendChild(ModDomHelper.createNameValueNodes(document,
               BasketData.ITEMTOTALINBASKET, numberInBasket));
               
               //add = total is number * items price
               Money itemPrice = itemInterface.getPrice();
               Money itemTotal = new Money(itemPrice);
               itemTotal.multiply(basketInterface.getNumberOf(product).intValue());
               
               node.appendChild(ModDomHelper.createNameValueNodes(document,
               BasketData.ITEMTOTAL, itemTotal.toString()));
               
               basketNode.appendChild(node);
            }
            else
            {
               logUtil.put("Product Failed: " + product,this,"toXmlNode");
            }
            numberOfResults++;
         }
         
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
