var cssElementNodeName = "CSS_ELEMENT_NAME";
var elementNameSpace = sep + cssElementNodeName + properties;

function CssElementAndProperty(aCssElementName, aCssProperty)
{
   this.cssElementName = aCssElementName;
   this.cssProperty = aCssProperty;

   this.getProperty = CssElementAndPropertyGetProperty;
   this.setProperty = CssElementAndPropertySetProperty;
      
   this.getName = CssElementAndPropertyGetElementName;
   this.setName = CssElementAndPropertySetElementName;

   this.toString = CssElementAndPropertyToString;
}

function CssElementAndPropertyGetElementName()
{
   return this.cssElementName;
}

function CssElementAndPropertySetElementName(aCssElementName)
{
   this.cssElementName = aCssElementName;
}

function CssElementAndPropertyGetProperty()
{
   return this.cssProperty;
}

function CssElementAndPropertySetProperty(aCssProperty)
{
   this.cssProperty = aCssProperty;
}

function CssElementAndPropertyToString()
{
   return this.cssElementName + CommonLabels.getInstance().COLON_SEP + 
      this.cssProperty.toString();
}

function getElementName(nameSpaceString)
{
   //find last elementname in namespace
   var startIndex = nameSpaceString.lastIndexOf(cssElementNodeName);
   
   var propertyKey = properties + value + equals;
   var elementNameStartIndex = 
      nameSpaceString.indexOf(propertyKey, startIndex);
   
   var elementNameEndIndex = 
      nameSpaceString.indexOf(propSep, elementNameStartIndex);
   
   //if no more properties use the string length
   if(elementNameEndIndex < 0)
   {
      elementNameEndIndex = 
         nameSpaceString.indexOf(sep, elementNameStartIndex);

      if(elementNameEndIndex < 0)
      {
         elementNameEndIndex = nameSpaceString.length;
      }
   }

   //startIndex
   var cssElementName = 
      nameSpaceString.substring(
         elementNameStartIndex + propertyKey.length, elementNameEndIndex);

   return cssElementName;
}
