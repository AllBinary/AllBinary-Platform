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
package org.allbinary.logic.communication.log.config.type;

//import java.io.PrintWriter;

import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeData;

import org.w3c.dom.Node;
   
import org.allbinary.data.tree.dom.DomNodeHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.communication.log.config.LoggingInitInfo;
import org.allbinary.util.BasicArrayList;

public class LogConfigTypes
{
   public static final String JSP_ERROR_NO_FUNCTION = "in JSP Not In Function";

   public static BasicArrayList LOGGING = new BasicArrayList();

   static
   {
       LogConfigTypes.init();
   }
   
   //public static String ALLBINARY_HELPERS_REQUEST = "org.allbinary.helpers.request";
   
   private LogConfigTypes()
   {
   }
   
   /*
   public static synchronized void init(PrintWriter printWriter)
   {
      try
      {
         LoggingInitInfo loggingInitInfo = new LoggingInitInfo();
         Vector logConfigTypeVector = loggingInitInfo.getTypeVector();
         printWriter.print("Number Of Log Configs: " + loggingInitInfo.getNumberOfLogConfigs() + "<br/>");
         printWriter.print("Number Of Log Config Type Names: " + logConfigTypeVector.size() + "<br/>");
    
         iter = logConfigTypeVector;
         while(iter.hasNext())
         {
            LogConfigType logConfigType = (LogConfigType) iter.next();
            String nextLogTypeName = (String) logConfigType.getName();
            printWriter.print("Log Type Name: " + nextLogTypeName + "<br/>");
         }
         LogConfigTypes.LOGGING.addAll(logConfigTypeVector);
         LogConfigTypes.LOGGING.add(LogConfigTypes.NETBEANS_MODULE);
      }
      catch(Exception e)
      {
         printWriter.print("Unable to initialize LogconfigTypes: <br/>" + e.getMessage());
      }
   }
    */
   
   public static synchronized void init()
   {
      try
      {
         PreLogUtil.put("Initialize LogconfigTypes" ,"LogConfigTypes","init()");

         LogConfigTypeFactory.getInstance();
         
         //Load the Specified configuration files
         final LoggingInitInfo loggingInitInfo = new LoggingInitInfo();
         final BasicArrayList logConfigTypeVector = loggingInitInfo.getTypeList();
         PreLogUtil.put("Number Of Log Configs: " + loggingInitInfo.getNumberOfLogConfigs(),"LogConfigTypes","init()");
         PreLogUtil.put("Number Of Log Config Type Names: " + logConfigTypeVector.size(),"LogConfigTypes","init()");
         
         LogConfigTypes.LOGGING.addAll(logConfigTypeVector);
         LogConfigTypes.LOGGING.add(LogConfigTypeFactory.getInstance().NETBEANS_MODULE);
      }
      catch(Exception e)
      {
         PreLogUtil.put("Unable to initialize LogConfigTypes","LogConfigTypes","init()", e);
      }
   }
   
   public static LogConfigType getInstance(final Node node) throws Exception
   {
      final Node nameValueNode =
         DomSearchHelper.getNode(LogConfigTypeData.getInstance().NAME, node.getChildNodes());
      final String name = DomNodeHelper.getTextNodeValue(nameValueNode);

      final Node descriptionValueNode =
         DomSearchHelper.getNodeNoThrow(LogConfigTypeData.getInstance().DESCRIPTION, node.getChildNodes());
      
      if(descriptionValueNode != null)
      {
         final String description = 
            DomNodeHelper.getTextNodeValue(descriptionValueNode);
      }

      final BasicArrayList availableLogConfigTypes = LogConfigType.availableLogConfigTypes;
      
      final int size = availableLogConfigTypes.size();
      LogConfigType logConfigType;
      for(int index = 0; index < size; index++)
      {
         logConfigType = (LogConfigType) availableLogConfigTypes.get(index);
         if(logConfigType.getName().compareTo(name) == 0)
         {
            return logConfigType;
         }
      }
      throw new Exception("No Such LogConfigType: " + name);
   }
}
