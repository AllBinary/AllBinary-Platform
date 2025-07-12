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

               <xsl:variable name="paymentTransactionFpTimeStamp" select="x_fp_timestamp/value" />
               <xsl:variable name="paymentTransactionFpSequence" select="x_fp_sequence/value" />
               <xsl:variable name="paymentTransactionFpHash" select="x_fp_hash/value" />

               <xsl:variable name="orderId" select="ORDERHISTORY/ORDER/ORDER_ID/value" />

               <xsl:variable name="orderTotal" select="ORDERHISTORY/ORDERHISTORY_TOTAL/value" />

               <xsl:variable name="email" select="EMAIL_NAME" />

               <xsl:variable name="paymentGatewayLoginId" select="PAYMENTGATEWAY_NAME/x_login" />
               <xsl:variable name="paymentGatewayTestServer" select="PAYMENTGATEWAY_NAME/SERVER2" />
               <xsl:variable name="paymentGatewayShowForm" select="PAYMENTGATEWAY_NAME/x_show_form" />
               <xsl:variable name="paymentGatewayServer" select="PAYMENTGATEWAY_NAME/SERVER" />
               <xsl:variable name="paymentGatewayMode" select="PAYMENTGATEWAY_NAME/MODE" />

               <xsl:variable name="customerUserName" select="CUSTOMER/CUSTOMER_USER_NAME/value" />

               <xsl:variable name="FINISH" select="'Finish'" />
               <xsl:variable name="FINISHNAME" select="'FinishWithAuthorizeNetSimName'" />

            <xsl:variable name="bodyHeading" select="'Payments'" />

<form name="{$FINISHNAME}" action="{$paymentGatewayServer}" method="POST">

<input type="hidden" name="x_login" value="{$paymentGatewayLoginId}" />
<!-- input type="hidden" name="x_currency_code" value="$currencycode" /> -->

<INPUT type="hidden" name="x_version" value="3.1" />
<INPUT type="hidden" name="x_show_form" value="PAYMENT_FORM" />
<input type="hidden" name="x_amount" value="{$orderTotal}" />
<input type="hidden" name="x_cust_id" value="{$customerUserName}" />
<INPUT type="hidden" name="x_test_request" value="TRUE" />

<input type="hidden" name="x_description" value="Order Number: {$orderId} - Order item(s) info can be obtained in the account section of the home site" />
<input type="hidden" name="x_invoice_num" value="{$orderId}" />

<input type="hidden" name="x_fp_timestamp" value="{$paymentTransactionFpTimeStamp}" />
   
<input type="hidden" name="x_fp_sequence" value="{$paymentTransactionFpSequence}" />

<input type="hidden" name="x_fp_hash" value="{$paymentTransactionFpHash}" />

<input type="hidden" name="AdminCommand" value="{$FINISHNAME}" /> 

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$FINISHNAME" />
   </xsl:with-param>
   <xsl:with-param name="value">
      <xsl:value-of select="$FINISH" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$FINISHNAME" />
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