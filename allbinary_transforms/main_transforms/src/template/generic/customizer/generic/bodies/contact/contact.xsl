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
            <xsl:for-each select="CONTACT_NAME" >
            <xsl:variable name="contactName" select="CONTACT_NAME" />
            <xsl:variable name="contactCity" select="STREETADDRESS_CITY" />
            <xsl:variable name="contactState" select="STREETADDRESS_STATE" />
            <xsl:variable name="contactZip" select="STREETADDRESS_CODE" />
            <xsl:variable name="contactPhoneNumber" select="CUSTOMER_WORKPHONE" />

<table class="table" >

  <tr>
    <td width="51%">Name:</td>
    <td width="49%">
       <textarea class="text" name="%= ContactData.NAME %" rows="10" 
          cols="48" wrap="virtual" >
          <xsl:value-of select="$contactName" disable-output-escaping="yes"/>
       </textarea>
    </td>
  </tr>

  <tr>
    <td width="51%">City:</td>
    <td width="49%">
       <textarea class="text" name="%= StreetAddressData.CITY %" rows="10" 
          cols="48" wrap="virtual" >
          <xsl:value-of select="$contactCity" disable-output-escaping="yes"/>
       </textarea>
    </td>
  </tr>

  <tr>
    <td width="51%">State:</td>
    <td width="49%">
       <textarea class="text" name="%= StreetAddressData.STATE %" rows="10" 
          cols="48" wrap="virtual" >
          <xsl:value-of select="$contactState" disable-output-escaping="yes"/>
       </textarea>
    </td>
  </tr>

  <tr>
    <td width="51%">Zip:</td>
    <td width="49%">
       <textarea class="text" name="%= StreetAddressData.CODE %" rows="10" 
          cols="48" wrap="virtual" >
          <xsl:value-of select="$contactZip" disable-output-escaping="yes"/>
       </textarea>
    </td>
  </tr>

  <tr>
    <td width="51%">Phone Number:</td>
    <td width="49%">
       <textarea class="text" name="%= UserData.WORKPHONE %" rows="10" 
          cols="48" wrap="virtual" >
          <xsl:value-of select="$contactPhoneNumber" disable-output-escaping="yes"/>
       </textarea>
    </td>
  </tr>
        
</table>


            </xsl:for-each>
         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>