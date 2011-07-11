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
   <xsl:import href="/template/generic/buttons/item.xsl" />
   <xsl:import href="/template/generic/buttons/links.xsl" />

   <xsl:template name="list">
   
<table cellspacing="1"  cellpadding="1">

<xsl:for-each select="LIST_NAME" >
<xsl:variable name="ITEMNAME" select="NAME/value" />
<xsl:variable name="ITEMPAGE" select="PAGE/value" />
<tr>
<td>

<xsl:call-template name="smallAnchorItem" >
   <xsl:with-param name="page">
      <xsl:value-of select="$ITEMPAGE" />
   </xsl:with-param>
   <xsl:with-param name="name">
      <xsl:value-of select="$ITEMNAME" />
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>
</xsl:for-each>

</table>
   
   </xsl:template>

   
   <xsl:template name="listAndMenuItem">
   <xsl:param name="label"/>

      <xsl:for-each select="CATEGORIES_NAME" >
   
<table cellspacing="1"  cellpadding="1">
<tr>
<td>

<xsl:call-template name="menuItem">
   <xsl:with-param name="name">
      <xsl:value-of select="$label" />
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>
</table>

<xsl:call-template name="list" />

      </xsl:for-each>
   
   </xsl:template>

</xsl:stylesheet> 
