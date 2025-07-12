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
                xmlns:ecommerce="/WEB-INF/ecommerce.tld"
>

   <xsl:import href="/template/generic/javascript/imports/globals/javascriptGlobals.xsl" />

   <xsl:import href="/generic/imports/jsp/directive/include/include.xsl" />
   <xsl:import href="/template/generic/buttons/buttonsPreview.xsl" />

   <xsl:template name="menuPreview" >

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

   <div ID="leftsidebar" class="leftsidebar" 
      onClick="abScrollTo('leftsidebar{$scrollName}');{$javascriptErrorControl}" >

<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      categoryListPreview
   </xsl:with-param>
</xsl:call-template>
<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      productSearchPreview
   </xsl:with-param>
</xsl:call-template>
   
   </div>
   
   <p/>

<ecommerce:authentication roles="%= roles %" command="%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %">         
   <div ID="leftsidebar" class="leftsidebar" 
      onClick="abScrollTo('leftsidebar{$scrollName}');{$javascriptErrorControl}" >

<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      authenticationPreview
   </xsl:with-param>
</xsl:call-template>
   
   </div>
   <p/>
</ecommerce:authentication>
   
   <div ID="leftsidebar" class="leftsidebar" 
      onClick="abScrollTo('leftsidebar{$scrollName}');{$javascriptErrorControl}" >

<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      customerLinksStyleTwoPreview
   </xsl:with-param>
</xsl:call-template>

<xsl:call-template name="jspDirectiveIncludeWindow" >
   <xsl:with-param name="fileName" >
      helpListPreview
   </xsl:with-param>
</xsl:call-template>   
   
   </div>

    </xsl:template>

</xsl:stylesheet> 
