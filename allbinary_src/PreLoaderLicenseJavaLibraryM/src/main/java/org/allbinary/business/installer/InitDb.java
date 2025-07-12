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
package org.allbinary.business.installer;

import java.lang.reflect.Method;

import org.allbinary.business.init.db.DbConnectionInfo;
import org.allbinary.logic.system.security.licensing.LicensingException;

//Warning you must have sql root access
public class InitDb
{
   private Object object;
   
   private final String METHOD_CALL_GET_HOST_NAME = "getHostName";
   private final String METHOD_CALL_ADD_USERS = "addUsers";
   private final String METHOD_CALL_ADD_DATABASES = "addDatabases";
   private final String METHOD_CALL_ADD_TABLES = "addTables";
   private final String METHOD_CALL_TEMP_MAIN_PATH = "useTemporaryMainPath";
   private final String METHOD_CALL_MAIN_PATH = "useNormalMainPath";
   
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
         Method method = dynamicClass.getMethod(METHOD_CALL_GET_HOST_NAME, null);
         
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
         Method method = dynamicClass.getMethod(METHOD_CALL_ADD_USERS, null);
         
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
         Method method = dynamicClass.getMethod(METHOD_CALL_ADD_DATABASES, null);
         
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
         Method method = dynamicClass.getMethod(METHOD_CALL_ADD_TABLES, null);
         
         Boolean result = (Boolean) method.invoke(object, null);
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
         Method method = dynamicClass.getMethod(METHOD_CALL_TEMP_MAIN_PATH, null);
         
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
         Method method = dynamicClass.getMethod(METHOD_CALL_MAIN_PATH, null);
         
         Boolean result = (Boolean) method.invoke(object,null);
         return result.booleanValue();
      }
      catch(Exception e)
      {
         return false;
      }
   }
   
}
