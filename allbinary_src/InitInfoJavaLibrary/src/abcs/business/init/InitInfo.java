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
package abcs.business.init;

import java.util.HashMap;

import abcs.logic.java.bool.BooleanUtil;

import abcs.logic.basic.path.AbPath;

import abcs.logic.basic.io.file.directory.Directory;

import abcs.logic.communication.log.PreLogUtil;

public class InitInfo
{
	private static final InitInfo instance = new InitInfo();
	
	public static InitInfo getInstance() {
		return instance;
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
         testing = (String) hashMap.get(TESTING);
         mainPath = (String) hashMap.get(MAINPATH);
         testHtmlPath = (String) hashMap.get(TESTHTMLPATH);
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
         testing = "PathNotSet";
         mainPath = "PathNotSet";
         testHtmlPath = "PathNotSet";
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
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADER))
         {         
            PreLogUtil.put("created entity","InitInfo","set()");
         }
         
         if(!initInfoEntity.is())
         {
            if( abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADER))
            {      
               PreLogUtil.put("adding","InitInfo","set()");
            }
            initInfoEntity.add();
         }
         else
         {
            if( abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADER))
            {            
               PreLogUtil.put("update","InitInfo","set()");
            }
            initInfoEntity.update();
         }
         hasRead = false;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {         
            PreLogUtil.put("error","InitInfo","set()",e);
         }
      }
   }
   
   public synchronized void set(HashMap hashMap)
   {
      try
      {
         testing = (String) hashMap.get(TESTING);
         mainPath = new AbPath((String) hashMap.get(MAINPATH));
         testHtmlPath = new AbPath((String) hashMap.get(TESTHTMLPATH));
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {         
            PreLogUtil.put("error","InitInfo","set()",e);
         }
      }
   }
   
   private synchronized void get()
   {
      try
      {
         testing = null;
         mainPath = null;
         testHtmlPath = null;
         
         InitInfoEntity initInfoEntity = new InitInfoEntity();
         initInfoEntity.get();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {         
            PreLogUtil.put("error","InitInfo","set()",e);
         }
         return;
      }
   }
   
   public synchronized void setHasRead(boolean value)
   {
      hasRead = value;
   }
   
   private synchronized void updateIfNeeded()
   {
      try
      {
         if(!hasRead)
         {
            hasRead = true;
            this.get();
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {         
            PreLogUtil.put("error","InitInfo","updateIfNeeded()",e);
         }
         return;
      }
   }
   
   public boolean isTesting()
   {
      try
      {
         this.updateIfNeeded();
         return BooleanUtil.getInstance().getFromString(testing);
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.PRELOADERERROR))
         {         
            PreLogUtil.put("error","InitInfo","updateIfNeeded()",e);
         }
         return false;
      }
   }

   public String getTesting()
   {
      this.updateIfNeeded();
      return testing;
   }
   
   public String getTestHtmlPath()
   {
      this.updateIfNeeded();
      if(testHtmlPath != null)
      {
         return testHtmlPath.toString();
      }
      else
      {
	 return null;
      }
   }
   
   public String getMainPath()
   {
      this.updateIfNeeded();
      if(mainPath != null)
      {
         return mainPath.toString();
      }
      else
      {
	 return null;
      }
   }
   
   public void setTesting(String value)
   {
      testing = value;
   }
   
   public void setTestHtmlPath(AbPath value)
   {
      this.testHtmlPath = value;
   }
   
   public void setMainPath(AbPath value)
   {
      this.mainPath = value;
   }

   public boolean isTestingValid(String testing) throws Exception
   {
      return BooleanUtil.getInstance().isStringBoolean(testing);
   }
   
   public boolean isTestHtmlPathValid(AbPath value)
   {
      return Directory.create(value);
   }
   
   public boolean isMainPathValid(AbPath value)
   {
      return Directory.create(value);      
   }

   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();
      hashMap.put(TESTING, this.getTesting());
      hashMap.put(MAINPATH, this.getMainPath());
      hashMap.put(TESTHTMLPATH, this.getTestHtmlPath());
      return hashMap;
   }
}
