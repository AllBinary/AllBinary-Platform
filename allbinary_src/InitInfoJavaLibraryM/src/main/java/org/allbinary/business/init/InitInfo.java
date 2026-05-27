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
package org.allbinary.business.init;

import java.util.HashMap;

import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory;
import org.allbinary.logic.communication.log.config.type.LogConfigTypes;
import org.allbinary.logic.io.path.AbPath;
import org.allbinary.logic.java.bool.BooleanUtil;
import org.allbinary.logic.string.StringUtil;

public class InitInfo
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

	private static final InitInfo instance = new InitInfo();
	
	public static InitInfo getInstance() {
		return InitInfo.instance;
	}
	
   public final String TESTING = "TESTING";
   public final String MAINPATH = "MAINPATH";
   public final String TESTHTMLPATH = "TESTHTMLPATH";

   private String testing = null;
   private AbPath mainPath = null;
   //used to store newly generated pages before copied to specific storefront
   private AbPath testHtmlPath = null;
   
   private boolean hasRead = false;
 
   private InitInfo()
   {
	   
   }
   
   /*
   public InitData(HashMap hashMap)
   {
      try
      {
         this.testing = (String) hashMap.get(this.TESTING);
         this.mainPath = (String) hashMap.get(this.MAINPATH);
         this.testHtmlPath = (String) hashMap.get(this.TESTHTMLPATH);
         webappPath = (String) hashMap.get(WEBAPPPATH);
      }
      catch(Exception e)
      {
    
      }
   }
    */
   /*
   public InitData()
   {
      try
      {
         this.testing = "PathNotSet";
         this.mainPath = "PathNotSet";
         this.testHtmlPath = "PathNotSet";
         webappPath = "PathNotSet";
      }
      catch(Exception e)
      {
    
      }
   }
    */
   
   public synchronized void set() throws InitException
   {
      try
      {
         InitInfoEntity initInfoEntity = new InitInfoEntity();
         
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
         {         
            PreLogUtil.put("created entity","InitInfo","set()");
         }
         
         if(!initInfoEntity.is())
         {
            if( LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
            {      
               PreLogUtil.put("adding","InitInfo","set()");
            }
            initInfoEntity.add();
         }
         else
         {
            if( LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADER))
            {            
               PreLogUtil.put("update","InitInfo","set()");
            }
            initInfoEntity.update();
         }
         this.hasRead = false;
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
         {         
            PreLogUtil.putOE("error","InitInfo","set()",e);
         }
      }
   }
   
   public synchronized void set(HashMap hashMap)
   {
      try
      {
         this.testing = (String) hashMap.get(this.TESTING);
         this.mainPath = new AbPath((String) hashMap.get(this.MAINPATH), StringUtil.getInstance().EMPTY_STRING);
         this.testHtmlPath = new AbPath((String) hashMap.get(this.TESTHTMLPATH), StringUtil.getInstance().EMPTY_STRING);
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
         {         
            PreLogUtil.putOE("error","InitInfo","set()",e);
         }
      }
   }
   
   private synchronized void get()
   {
      try
      {
         this.testing = null;
         this.mainPath = null;
         this.testHtmlPath = null;
         
         InitInfoEntity initInfoEntity = new InitInfoEntity();
         initInfoEntity.get();
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
         {         
            PreLogUtil.putOE("error","InitInfo","set()",e);
         }
         return;
      }
   }
   
   public synchronized void setHasRead(boolean value)
   {
      this.hasRead = value;
   }
   
   private synchronized void updateIfNeeded()
   {
      try
      {
         if(!this.hasRead)
         {
            this.hasRead = true;
            this.get();
         }
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
         {         
            PreLogUtil.putOE("error","InitInfo","updateIfNeeded()",e);
         }
         return;
      }
   }
   
   public boolean isTesting()
   {
      try
      {
         this.updateIfNeeded();
         return BooleanUtil.getInstance().getFromString(this.testing);
      }
      catch(Exception e)
      {
         if(LogConfigTypes.LOGGING.contains(LogConfigTypeFactory.getInstance().PRELOADERERROR))
         {         
            PreLogUtil.putOE("error","InitInfo","updateIfNeeded()",e);
         }
         return false;
      }
   }

   public String getTesting()
   {
      this.updateIfNeeded();
      return this.testing;
   }
   
   public String getTestHtmlPath()
   {
      this.updateIfNeeded();
      if(this.testHtmlPath != null)
      {
         return this.testHtmlPath.toString();
      }
      else
      {
	 return null;
      }
   }
   
   public String getMainPath()
   {
      this.updateIfNeeded();
      if(this.mainPath != null)
      {
         return this.mainPath.toString();
      }
      else
      {
	 return null;
      }
   }
   
   public void setTesting(String value)
   {
      this.testing = value;
   }
   
   public void setTestHtmlPath(AbPath value)
   {
      this.testHtmlPath = value;
   }
   
   public void setMainPath(AbPath value)
   {
      this.mainPath = value;
   }

   public boolean isMainPathValid(AbPath abPath)
   {
       return true;
   }
   
   public boolean isTestHtmlPathValid(AbPath abPath)
   {
       return true;
   }
   
   public boolean isTestingValid(String testing) throws Exception
   {
      return BooleanUtil.getInstance().isStringBoolean(testing);
   }

   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      hashMap.put(this.TESTING, this.getTesting());
      hashMap.put(this.MAINPATH, this.getMainPath());
      hashMap.put(this.TESTHTMLPATH, this.getTestHtmlPath());
      return hashMap;
   }
}
