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
package org.allbinary.business.user.commerce.money.payment.gateway.modules.processor.authorizenet.sim;

/**
 *
 * @author tberthel
 */
public class AuthorizeNetSimData
{
   /** Creates a new instance of AuthorizeNetSimData */
   private AuthorizeNetSimData()
   {
   }

   public static final String X_FP_HASH = "x_fp_hash";
   public static final String X_FP_SEQUENCE = "x_fp_sequence";
   public static final String X_FP_TIMESTAMP = "x_fp_timestamp";
   
   public static final String X_SHOW_FORM = "x_show_form";

   public static final String X_HEADER_HTML_PAYMENT_FORM = "x_header_html_payment_form";
   public static final String X_FOOTER_HTML_PAYMENT_FORM = "x_footer_html_payment_form";
   
   public static final String X_HEADER_HTML_RECEIPT = "x_header_html_receipt";
   public static final String X_FOOTER_HTML_RECEIPT = "x_footer_html_receipt";
   public static final String X_RECEIPT_LINK_TEXT = "x_receipt_link_text";
   public static final String X_RECEIPT_LINK_URL = "x_receipt_link_url";
   public static final String X_LOGO_URL = "x_logo_url";
   public static final String X_BACKGROUND_URL = "x_background_url";
   public static final String X_COLOR_BACKGROUND = "x_color_background";
   public static final String X_COLOR_LINK = "x_color_link";
   public static final String X_COLOR_TEXT = "x_color_text";
   public static final String X_RELAY_URL = "x_relay_url";
}
