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
   xmlns:jsp="http://java.sun.com/JSP/Page" >
   <xsl:import href="/template/generic/hedges/menu/menuCustomerStyleOne.xsl" />
    <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
         
<xsl:text disable-output-escaping="yes" >
<![CDATA[
<xsl:stylesheet version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   xmlns:jsp="http://java.sun.com/JSP/Page" >
   
   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
]]>
</xsl:text>

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     xmlns:jutil="/WEB-INF/jutil.tld"
                     xmlns:transform="/WEB-INF/transform.tld"
                     version="1.2">
<jsp:directive.page contentType="text/html"/>
<HTML>
<HEAD>
<jsp:directive.include file="globals.jsp" />
<jsp:directive.include file="metatags.jsp" />
<TITLE>

<xsl:text disable-output-escaping="yes" >
<![CDATA[
            <xsl:for-each select="PAGE_NAME" >
            <xsl:variable name="pageTitle" select="TITLE_NAME/TITLE_TEXT/value" />
<xsl:value-of select="$pageTitle" />
            </xsl:for-each>
]]>
</xsl:text>

</TITLE>
<jsp:directive.include file="javascript.jsp" />
<jsp:directive.include file="css.jsp" />
</HEAD>

<jutil:element name="BODY">
<jutil:attribute name="class">
body
</jutil:attribute>
<jutil:attribute name="onload">
init()
</jutil:attribute>

<table class="border" width="100%" align="center" height="100%" >
<tr>
<td valign="top" height="100%" >

<table class="mainBackground" width="99%" align="center" height="99%">
<tr>
<td valign="top" height="100%">

<table width="100%">
<tr>
<td valign="top" align="center">
<jsp:directive.include file="topbarAll.jsp" />
</td>
</tr>

<tr>
<td valign="top">

<table width="100%">
<tr>

<td width="100" valign="top">
</td>

<td valign="top">
<xsl:text disable-output-escaping="yes" >
   <![CDATA[
      <transform:component name="body"/>
   ]]>
</xsl:text>
</td>

<td width="100" valign="top">
</td>

</tr>

</table>

</td>
</tr>
</table>

</td>
</tr>

<tr>
<td valign="top" align="center" >
<jsp:directive.include file="copyright.jsp"/>

</td>
</tr>
</table>

</td>
</tr>
</table>

</jutil:element>

</HTML>
</jsp:root>

<xsl:text disable-output-escaping="yes" >
<![CDATA[
         </xsl:for-each>
       </xsl:for-each>                     
    </xsl:template>
</xsl:stylesheet> 
]]>
</xsl:text>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>
</xsl:stylesheet> 