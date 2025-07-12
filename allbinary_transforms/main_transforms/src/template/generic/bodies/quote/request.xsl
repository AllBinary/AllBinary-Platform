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

   <xsl:import href="/template/generic/bodies/imports/jsp/forward.xsl" />
   <xsl:import href="/template/generic/buttons/buttons.xsl" />
   
   <xsl:output method="xml" indent="yes" />

<xsl:template name="defaultBody" 
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:jutil="/WEB-INF/jutil.tld"
              xmlns:ecommerce="/WEB-INF/ecommerce.tld"
>

<ecommerce:authentication 
   command="%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %" 
   roles="%= roles %" >
   
   <xsl:call-template name="jspForward">
      <xsl:with-param name="PAGE">
         FORWARDLOGOUTPAGE
      </xsl:with-param>
      <xsl:with-param name="FORWARDPAGE">
         FORWARDQUOTEREQUESTPAGE
      </xsl:with-param>
      <xsl:with-param name="FORWARDNAME">
         BODYLOGINNAME
      </xsl:with-param>
      <xsl:with-param name="FORWARDVALUE">
         LOGIN
      </xsl:with-param>
   </xsl:call-template>

</ecommerce:authentication>

<jsp:scriptlet>
   if(command!=null)
   {
      if(command.compareTo(QUOTEREQUESTNAME)==0)
      {
</jsp:scriptlet>
<ecommerce:quoterequest 
   command="%= GLOBALS.INSERT %"
   isSelected="true" 
   storeName="%= STORENAME %" >
   <jsp:forward page="%= FORWARDQUOTEREQUESTCOMPLETEPAGE %" /> 
</ecommerce:quoterequest >

<jsp:forward page="%= FORWARDLOGOUTPAGE %"/>
<jsp:scriptlet>
      }
   }
</jsp:scriptlet>

<jutil:element name="form">
   <jutil:attribute name="name">
      <jsp:expression>QUOTEREQUESTNAME</jsp:expression>
   </jutil:attribute>
   <jutil:attribute name="method">
      POST
   </jutil:attribute>   
   <jutil:attribute name="action">
      <jsp:expression>QUOTEREQUESTPAGE</jsp:expression>
   </jutil:attribute>   

If you prefer to call in or email your quote request you can.<p/>
<table class="table" border="0" cellpadding="2" cellspacing="2" style="border-collapse: collapse" bordercolor="#111111" width="465" id="AutoNumber2">
  <tr>
    <td valign="top" >Project Description:</td>
    <td>
    
   <jutil:element name="textarea">
      <jutil:attribute name="class">
         main
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>QuoteRequestData.PROJECT_INFO</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="rows">
         20
      </jutil:attribute>
      <jutil:attribute name="cols">
         40
      </jutil:attribute>
      <jutil:attribute name="wrap">
         virtual
      </jutil:attribute>
      <jutil:attribute name="tabindex">
         1
      </jutil:attribute>      
   </jutil:element>
    
    </td>
  </tr>
  <tr>
    <td valign="top" >Comments:</td>
    <td>
    
   <jutil:element name="textarea">
      <jutil:attribute name="class">
         main
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>QuoteRequestData.CUSTOMER_COMMENTS</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="rows">
         10
      </jutil:attribute>
      <jutil:attribute name="cols">
         40
      </jutil:attribute>
      <jutil:attribute name="wrap">
         virtual
      </jutil:attribute>
      <jutil:attribute name="tabindex">
         2
      </jutil:attribute>      
   </jutil:element>
  
    </td>
  </tr>
  <tr>
    <td valign="top" >Budget Available: </td>
    <td>
    
   <jutil:element name="textarea">
      <jutil:attribute name="class">
         main
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>QuoteRequestData.BUDGET</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="rows">
         10
      </jutil:attribute>
      <jutil:attribute name="cols">
         40
      </jutil:attribute>
      <jutil:attribute name="wrap">
         virtual
      </jutil:attribute>
      <jutil:attribute name="tabindex">
         3
      </jutil:attribute>      
   </jutil:element>
      
    </td>
  </tr>
  <tr>
    <td valign="top" >Time Frame:</td>
    <td>

   <jutil:element name="textarea">
      <jutil:attribute name="class">
         main
      </jutil:attribute>
      <jutil:attribute name="name">
         <jsp:expression>QuoteRequestData.TIMEFRAME</jsp:expression>
      </jutil:attribute>
      <jutil:attribute name="rows">
         10
      </jutil:attribute>
      <jutil:attribute name="cols">
         40
      </jutil:attribute>
      <jutil:attribute name="wrap">
         virtual
      </jutil:attribute>
      <jutil:attribute name="tabindex">
         4
      </jutil:attribute>      
   </jutil:element>

    </td>
  </tr>
</table>
<p />

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
      QUOTEREQUESTNAME
   </xsl:with-param>
   <xsl:with-param name="value">
      SUBMIT
   </xsl:with-param>
   <xsl:with-param name="command">
      QUOTEREQUESTNAME
   </xsl:with-param>
</xsl:call-template>

</jutil:element>
   
</xsl:template>   

   <xsl:template match="/html" >    
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >

             <jsp:root
                     xmlns:jsp="http://java.sun.com/JSP/Page"
                     xmlns:jutil="/WEB-INF/jutil.tld"
                     xmlns:ecommerce="/WEB-INF/ecommerce.tld"
                     version="1.2">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>

         <xsl:variable name="defaultBodyHeading" select="'Quote Request'" />
      
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