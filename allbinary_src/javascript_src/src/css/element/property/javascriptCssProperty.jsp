var cssPropertyNodeName = "CSS_PROPERTY_NAME";
var cssPropertyValuesNodeName = "CSS_PROPERTY_VALUES";
var cssPropertyValueNodeName = "CSS_PROPERTY_VALUE";

var cssPropertyNameSpace = sep + cssPropertyNodeName + properties;

function CssProperty(name, value)
{
   this.name = name;
   this.value = value;

   this.getName = CssPropertyGetName;
   this.getValue = CssPropertyGetValue;

   this.setName = CssPropertySetName;
   this.setValue = CssPropertySetValue;

   this.toString = CssPropertyToString;
}

function CssPropertyGetName()
{
   return this.name;
}

function CssPropertyGetValue()
{
   return this.value;
}

function CssPropertySetName(aCssPropertyName)
{
   this.name = aCssPropertyName;
}

function CssPropertySetValue(aCssPropertyValue)
{
   this.value = aCssPropertyValue;
}

function CssPropertyToString()
{
   return this.name + " = " + this.value;
}

function getPropertyName(nameSpaceString)
{
   //find last elementname in namespace
   var startIndex = nameSpaceString.lastIndexOf(cssElementNodeName);

   if(startIndex < 0) return null;
   
   var propertyKey = cssPropertyNodeName + properties + value + equals;
   var propertyNameStartIndex = 
      nameSpaceString.indexOf(propertyKey, startIndex);

   if(propertyNameStartIndex < 0) return null;
   
   var propertyNameEndIndex = 
      nameSpaceString.indexOf(sep, propertyNameStartIndex);

   //if no more properties use the string length
   if(propertyNameEndIndex < 0)
   {
      propertyNameEndIndex = nameSpaceString.length;
   }

   //startIndex
   var cssPropertyName = 
      nameSpaceString.substring(
         propertyNameStartIndex + propertyKey.length, propertyNameEndIndex);

   return cssPropertyName;
}
