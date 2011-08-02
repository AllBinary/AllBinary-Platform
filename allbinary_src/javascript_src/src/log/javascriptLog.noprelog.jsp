//set css element property
function logDomNodeAttributes(domNodeId)
{
   var domNode = document.getElementById(domNodeId);
   
   for(index = 0; index < domNode.attributes.length; index++)
      log(domNode.attributes[index].name + "=" + domNode.attributes[index].value + " " );
}

function log(text)
{
   var javascriptLog = "javascriptLog";
   var domNode = document.getElementById(javascriptLog);
   domNode.innerHTML = domNode.innerHTML + "<br/><br/>" + text;
}
