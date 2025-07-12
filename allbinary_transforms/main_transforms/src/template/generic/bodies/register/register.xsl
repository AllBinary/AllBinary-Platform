<xsl:stylesheet version="1.0" 
   xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
   xmlns:jsp="http://java.sun.com/JSP/Page"
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

   <xsl:import href="/template/generic/bodies/imports/register.xsl" />
   
   <xsl:output method="xml" indent="yes" />
   
<xsl:template name="defaultBody" 
   xmlns:jsp="http://java.sun.com/JSP/Page"
              xmlns:ecommerce="/WEB-INF/ecommerce.tld"
   xmlns:generic="/WEB-INF/generic.tld"
>

<jsp:scriptlet>
 String forwardPage = request.getParameter(ForwardData.PAGE);
</jsp:scriptlet>

<jsp:scriptlet>
   if(command!=null)
   {
      if(command.compareTo(GLOBALS.REGISTER)==0)
      {
</jsp:scriptlet>
<generic:user 
   command="%= GLOBALS.INSERT %" 
   storeName="%= STORENAME %"
   role="%= BasicUserRole.CUSTOMER.toString() %"
   xsl="%= IGNOREXMLXSL %" >
<ecommerce:user 
   command="%= GLOBALS.INSERT %"
   isSelected="true"
   role="%= BasicUserRole.CUSTOMER.toString() %" 
   enable="No" >
   <ecommerce:authentication 
      command="%= GLOBALS.PROCESSBODYIFAUTHENTICATED %" 
      userName="%= userName %" 
      password="%= password %" 
      roles="%= roles %" >
      <jsp:forward page="%= forwardPage %" />
   </ecommerce:authentication>
   <jsp:forward page="%= FORWARDLOGOUTPAGE %"/>
</ecommerce:user>
</generic:user>
<jsp:scriptlet>
      }
   }
</jsp:scriptlet>

<xsl:call-template name="registerInputForm">
   <xsl:with-param name="page">
      REGISTERPAGE
   </xsl:with-param>
   <xsl:with-param name="xslForwardPage">
      forwardPage
   </xsl:with-param>   
</xsl:call-template>

<p/>
   
</xsl:template>
   
    <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     xmlns:ecommerce="/WEB-INF/ecommerce.tld"
                     xmlns:generic="/WEB-INF/generic.tld"
                     version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

         <xsl:variable name="defaultBodyHeading" select="'Registration For New Users'" />
      
         <xsl:for-each select="BODY_NAME" >

            <xsl:variable name="titleTextName" select="TITLE_NAME/TITLE_TEXT/name" />
            <xsl:variable name="titleTextValue" select="TITLE_NAME/TITLE_TEXT/value" />
            <xsl:variable name="titleTextLen" select="string-length(normalize-space($titleTextValue))" />
                    
            <xsl:variable name="bodyDataName" select="name" />
            <xsl:variable name="bodyDataValue" select="value" />
            <xsl:variable name="bodyDataLen" select="string-length(normalize-space($bodyDataValue))" />

                     <xsl:if test="$titleTextLen = 0" >

                        <xsl:if test="$bodyDataLen = 0" >
<div class="mainHeading">
                        <p><xsl:value-of select="$defaultBodyHeading" /></p>
   <div class="main">
      <xsl:call-template name="defaultBody" />
   </div>
      
</div>
                        </xsl:if>

                        <xsl:if test="$bodyDataLen != 0" >
                           <xsl:value-of select="$bodyDataValue" />
                        </xsl:if>
                        
                     </xsl:if>
                     
                     <xsl:if test="$titleTextLen != 0" >
                        
                        <xsl:if test="$bodyDataLen != 0" >
                     
<div class="mainHeading">
                        <p><xsl:value-of select="$titleTextValue" /></p>

                        <xsl:value-of select="$bodyDataValue" />
</div>
                        </xsl:if>

                        <xsl:if test="$bodyDataLen = 0" >
<div class="mainHeading">
                        <p><xsl:value-of select="$titleTextValue" /></p>
   <div class="main">
      <xsl:call-template name="defaultBody" />
   </div>
      
</div>
                        </xsl:if>

                     </xsl:if>
                     
         </xsl:for-each>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>