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


import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.io.AbFileLocalInputStream;

import org.allbinary.logic.io.file.AbFile;

import org.allbinary.logic.io.path.AbPath;
import org.allbinary.globals.PATH_GLOBALS;
import org.allbinary.util.BasicArrayList;

public class LoggingInitInfo
{
   private static final String INITFILENAME = "logConfig.xml";
   
   private static final String PACKAGE = PATH_GLOBALS.getInstance().INIT_PATH;
   
   private static BasicArrayList logConfigInfoList = null;
   
   private static boolean hasRead = false;
   
   public LoggingInitInfo()
   {
   }

   public synchronized static Document getDoc() throws Exception
   {
      //PreLogUtil.put("Webapp Path: " + URLGLOBALS.getWebappPath(), "LoggingInitInfo", "getDoc");
      //PreLogUtil.put("Package: " + PACKAGE, "LoggingInitInfo", "getDoc");
      final String path = URLGLOBALS.getWebappPath() + PACKAGE;
      final AbPath FILEABPATH = new AbPath(path, INITFILENAME);
      //PreLogUtil.put("File Path: " + FILEABPATH, "LoggingInitInfo", "getDoc");
      
      final AbFile file = new AbFile(FILEABPATH);

      final Document document = 
         DomDocumentHelper.create(new AbFileLocalInputStream(file));
      
      return document;
   }
   
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
         final Document document = LoggingInitInfo.getDoc();

         final NodeList logConfigsNodeList =
            document.getElementsByTagName(LogConfigsData.getInstance().NAME);

         final Node logConfigsNode = logConfigsNodeList.item(0);
         
         final Vector logConfigNodeVector = 
            DomSearchHelper.getAllNodes(
               LogConfigData.getInstance().NAME, logConfigsNode.getChildNodes());
	    
         LoggingInitInfo.logConfigInfoList = new BasicArrayList();
	    
         final int size = logConfigNodeVector.size();
         for (int i = 0; i < size; i++)
         {
            final Node node = (Node) logConfigNodeVector.elementAt(i);
            LogConfig logConfigInfo = new LogConfig(node);
            LoggingInitInfo.logConfigInfoList.add(logConfigInfo);
         }

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
   
   public synchronized static void set(BasicArrayList logConfigInfoVector)
   {
      LoggingInitInfo.logConfigInfoList = logConfigInfoVector;
   }

   public static BasicArrayList get() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      return LoggingInitInfo.logConfigInfoList;
   }

   public static BasicArrayList getTypeNameList() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      
      BasicArrayList allLogTypeVector = new BasicArrayList();
      
      final int size = LoggingInitInfo.logConfigInfoList.size();
      for(int index = 0; index < size; index++)
      {
         final LogConfig logConfigInfo = (LogConfig) LoggingInitInfo.logConfigInfoList.objectArray[index];
         BasicArrayList logTypeVector = logConfigInfo.getTypeVector();
         
         if(logConfigInfo.isEnabled())
         {
            allLogTypeVector.addAll(logTypeVector);
         }
      }
      return allLogTypeVector;
   }

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
         final Document document = LoggingInitInfo.getDoc();
         return DomDocumentHelper.toString(document);
      }
      catch(Exception e)
      {
	 return "Error";
      }
   }
}
