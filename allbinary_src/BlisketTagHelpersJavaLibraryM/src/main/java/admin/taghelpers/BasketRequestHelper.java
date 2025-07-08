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
import org.allbinary.business.context.modules.storefront.StoreFront;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.commerce.inventory.basket.BasketData;
import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;

import org.allbinary.business.user.commerce.inventory.item.BasicItemData;


import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

import org.allbinary.data.tables.user.commerce.inventory.item.InventoryEntityFactory;

import org.allbinary.logic.communication.http.request.session.WeblisketSession;

public class BasketRequestHelper
    extends TagHelper
{
    protected final LogUtil logUtil = LogUtil.getInstance();
  

   private final WeblisketSession weblisketSession;
   
   private final StoreFrontInterface storeFrontInterface;   
   private final HashMap propertiesHashMap;
   private final PageContext pageContext;
   
   private final HttpServletRequest request;   
   
   private String id;
   private String num;
   
   private final int MAX = 200;
   
   public BasketRequestHelper(HashMap propertiesHashMap, PageContext pageContext)
   {
      this.propertiesHashMap = propertiesHashMap;
      this.pageContext = pageContext;
      
      this.request = (HttpServletRequest) pageContext.getRequest();
      
      String storeName = (String) propertiesHashMap.get(StoreFrontData.getInstance().NAME);
      
      if(storeName != null)
      {
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      }
      else
      {
    	  this.storeFrontInterface = new StoreFront();
      }
      
      this.weblisketSession = new WeblisketSession(propertiesHashMap, pageContext);
      
      this.getFormData();
   }
   
   private void getFormData()
   {
      this.id = request.getParameter(BasicItemData.ID);
      this.num = request.getParameter(BasketData.ITEMTOTALINBASKET);
   }
   
   public Boolean addItemToBasket()
   {
      try
      {                  
         if(id!=null && num!=null)
         {
            BasketInterface basket = this.weblisketSession.getOrder().getBasket();

            if(InventoryEntityFactory.getInstance().getInventoryEntityInstance().getItem(id)==null)
            {
               return Boolean.FALSE;
            }

            basket.addItem(id,num);
         }
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put("Successfully added item to Basket",this,"addItemToBasket()");
         }
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"addItemToBasket()",e);
         }
         return Boolean.FALSE;
      }
   }
   
   public Boolean removeItemFromBasket()
   {
      try
      {         
         if(id!=null)
         {
            BasketInterface basket = this.weblisketSession.getOrder().getBasket();
            basket.removeItem(id);
         }
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put("Successfully removed item from Basket",this,"removeItemFromBasket()");
         }
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"removeItemFromBasket()",e);
         }
         return Boolean.FALSE;
      }
   }

   public Boolean adjustBasket()
   {
      try
      {
         //int index = 2;
         BasketInterface basket = this.weblisketSession.getOrder().getBasket();
         
         if(id!=null && num!=null) basket.adjustItem(id,num);
         /*
         while(index < this.MAX)
         {
            
            String nextId = BasicItemData.ID + new Integer(index).toString();
            String nextNum = BasicItemData.ITEMTOTALINBASKET + new Integer(index).toString();
            id = request.getParameter(nextId);
            num = request.getParameter(nextNum);
            index++;
         }
         */
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGS))
         {
            logUtil.put("Successfull Adjusted Basket",this,"adjustBasket()");
         }
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION,this,"adjustBasket()",e);
         }
         return Boolean.FALSE;
      }
   }

}
