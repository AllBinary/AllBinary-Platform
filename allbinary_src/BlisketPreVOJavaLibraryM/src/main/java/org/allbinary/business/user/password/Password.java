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
package org.allbinary.business.user.password;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.user.UserData;
import org.allbinary.logic.control.crypt.SuperCrypt;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import org.allbinary.business.entry.EntryData;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonStrings;

public class Password
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   //private static String className = "Password";

   private String password;
   
   public Password(String password)
   {
      this.password = password;
   }

   public void set(String value)
   {
      this.password = value;
   }

   public String get()
   {
      return this.password;
   }

   public Boolean isValid()
   {
       final CommonStrings commonStrings = CommonStrings.getInstance();

      try
      {
         Boolean valid = Boolean.TRUE;
         
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VALIDATION))
         {
            logUtil.put("Password: " + this.password, this, commonStrings.IS_VALID);
         }

         if(!StringValidationUtil.getInstance().isValidRequired(this.password, 6, UserData.MAXLEN))
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VALIDATION))
            {
               logUtil.put("Password is invalid", this, commonStrings.IS_VALID);
            }
            valid = Boolean.FALSE;
         }

         return valid;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VALIDATIONERROR))
         {
            logUtil.put("Failed to validate form","Password",commonStrings.IS_VALID,e);
         }
         return Boolean.FALSE;
      }
   }

   public String getValidationInfo()
   {
      if(!StringValidationUtil.getInstance().isValidRequired(this.password, 6, UserData.MAXLEN))
      {
         return "Please enter a Password with more than 6 characters.<br />";
      }

      final StringUtil stringUtil = StringUtil.getInstance();
      return stringUtil.EMPTY_STRING;
   }

   public Vector toVector(String secret)
   {
      this.password = StringUtil.getInstance().getInstance(this.password);

      int random = new Random().nextInt(SuperCrypt.KEYMAX);
      Vector vector = new Vector();
      vector.add(new Integer(random).toString());
      vector.add(secret);
      vector.add(new SuperCrypt(random).encrypt(password));
      return vector;
   }

   public HashMap toHashMap(String secret)
   {
      this.password = StringUtil.getInstance().getInstance(this.password);

      HashMap values = new HashMap();
      int random = new Random().nextInt(SuperCrypt.KEYMAX);
      values.put(EntryData.getInstance().ENCRYPTION, new Integer(random).toString());
      values.put(UserData.SECRET, secret);
      values.put(UserData.PASSWORD, new SuperCrypt(random).encrypt(this.password));
      return values;
   }
}
