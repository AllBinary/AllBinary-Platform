//Recursively find all child dom elements with and update their style
function updatePreviewStyle(domNode)
{
   //log("updatePreviewStyle: " + domNode);
   if(domNode !=null)
   {
      //Node.ELEMENT_NODE == 1
      if(domNode.nodeType == 1)
      {
         if(domNode.getAttribute("class"))
         {
            //log("updatePreviewStyle: Prop: " + domNode.getAttribute("class"));
            setDomNodeStyleWithCssElement(domNode, "." + domNode.getAttribute("class"));
         }
      }

      var childElementNodeList = domNode.childNodes;
      var size = childElementNodeList.length;
      for(var index = 0; index < size; index++)
      {
         var childDomNode = childElementNodeList.item(index);
         //Node.ELEMENT_NODE == 1
         if(childDomNode.nodeType == 1)
         {
            updatePreviewStyle(childDomNode);
         }
      }
   }
}
