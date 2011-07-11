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

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- -->
    <xsl:template match="/html" 
       xmlns:jsp="http://java.sun.com/JSP/Page" >
       <xsl:for-each select="en" >
          <xsl:for-each select="US" >

<jsp:forward page="eProcessingNetwork_AuthorizeNet_SIM_EmulationShipping.jsp" />
          
          </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
