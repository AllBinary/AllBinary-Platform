<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
   
   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
            <xsl:for-each select="ABOUT_NAME" >
            <xsl:variable name="aboutText" select="ABOUT_TEXT" />

<table class="table" >

  <tr>
    <td width="51%">Text:</td>
    <td width="49%">
       <textarea class="text" name="%= AboutData.TEXT %" rows="10" 
          cols="48" wrap="virtual" >
          <xsl:value-of select="$aboutText" disable-output-escaping="yes"/>
       </textarea>
    </td>
  </tr>

</table>

            </xsl:for-each>
         </xsl:for-each>
      </xsl:for-each>
   </xsl:template>

</xsl:stylesheet>