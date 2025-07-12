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

   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
         <xsl:variable name="bodyHeading" select="''" />
         
<div class="mainHeading">
<p><xsl:value-of select="$bodyHeading" /></p>
<div class="main">

<xsl:text disable-output-escaping="yes" >
<![CDATA[

]]>
</xsl:text>

</div>
</div>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>