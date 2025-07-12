function setStyleRule(cssElementAndProperty)
{
   var aCssElementName = cssElementAndProperty.getName();
   var aCssPropertyName = cssElementAndProperty.cssProperty.name;
   var aCssPropertyValue = cssElementAndProperty.cssProperty.value;

   //log("SetStyleRule: cssElementAndProperty.toString(): " + cssElementAndProperty.toString());
   
   var cssRule = getCssRuleWithCssElementName(aCssElementName);
   var cssElementProperties = getCssTextFromCssRule(cssRule);

   var startIndexOfProperty = cssElementProperties.indexOfIgnoreCase(aCssPropertyName);

   //If property is new then append
   if(startIndexOfProperty < 0)
   {
      //log("setStyleRule: Appending Old Style: " + cssElementProperties);
      var cssElementText = cssElementProperties + 
         aCssPropertyName + CommonLabels.getInstance().COLON_SEP + aCssPropertyValue + "; ";
      //log("setStyleRule: New Style with added property: " + cssElementText);
      setCssTextForCssRule(cssRule, cssElementText);
   }
   else
   //If property is old then update property
   {
      //log("setStyleRule: Updating Old Style: " + cssElementProperties);

      var startIndexOfPropertyValue = 
         cssElementProperties.indexOf(":", startIndexOfProperty);
      //log("setStyleRule: startIndexOfPropertyValue: " + startIndexOfPropertyValue);

      var endIndexOfPropertyValue = 
         cssElementProperties.indexOf(";", startIndexOfPropertyValue);
      //log("setStyleRule: endIndexOfPropertyValue: " + endIndexOfPropertyValue);

      if(startIndexOfPropertyValue > 0)
      {
         var startCssElementProperties = 
            cssElementProperties.substring(
               0, startIndexOfPropertyValue + 1);

         //if no semicolon at the end
         if(endIndexOfPropertyValue < 0)
            endIndexOfPropertyValue = cssElementProperties.length;

         var endCssElementProperties = 
            cssElementProperties.substring(
               endIndexOfPropertyValue, cssElementProperties.length);

        //log("startCssElementProperties: " + startCssElementProperties);
        //log("endCssElementProperties: " + endCssElementProperties);

         //START +  + END
         var newStyle = 
            startCssElementProperties + 
            " " + aCssPropertyValue + 
            endCssElementProperties;

         //log("setStyleRule: New Style with updated property: " + newStyle);
                  
         //var priority = cssRule.style.getPropertyPriority(aCssPropertyName);
         //log("cssRule.style: " + cssRule.style + "Priority: " + priority);                  
         //cssRule.style.setProperty(aCssPropertyName, aCssPropertyNameValue, 0);

         deleteRule(styleSheetIndex, cssRulesIndex);
         insertRule(styleSheetIndex, aCssElementName, newStyle, cssRulesIndex);

      }
   }
   //log("setStyleRule: End");
}
