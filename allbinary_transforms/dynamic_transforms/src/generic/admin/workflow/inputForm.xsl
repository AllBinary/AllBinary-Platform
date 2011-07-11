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
      <xsl:template name="inputForm" >
      
         <xsl:variable name="nameName" select="WORKFLOW_NAME/name" />
         <xsl:variable name="nameValue" select="WORKFLOW_NAME/value" />

         <xsl:variable name="dataName" select="WORKFLOW_DATA/name" />
         <xsl:variable name="dataValue" select="WORKFLOW_DATA/value" />
         
         <xsl:variable name="command" select="REQUEST/AdminCommand/value" />

<table class="table" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="500">
  <tr>
    <td width="51%">Name:</td>
    <td width="49%">
<input class="text" type="text" name="{$nameName}" value="{$nameValue}" size="38" /></td>
  </tr>
  <tr>
    <td width="51%">Data:</td>
       <textarea name="{$dataName}" rows="10" 
          cols="48" wrap="virtual" >
          <xsl:value-of select="$dataValue" disable-output-escaping="yes"/>
       </textarea>
    <td width="49%">
    </td>
  </tr>
</table>

      </xsl:template>
</xsl:stylesheet> 
