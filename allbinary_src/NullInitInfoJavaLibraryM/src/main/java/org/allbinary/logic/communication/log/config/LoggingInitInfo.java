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
package org.allbinary.logic.communication.log.config;

import org.allbinary.util.BasicArrayList;

public class LoggingInitInfo
{      
   private static BasicArrayList logConfigInfoList = null;
   
   private static boolean hasRead = false;
   
   public LoggingInitInfo()
   {
   }

//   public synchronized static Document getDoc() throws Exception
//   {
//   }
   
   public synchronized static void write() throws Exception
   {
      try
      {
         //write new logConfigFile
         
	 hasRead = false;
      }
      catch(Exception e)
      {
	 throw e;
      }
   }
   
   private synchronized static void read() throws Exception
   {
      try
      {
          /*
         //Document document = LoggingInitInfo.getDoc();

         NodeList logConfigsNodeList =
            document.getElementsByTagName(LogConfigsData.getInstance().NAME);

         Node logConfigsNode = logConfigsNodeList.item(0);
         
         Vector logConfigNodeVector = 
            DomSearchHelper.getAllNodes(
               LogConfigData.getInstance().NAME, logConfigsNode.getChildNodes());
	    
         LoggingInitInfo.logConfigInfoVector = new Vector();
	    
         iter = logConfigNodeVector;
         while(iter.hasNext())
         {
            Node node = (Node) iter.next();
            LogConfig logConfigInfo = new LogConfig(node);
            LoggingInitInfo.logConfigInfoVector.add(logConfigInfo);
         }
*/
      }
      catch(Exception e)
      {
         throw e;
      }
   }
   
   public synchronized static void setHasRead(boolean value)
   {
      LoggingInitInfo.hasRead = value;
   }
   
   private synchronized static void updateIfNeeded() throws Exception
   {
      if(!hasRead)
      {
	 LoggingInitInfo.read();
	 hasRead = true;
	 if(LoggingInitInfo.logConfigInfoList == null)
	 {
	    throw new Exception("Read Failed");
	 }
      }
   }
   
   public boolean isValid()
   {
      try
      {
	 return true;
      }
      catch(Exception e)
      {
	 return false;
      }
   }
   
   public synchronized static void set(BasicArrayList logConfigInfoList)
   {
      LoggingInitInfo.logConfigInfoList = logConfigInfoList;
   }

   public static BasicArrayList get() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      return LoggingInitInfo.logConfigInfoList;
   }

   /*
   public static BasicArrayList getTypeNameList() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      
      BasicArrayList allLogTypeVector = new BasicArrayList();
      iter = LoggingInitInfo.logConfigInfoVector;
      
      while(iter.hasNext())
      {
         LogConfig logConfigInfo = (LogConfig) iter.next();
         BasicArrayList logTypeVector = logConfigInfo.getTypeVector();
         
         if(logConfigInfo.isEnabled())
         {
            allLogTypeVector.addAll(logTypeVector);
         }
      }
      return allLogTypeVector;
   }
    */

   public static BasicArrayList getTypeList() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      
      final BasicArrayList allLogTypeVector = new BasicArrayList();
      
      final int size = LoggingInitInfo.logConfigInfoList.size();
      for(int index = 0; index < size; index++)
      {
         final LogConfig logConfigInfo = (LogConfig) LoggingInitInfo.logConfigInfoList.objectArray[index];
         final BasicArrayList logTypeVector = logConfigInfo.getTypeVector();

         if(logConfigInfo.isEnabled())
         {
            allLogTypeVector.addAll(logTypeVector);
         }
      }
      return allLogTypeVector;
   }
   
   public int getNumberOfLogConfigs() throws Exception
   {
      return LoggingInitInfo.logConfigInfoList.size();
   }
   
   public String toString()
   {
      try
      {
          return null;
          /*
         Document document = LoggingInitInfo.getDoc();
         return DomDocumentHelper.toString(document);
         */
      }
      catch(Exception e)
      {
	 return "Error";
      }
   }
}
