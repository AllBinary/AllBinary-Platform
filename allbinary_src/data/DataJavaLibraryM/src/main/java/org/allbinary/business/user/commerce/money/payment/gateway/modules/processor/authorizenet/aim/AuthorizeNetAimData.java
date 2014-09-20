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
package org.allbinary.business.user.commerce.money.payment.gateway.modules.processor.authorizenet.aim;

/**
 *
 * @author tberthel
 */
public class AuthorizeNetAimData
{
   
   /** Creates a new instance of AuthorizeNetSimData */
   private AuthorizeNetAimData()
   {
   }

   //Start AIM Required
   
   //5
   public static final String X_DELIM_DATA = "x_delim_data";
   //End AIM Required
   
   //Optional
   //1
   public static final String X_DELIM_CHAR = "x_delim_char";
   //1
   public static final String X_ENCAP_CHAR = "x_encap_char";
   
   //Unk len - Required for AUTH_ONLY and AUTH_CAPTURE
   //For FDC Nashville and Vital
   public static final String X_AUTHENTICATION_INDICATOR = "x_authentication_indicator";
   //Unk len - Required for AUTH_ONLY and AUTH_CAPTURE
   public static final String X_CARDHOLDER_AUTHENTICATION_VALUE = "x_cardholder_authentication_value";
   
   
   //Wells Fargo Only
   //50 - when tax id and ssn are not provided
   //i.e. for all echecks that don't provide the customer_tax_id
   public static final String X_DRIVERS_LICENSE_NUM = "x_drivers_license_num";
   //2 -
   public static final String X_DRIVERS_LICENSE_STATE = "x_drivers_license_state";
   //unk len - format: YYYY-MM-DDD, YYYY/M/DD, MM/DD/YYYY, MM-DD-YYYY
   public static final String X_DRIVERS_LICENSE_DOB = "x_drivers_license_dob";

}
