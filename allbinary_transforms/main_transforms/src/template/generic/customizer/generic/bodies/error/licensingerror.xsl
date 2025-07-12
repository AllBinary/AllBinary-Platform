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
            <xsl:for-each select="ERROR_DATA" >
            <xsl:variable name="errorText" select="ERROR_TEXT" />

<table class="table" >

  <tr>
    <td width="51%">Text:</td>
    <td width="49%">
       <textarea class="text" name="%= ErrorData.TEXT %" rows="10" 
          cols="48" wrap="virtual" >
          
          <xsl:if test="not($errorText)" >
             Please notify the site administrator.
          </xsl:if>
          <xsl:if test="$errorText" >
             <xsl:value-of select="$errorText" disable-output-escaping="yes"/>
          </xsl:if>
          
       </textarea>
    </td>
  </tr>

</table>
           </xsl:for-each>
         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>