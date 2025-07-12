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

    <xsl:template name="menuItem"
                  xmlns:jutil="/WEB-INF/jutil.tld">
      <xsl:param name="name"/>
      
      <jutil:element name="div">
      <jutil:attribute name="ID"><xsl:value-of select="$name" /></jutil:attribute>
      <jutil:attribute name="class">menuItem</jutil:attribute>

      <xsl:value-of select="$name" />
      </jutil:element>
            
   </xsl:template>   

</xsl:stylesheet> 