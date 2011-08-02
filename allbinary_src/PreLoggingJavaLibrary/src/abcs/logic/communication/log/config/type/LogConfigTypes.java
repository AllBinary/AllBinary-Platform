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
package abcs.logic.communication.log.config.type;

//import java.io.PrintWriter;

import java.util.Vector;

import org.w3c.dom.Node;
   
import abcs.data.tree.dom.DomNodeHelper;
import abcs.data.tree.dom.DomSearchHelper;

import abcs.logic.communication.log.PreLogUtil;
import abcs.logic.communication.log.config.LoggingInitInfo;
import org.allbinary.util.BasicArrayList;

public class LogConfigTypes
{
   public static final String JSP_ERROR_NO_FUNCTION = "in JSP Not In Function";

   public static Vector LOGGING = new Vector();

   static
   {
       LogConfigTypes.init();
   }
   
   //public static String ALLBINARY_HELPERS_REQUEST = "allbinary.helpers.request";
   
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
    
         Iterator iter = logConfigTypeVector.iterator();
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

         //Load the Specified configuration files
         LoggingInitInfo loggingInitInfo = new LoggingInitInfo();
         Vector logConfigTypeVector = loggingInitInfo.getTypeVector();
         PreLogUtil.put("Number Of Log Configs: " + loggingInitInfo.getNumberOfLogConfigs(),"LogConfigTypes","init()");
         PreLogUtil.put("Number Of Log Config Type Names: " + logConfigTypeVector.size(),"LogConfigTypes","init()");
         
         LogConfigTypes.LOGGING.addAll(logConfigTypeVector);
         LogConfigTypes.LOGGING.add(LogConfigType.NETBEANS_MODULE);
      }
      catch(Exception e)
      {
         PreLogUtil.put("Unable to initialize LogConfigTypes","LogConfigTypes","init()", e);
      }
   }
   
   public static LogConfigType getInstance(Node node) throws Exception
   {
      Node nameValueNode =
         DomSearchHelper.getNode(LogConfigTypeData.getInstance().NAME, node.getChildNodes());
      String name = 
         DomNodeHelper.getTextNodeValue(nameValueNode);

      Node descriptionValueNode =
         DomSearchHelper.getNodeNoThrow(LogConfigTypeData.getInstance().DESCRIPTION, node.getChildNodes());
      
      if(descriptionValueNode != null)
      {
         String description = 
            DomNodeHelper.getTextNodeValue(descriptionValueNode);
      }

      BasicArrayList availableLogConfigTypes = LogConfigType.availableLogConfigTypes;
      
      int size = availableLogConfigTypes.size();
      for(int index = 0; index < size; index++)
      {
         LogConfigType logConfigType = (LogConfigType) availableLogConfigTypes.get(index);
         if(logConfigType.getName().compareTo(name) == 0)
         {
            return logConfigType;
         }
      }
      throw new Exception("No Such LogConfigType: " + name);
   }
}
