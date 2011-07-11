
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!--    
    //LoginId
      return this.getPaymentGatewayInterface().getSpecial1();

   //TestServer
      return this.getPaymentGatewayInterface().getSpecial2();
   
   //showForm
   
X_LOGIN = "x_login";
//3.1
X_VERSION = "x_version";
//TRUE or FALSE
X_TEST_REQUEST = "x_test_request";

//PAYMENT_FORM
X_SHOW_FORM = "x_show_form";
X_RELAY_RESPONSE = "x_relay_response";
X_DUPLICATE_WINDOW = "x_duplicate_window";

X_HEADER_HTML_PAYMENT_FORM = "x_header_html_payment_form";
X_FOOTER_HTML_PAYMENT_FORM = "x_footer_html_payment_form";
X_HEADER_HTML_RECEIPT = "x_header_html_receipt";
X_FOOTER_HTML_RECEIPT = "x_footer_html_receipt";
X_RECEIPT_LINK_TEXT = "x_receipt_link_text";
X_RECEIPT_LINK_URL = "x_receipt_link_url";
X_LOGO_URL = "x_logo_url";
X_BACKGROUND_URL = "x_background_url";
X_COLOR_BACKGROUND = "x_color_background";
X_COLOR_LINK = "x_color_link";
X_COLOR_TEXT = "x_color_text";
X_RELAY_URL = "x_relay_url";
   -->

<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

   <xsl:template name="AuthorizeNetAIMPaymentGateway" >
      <xsl:param name="paymentGateway" />
      
      <xsl:for-each select="$paymentGateway">

      <xsl:variable name="paymentGatewayName" select="'PAYMENTGATEWAY_NAME'"/>
      <xsl:variable name="paymentGatewayNameValue" select="PAYMENTGATEWAY_NAME"/>
      <xsl:variable name="paymentGatewayValue" select="PAYMENTGATEWAY_VALUE"/>

<a href="http://www.authorize.net" ><xsl:value-of select="$paymentGatewayValue" /></a> Configuration:<p></p>

<p>You may obtain all of information required from your payment gateway service. 
  If you need help you may contact us at support@allbinary.com.</p>

<p>Required Fields:</p>

    <xsl:variable name="storeNameName" select="STOREFRONT_NAME/STOREFRONT_NAME/name" />
    <xsl:variable name="storeNameValue" select="STOREFRONT_NAME/STOREFRONT_NAME/value" />

       <input class="text" name="{$paymentGatewayName}" value="{$paymentGatewayNameValue}" type="hidden" /> 

<table class="table" width="500" border="0">

  <tr> 
    <td width="40%">Login Id:</td>
    <td width="60%">              
    <xsl:variable name="name" select="'x_login'"/>
    <xsl:variable name="value" select="x_login"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>         
    </td>
  </tr>

  <tr>
    <td width="40%">Transaction Key:</td>
    <td width="60%">              
    <xsl:variable name="name" select="'x_tran_key'"/>
    <xsl:variable name="value" select="x_tran_key"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>
    </td>
  </tr>

  <tr> 
    <td width="40%">Version:</td>
    <td width="60%">              
    <xsl:variable name="name" select="'x_version'"/>
    <xsl:variable name="value" select="x_version"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>         
    </td>
  </tr>

  <tr> 
    <td width="40%">Test Request:</td>
    <td width="60%">              
       <xsl:variable name="name" select="'x_test_request'"/>
       <xsl:variable name="value" select="x_test_request"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60" />
    </td>
  </tr>

  <tr> 
    <td width="40%">Mode:</td>
    <td width="60%">
    <xsl:variable name="name" select="'MODE'"/>
    <xsl:variable name="value" select="MODE"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>

  <tr>
    <td width="40%">Optional:</td>
  </tr>

  <tr> 
    <td width="40%">Delim Data:</td>
    <td width="60%">
    <xsl:variable name="name" select="'x_delim_data'"/>
    <xsl:variable name="value" select="x_delim_data"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>

  <tr>
    <td width="40%">Delim Char:</td>
    <td width="60%">
    <xsl:variable name="name" select="'x_delim_char'"/>
    <xsl:variable name="value" select="x_delim_char"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>

  <tr>
    <td width="40%">Encap Char:</td>
    <td width="60%">
    <xsl:variable name="name" select="'x_encap_char'"/>
    <xsl:variable name="value" select="x_encap_char"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>

  <tr>
    <td width="40%">Relay Response:</td>
    <td width="60%">
    <xsl:variable name="name" select="'x_relay_response'"/>
    <xsl:variable name="value" select="x_relay_response"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>         
    </td>
  </tr>
  
  <tr>
    <td width="40%">Duplicate Window:</td>
    <td width="60%">
    <xsl:variable name="name" select="'x_duplicate_window'"/>
    <xsl:variable name="value" select="x_duplicate_window"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>         
    </td>
  </tr>
   
</table>

         </xsl:for-each>

   </xsl:template>

</xsl:stylesheet>

