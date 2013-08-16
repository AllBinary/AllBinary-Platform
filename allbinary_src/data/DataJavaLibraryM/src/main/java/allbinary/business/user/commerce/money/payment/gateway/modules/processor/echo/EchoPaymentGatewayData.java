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
package allbinary.business.user.commerce.money.payment.gateway.modules.processor.echo;

public class EchoPaymentGatewayData
{
   private EchoPaymentGatewayData()
   {
   }
   
   //Validation Data
   public static String TRANSACTION_TYPE = "transaction_type";
   public static String MERCHANT_ECHO_ID = "merchant_echo_id";
   public static String ORDER_TYPE = "order_type";
   public static String MERCHANT_PIN = "merchant_pin";
   public static String ISP_ECHO_ID = "isp_echo_id";
   public static String ISP_PIN = "isp_pin";
   public static String BILLING_IP_ADDRESS = "billing_ip_address";
   public static String MERCHANT_EMAIL = "merchant_email";
   
   //Transaction Info
   public static String AUTHORIZATION = "authorization";
   public static String BILLING_PREFIX = "billing_prefix";
   public static String BILLING_NAME = "billing_name";
   public static String BILLING_FIRST_NAME = "billing_first_name";
   public static String BILLING_LAST_NAME = "billing_last_name";
   public static String BILLING_COMPANY_NAME = "billing_company_name";
   public static String BILLING_ADDRESS1 = "billing_address1";
   public static String BILLING_ADDRESS2 = "billing_address2";
   public static String BILLING_CITY = "billing_city";
   public static String BILLING_STATE = "billing_state";
   public static String BILLING_ZIP = "billing_zip";
   public static String BILLING_COUNTRY = "billing_country";
   public static String BILLING_PHONE = "billing_phone";
   public static String BILLING_FAX = "billing_fax";
   public static String BILLING_EMAIL = "billing_email";
   
   public static String CC_NUMBER = "cc_number";
   public static String CCEXP_MONTH = "ccexp_month";
   public static String CCEXP_YEAR = "ccexp_year";
   public static String CNP_RECURRING = "cnp_recurring";
   public static String cnp_security = "cnp_security";
   
   public static String COUNTER = "counter";
   public static String DEBUG = "debug";
   
   public static String EC_ACCOUNT = "ec_account";
   public static String EC_ACCOUNT_TYPE = "ec_account_type";
   public static String EC_ADDRESS1 = "ec_address1";
   public static String EC_ADDRESS2 = "ec_address2";
   public static String EC_BANK_NAME = "ec_bank_name";
   public static String EC_CITY = "ec_city";
   public static String EC_EMAIL = "ec_email";
   public static String EC_FIRST_NAME = "ec_first_name";
   public static String EC_ID_COUNTRY = "ec_id_country";
   public static String EC_ID_EXP_MM = "ec_id_exp_mm";
   public static String EC_ID_EXP_DD = "ec_id_exp_dd";
   public static String EC_ID_EXP_YY = "ec_id_exp_yy";
   public static String EC_ID_NUMBER = "ec_id_number";
   public static String EC_ID_STATE = "ec_id_state";
   public static String EC_ID_TYPE = "ec_id_type";
   public static String EC_LAST_NAME = "ec_last_name";
   public static String EC_BUSINESS_NAME = "ec_BUSINESS_NAME";
   public static String EC_LICENSE_NUMBER = "ec_LICENSE_NUMBER";
   public static String EC_LICENSE_STATE = "ec_LICENSE_STATE";
   public static String EC_MERCHANT_REF = "ec_MERCHANT_REF";
   public static String EC_NBDS_CODE = "ec_NBDS_CODE";
   public static String EC_OTHER_NAME = "ec_other_name";
   public static String EC_PAYEE = "ec_payee";
   public static String EC_PAYMENT_TYPE = "ec_payment_type";
   public static String EC_RT = "ec_rt";
   public static String EC_SERIAL_NUMBER = "ec_serial_number";
   public static String EC_STATE = "ec_state";
   public static String EC_TRANSACTION_DT = "ec_transaction_dt";
   public static String EC_ZIP = "ec_zip";
   
   public static String GRAND_TOTAL = "grand_total";
   public static String MERCHANT_TRACE_NBR = "merchant_trace_nbr";
   public static String ORDER_NUMBER = "order_number";
   
   public static String ORIGINAL_AMOUNT = "original_amount";
   public static String ORIGINAL_TRANDATE_MM = "original_trandate_mm";
   public static String ORIGINAL_TRANDATE_DD = "original_trandate_dd";
   public static String ORIGINAL_TRANDATE_YYYY = "original_trandate_yyyy";
   public static String ORIGINAL_REFERENCE = "original_reference";
   
   public static String PRODUCT_DESCRIPTION = "product_description";
   public static String PURCHASE_ORDER_NUMBER = "purchase_order_number";

   public static String SALES_TAX = "sales_tax";

}
