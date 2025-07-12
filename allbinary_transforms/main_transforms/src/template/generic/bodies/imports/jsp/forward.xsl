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

   <xsl:import href="/template/generic/bodies/imports/jsp/forwardData.xsl" />
   
   <xsl:template name="jspForward" >
      <xsl:param name="PAGE" />
      <xsl:param name="FORWARDPAGE" />
      <xsl:param name="FORWARDNAME" />
      <xsl:param name="FORWARDVALUE" />
      <xsl:param name="AdminCommand" />

      <xsl:variable name="PAGENoSpace" select="normalize-space($PAGE)" />
      <xsl:variable name="FORWARDPAGENoSpace" select="normalize-space($FORWARDPAGE)" />
      <xsl:variable name="FORWARDNAMENoSpace" select="normalize-space($FORWARDNAME)" />
      <xsl:variable name="FORWARDVALUENoSpace" select="normalize-space($FORWARDVALUE)" />
      <xsl:variable name="AdminCommandNoSpace" select="normalize-space($AdminCommand)" />

      <jsp:forward page="%= {$PAGENoSpace} %" >
         <xsl:if test="$FORWARDPAGENoSpace" >
            <jsp:param name="{$FORWARDPAGENAME}" value="%= {$FORWARDPAGENoSpace} %" />
         </xsl:if>
         <xsl:if test="$FORWARDNAMENoSpace" >
            <jsp:param name="{$FORWARDNAMENAME}" value="%= {$FORWARDNAMENoSpace} %" />
         </xsl:if>
         <xsl:if test="$FORWARDVALUENoSpace" >
            <jsp:param name="{$FORWARDVALUENAME}" value="%= {$FORWARDVALUENoSpace} %" />
         </xsl:if>
         <xsl:if test="$AdminCommandNoSpace" >
            <jsp:param name="AdminCommand" value="%= {$AdminCommandNoSpace} %" />
         </xsl:if>
      </jsp:forward>

    </xsl:template>

</xsl:stylesheet>