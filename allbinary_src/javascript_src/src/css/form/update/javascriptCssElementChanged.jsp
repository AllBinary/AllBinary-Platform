function getChangedCssElementAndPropertyArray(
   rootCssElementTreeId, rootCssElementAndProperty)
{
   //The html div of div elements that contain an attribute with the name of id 
   //containing the value of a heirarchy of css element names
   //log("getChangedCssElementAndPropertyArray rootCssElementTreeId: " + 
   //   rootCssElementTreeId);

   var rootCssElementTree = document.getElementById(rootCssElementTreeId);

   //log("getChangedCssElementAndPropertyArray rootCssElementTree: " + 
     // rootCssElementTree);

   var rootCssElementName = getElementName(rootCssElementTree.id);
   //log("Root CssElementAndProperty: " + rootCssElementAndProperty.toString());

   cssElementAndPropertyArray = new Array(200);

   var childCssElementAndPropertyArray = 
      getChildCssElementAndPropertyArray(
         rootCssElementTree, rootCssElementAndProperty, 
         cssElementAndPropertyArray, 0);

   //Create an array that is the same length as the data contained within
   //Should replace with Array wrapper
   var arrayLength = childCssElementAndPropertyArray.length;
   var size = 0;
   for(var index = 0; index < arrayLength; index++)
   {
      if(childCssElementAndPropertyArray[index] == null)
      {
         size = index;
         break;
      }
   }
   
   var exactCssElementAndPropertyArray = new Array(size);
   for(var index = 0; index < size; index++)
   {
      exactCssElementAndPropertyArray[index] = childCssElementAndPropertyArray[index];
   }
   
   //log("Number Of Css Elements to Change: " + exactCssElementAndPropertyArray.length);
   
   return exactCssElementAndPropertyArray;
}
