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

   <xsl:import href="/template/generic/buttons/buttonsPreview.xsl" />

   <xsl:output method="xml" indent="yes" />

   <xsl:template match="/html" >
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     xmlns:jutil="/WEB-INF/jutil.tld"
                     version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */ 
</jsp:scriptlet>

<table cellspacing="1" cellpadding="1">
   <tr>
      <td>
<jutil:element name="form">

<xsl:call-template name="submitMenuButtonItemPreview">
   <xsl:with-param name="name">
      PRODUCTSEARCHNAME
   </xsl:with-param>
   <xsl:with-param name="value">
      SEARCH
   </xsl:with-param>
   <xsl:with-param name="command">
      PRODUCTSEARCHNAME
   </xsl:with-param>
</xsl:call-template>

</jutil:element>

      </td>
   </tr>
</table>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet> 
