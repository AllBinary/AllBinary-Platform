function getSiblingCssElementAndPropertyArray(
   domNode, cssElementAndPropertyArray, cssElementAndPropertyIndex)
{
   if(domNode !=null)
   {
      if(domNode.id)
      {
         var rootCssElementName = getElementName(domNode.id);
         //log("Next Css Element Name ID Attr: " + rootCssElementName);
      }

      while(domNode.nextSibling)
      {
         domNode = domNode.nextSibling;
         //Node.ELEMENT_NODE == 1
         if(domNode.nodeType == 1 && domNode.nodeName == "INPUT")
         {
            if(domNode.name)
            {
               var rootCssElementName = getElementName(domNode.name);
               //log("Next Css Element Name Name Attr: " + rootCssElementName);
            }
            //log("getChildCssElementAndPropertyArray NodeName: " + domNode.nodeName);
         }
      }
   }
   return cssElementAndPropertyArray;
}
