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
package org.allbinary.business.user.username;

import org.allbinary.logic.basic.string.StringUtil;
import org.allbinary.StringValidationUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.UserData;

import java.util.HashMap;

public class UserName
{
   private static final UserName instance = new UserName();

   //private static String className = "UserName";
   
   private String userName;

   private UserName()
   {
   }

   public UserName(HashMap hashMap)
   {
      this.userName = (String) hashMap.get(UserData.USERNAME);
   }

   /*
   public UserName()
   {
      this.userName = Ab
   }
   */

   public Boolean isValid()
   {
      try
      {
         return this.isValid(userName);
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form", this, "isUserNameValid()", e));
         }
         return Boolean.FALSE;
      }
   }

   public String get()
   {
      return this.userName;
   }
   
   public static Boolean isValid(String aUserName)
   {
      try
      {
         Boolean valid = Boolean.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VALIDATION))
         {
            LogUtil.put(LogFactory.getInstance("UserName: " + aUserName, instance, "isValid()"));
         }

         if(!StringValidationUtil.getInstance().isValidRequired(aUserName, 5, UserData.MAXLEN))
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.VALIDATION))
            {
               LogUtil.put(LogFactory.getInstance("UserName is invalid", instance, "isValid()"));
            }

            valid = Boolean.FALSE;
         }
                  
         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form", instance, "isValid()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   public static String getValidationInfo(String aUserName)
   {
      if(!StringValidationUtil.getInstance().isValidRequired(aUserName, 5, UserData.MAXLEN))
      {
         return "Please enter a User Name with more than 5 characters.<br/>";
      }
      return StringUtil.getInstance().EMPTY_STRING;
   }
   
}
