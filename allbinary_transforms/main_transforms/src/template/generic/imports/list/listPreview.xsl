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
   <xsl:import href="/template/generic/buttons/itemPreview.xsl" />
   <xsl:import href="/template/generic/buttons/linksPreview.xsl" />

   <xsl:template name="listPreview">
   
<table cellspacing="1"  cellpadding="1">

<tr>
<td>

<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      None
   </xsl:with-param>
   <xsl:with-param name="name">
      Preview Item 1
   </xsl:with-param>
</xsl:call-template>
<br/>
<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      None
   </xsl:with-param>
   <xsl:with-param name="name">
      Preview Item 2
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

</table>
   
   </xsl:template> 

   
   <xsl:template name="listAndMenuItemPreview">
   <xsl:param name="label"/>
   
<table cellspacing="1"  cellpadding="1">
<tr>
<td>

<xsl:call-template name="menuItemPreview">
   <xsl:with-param name="name">
      Category 1
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>
</table>

<xsl:call-template name="listPreview" />

<table cellspacing="1"  cellpadding="1">
<tr>
<td>

<xsl:call-template name="menuItemPreview">
   <xsl:with-param name="name">
      Category 2
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>
</table>

<xsl:call-template name="listPreview" />

   </xsl:template> 

</xsl:stylesheet> 
