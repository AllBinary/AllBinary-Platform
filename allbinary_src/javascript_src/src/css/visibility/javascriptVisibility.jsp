var displayHideStyle="display: none";
var displayShowStyle="display: block";

function toggleVisibility(domNode)
{
   //log("toggleVisibility: Starting");
   var cssText = getDomNodeStyle(domNode);
   //log("toggleVisibility: is " + cssText + " = " + displayShowStyle);
   //log("Result: " + cssText.equalsIgnoreCase(displayShowStyle));
   if(cssText.equalsIgnoreCase(displayShowStyle) ||
      cssText.equalsIgnoreCase(displayShowStyle + ";"))
   {
      //log("toggleVisibility: hidding");
      setDomNodeStyle(domNode, displayHideStyle);
   }
   else
   {
      //log("toggleVisibility: showing");
      setDomNodeStyle(domNode, displayShowStyle);
   }
}

function toggleVisibilityByElementId(domNodeId)
{
   //log("toggleVisibilityByElementId: " + domNodeId);
   toggleVisibility(document.getElementById(domNodeId));
}

function display(domNode)
{
   setDomNodeStyle(domNode, displayShowStyle);
}

function hide(domNode)
{
   setDomNodeStyle(domNode, displayHideStyle);
}
