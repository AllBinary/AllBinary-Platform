function getCssRuleWithCssElementName(aCssElementName)
{
   var documentStyleSheets = getStyleSheets();
   for(styleSheetIndex = 0; 
   styleSheetIndex < documentStyleSheets.length; 
   styleSheetIndex++)
   {
      var cssRules = getCssRules(styleSheetIndex);
      
      for(cssRulesIndex = 0; 
      cssRulesIndex < cssRules.length; 
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
   //else
   //log("Id does not exist: " + domNodeId);
}
