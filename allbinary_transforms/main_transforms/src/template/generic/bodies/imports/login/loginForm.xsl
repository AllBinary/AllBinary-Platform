<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsp="http://java.sun.com/JSP/Page"
                xmlns:jutil="/WEB-INF/jutil.tld"
                xmlns:ecommerce="/WEB-INF/ecommerce.tld"
>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:import href="/template/generic/bodies/imports/jsp/forward.xsl" />
   
   <xsl:output method="xml" indent="yes" />

   <xsl:template name="loginForm" >
      <xsl:param name="xslForwardPage" />
      <xsl:param name="xslForwardName" />
      <xsl:param name="xslForwardValue" />

<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

<ecommerce:authentication 
   command="%= GLOBALS.PROCESSBODYIFAUTHENTICATED %" 
   userName="%= userName %" 
   password="%= password %" 
   roles="%= roles %">
   
   <xsl:call-template name="jspForward">
      <xsl:with-param name="PAGE">
         forwardPage
      </xsl:with-param>
      <xsl:with-param name="FORWARDPAGE">
         forwardPage
      </xsl:with-param>
      <xsl:with-param name="FORWARDNAME">
         forwardName
      </xsl:with-param>
      <xsl:with-param name="FORWARDVALUE">
         forwardValue
      </xsl:with-param>
      <xsl:with-param name="AdminCommand">
         REVIEWORDERS
      </xsl:with-param>
   </xsl:call-template>
      
</ecommerce:authentication>

<jutil:element name="form">
   <jutil:attribute name="method">
      POST
   </jutil:attribute>   
   <jutil:attribute name="name">
      <jsp:expression>forwardName</jsp:expression>
   </jutil:attribute>   
   <jutil:attribute name="action">
      <jsp:expression>LOGINPAGE</jsp:expression>
   </jutil:attribute>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>ForwardData.PAGE</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="value">
         <jsp:expression><xsl:value-of select="$xslForwardPage" /></jsp:expression>
      </jutil:attribute>
   </jutil:element>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>ForwardData.NAME</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="value">
         <jsp:expression><xsl:value-of select="$xslForwardName" /></jsp:expression>
      </jutil:attribute>
   </jutil:element>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>ForwardData.VALUE</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="value">
         <jsp:expression><xsl:value-of select="$xslForwardValue" /></jsp:expression>
      </jutil:attribute>
   </jutil:element>
      
<table class="table" >
<tr>
<td>User Name:</td>
<td>

<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>WeblisketSessionData.REMOVABLEUSERNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>30</jsp:expression>
   </jutil:attribute>
</jutil:element>

</td>
</tr>
<tr>
<td>Password:</td>
<td>

<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      password
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>WeblisketSessionData.REMOVABLEPASSWORD</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>30</jsp:expression>
   </jutil:attribute>
</jutil:element>

</td>
</tr>
</table>
<p/>

   <jutil:element name="input">
      <jutil:attribute name="type">
         hidden
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>GLOBALS.ADMINCOMMAND</jsp:expression>
      </jutil:attribute>
   </jutil:element>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      forwardName
   </xsl:with-param>
   <xsl:with-param name="value">
      forwardValue
   </xsl:with-param>
   <xsl:with-param name="command">
      NONE
   </xsl:with-param>
</xsl:call-template>

</jutil:element>
   
   </xsl:template>

</xsl:stylesheet>