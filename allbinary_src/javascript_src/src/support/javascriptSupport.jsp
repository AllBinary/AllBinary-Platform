var UNSUPPORTED = "This Page Does Not Support Your Browser: ";

function initSupport()
{
   if(!document.getElementById)
   {
      alert(UNSUPPORTED + " document.getElementById");
   }

   // && !document.getElementsByTagName && !(document.styleSheets && document.all)
   if(!document.styleSheets)
   {
      alert(UNSUPPORTED + " document.styleSheets");
   }

   if(document.styleSheets)
   {

   if(!document.styleSheets[0])
   {
      alert(UNSUPPORTED + " document.styleSheets[0]");
   }

   if(!document.styleSheets[0].cssRules && !document.styleSheets[0].rules)
   {
      alert(UNSUPPORTED + " document.styleSheets[0].cssRules or rules");
   }

   if(!document.styleSheets[0].deleteRule && !document.styleSheets[0].removeRule)
   {
      alert(UNSUPPORTED + " document.styleSheets[0].deleteRule or removeRule");
   }

   if(!document.styleSheets[0].insertRule && !document.styleSheets[0].addRule)
   {
      alert(UNSUPPORTED + " document.styleSheets[0].insertRule or addRule");
   }

   }
}