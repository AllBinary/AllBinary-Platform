
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
   if(documentStyleSheets[styleSheetIndex].getAttribute('rel'))
   {
      return documentStyleSheets[styleSheetIndex].rel;
   }
   else 
   {
      alert(UNSUPPORTED + " document.styleSheets[index].cssRules or rules");
   }
}

function getOtherStyleSheets(linkNodeArray, styleNodeArray)
{
   for(var index = 0, otherStyleSheets = []; linkNodeArray[index]; index++ ) 
   {
      var rel = linkNodeArray[index].rel ? linkNodeArray[index].rel : linkNodeArray[index].getAttribute ? linkNodeArray[index].getAttribute('rel') : '';
      if(typeof(rel) == 'string' && rel.toLowerCase().indexOf('style') + 1)
      {
         otherStyleSheets[otherStyleSheets.length] = linkNodeArray[index]; 
      }
   } 
   
   for( var index = 0; styleNodeArray[index]; index++ )
   {
      otherStyleSheets[otherStyleSheets.length] = styleNodeArray[index]; 
   } 
   return otherStyleSheets;
}

function getStyleSheets()
{
   if(document.styleSheets)
   {
      return document.styleSheets;
   }
   else
   if(document.getElementsByTagName) 
   { 
      var linkNodeArray = document.getElementsByTagName('link');
      var styleNodeArray = document.getElementsByTagName('style');
      return getOtherStyleSheets(linkNodeArray, styleNodeArray);
   } 
   else 
   if(document.styleSheets && document.all) 
   { 
      var linkNodeArray = document.all.tags('LINK');
      var styleNodeArray = document.all.tags('STYLE');
      return getOtherStyleSheets(linkNodeArray, styleNodeArray);
   } 
   else
   {
      alert(UNSUPPORTED + " document.styleSheets or (links and style) styleSheets");
   }
}
