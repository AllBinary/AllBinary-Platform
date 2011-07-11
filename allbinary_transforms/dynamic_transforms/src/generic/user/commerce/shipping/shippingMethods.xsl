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

<!--
Should probably have the customers last selection recorded        
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="html"/>
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

            <xsl:for-each select="SHIPPINGMETHODS_ORDERSUMMARIES" >   
Shipping Methods:<p></p>
               <xsl:for-each select="SHIPPINGMETHOD_ORDERSUMMARY" >
      
         <xsl:variable name="defaultShippingMethod" select="ENTRY_DEFAULT/value" />
         
         <xsl:variable name="subTotal" select="SHIPPINGMETHOD_SUBTOTAL/value" />
         <xsl:variable name="shippingCost" select="SHIPPINGMETHOD_SHIPPINGCOST/value" />
         <xsl:variable name="tax" select="SHIPPINGMETHOD_TAX/value" />
         <xsl:variable name="total" select="SHIPPINGMETHOD_TOTAL/value" />

         <xsl:for-each select="SHIPPINGMETHOD_NAME" >
         
            <xsl:variable name="shippingNameName" select="SHIPPINGMETHOD_NAME/name" />
            <xsl:variable name="shippingName" select="SHIPPINGMETHOD_NAME/value" />

         <xsl:if test="$defaultShippingMethod=$shippingName" >
<input type="radio" name="{$shippingNameName}" value="{$shippingName}" checked="true" />
         </xsl:if>
         <xsl:if test="$defaultShippingMethod!=$shippingName" >
<input type="radio" name="{$shippingNameName}" value="{$shippingName}" />
         </xsl:if>

<xsl:value-of select="$shippingName" />
         </xsl:for-each>  
<p></p>
<p>
<table class="table" cellpadding="3" >
<tr>
<td width="120" height="22" >
Sub Total: 
</td>
<td width="120" height="22" >
<xsl:value-of select="$subTotal" />
</td>
</tr>

<tr>
<td width="120" height="22" >
Shipping: 
</td>
<td width="120" height="22" >
<xsl:value-of select="$shippingCost" />
</td>
</tr>

<tr>
<td width="120" height="22" >
Tax: 
</td>
<td width="120" height="22" >
<xsl:value-of select="$tax" />
</td>
</tr>

<tr>
<td width="120" height="22" >
Total: 
</td>
<td width="120" height="22" >
<xsl:value-of select="$total" />
</td>
</tr>
</table>
</p>
      </xsl:for-each>     
   </xsl:for-each>     
   
   </xsl:for-each>
   </xsl:for-each>
          
   </xsl:template>
</xsl:stylesheet>