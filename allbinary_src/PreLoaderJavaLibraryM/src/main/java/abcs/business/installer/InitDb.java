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
package abcs.business.installer;

import java.lang.reflect.Method;

import abcs.logic.system.security.licensing.LicensingException;

import abcs.business.init.db.DbConnectionInfo;

//Warning you must have sql root access
public class InitDb
{
   private Object object;
   
   public InitDb() throws LicensingException
   {
   }
   
   public InitDb(DbConnectionInfo dbConnectionInfo) throws LicensingException
   {
   }

   protected void setHelper(Object object)
   {
      this.object = object;
   }
   
   protected Object getHelper()
   {
      return this.object;
   }
   
   private String getHostName()
   {      
      try
      {
         Class dynamicClass = object.getClass();
         Method method = dynamicClass.getMethod("getHostName",null);
         
         String result = (String) method.invoke(object,null);
         return result;
      }
      catch(Exception e)
      {
         return null;
      }      
   }
      
   public boolean addUsers()
   {
      try
      {
         Class dynamicClass = object.getClass();
         Method method = dynamicClass.getMethod("addUsers",null);
         
         Boolean result = (Boolean) method.invoke(object,null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         return false;
      }
   }

   public boolean addDatabases()
   {
      try
      {
         Class dynamicClass = object.getClass();
         Method method = dynamicClass.getMethod("addDatabases",null);
         
         Boolean result = (Boolean) method.invoke(object,null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         return false;
      }
   }

   public boolean addTables()
   {
      try
      {
         Class dynamicClass = object.getClass();
         Method method = dynamicClass.getMethod("addTables",null);
         
         Boolean result = (Boolean) method.invoke(object,null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         return false;
      }
   }

   public boolean useTemporaryMainPath()
   {
      try
      {
         Class dynamicClass = object.getClass();
         Method method = dynamicClass.getMethod("useTemporaryMainPath",null);
         
         Boolean result = (Boolean) method.invoke(object,null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         return false;
      }
   }

   public boolean useNormalMainPath()
   {
      try
      {
         Class dynamicClass = object.getClass();
         Method method = dynamicClass.getMethod("useNormalMainPath",null);
         
         Boolean result = (Boolean) method.invoke(object,null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         return false;
      }
   }
   
}
