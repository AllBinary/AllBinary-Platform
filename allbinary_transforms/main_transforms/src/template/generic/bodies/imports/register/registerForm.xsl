<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsp="http://java.sun.com/JSP/Page"
                xmlns:jutil="/WEB-INF/jutil.tld"
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

   <xsl:output method="xml" indent="yes" />

   <xsl:template name="registerForm" >
      <xsl:param name="xslForwardPage" />

<jutil:element name="form">
   <jutil:attribute name="name">   
      <jsp:expression>NEWREGISTRATIONNAME</jsp:expression>      
   </jutil:attribute>
   <jutil:attribute name="method">
      POST
   </jutil:attribute>   
   <jutil:attribute name="action">
      <jsp:expression>REGISTERPAGE</jsp:expression>
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
         <jsp:expression>GLOBALS.ADMINCOMMAND</jsp:expression>
      </jutil:attribute>
   </jutil:element>
   
<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      NEWREGISTRATIONNAME
   </xsl:with-param>
   <xsl:with-param name="value">
      NEWREGISTRATION
   </xsl:with-param>
   <xsl:with-param name="command">
      NEWREGISTRATIONNAME
   </xsl:with-param>
</xsl:call-template>

</jutil:element>
   
   </xsl:template>

</xsl:stylesheet>