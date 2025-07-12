<xsl:stylesheet version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   xmlns:jsp="http://java.sun.com/JSP/Page"
>

   <xsl:import href="/template/generic/bodies/imports/generic/body/body.xsl" />

   <xsl:output method="xml" indent="yes" />

    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

   <xsl:call-template name="body" >
      <xsl:with-param name="bodyNode" >
         <xsl:value-of select="BODY_NAME" />
      </xsl:with-param>
      <xsl:with-param name="defaultBodyHeading" >
         Auctions
      </xsl:with-param>
   </xsl:call-template>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>