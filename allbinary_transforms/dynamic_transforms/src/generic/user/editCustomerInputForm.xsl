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

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
   <xsl:import href="/generic/imports/buttons/buttons.xsl" />
   <xsl:import href="/generic/customer/customerInputForm.xsl" />

   <xsl:output method="html"/>

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

   
      <xsl:for-each select="USER_NAME" >

<form name="Update" method="POST" action="CustomerProfile.jsp" >

   <xsl:call-template name="customerInputForm" />
         
<p />
   
   <xsl:call-template name="hiddenSubmitCommand" />

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      Update
   </xsl:with-param>
   <xsl:with-param name="command">
      Update
   </xsl:with-param>
</xsl:call-template>
   
</form>

      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>            
                        
   </xsl:template>
</xsl:stylesheet> 
