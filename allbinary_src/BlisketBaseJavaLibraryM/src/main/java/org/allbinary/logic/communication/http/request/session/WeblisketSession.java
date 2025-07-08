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
package org.allbinary.logic.communication.http.request.session;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.commerce.inventory.basket.Basket;
import org.allbinary.business.user.commerce.inventory.order.Order;
import org.allbinary.business.user.commerce.inventory.order.OrderInterface;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.business.user.role.UserRole;
import org.allbinary.business.user.role.UserRoleB;
import org.allbinary.business.user.role.UserRoleData;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.java.bool.BooleanFactory;

public class WeblisketSession implements WeblisketSessionInterface
{
   private HttpSession session;

   /*
   public WeblisketSession(PageContext pageContext)
   {
      this.pageContext = pageContext;
    
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      this.session = request.getSession(true);
   }
    */
   
   public WeblisketSession(HashMap hashMap, PageContext pageContext)
   {
      this(hashMap, (HttpServletRequest) pageContext.getRequest());
      //PageContext pageContext1 = pageContext;
   }

   public WeblisketSession(HashMap hashMap, HttpServletRequest httpServletRequest)
   {
      HashMap propertiesHashMap = hashMap;
      
      HttpServletRequest request = httpServletRequest;
      this.session = request.getSession(true);
      
      //TWB replace when storeName is no longer an attribute for store views and session only
      if(this.getStoreName() == null)
      {
         this.setStoreName((String) propertiesHashMap.get(StoreFrontData.getInstance().NAME));
      }
      
      //this.session = pageContext.getSession(true);
   }
   
      /*
      if(session != null)
      {
         this.paymentMethod = (String) session.getAttribute(PaymentGatewayData.NAME.toString());
      }
       */
   
   public String getId()
   {
      return this.session.getId();
   }
   
   public void setPassword(String password)
   {
      session.setAttribute(UserData.PASSWORD, password);
   }
   
   public void setUserName(String userName)
   {
      session.setAttribute(UserData.USERNAME,userName);
   }
   
   public void setPaymentMethod(String value)
   {
      session.setAttribute(PaymentGatewayData.NAME.toString(), value);
   }
   
   public void setAuthenticated()
   {
      session.setAttribute(WeblisketSessionData.AUTHENTICATED, BooleanFactory.getInstance().TRUE_STRING);
   }
   
   public void setRole(UserRole aRole)
   {
      session.setAttribute(UserRoleData.NAME.toString(), aRole);
   }
   
   public void setAttempts(Integer value)
   {
      session.setAttribute(WeblisketSessionData.ATTEMPTS,value);
   }
   
   public void setTimeout(String value)
   {
      this.session.setAttribute(WeblisketSessionData.TIMEOUT, value);
   }
   
   public void setStoreName(String value)
   {
      this.session.setAttribute(StoreFrontData.getInstance().NAME, value);
   }
   
   public String getWebAppPath()
   {
      return (String) this.session.getAttribute(org.allbinary.globals.GLOBALS2.WEBAPPPATH);
   }
      
   public String getUserName()
   {
      return (String) session.getAttribute(UserData.USERNAME);
   }
   
   public String getPassword()
   {
      return (String) session.getAttribute(UserData.PASSWORD);
   }
   
   public String getPaymentMethod()
   {
      return (String) session.getAttribute(PaymentGatewayData.NAME.toString());
   }
   
   public String getAuthentication()
   {
      return (String) session.getAttribute(WeblisketSessionData.AUTHENTICATED);
   }
      
   public Integer getAttempts()
   {
      return (Integer) session.getAttribute(WeblisketSessionData.ATTEMPTS);
   }
   
   public String getTimeout()
   {
      return (String) session.getAttribute(WeblisketSessionData.TIMEOUT);
   }
   
   public String getStoreName()
   {
      return (String) session.getAttribute(StoreFrontData.getInstance().NAME);
   }
   
   public UserRole getRole() 
   throws Exception
   {
	  //This could be a serialized object
	  UserRole userRole = (UserRole) session.getAttribute(UserRoleData.NAME.toString());

	  if(userRole != null)
	  {
		  return UserRoleB.getRole(userRole.getBasicUserRole().getRole());
	  }
	  else
	  {
		  return null;
	  }
   }
   
   public OrderInterface getOrder() throws Exception
   {
      StoreFrontInterface storeFrontInterface =
          StoreFrontFactory.getInstance(this.getStoreName());
       
      String basketName =
          storeFrontInterface.getBasketName();

      if(!StringValidationUtil.getInstance().isEmpty(basketName))
      {
         
         OrderInterface orderInterface =
            (OrderInterface) session.getAttribute(basketName);
         
         if(orderInterface == null)
         {
            orderInterface = (OrderInterface) new Order(new Basket());
         /*OrderFactory.getInstance(this.propertiesHashMap,this.pageContext);*/
            //session.setAttribute(basketName, orderInterface);
         }

         //Need to serialize new object since the previous object may not be the same
         session.setAttribute(basketName, orderInterface);
         
         return orderInterface;
      }
      else
      {
         throw new Exception("Basket Name Not Found");
      }
   }

   /*
   public BasketInterface getBasket() throws Exception
   {
      final CommonStrings commonStrings = CommonStrings.getInstance();
      try
      {
         OrderInterface orderInterface = this.getOrder();
         
         BasketInterface basketInterface = orderInterface.getBasket();
         
         if(basketInterface == null)
         {
            basketInterface = (BasketInterface) new Basket();

            //BasketFactory.getInstance(
              // this.storeFrontInterface,
               //this.propertiesHashMap,
               //this.pageContext);

            //orderInterface.setBasket((BasketInterface) basketInterface);
            //this.updateOrder();
            //Need to serialize new object since the previous object may not be the same
            session.setAttribute(basketName, orderInterface);
         }
         return basketInterface;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "getBasket()", e);
         }
         throw e;
      }
   }
   */
   
   public void setAuthenticated(boolean value)
   {
      session.setAttribute(WeblisketSessionData.AUTHENTICATED, BooleanFactory.getInstance().FALSE_STRING);
   }

   public void clear()
   {
      this.session.removeAttribute(WeblisketSessionData.AUTHENTICATED);
      this.session.removeAttribute(WeblisketSessionData.TIMEOUT);
      this.session.removeAttribute(UserRoleData.NAME.toString());
      this.session.removeAttribute(UserData.USERNAME);
   }

   /*
   public void invalidate()
   {
      this.clear();
      this.session.invalidate();
   }
   */

   public long getCreationTime()
   {
      return session.getCreationTime();
   }
   
   public long getLastAccessedTime()
   {
      return session.getLastAccessedTime();
   }
   
   public void removeBasket()
   {
      StoreFrontInterface storeFrontInterface =
         StoreFrontFactory.getInstance(this.getStoreName());
      
      String basketName =
         storeFrontInterface.getBasketName();
      
      if(!StringValidationUtil.getInstance().isEmpty(basketName))
      {
         session.removeAttribute(basketName);
      }
   }
}
