<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:import href="/generic/imports/buttons/buttons.xsl" />
   
   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
         
            <xsl:variable name="paymentGatewayServer" select="PAYMENTGATEWAY_NAME/SERVER" />
            <xsl:variable name="paymentGatewayPaypalEmail" select="PAYMENTGATEWAY_NAME/SPECIAL1" />

Please enter your invoice number or company name in the comments section.<p/>

<form name="MakePaymentName" method="POST" action="{$paymentGatewayServer}" >

<input type="hidden" name="cmd" value="_xclick" /> 

<input type="hidden" name="business" value="{$paymentGatewayPaypalEmail}" /> 

<table class="main" >
<tr>
<td>
Comments: 
</td>
<td>
<input type="text" name="item_name" value="" />
</td>
</tr>
<input type="hidden" name="item_number" 
  value="Make Payment Order item(s) Not Available" />
<tr>
<td>
Amount: 
</td>
<td>
<input type="text" name="amount" value="" />
</td>
</tr>
</table>
<br/>

<input type="hidden" name="AdminCommand" /> 

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      MakePaymentName
   </xsl:with-param>
   <xsl:with-param name="value">
      Make Payment
   </xsl:with-param>
   <xsl:with-param name="command">
      MakePaymentName
   </xsl:with-param>
</xsl:call-template>

</form>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>