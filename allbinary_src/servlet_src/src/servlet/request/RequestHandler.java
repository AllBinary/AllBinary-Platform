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
package servlet.request;

import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.allbinary.logic.system.security.licensing.LicensingException;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.logic.control.workflow.WorkFlowInterface;
import org.allbinary.logic.control.workflow.RequestWorkFlowFactory;

public class RequestHandler extends HttpServlet
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public void init(ServletConfig config) throws ServletException
   {
      super.init(config);
   }
   
   public void destroy()
   {
      
   }
   
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
   {
      try
      {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         
         WorkFlowInterface workflowInterface =
         RequestWorkFlowFactory.getInstance((ServletRequest) request, (ServletResponse) response,
         this.getServletConfig(),
         this.getServletContext());
         
         if(workflowInterface != null)
         {
            out.print(workflowInterface.process());
         }
         out.flush();
         out.close();
      }
      catch(LicensingException e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SERVLETERROR))
         {
            logUtil.put("Servlet LicensingException",this,"processRequest()",e);
         }
         response.sendRedirect(abcs.globals.URLGLOBALS.LICENSEERRORPAGE);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SERVLETERROR))
         {
            logUtil.put("Servlet Exception",this,"processRequest()",e);
         }
         response.sendRedirect(abcs.globals.URLGLOBALS.ERRORPAGE);
      }
   }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
   {
      processRequest(request, response);
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
   {
      processRequest(request, response);
   }

   public String getServletInfo()
   {
      return "WorkFlow Request Handler";
   }
}
