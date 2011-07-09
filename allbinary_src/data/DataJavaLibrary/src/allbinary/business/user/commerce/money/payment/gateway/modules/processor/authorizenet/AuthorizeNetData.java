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
package allbinary.business.user.commerce.money.payment.gateway.modules.processor.authorizenet;

/**
 *
 * @author tberthel
 */
public class AuthorizeNetData
{
   
   /** Creates a new instance of AuthorizeNetSimData */
   private AuthorizeNetData()
   {
   }
   
   //Required
   //20
   public static final String X_LOGIN = "x_login";
   
   //16
   public static final String X_TRAN_KEY = "x_tran_key";

   //len 3
   //values 2.5 3.0 3.1
   public static final String X_VERSION = "x_version";

   //5
   //Testing only
   public static final String X_TEST_REQUEST = "x_test_request";

   //NA on len
   public static final String X_RELAY_RESPONSE = "x_relay_response";
   
   //defaults to 120 i.e. 2 minutes before a duplicate transaction is allowed
   //0-28800 or 0 - 8 hours
   public static final String X_DUPLICATE_WINDOW = "x_duplicate_window";

   //Normal Customer Data
   //50
   public static final String X_FIRST_NAME = "x_first_name";
   //50
   public static final String X_LAST_NAME = "x_last_name";
   //50
   public static final String X_COMPANY = "x_company";
   //60
   public static final String X_ADDRESS = "x_address";
   //40
   public static final String X_CITY = "x_city";
   //40
   //Verified when passed
   public static final String X_STATE = "x_state";
   //20
   public static final String X_ZIP = "x_zip";
   //60
   //Verified when passed
   public static final String X_COUNTRY = "x_country";
   //25
   //Format: (111)111-2222
   public static final String X_PHONE = "x_phone";
   //25
   public static final String X_FAX = "x_fax";
   //End Customer Data

   //Extra Customer Data
   //20
   public static final String X_CUST_ID = "x_cust_id";
   //15
   public static final String X_CUSTOMER_IP = "x_customer_ip";
   //9
   public static final String X_CUSTOMER_TAX_ID = "x_customer_tax_id";
   //End Extra Customer Data
  
   //Email
   //TRUE to send notification email
   //FALSE to send notification email
   //5
   public static final String X_EMAIL_CUSTOMER = "x_email_customer";
   //255
   public static final String X_EMAIL = "x_email";
   public static final String X_EMAIL_MERCHANT = "x_email_merchant";

   //20
   public static final String X_INVOICE_NUM = "x_invoice_num";
   //255
   public static final String X_DESCRIPTION = "x_description";
   

   //50
   public static final String X_SHIP_TO_FIRST_NAME = "x_ship_to_first_name";
   //50
   public static final String X_SHIP_TO_LAST_NAME = "x_ship_to_last_name";
   //50
   public static final String X_SHIP_TO_COMPANY = "x_ship_to_company";
   //60
   public static final String X_SHIP_TO_ADDRESS = "x_ship_to_address";
   //40
   public static final String X_SHIP_TO_CITY = "x_ship_to_city";
   //40
   public static final String X_SHIP_TO_STATE = "x_ship_to_state";
   //20
   public static final String X_SHIP_TO_ZIP = "x_ship_to_zip";
   //60
   public static final String X_SHIP_TO_COUNTRY = "x_ship_to_country";
   
   public static final String X_AMOUNT = "x_amount";   

   //Optional Transaction Data
   //3
   public static final String X_CURRENCY_CODE = "x_currency_code";
   
   //Default CC for Credit Card
   public static final String X_METHOD = "x_method";

   //Type values = AUTH_CAPTURE, AUTH_ONLY, CAPTURE_ONLY, CREDIT, VOID, PRIOR_AUTH_CAPTURE
   public static final String X_TYPE = "x_type";
   
   //3 = yes or no also required if echeck_type = WEB
   public static final String X_RECURRING_BILLING = "x_recurring_billing";
      
   //For eCheck
   public static final String X_BANK_ABA_CODE = "x_bank_aba_code";
   public static final String X_BANK_ACCT_NUM = "x_bank_acct_num";
   public static final String X_BANK_ACCT_TYPE = "x_bank_acct_type";
   public static final String X_BANK_NAME = "x_bank_name";
   public static final String X_BANK_ACCT_NAME = "x_bank_acct_name";
   //CCD, PPD, TEL, WEB
   public static final String X_ECHECK_TYPE = "x_echeck_type";
   
   //Required for CC   
   public static final String X_CARD_NUM = "x_card_num";
   public static final String X_EXP_DATE = "x_exp_date";
      
   //3 or 4 - Optional for credit card - cvv2 or cvc2 or cid
   //The number on the back of the card
   public static final String X_CARD_CODE = "x_card_code";
      
   //10 - Required if type = CREDIT, VOID, or PRIOR_AUTH_CAPTURE
   //The id of a prevous transaction
   public static final String X_TRANS_ID = "x_trans_id";

   //6 it type = CAPTURE_ONLY
   public static final String X_AUTH_CODE = "x_auth_code";

   //Level 2 Data
   //25 - Purchase Order Number
   public static final String X_PO_NUM = "x_po_num";
   
   //15 - Tax Amount
   public static final String X_TAX = "x_tax";
   //5 - TRUE or FALSE
   public static final String X_TAX_EXEMPT = "x_tax_exempt";
   //10 - freight
   public static final String X_FREIGHT = "x_freight";
   //10
   public static final String X_DUTY = "x_duty";

}
