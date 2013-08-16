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
package allbinary.business.user;

public class UserData
{
   private UserData()
   {
   }
   
   public static int MAXLEN = 250;

   //public static final String CUSTOMERS = "CUSTOMERS";
   
   public static final String NAME = "USER_NAME";
   
   //user variables
   public static final String USERNAME = "CUSTOMER_USER_NAME";
   public static final String PASSWORD = "CUSTOMER_PASSWORD";   
   
   public static final String PREFIXNAME = "CUSTOMER_PREFIX_NAME";
   public static final String FIRSTNAME = "CUSTOMER_FIRST_NAME";
   public static final String LASTNAME = "CUSTOMER_LAST_NAME";
   public static final String MIDDLENAME = "CUSTOMER_MIDDLE_NAME";
   public static final String SUFFIXNAME = "CUSTOMER_SUFFIX_NAME";
   public static final String COMPANY = "CUSTOMER_COMPANY";
   public static final String POSITIONATCOMPANY = "CUSTOMER_POSITION_AT_COMPANY";
   public static final String MAINEMAIL = "CUSTOMER_MAIN_EMAIL";
   public static final String SECONDARYEMAIL = "CUSTOMER_SECONDARY_EMAIL";
   public static final String HOMEPHONE = "CUSTOMER_HOME_PHONE";
   public static final String CELLPHONE = "CUSTOMER_CELL_PHONE";
   public static final String WORKPHONE = "CUSTOMER_WORK_PHONE";
   public static final String OTHERCONTACT = "CUSTOMER_OTHER_CONTACT";
   public static final String ELECTRONICDEVICE = "CUSTOMER_ELECTRONIC_DEVICE";
   public static final String FAX = "CUSTOMER_FAX";
   
   public static final String SECRET = "CUSTOMER_SECRET";
   
   public static final String PERMISSIONS = "CUSTOMER_PERMISSIONS";
   
   //For rarely used data - should not be loaded for most user business objects in future orm imp
   public static final String CONFIGURATION = "CUSTOMER_CONFIGURATION";
}
