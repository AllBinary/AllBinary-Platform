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
package org.allbinary.logic.communication.http.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.allbinary.globals.FREEBLISKET_PATH_GLOBALS;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;

public class AbResponseHandler
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private AbResponseHandler()
   {
   }
   
   public static void sendJspTagRedirect(
   PageContext pageContext,   
   Exception e) throws JspTagException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().JSPTAGERROR))
         {
            logUtil.put("Jsp Tag Exception", "AbResponseHandler", "sendJspRedirect()", e);
         }

         AbResponseHandler.sendRedirect(pageContext, FREEBLISKET_PATH_GLOBALS.getInstance().ERRORPAGE);
      }
      catch(Exception e2)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().LICENSINGERROR))
         {
            logUtil.put("Exception in Redirect Handling","AbResponseHandler","sendJspRedirect",e);
         }
         throw new JspTagException();
      }
   }


   public static void sendServletRedirect(
   PageContext pageContext,
   Exception e) throws ServletException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().JSPTAGERROR))
         {
            logUtil.put("Jsp Tag Exception","AbResponseHandler","sendJspRedirect()",e);
         }

         AbResponseHandler.sendRedirect(pageContext,FREEBLISKET_PATH_GLOBALS.getInstance().ERRORPAGE);
      }
      catch(Exception e2)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().LICENSINGERROR))
         {
            logUtil.put("Exception in Redirect Handling","AbResponseHandler","sendJspRedirect",e);
         }
         throw new ServletException();
      }
   }

   public static void sendJspTagLicensingRedirect(
   PageContext pageContext, Exception e) throws JspTagException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().JSPTAGERROR))
         {
            logUtil.put("Licensing Exception","AbResponseHandler","sendJspTagLicensingRedirect()",e);
         }
         AbResponseHandler.sendRedirect(pageContext, FREEBLISKET_PATH_GLOBALS.getInstance().LICENSEERRORPAGE);
      }
      catch(Exception e2)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().LICENSINGERROR))
         {
            logUtil.put("Exception in Redirect Handling","AbResponseHandler","sendJspTagLicensingRedirect",e);
         }
         throw new JspTagException();
      }
   }

   public static void sendServletLicensingRedirect(PageContext pageContext, Exception e) throws ServletException
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().JSPTAGERROR))
         {
            logUtil.put("Licensing Exception","AbResponseHandler","sendServletLicensingRedirect()",e);
         }
         AbResponseHandler.sendRedirect(pageContext, FREEBLISKET_PATH_GLOBALS.getInstance().LICENSEERRORPAGE);
      }
      catch(Exception e2)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().LICENSINGERROR))
         {
            logUtil.put("Exception in Redirect Handling","AbResponseHandler","sendServletLicensingRedirect",e);
         }
         throw new ServletException();
      }
   }
   
   public static void sendRedirect(PageContext pageContext,String page) throws Exception
   {
      HttpServletRequest request = 
         (HttpServletRequest) pageContext.getRequest();
      HttpServletResponse response = 
         (HttpServletResponse) pageContext.getResponse();

      RequestDispatcher requestDispatcher =
         request.getRequestDispatcher(page);

      if(response.isCommitted())
      {         
         response.sendRedirect(page);      
         //requestDispatcher.forward(request, response);
         //requestDispatcher.include(request, response);
      }
      else
      {
         try
         {
            requestDispatcher.forward(request, response);
         }catch(Exception e)
         {
            pageContext.getOut().print("Error: " + page);
         }
      }
   }
}