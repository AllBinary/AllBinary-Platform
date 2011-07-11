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

<xsl:output method="html" version="4.0" encoding="UTF-8" indent="yes"/>

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
         
            <xsl:for-each select="PAYMENTGATEWAYS_NAME">
Existing Gateways:<p/>

<form method="POST" action="storeAdmin.jsp">
<select class="select" name="PAYMENTGATEWAY_NAME" >
    <xsl:for-each select="PAYMENTGATEWAY_NAME" >
      <xsl:variable name="name" select="PAYMENTGATEWAY_NAME"/>
      <xsl:variable name="value" select="PAYMENTGATEWAY_VALUE"/>
         <option value="{$name}" >
         <xsl:value-of select="$value"/>
         </option>
    </xsl:for-each>
</select>

<xsl:text disable-output-escaping="yes" >
<![CDATA[
<input type="hidden" 
   value="PaymentGatewayTab" 
   name="TAB_COMMAND" />
]]>
</xsl:text>

<input class="submit" type="submit" 
   value="Edit" 
   name="AdminCommand" />
</form>

            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>

   </xsl:template>    
</xsl:stylesheet>

