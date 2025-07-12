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
package org.allbinary.logic.control.workflow;

import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.data.tables.workflow.WorkFlowEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.io.path.AbPathData;
import org.allbinary.logic.system.security.licensing.LicensingException;

public class RequestWorkFlowFactory
{
   private RequestWorkFlowFactory()
   {
   }  

   //Used by user request
   //Assumes store name is in request path - usually a user request
   //i.e. getting a workflow from the db for processing the actual workflow
   public static WorkFlowInterface getInstance(ServletRequest request, 
      ServletResponse response, 
      ServletConfig servletConfig, 
      ServletContext servletContext) throws Exception, LicensingException
   {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      String requestCommand = httpRequest.getPathInfo();

      int index = requestCommand.indexOf(AbPathData.getInstance().SEPARATOR);

      String storeName = requestCommand.substring(0,index);
      String requestName = requestCommand.substring(index,requestCommand.length());
      
      //The entity factory uses the WorkFlowFactory.getInstance(HashMap hashMap)
      return WorkFlowEntityFactory.getInstance().create2().get(requestName, storeName);
   }

   //Used to validate, delete, edit, and/or view
   public static WorkFlowInterface getInstance(HashMap hashMap, PageContext pageContext) throws Exception, LicensingException
   {
      String workFlowName = (String) 
          pageContext.getRequest().getParameter(
        		  WorkFlowData.getInstance().NAME);

      WeblisketSession weblisketSession = 
         new WeblisketSession(hashMap, pageContext);
      String storeName = weblisketSession.getStoreName();
      
      //The entity factory uses the WorkFlowFactory.getInstance(HashMap hashMap)
      return WorkFlowEntityFactory.getInstance().create2().get(workFlowName, storeName);
   }
}
