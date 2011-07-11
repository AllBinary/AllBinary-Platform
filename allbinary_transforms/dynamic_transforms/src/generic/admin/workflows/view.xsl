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
      
   <xsl:for-each select="WORKFLOWS" >
            
<xsl:if test="not(WORKFLOW)" >
<div class="main">
No WorkFlows To List.
</div>
</xsl:if>

<p>WorkFlows:</p>
<div class="main">

<table class="table" border="1" cellpadding="0" cellspacing="0" width="500" >

<tr valign="top" >

<td>
<b>Name</b>
</td>      

</tr>
         
      <xsl:for-each select="WORKFLOW" >
      
         <xsl:variable name="nameName" select="WORKFLOW_NAME/name" />
         <xsl:variable name="nameValue" select="WORKFLOW_NAME/value" />
         
<tr valign="top" >

<td>
<a href="workFlowManager.jsp?AdminCommand=Edit&#38;{$nameName}={$nameValue}" >
<xsl:value-of select="$nameValue" disable-output-escaping="yes" />
</a>
</td>      

</tr>

      </xsl:for-each>
</table>      
<br></br>

</div>

   </xsl:for-each>
   </xsl:for-each>
   </xsl:for-each>      
   
   </xsl:template>
</xsl:stylesheet> 
