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

   <xsl:template name="lessThanSymbol" >
      <xsl:text disable-output-escaping="yes" ><![CDATA[&#60;]]></xsl:text>
   </xsl:template>
   
   <xsl:template name="trademarkSymbol" >
      <xsl:text disable-output-escaping="yes" ><![CDATA[&#8482;]]></xsl:text>
   </xsl:template>

   <xsl:template name="ampersandSymbol" >
      <xsl:text disable-output-escaping="yes" ><![CDATA[&#38;]]></xsl:text>
   </xsl:template>

   <xsl:template name="copyrightSymbol" >
      <xsl:text disable-output-escaping="yes" ><![CDATA[&#169;]]></xsl:text>
   </xsl:template>

</xsl:stylesheet>