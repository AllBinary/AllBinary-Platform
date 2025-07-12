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
>

   <xsl:import href="/generic/imports/jsp/directive/include/include.xsl" />

   <xsl:template name="menuPreview" >

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      categoryListPreview
   </xsl:with-param>
</xsl:call-template>
<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      customerLinksStyleTwoPreview
   </xsl:with-param>
</xsl:call-template>
<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      miniBasketPreview
   </xsl:with-param>
</xsl:call-template>
<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      productSearchPreview
   </xsl:with-param>
</xsl:call-template>

    </xsl:template>

</xsl:stylesheet> 
