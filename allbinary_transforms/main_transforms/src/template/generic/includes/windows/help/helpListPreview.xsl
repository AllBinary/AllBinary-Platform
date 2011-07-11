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

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:import href="/template/generic/imports/encoding/specialEncoding.xsl" />
    
   <xsl:import href="/template/generic/buttons/itemPreview.xsl" />
   <xsl:import href="/template/generic/buttons/linksPreview.xsl" />
   <xsl:import href="/template/generic/imports/list/listPreview.xsl" />
    
   <xsl:output method="xml" indent="yes" />

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
            
<jsp:root 
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:jutil="/WEB-INF/jutil.tld"
   xmlns:admin="/WEB-INF/admin.tld"   
   xmlns:ecommerce="/WEB-INF/ecommerce.tld"   
   xmlns:generic="/WEB-INF/generic.tld"
   xmlns:payment="/WEB-INF/payment.tld"
   xmlns:transform="/WEB-INF/transform.tld"
   xmlns:transformInfoObjectConfig="/WEB-INF/transformInfoObjectConfig.tld"
   version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<table cellspacing="1"  cellpadding="1" >
<tr>
<td>
<xsl:call-template name="menuItemPreview" >
   <xsl:with-param name="name" >
      Help
   </xsl:with-param>
</xsl:call-template>
</td>
</tr>

<tr>
<td>
<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      TERMSANDCONDITIONSPAGE
   </xsl:with-param>
   <xsl:with-param name="name">
      Terms <xsl:call-template name="ampersandSymbol" /> Conditions
   </xsl:with-param>
</xsl:call-template>
</td>
</tr>

<tr>
<td>
<xsl:call-template name="smallAnchorItemPreview" >
   <xsl:with-param name="page">
      PRIVACYPAGE
   </xsl:with-param>
   <xsl:with-param name="name">
      Privacy
   </xsl:with-param>
</xsl:call-template>
</td>
</tr>
</table>

<xsl:for-each select="CATEGORIES_NAME" >
   <xsl:call-template name="listPreview" />
</xsl:for-each>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet> 
