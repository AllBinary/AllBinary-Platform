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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:import href="/template/generic/imports/encoding/specialEncoding.xsl" />

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

<script language="JavaScript">
function cssPropertyChange(tagId)
{
   if(!document.getElementById) return;
   
   //var domElement = document.getElementById(tagId);
   
}

function getCssStyle(aCssElement)
{
   for(styleSheetIndex = 0; 
   styleSheetIndex <xsl:call-template name="lessThanSymbol" /> document.styleSheets.length; 
   styleSheetIndex++)
   {
      var cssRules = document.styleSheets[styleSheetIndex].cssRules;

      for(cssRulesIndex = 0; 
      cssRulesIndex <xsl:call-template name="lessThanSymbol" /> cssRules.length; 
      cssRulesIndex++)
      {
         var cssRule = cssRules[cssRulesIndex];
         var cssElementName = cssRule.selectorText;

         if(aCssElement == cssElementName)
         {
            return cssRule;
         }
      }
   }
}

function getCssStyleText(aCssElement)
{
   var cssRule = getCssStyle(aCssElement);

   var cssElementText = cssRule.cssText;

   var startIndex = cssElementText.indexOf("{");
   var endIndex = cssElementText.indexOf("}");
   var cssElementProperties = 
      cssElementText.substring(startIndex + 1, endIndex);

   return cssElementProperties;
}

function setStyle(tag, aCssElement)
{
   if(!document.getElementById) return;

   var cssElementProperties = getCssStyleText(aCssElement);
   var domElement = document.getElementById(tag);
   domElement.setAttribute("style", cssElementProperties);
}

function setStyle(tag, aCssElement)
{
   for(styleSheetIndex = 0; 
   styleSheetIndex &#60; document.styleSheets.length; 
   styleSheetIndex++)
   {
      var cssRules = document.styleSheets[styleSheetIndex].cssRules;
      
      for(cssRulesIndex = 0; 
      cssRulesIndex &#60; cssRules.length; 
      cssRulesIndex++)
      {
         var cssRule = cssRules[cssRulesIndex];
         var cssElementName = cssRule.selectorText;
         
         if(aCssElement == cssElementName)
         {
            var cssElementText = cssRule.cssText;

            var startIndex = cssElementText.indexOf("{");
            var endIndex = cssElementText.indexOf("}");
            var cssElementProperties = 
               cssElementText.substring(startIndex + 1, endIndex);

            var domElement = document.getElementById(tag);
            domElement.setAttribute("style", cssElementProperties);
         }
      }   
   }
}

function pulsate(myElementID)
{
   if(!document.getElementById) return;
   var myElement = document.getElementById(myElementID);

   var aCssStyleText = getCssStyleText('flashTextOff');

   if(myElement.style == aCssStyleText)
      setStyle(myElementID, 'flashTextOn');
   else
      setStyle(myElementID, 'flashTextOff');
}

function pulsateAll()
{
   if(!document.getElementById) return;

   var special = "special";

   if(document.getElementById(special))
      pulsate(special);

   if(document.getElementById(special + "PriceOne"))
      pulsate(special + "PriceOne");

   if(document.getElementById(special + "PriceTwo"))
      pulsate(special + "PriceTwo");
}

function adminCommandSubmit(formName, commandValue)
{
   var aForm = document.forms[formName];
   aForm.AdminCommand.value = commandValue;
   aForm.submit();
}

function init()
{   
   setInterval("pulsateAll()",2000);
}

</script>

</jsp:root>

         </xsl:for-each>
       </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>
