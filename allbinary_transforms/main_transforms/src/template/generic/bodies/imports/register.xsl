<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsp="http://java.sun.com/JSP/Page"
                xmlns:jutil="/WEB-INF/jutil.tld">
   <xsl:import href="/template/generic/buttons/buttons.xsl" />

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

   <xsl:template name="registerInputForm" >
      <xsl:param name="page"/>
      <xsl:param name="xslForwardPage" />

*=Required Fields

<jutil:element name="form">
   <jutil:attribute name="method">
      POST
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>QUOTEREGISTRATIONNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="action">
   <jsp:expression><xsl:value-of select="$page" /></jsp:expression>
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
   
<table class="table" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" >

  <tr>
    <td width="200">Prefix:</td>
    <td width="300">

<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>UserData.PREFIXNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>3</jsp:expression>
   </jutil:attribute>
</jutil:element>

    </td>
  </tr>
  <tr>
    <td width="200">*First Name:</td>
    <td width="300">

<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>UserData.FIRSTNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>19</jsp:expression>
   </jutil:attribute>
</jutil:element>
    
    </td>
  </tr>
  <tr>
    <td width="200">Middle Initial:</td>
    <td width="300">
    
<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>UserData.MIDDLENAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>3</jsp:expression>
   </jutil:attribute>
</jutil:element>
    
    </td>
  </tr>
  <tr>
    <td width="200">*Last Name:</td>
    <td width="300">
    
<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>UserData.LASTNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>19</jsp:expression>
   </jutil:attribute>
</jutil:element>

    </td>
  </tr>
  <tr>
    <td width="200">Suffix:</td>
    <td width="300">
    
<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>UserData.SUFFIXNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>3</jsp:expression>
   </jutil:attribute>
</jutil:element>
    
    </td>
  </tr>
  <tr>
    <td width="200">*User Name:</td>
    <td width="300">
    
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
      <jsp:expression>19</jsp:expression>
   </jutil:attribute>
</jutil:element>
    
    </td>
  </tr>
  <tr>
    <td width="200">*Email Address:</td>
    <td width="300">
    
<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>UserData.MAINEMAIL</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>19</jsp:expression>
   </jutil:attribute>
</jutil:element>

    </td>
  </tr>
  <tr>
    <td width="200">*Password:</td>
    <td width="300">
    
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
      <jsp:expression>19</jsp:expression>
   </jutil:attribute>
</jutil:element>
    
    </td>
  </tr>
  <tr>
    <td width="200">Phone:</td>
    <td width="300">

<jutil:element name="input">
   <jutil:attribute name="class">
      main
   </jutil:attribute>
   <jutil:attribute name="type">
      text
   </jutil:attribute>
   <jutil:attribute name="name">
      <jsp:expression>UserData.HOMEPHONE</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="size">
      <jsp:expression>19</jsp:expression>
   </jutil:attribute>
</jutil:element>

    </td>
  </tr>  
</table>

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
      QUOTEREGISTRATIONNAME
   </xsl:with-param>
   <xsl:with-param name="value">
      GLOBALS.REGISTER
   </xsl:with-param>   
   <xsl:with-param name="command">
      GLOBALS.REGISTER
   </xsl:with-param>
</xsl:call-template>

<p/>

</jutil:element>

    </xsl:template>

</xsl:stylesheet>