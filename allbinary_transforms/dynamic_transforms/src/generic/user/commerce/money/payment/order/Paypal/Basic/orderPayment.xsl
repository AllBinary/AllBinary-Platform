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

            <xsl:for-each select="PAYMENTTRANSACTION_NAME" >
         
               <xsl:variable name="orderId" select="ORDERHISTORY/ORDER/ORDER_ID/value" />
         
               <xsl:variable name="orderTotal" select="ORDERHISTORY/ORDERHISTORY_TOTAL/value" />

               <xsl:variable name="email" select="EMAIL_NAME" />
               
               <xsl:variable name="paymentGatewayServer" select="PAYMENTGATEWAY_NAME/SERVER" />
               <xsl:variable name="paymentGatewayPaypalEmail" select="PAYMENTGATEWAY_NAME/SPECIAL1" />
               
               <xsl:variable name="customerMainEmail" select="CUSTOMER/CUSTOMER_MAIN_EMAIL/value" />
               
               <xsl:variable name="FINISHWITHPAYPAL" select="'Finish'" />
               <xsl:variable name="FINISHWITHPAYPALNAME" select="'FinishWithPaypalName'" />
               
            <xsl:variable name="bodyHeading" select="'Payments'" />

<form name="{$FINISHWITHPAYPALNAME}" method="POST" action="{$paymentGatewayServer}" >
   
<input type="hidden" name="cmd" value="_xclick" /> 

<input type="hidden" name="business" value="{$paymentGatewayPaypalEmail}" /> 

<input type="hidden" name="item_name" value="Order Number: {$orderId}" />

<input type="hidden" name="item_number" 
   value="Order item(s) info can be obtained in the account section of the home site" />
<input type="hidden" name="amount" value="{$orderTotal}" />

<input type="hidden" name="AdminCommand" /> 

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$FINISHWITHPAYPALNAME" />
   </xsl:with-param>
   <xsl:with-param name="value">
      <xsl:value-of select="$FINISHWITHPAYPAL" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$FINISHWITHPAYPALNAME" />
   </xsl:with-param>
</xsl:call-template>

</form>

<xsl:if test="$email" >
We have sent an e-mail Receipt to <xsl:value-of select="$customerMainEmail" />, but you can print or save this page for your records.<p/>
</xsl:if>

<xsl:if test="not($email)" >
You can print or save this page for your records.<p/>
</xsl:if>

            </xsl:for-each>
         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>