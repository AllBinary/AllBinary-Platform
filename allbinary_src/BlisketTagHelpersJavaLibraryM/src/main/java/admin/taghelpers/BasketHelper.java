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


import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.business.user.commerce.inventory.basket.BasketInterface;


import org.allbinary.logic.communication.http.request.session.WeblisketSession;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;

public class BasketHelper
    extends TagHelper
{
    
   private WeblisketSession weblisketSession;
   
   private StoreFrontInterface storeFrontInterface;   
   private HashMap propertiesHashMap;
   private PageContext pageContext;   
   
   private final int MAX = 200;
   
   public BasketHelper(HashMap hashMap, PageContext pageContext)
   {
      this.propertiesHashMap = hashMap;
      this.pageContext = pageContext;

      String storeName = (String) propertiesHashMap.get(StoreFrontData.getInstance().NAME);
      
      if(storeName!=null)
      {       
         this.storeFrontInterface = 
         StoreFrontFactory.getInstance(storeName);
      }
      this.weblisketSession = 
         new WeblisketSession(hashMap, pageContext);      
   }
   
   public Boolean isBasketEmpty()
   {
      try
      {
         BasketInterface basket = this.weblisketSession.getOrder().getBasket();
         if(basket.getNumberOfItems().intValue() <= 0)
         {
            return Boolean.TRUE;
         }
         else
         {
            return Boolean.FALSE;
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION,this,"isBasketEmpty()",e));
         }
         return Boolean.TRUE;         
      }
   }

}
