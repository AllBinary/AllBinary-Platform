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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
   <xsl:output method="html"/>
   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

      <xsl:for-each select="BASKET" >

         <xsl:variable name="basketSubTotal" select="BASKET_SUBTOTAL" />
         <xsl:variable name="basketItemTotal" select="SEARCHDATA_TOTALNUMBEROFITEMS" />
         
<table class="miniTable" border="1" cellspacing="1" cellpadding="1" width="100%" >

<tr valign="top" >
<td>
Unique Items:  <xsl:value-of select="$basketItemTotal" disable-output-escaping="yes"/>
</td>
</tr>

<tr valign="top" >
<td>
Total:  <xsl:value-of select="$basketSubTotal" disable-output-escaping="yes"/>
</td>
</tr>

</table>
      </xsl:for-each>
      
      </xsl:for-each>
      </xsl:for-each>
      
   </xsl:template>
</xsl:stylesheet> 
