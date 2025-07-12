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
   xmlns:jsp="http://java.sun.com/JSP/Page"
                xmlns:transform="/WEB-INF/transform.tld"
>

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:import href="/generic/imports/jsp/include/include.xsl" />
   <xsl:import href="/generic/imports/jsp/directive/include/include.xsl" />

   <xsl:import href="/template/generic/hedges/menu/menuCustomerStyleOnePreview.xsl" />

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
                     xmlns:transform="/WEB-INF/transform.tld"
                     version="1.2">

<xsl:call-template name="jspDirectiveIncludeInclude" >
   <xsl:with-param name="fileName" >
      globals
   </xsl:with-param>
</xsl:call-template>

<xsl:call-template name="jspInclude" >
   <xsl:with-param name="fileName" >
      javascriptPreview
   </xsl:with-param>
</xsl:call-template>

<table id="preview" class="border" 
   onClick="abScrollTo('body{$scrollName}');{$javascriptErrorControl}" >
   <tr>
      <td>

<table class="mainBackground" 
   onClick="abScrollTo('mainBackground{$scrollName}');{$javascriptErrorControl}" 
   width="99%" align="center" height="99%" >
<tr>
<td valign="top" height="100%" >

<table width="100%">
<tr>
<td valign="top" align="center">

<xsl:call-template name="jspDirectiveIncludeInclude" >
   <xsl:with-param name="fileName" >
      topbarSupportPreview
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

<tr>
<td valign="top">

<table width="100%">
<tr>

<td width="100" valign="top">

<xsl:call-template name="jspDirectiveIncludeInclude" >
   <xsl:with-param name="fileName" >
      leftProductsPreview
   </xsl:with-param>
</xsl:call-template>

</td>

<td valign="top">
<xsl:text disable-output-escaping="yes" >
   <![CDATA[
      <transform:component name="body"/>
   ]]>
</xsl:text>
</td>

<td width="100" valign="top">
   <xsl:call-template name="menuPreview" >
   </xsl:call-template>
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

<xsl:call-template name="jspDirectiveIncludeInclude" >
   <xsl:with-param name="fileName" >
      copyrightPreview
   </xsl:with-param>
</xsl:call-template>

</td>
</tr>

</table>

      </td>
   </tr>
</table>

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