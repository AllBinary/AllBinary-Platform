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

import java.util.HashMap;

import org.allbinary.business.user.UserData;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonStrings;

public class UserName
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   private static final UserName instance = new UserName();

   public static UserName getInstance() {
       return instance;
   }

   //private String className = "UserName";
   
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
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put("Failed to validate form", this, "isUserNameValid()", e);
         }
         return Boolean.FALSE;
      }
   }

   public String get()
   {
      return this.userName;
   }
   
   public Boolean isValid(String aUserName)
   {
       final CommonStrings commonStrings = CommonStrings.getInstance();

      try
      {
         final BooleanFactory booleanFactory = BooleanFactory.getInstance();
         Boolean valid = booleanFactory.TRUE;

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VALIDATION))
         {
            logUtil.put("UserName: " + aUserName, this, commonStrings.IS_VALID);
         }

         if(!StringValidationUtil.getInstance().isValidRequired(aUserName, 5, UserData.MAXLEN))
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VALIDATION))
            {
               logUtil.put("UserName is invalid", this, commonStrings.IS_VALID);
            }

            valid = booleanFactory.FALSE;
         }
                  
         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().SQLTAGSERROR))
         {
            logUtil.put("Failed to validate form", this, commonStrings.IS_VALID,e);
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
