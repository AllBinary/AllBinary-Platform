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
   //log("getCssTextForCssRule: " + cssRule);
   //Netscape and Firefox
   if(cssRule.cssText)
   {
      var cssElementText = cssRule.cssText;

      //log("getCssTextForCssRule: cssRule.cssText: " + cssElementText);

      var startIndex = cssElementText.indexOf(START);
      var endIndex = cssElementText.indexOf(END);

      var cssElementProperties = 
        cssElementText.substring(startIndex + 1, endIndex);

      return cssElementProperties;
   }
   else 
   if(cssRule.style.cssText)
   {
      //log("getCssTextForCssRule: cssRule.style.cssText: " + cssRule.style.cssText);
      return cssRule.style.cssText;
   }
   else
   {
      alert(UNSUPPORTED + " get cssRule.cssText and cssRule.style.cssText");
   }
}

//Browser Support Method
function setCssTextForCssRule(cssRule, cssText)
{
   //log("setCssTextForCssRule: " + cssRule + " cssTex: " + cssText);
   if(cssRule.cssText)
   {
      //add brackets
      cssRule.cssText = START + cssText + END;
   }
   else 
   if(cssRule.style.cssText)
   {
      cssRule.style.cssText = cssText;
   }
   else
   {
      alert(UNSUPPORTED + " set cssRule.cssText and cssRule.style.cssText");
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
      alert(UNSUPPORTED + " cssRules[cssRulesIndex] or cssRules.item(cssRulesIndex)");
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
      alert(UNSUPPORTED + " document.styleSheets[index].cssRules or rules");
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
      alert(UNSUPPORTED + " document.styleSheets or (links and style) styleSheets");
   }
}

//Browser Support Method
function deleteRule(styleSheetIndex, cssRulesIndex)
{
   //log("deleteRule: styleSheetIndex: " + styleSheetIndex + " cssRulesIndex: " + cssRulesIndex);

   var documentStyleSheets = getStyleSheets();
   if(documentStyleSheets[styleSheetIndex].deleteRule)
   {
      documentStyleSheets[styleSheetIndex].deleteRule(cssRulesIndex);
   }
   else if(documentStyleSheets[styleSheetIndex].removeRule)
   {
      documentStyleSheets[styleSheetIndex].removeRule(cssRulesIndex);
   }
   else 
   {
      alert(UNSUPPORTED + " documentStyleSheets[styleSheetIndex].deleteRule or removeRule");
   }
}

//Browser Support Method
function insertRule(styleSheetIndex, cssElementName, newStyle, cssRulesIndex)
{
   //log("insertRule: styleSheetIndex: " + styleSheetIndex + " cssRulesIndex: " + cssRulesIndex);

   var documentStyleSheets = getStyleSheets();
   if(documentStyleSheets[styleSheetIndex].insertRule)
   {
      //log("insertRule: newStyle: " + cssElementName + " " + START + newStyle + END);
      documentStyleSheets[styleSheetIndex].insertRule(cssElementName + " " + START + newStyle + END, cssRulesIndex);
   }
   else if(documentStyleSheets[styleSheetIndex].addRule)
   {
      //log("insertRule: newStyle: " + newStyle);
      documentStyleSheets[styleSheetIndex].addRule(cssElementName, newStyle, cssRulesIndex);
   }
   else 
   {
      alert(UNSUPPORTED + " documentStyleSheets[styleSheetIndex].insertRule or addRule");
   }
}
