<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

   <xsl:template name="PaypalBasicPaymentGateway" >
      <xsl:param name="paymentGateway" />
      
      <xsl:for-each select="$paymentGateway">

      <xsl:variable name="paymentGatewayName" select="'PAYMENTGATEWAY_NAME'"/>
      <xsl:variable name="paymentGatewayNameValue" select="PAYMENTGATEWAY_NAME"/>
      <xsl:variable name="paymentGatewayValue" select="PAYMENTGATEWAY_VALUE"/>
      
<a href="http://www.paypal.com" ><xsl:value-of select="$paymentGatewayValue" /></a> Configuration:<p></p>

<p>Use an email address that has been verified with your payment gateway service. 
  If you need help you may contact us at support@allbinary.com.</p>  
       <input class="text" name="{$paymentGatewayName}" value="{$paymentGatewayNameValue}" type="hidden" /> 
    <xsl:variable name="name" select="STOREFRONT_NAME/STOREFRONT_NAME/name"/>
    <xsl:variable name="value" select="STOREFRONT_NAME/STOREFRONT_NAME/value"/>
       <input name="{$name}" value="{$value}" type="hidden" /> 
<table class="table" width="500" border="0">
  <tr> 
    <td width="40%">Usually: https://www.paypal.com/cgi-bin/webscr</td>
  </tr>
  <tr>
    <td>
  <table class="table" width="500" border="0">    
  <tr> 
    <td width="40%">Server:</td>
    <td width="60%">              
    <xsl:variable name="name" select="'SERVER'"/>
    <xsl:variable name="value" select="SERVER"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>
    </td>
  </tr>  
  </table>
     </td>
  </tr>
  <tr> 
    <td width="40%">The email you use for your <a href="http://www.paypal.com" >Paypal</a> account</td>
  </tr>
  <tr>
    <td>
  <table class="table" width="500" border="0">  
  <tr>
    <td width="40%">Email Address:</td>
    <td width="60%">              
    <xsl:variable name="name" select="'SPECIAL1'"/>
    <xsl:variable name="value" select="SPECIAL1"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>     
    </td>
  </tr>
  </table>
    </td>
  </tr>  
</table>

         </xsl:for-each>

   </xsl:template>

</xsl:stylesheet>

