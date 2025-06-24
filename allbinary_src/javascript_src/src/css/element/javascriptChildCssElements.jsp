//Recursively find all child input tags with a name space and add it to the array
function getChildCssElementAndPropertyArray(
   domNode, rootCssElementAndProperty, 
   cssElementAndPropertyArray, cssElementAndPropertyIndex)
{
   if(domNode !=null)
   {
      //log("getChildCssElementAndPropertyArray NodeName: " + domNode.nodeName);
      //&& domNode.nodeName == "DIV"
      //Node.ELEMENT_NODE == 1
      if(domNode.nodeType == 1)
      {
         //log("getChildCssElementAndPropertyArray: " + domNode);
         if(domNode.id)
         {
            var cssElementName = getElementName(domNode.id);
            //log("Adding: " + cssElementName + " where index = " + cssElementAndPropertyIndex);
            cssElementAndPropertyArray[cssElementAndPropertyIndex] = 
               new CssElementAndProperty(
                  cssElementName, rootCssElementAndProperty.cssProperty);
            //log("Element Name Name Attr: " + cssElementName);
         }
      }

      var childElementNodeList = domNode.childNodes;
      var size = childElementNodeList.length;
      for(var index = 0; index < size; index++)
      {
         var childDomNode = childElementNodeList.item(index);
         //log("getChildCssElementAndPropertyArray NodeType: " + childDomNode.nodeType);
         //&& childDomNode.nodeName != "TABLE"
         //Node.ELEMENT_NODE == 1
         if(childDomNode.nodeType == 1)
         {
            cssElementAndPropertyIndex = cssElementAndPropertyIndex + 1;
            getChildCssElementAndPropertyArray(
               childDomNode, rootCssElementAndProperty, 
               cssElementAndPropertyArray, cssElementAndPropertyIndex);
         }
      }
   }
   return cssElementAndPropertyArray;
}
