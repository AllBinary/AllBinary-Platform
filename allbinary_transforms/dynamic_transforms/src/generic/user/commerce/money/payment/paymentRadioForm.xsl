<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
   <xsl:import href="/generic/imports/buttons/buttons.xsl" />

   <xsl:output method="html"/>
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
   
      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePath" select="STOREFRONT_NAME/STOREFRONT_CURRENTHOSTNAMEPATH/value" />
   
      <xsl:variable name="storeCurrentHomeHostNameName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/name" />
      <xsl:variable name="storeCurrentHomeHostName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAME/value" />

      <xsl:variable name="storeCurrentHomeHostNamePathName" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHomeHostNamePath" select="STOREFRONT_NAME/CURRENTHOMEHOSTNAMEPATH/value" />

      <xsl:variable name="SELECTPAYMENTNAME" select="SELECTPAYMENT/name" />
      <xsl:variable name="SELECTPAYMENT" select="SELECTPAYMENT/value" />

      <xsl:variable name="REMOVEPAYMENTNAME" select="REMOVEPAYMENT/name" />
      <xsl:variable name="REMOVEPAYMENT" select="REMOVEPAYMENT/value" />

      <xsl:variable name="ADDNEWPAYMENTNAME" select="ADDNEWPAYMENT/name" />
      <xsl:variable name="ADDNEWPAYMENT" select="ADDNEWPAYMENT/value" />

      <xsl:for-each select="verisign/PAYMENT" >
            
         <xsl:variable name="paymentDefaultName" select="DEFAULT_PAYMENT/name" />
         <xsl:variable name="paymentDefault" select="DEFAULT_PAYMENT/value" />

         <xsl:variable name="paymentNameName" select="NAME/name" />
         <xsl:variable name="paymentName" select="NAME/value" />
         <xsl:variable name="paymentTypeName" select="TYPE/name" />
         <xsl:variable name="paymentType" select="TYPE/value" />
         <xsl:variable name="paymentExpirationName" select="EXPIRATION/name" />
         <xsl:variable name="paymentExpiration" select="EXPIRATION/value" />
         <xsl:variable name="paymentLastFourName" select="NUMBER/name" />
         <xsl:variable name="paymentLastFour" select="NUMBER/value" />
         <xsl:variable name="paymentTenderTypeName" select="TENDERTYPE/name" />
         <xsl:variable name="paymentTenderType" select="TENDERTYPE/value" />
         <xsl:variable name="paymentTransactionTypeName" select="TRANSACTIONTYPE/name" />
         <xsl:variable name="paymentTransactionType" select="TRANSACTIONTYPE/value" />
         <xsl:variable name="paymentAbaName" select="ABA/name" />
         <xsl:variable name="paymentAba" select="ABA/value" />
         <xsl:variable name="paymentAccountName" select="ACCOUNT/name" />
         <xsl:variable name="paymentAccount" select="ACCOUNT/value" />
         <xsl:variable name="paymentAccountTypeName" select="ACCOUNTTYPE/name" />
         <xsl:variable name="paymentAccountType" select="ACCOUNTTYPE/value" />
         <xsl:variable name="paymentAuthorizationCodeName" select="AUTHORIZATIONCODE/name" />
         <xsl:variable name="paymentAuthorizationCode" select="AUTHORIZATIONCODE/value" />
         <xsl:variable name="paymentCheckNumberName" select="CHECKNUMBER/name" />
         <xsl:variable name="paymentCheckNumber" select="CHECKNUMBER/value" />
         <xsl:variable name="paymentDriversLicenseName" select="DRIVERSLICENSE/name" />
         <xsl:variable name="paymentDriversLicense" select="DRIVERSLICENSE/value" />
         <xsl:variable name="paymentMagneticInkCheckReaderName" select="MAGNETICINKCHECKREADER/name" />
         <xsl:variable name="paymentMagneticInkCheckReader" select="MAGNETICINKCHECKREADER/value" />

            <xsl:if test="$paymentDefault!=$paymentDefaultName" >
<input type="radio" name="{$paymentNameName}" value="{$paymentName}" />
            </xsl:if>
            <xsl:if test="$paymentDefault=$paymentDefaultName" >            
<input type="radio" name="{$paymentNameName}" value="{$paymentName}" checked="true" />
name="INDEX"
            </xsl:if>
<br></br>
<xsl:value-of select="$paymentName" />
<br></br>
<xsl:value-of select="$paymentType" />
<br></br>
XXXXXXXXXXXX<xsl:value-of select="$paymentLastFour" />
<br></br>
<xsl:value-of select="$paymentExpiration" />
<br></br>
      </xsl:for-each>

   <xsl:call-template name="hiddenSubmitCommand" />

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$SELECTPAYMENTNAME" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$SELECTPAYMENT" />
   </xsl:with-param>
</xsl:call-template>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$REMOVEPAYMENTNAME" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$REMOVEPAYMENT" />
   </xsl:with-param>
</xsl:call-template>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$ADDNEWPAYMENTNAME" />
   </xsl:with-param>
   <xsl:with-param name="command">
      <xsl:value-of select="$ADDNEWPAYMENT" />
   </xsl:with-param>
</xsl:call-template>

         </xsl:for-each>
      </xsl:for-each>
            
   </xsl:template>
</xsl:stylesheet> 
