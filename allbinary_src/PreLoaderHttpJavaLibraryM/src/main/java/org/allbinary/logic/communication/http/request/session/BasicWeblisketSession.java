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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.user.UserData;
import org.allbinary.business.user.commerce.money.payment.gateway.PaymentGatewayData;
import org.allbinary.business.user.role.BasicUserRole;
import org.allbinary.business.user.role.BasicUserRoleData;
import org.allbinary.logic.java.bool.BooleanFactory;

public class BasicWeblisketSession
{
   private HttpSession session;
   
   //private HashMap propertiesHashMap;
   private PageContext pageContext;

   /*
   public WeblisketSession(PageContext pageContext)
   {
      this.pageContext = pageContext;
            
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      this.session = request.getSession(true);
   }
   */
   
   public BasicWeblisketSession(PageContext pageContext)
   {
      this.pageContext = pageContext;
      
      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      //Might use this instead for Google App Engine - this.getThreadLocalRequest().getSession();
      this.session = request.getSession(true);
   }    

   public void setRole(BasicUserRole aRole)
   {
      session.setAttribute(BasicUserRoleData.NAME.toString(), aRole);
   }
   
   public BasicUserRole getRole()
   {
      return (BasicUserRole) session.getAttribute(BasicUserRoleData.NAME.toString());
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

   public void setAuthenticated(boolean value)
   {
      session.setAttribute(WeblisketSessionData.AUTHENTICATED, BooleanFactory.getInstance().FALSE_STRING);
   }

   public void clear()
   {
      this.session.removeAttribute(WeblisketSessionData.AUTHENTICATED);
      this.session.removeAttribute(WeblisketSessionData.TIMEOUT);
      this.session.removeAttribute(BasicUserRoleData.NAME.toString());
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
	   long lastAccessedTime = session.getLastAccessedTime();
	   
	  //if(AbFileSystem.getInstance().isType())
	  //{
		//  Calendar calendar = Calendar.getInstance();
	  //}
      return lastAccessedTime; 
   }
}
