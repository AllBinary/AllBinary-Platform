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

<!--

CurrentPage: <xsl:value-of select="PAGE/value" disable-output-escaping="yes" />
 Total Number Of Pages: <xsl:value-of select="TOTALNUMBEROFPAGES/value" disable-output-escaping="yes" />

Total Results:
<xsl:value-of select="TOTALNUMBEROFITEMS/value" disable-output-escaping="yes" /> 

Found <xsl:value-of select="TOTALNUMBEROFITEMS/value" disable-output-escaping="yes" /> Products 
On <xsl:value-of select="TOTALNUMBEROFPAGES/value" disable-output-escaping="yes" /> Pages

-->

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>

   <xsl:import href="/template/generic/pages/imports/globals/pages.xsl" />

   <xsl:import href="/generic/imports/buttons/buttons.xsl" />

   <xsl:template name="pageLinks" >

      <xsl:param name="storeCurrentHomeHostName" />
      <xsl:param name="storeCurrentHomeHostNamePath" />
      <xsl:param name="storeStaticPath" />
      <xsl:param name="baseFileName" />
      <xsl:param name="idUnique" />
        
      <xsl:variable name="totalNumberOfItems" select="SEARCHDATA_TOTALNUMBEROFITEMS/value" />
      
      <xsl:variable name="totalNumberOfPages" select="SEARCHDATA_TOTALNUMBEROFPAGES/value" />
            
      <xsl:variable name="currentPageName" select="SEARCHDATA_PAGE/name" />
      <xsl:variable name="currentPage" select="SEARCHDATA_PAGE/value" />
         
<xsl:if test="$currentPage > 1" >
<td>
<form id="backOnePageForm{$idUnique}" method="POST" action="{$storeCurrentHomeHostName}{$storeCurrentHomeHostNamePath}{$storeStaticPath}{$baseFileName}{$currentPage - 1}{$extension}" >
<div ID="backOnePage" class="pageLinkMouseOver"
   onMouseOver="setDomNodeIdStyleWithCssElement('backOnePage','.pageLinkMouseOver');return true;"
   onMouseOut="setDomNodeIdStyleWithCssElement('backOnePage','.pageLinkMouseOut');return true;"
   onClick="submit('backOnePageForm{$idUnique}')" >
&lt;
</div>
</form>
</td>
</xsl:if>

<xsl:if test="$currentPage = 1" >
<td>
<form id="backOnePageForm{$idUnique}" method="POST" action="{$storeCurrentHomeHostName}{$storeCurrentHomeHostNamePath}{$storeStaticPath}{$baseFileName}{$extension}" >
<div ID="backOnePage" class="pageLinkMouseOut"
   onMouseOver="setDomNodeIdStyleWithCssElement('backOnePage','.pageLinkMouseOver');return true;"
   onMouseOut="setDomNodeIdStyleWithCssElement('backOnePage','.pageLinkMouseOut');return true;"
   onClick="submit('backOnePageForm{$idUnique}')" >
&lt;
</div>
</form>
</td>
</xsl:if>

<xsl:for-each select="pageInfo" >
   <xsl:variable name="nextPage" select="name" />
   <xsl:variable name="staticFile" select="value" />
<td>
<form id="page{$nextPage}Form{$idUnique}" method="POST" action="{$storeCurrentHomeHostName}{$storeCurrentHomeHostNamePath}{$storeStaticPath}{$staticFile}{$extension}" >
<xsl:if test="$currentPage!=$nextPage" >
<div ID="page{$nextPage}" class="pageLinkMouseOut"
   onMouseOver="setDomNodeIdStyleWithCssElement('page{$nextPage}','.pageLinkMouseOver');return true;"
   onMouseOut="setDomNodeIdStyleWithCssElement('page{$nextPage}','.pageLinkMouseOut');return true;"
   onClick="submit('page{$nextPage}Form{$idUnique}')" >
   <xsl:value-of select="name + 1" />
</div>
</xsl:if>

<xsl:if test="$currentPage=$nextPage" >
<div ID="page{$nextPage}" class="currentDefaultButtonItem" >
   <xsl:value-of select="name + 1" />
</div>
</xsl:if>

</form>
</td>
</xsl:for-each>

<xsl:if test="$totalNumberOfPages>$currentPage + 1" >
<td>
<form id="forwardOnePageForm{$idUnique}" method="POST" action="{$storeCurrentHomeHostName}{$storeCurrentHomeHostNamePath}{$storeStaticPath}{$baseFileName}{$currentPage + 1}{$extension}" >
<div ID="forwardOnePage" class="pageLinkMouseOut"
   onMouseOver="setDomNodeIdStyleWithCssElement('forwardOnePage','.pageLinkMouseOver');return true;"
   onMouseOut="setDomNodeIdStyleWithCssElement('forwardOnePage','.pageLinkMouseOut');return true;"
   onClick="submit('forwardOnePageForm{$idUnique}')" >
&gt;
</div>
</form>
</td>
</xsl:if>

   </xsl:template>

   <xsl:template name="pageLinksTable" >
      <xsl:param name="storeCurrentHomeHostName" />
      <xsl:param name="storeCurrentHomeHostNamePath" />
      <xsl:param name="storeStaticPath" />
      <xsl:param name="baseFileName" />
      <xsl:param name="idUnique" />
   
<table class="table" >
<tr>
<td>
Pages:
</td>
<td>
<xsl:call-template name="pageLinks" >
   <xsl:with-param name="storeCurrentHomeHostName" >
      <xsl:value-of select="$storeCurrentHomeHostName" />
   </xsl:with-param>
   <xsl:with-param name="storeCurrentHomeHostNamePath" >
      <xsl:value-of select="$storeCurrentHomeHostNamePath" />
   </xsl:with-param>
   <xsl:with-param name="storeStaticPath" >
      <xsl:value-of select="$storeStaticPath" />
   </xsl:with-param>
   <xsl:with-param name="baseFileName" >
      <xsl:value-of select="$baseFileName" />
   </xsl:with-param>
   <xsl:with-param name="idUnique" >
      <xsl:value-of select="$idUnique" />
   </xsl:with-param>
</xsl:call-template>
</td>
</tr>
</table>

   </xsl:template>
</xsl:stylesheet>
