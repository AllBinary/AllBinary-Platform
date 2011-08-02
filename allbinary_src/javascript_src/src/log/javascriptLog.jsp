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
   if(domNode!=null) 
   {
      domNode.innerHTML = domNode.innerHTML + "<br/><br/>" + text;
   }
   else
   {
      domNode = document.getElementById('javascriptPreLog');
      domNode.innerHTML = domNode.innerHTML + "<br/><br/>" + text;
   }
}
