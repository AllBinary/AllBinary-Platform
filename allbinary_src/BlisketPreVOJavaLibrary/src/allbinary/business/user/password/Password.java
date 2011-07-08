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
package allbinary.business.user.password;

import abcs.logic.basic.string.StringUtil;
import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.entry.EntryData;
import allbinary.business.user.UserData;
import allbinary.logic.control.crypt.SuperCrypt;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Password
{
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
      try
      {
         Boolean valid = Boolean.TRUE;
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VALIDATION))
         {
            LogUtil.put(LogFactory.getInstance("Password: " + this.password, this, "isValid()"));
         }

         if(!StringValidationUtil.getInstance().isValidRequired(this.password, 6, UserData.MAXLEN))
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VALIDATION))
            {
               LogUtil.put(LogFactory.getInstance("Password is invalid", this, "isValid()"));
            }
            valid = Boolean.FALSE;
         }

         return valid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VALIDATIONERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form","Password","isValid()",e));
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

      return "";
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
