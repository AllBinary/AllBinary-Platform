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

var UNSUPPORTED = "This Page Does Not Support Your Browser: ";

var START = "{";
var END = "}";

function getDomNodeStyle(domElement)
{
   var cssElementProperties = domElement.getAttribute("style");

   if(typeof(cssElementProperties) == 'string')
   {
      //log("getDomNodeStyle: " + domElement + " Style: " + cssElementProperties);
      return cssElementProperties;
   }
   else
   {
      var cssElementPropertiesString = domElement.style.cssText;
      //log("getDomNodeStyle: " + domElement + " Style: " + cssElementPropertiesString);
      return cssElementPropertiesString;
   }

}

function setDomNodeStyle(domElement,cssElementProperties)
{
   //log("setDomNodeStyle: " + domElement + " Style: " + cssElementProperties);

   domElement.setAttribute("style",cssElementProperties);
   domElement.style.cssText = cssElementProperties;
}

//Browser Support Method
function getCssTextFromCssRule(cssRule)
{
   //Netscape and Firefox
   if(cssRule.cssText)
   {
      var cssElementText = cssRule.cssText;

      var startIndex = cssElementText.indexOf(START);
      var endIndex = cssElementText.indexOf(END);

      var cssElementProperties = 
        cssElementText.substring(startIndex + 1, endIndex);

      return cssElementProperties;
   }
   else 
   if(cssRule.style.cssText)
   {
      return cssRule.style.cssText;
   }
   else
   {
   }
}

//Browser Support Method
function setCssTextForCssRule(cssRule, cssText)
{
   if(cssRule.cssText)
   {
      //add brackets
      cssRule.cssText = cssText;
   }
   else 
   if(cssRule.style.cssText)
   {
      cssRule.style.cssText = cssText;
   }
   else
   {
   }
}

//Browser Support Method
function getCssRule(cssRules, cssRulesIndex)
{
   //Netscape and Firefox
   if(cssRules[cssRulesIndex])
   {
      return cssRules[cssRulesIndex];
   }
   else
   //IE
   if(cssRules.item(cssRulesIndex))
   {
      return cssRules.item(cssRulesIndex);
   }
   else 
   {
   }
}

//Browser Support Method
function getCssRules(styleSheetIndex)
{
   var documentStyleSheets = getStyleSheets();
   if(documentStyleSheets[styleSheetIndex].cssRules)
   {
      return documentStyleSheets[styleSheetIndex].cssRules;
   }
   else 
   if(documentStyleSheets[styleSheetIndex].rules)
   {
      return documentStyleSheets[styleSheetIndex].rules;
   }
   else 
   {
   }
}

function getStyleSheets()
{
   if(document.styleSheets)
   {
      return document.styleSheets;
   }
   else
   {
   }
}

function getCssRuleWithCssElementName(aCssElementName)
{
   var documentStyleSheets = getStyleSheets();
   for(styleSheetIndex = 0; 
   styleSheetIndex &lt; documentStyleSheets.length; 
   styleSheetIndex++)
   {
      var cssRules = getCssRules(styleSheetIndex);
      
      for(cssRulesIndex = 0; 
      cssRulesIndex &lt; cssRules.length; 
      cssRulesIndex++)
      {
         var cssRule = getCssRule(cssRules,cssRulesIndex);
         var cssElementName = cssRule.selectorText;
         
         if(aCssElementName == cssElementName)
         {
            return cssRule;
         }
      }   
   }
   return null;
}

function getCssTextFromCssElement(aCssElementName)
{
   var cssRule = getCssRuleWithCssElementName(aCssElementName);
   var cssElementProperties = getCssTextFromCssRule(cssRule);
   return cssElementProperties;
   return null;
}

function setDomNodeStyleWithCssElement(domElement, aCssElementName)
{
   var cssElementProperties = getCssTextFromCssElement(aCssElementName);
   setDomNodeStyle(domElement,cssElementProperties);
}

function setDomNodeIdStyleWithCssElement(domNodeId, aCssElementName)
{
   var domElement = document.getElementById(domNodeId);
   if(domElement) setDomNodeStyleWithCssElement(domElement, aCssElementName);
   //else log("Id does not exist: " + domNodeId);
}

function log(text)
{
   var javascriptLog = "javascriptLog";
   var domNode = document.getElementById(javascriptLog);
   if(domNode!=null) 
   {
      domNode.innerHTML = domNode.innerHTML + "<br/><br/>" + text;
   }
   else
   {
      domNode = document.getElementById('javascriptPreLog');
      domNode.innerHTML = domNode.innerHTML + "<br/><br/>" + text;
   }
}

function pulsate(myElementID)
{
   if(!document.getElementById) return;
   var myElement = document.getElementById(myElementID);

   if(myElement)
   {
      var aCssStyleText = getCssTextFromCssElement('.flashTextOff');
      var myElementCssStyleText = getDomNodeStyle(myElement);

      if(!myElementCssStyleText ||
         (aCssStyleText == myElementCssStyleText ||
         aCssStyleText.indexOf(myElementCssStyleText) >= 1))
      {
         setDomNodeIdStyleWithCssElement(myElementID, '.flashTextOn');
      }
      else
      {
         setDomNodeIdStyleWithCssElement(myElementID, '.flashTextOff');
      }
   }
}

function pulsateAll()
{
   var special = "special";

   pulsate(special);
   pulsate(special + "PriceOne");
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
