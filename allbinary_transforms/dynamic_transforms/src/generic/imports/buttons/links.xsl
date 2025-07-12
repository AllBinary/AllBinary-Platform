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

   <xsl:template name="smallAnchorItem"
   >
      <xsl:param name="name"/>
      <xsl:param name="page"/>      

      <a class="smallCloak" href="{$page}" ><xsl:value-of select="$name" /></a>

   </xsl:template>

    <xsl:template name="anchor"
    >
      <xsl:param name="name"/>
      <xsl:param name="page"/>      

      <a class="bodyCloak" href="{$page}" ><xsl:value-of select="$name" /></a>

   </xsl:template>

</xsl:stylesheet> 