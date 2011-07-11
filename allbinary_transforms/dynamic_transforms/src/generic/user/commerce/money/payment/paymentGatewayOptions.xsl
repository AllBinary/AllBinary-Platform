
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

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

<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

            <xsl:for-each select="PAYMENTGATEWAYS_NAME">

               <xsl:if test="PAYMENTGATEWAY_NAME" >

<form method="POST" action="PaymentOptions.jsp" name="SelectPaymentGateway" >

    <xsl:for-each select="PAYMENTGATEWAY_NAME" >
       <xsl:variable name="defaultPaymentGateway" select="ENTRY_DEFAULT" />
       
       <xsl:variable name="name" select="PAYMENTGATEWAY_NAME"/>
       <xsl:variable name="value" select="PAYMENTGATEWAY_VALUE"/>
       
       <xsl:if test="$defaultPaymentGateway=$name" >
<input type="radio" name="PAYMENTGATEWAY_NAME" value="{$name}" checked="true" /><xsl:value-of select="$value" />
       </xsl:if>
       <xsl:if test="$defaultPaymentGateway!=$name" >
<input type="radio" name="PAYMENTGATEWAY_NAME" value="{$name}" /><xsl:value-of select="$value" />
       </xsl:if>

    </xsl:for-each>

<input type="hidden" name="AdminCommand" value="PAYMENTGATEWAY_NAME" />
<p/><p/>
<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      SelectPaymentGateway
   </xsl:with-param>
   <xsl:with-param name="value">
      Select
   </xsl:with-param>
   <xsl:with-param name="command">
      SelectPaymentGateway
   </xsl:with-param>
</xsl:call-template>

</form>
               </xsl:if>

               <xsl:if test="not(PAYMENTGATEWAY_NAME)" >
No payment options are currently available. Please contact the system administrator.<br></br>
              </xsl:if>

            </xsl:for-each>

         </xsl:for-each>
      </xsl:for-each>

   </xsl:template>    
</xsl:stylesheet>

