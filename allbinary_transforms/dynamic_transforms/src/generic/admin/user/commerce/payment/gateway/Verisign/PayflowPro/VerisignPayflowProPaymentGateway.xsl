<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

   <xsl:template name="VerisignPayflowProPaymentGateway" >
      <xsl:param name="paymentGateway" />
      
      <xsl:for-each select="$paymentGateway">

      <xsl:variable name="paymentGatewayName" select="'PAYMENTGATEWAY_NAME'"/>
      <xsl:variable name="paymentGatewayNameValue" select="PAYMENTGATEWAY_NAME"/>
      <xsl:variable name="paymentGatewayValue" select="PAYMENTGATEWAY_VALUE"/>

<a href="http://www.verisign.com" ><xsl:value-of select="$paymentGatewayValue" /></a> Configuration:<p></p>
<p>You may obtain all of information required from your payment gateway service. 
  If you need help you may contact us at support@allbinary.com.</p>

Server Address is usually: payflow.verisign.com with port 443<br/>
Test Server Address is usually: test-payflow.verisign.com with port 443<br/>
  
<p>Required Fields:</p>

       <input class="text" name="{$paymentGatewayName}" value="{$paymentGatewayNameValue}" type="hidden" /> 
    <xsl:variable name="storeNameName" select="STOREFRONT_NAME/STOREFRONT_NAME/name"/>
    <xsl:variable name="storeNameValue" select="STOREFRONT_NAME/STOREFRONT_NAME/value"/>
       <input name="{$storeNameName}" value="{$storeNameValue}" type="hidden" /> 
       
<table class="table" width="500" border="0">
  <tr> 
    <td width="40%">User Name:</td>
    <td width="60%">              
    <xsl:variable name="name" select="CUSTOMER_USER_NAME/name"/>
    <xsl:variable name="value" select="CUSTOMER_USER_NAME/value"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>         
    </td>
  </tr>
  <tr> 
    <td>Password:</td>
    <td>
    <xsl:variable name="name" select="CUSTOMER_PASSWORD/name"/>
    <xsl:variable name="value" select="CUSTOMER_PASSWORD/value"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/>     
    </td>
  </tr>
  <tr> 
    <td>Partner:</td>
    <td>
    <xsl:variable name="name" select="'SPECIAL1'"/>
    <xsl:variable name="value" select="SPECIAL1"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Vendor:</td>
    <td>
    <xsl:variable name="name" select="'SPECIAL2'"/>
    <xsl:variable name="value" select="SPECIAL2"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Server Protocol:</td>
    <td>
    <xsl:variable name="name" select="'SERVERPROTOCOL'"/>
    <xsl:variable name="value" select="SERVERPROTOCOL"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Server Address:</td>
    <td>
    <xsl:variable name="name" select="'SERVER'"/>
    <xsl:variable name="value" select="SERVER"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Server Port:</td>
    <td>
    <xsl:variable name="name" select="'SERVERPORT'"/>
    <xsl:variable name="value" select="SERVERPORT"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Server Path:</td>
    <td>
    <xsl:variable name="name" select="'SERVERPATH'"/>
    <xsl:variable name="value" select="SERVERPATH" />
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
</table>
<p>Optional Fields:</p>
<table class="table" width="500" border="0">
  <tr> 
    <td width="40%">Mode:</td>
    <td width="60%">
    <xsl:variable name="name" select="'MODE'"/>
    <xsl:variable name="value" select="MODE"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Test Server Protocol:</td>
    <td>
    <xsl:variable name="name" select="'TESTSERVERPROTOCOL'"/>
    <xsl:variable name="value" select="TESTSERVERPROTOCOL"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Test Server Address:</td>
    <td>
    <xsl:variable name="name" select="'TESTSERVER'"/>
    <xsl:variable name="value" select="TESTSERVER"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Test Server Port:</td>
    <td>
    <xsl:variable name="name" select="'TESTPORT'"/>
    <xsl:variable name="value" select="TESTPORT"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Test Server Path:</td>
    <td>
    <xsl:variable name="name" select="'TESTSERVERPATH'"/>
    <xsl:variable name="value" select="TESTSERVERPATH"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Proxy Server Protocol:</td>
    <td>
    <xsl:variable name="name" select="'PROXYPROTOCOL'"/>
    <xsl:variable name="value" select="PROXYPROTOCOL"/>
       <input name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Proxy Server Address:</td>
    <td>
    <xsl:variable name="name" select="'PROXYSERVER'"/>
    <xsl:variable name="value" select="PROXYSERVER"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Proxy Server Port:</td>
    <td>
    <xsl:variable name="name" select="'PROXYPORT'"/>
    <xsl:variable name="value" select="PROXYPORT"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
  <tr> 
    <td>Proxy Server Path:</td>
    <td>
    <xsl:variable name="name" select="'PROXYPATH'"/>
    <xsl:variable name="value" select="PROXYPATH"/>
       <input class="text" name="{$name}" value="{$value}" type="text" size="48" maxlength="60"/> 
    </td>
  </tr>
</table>

      </xsl:for-each>
                     
   </xsl:template>    
</xsl:stylesheet>

