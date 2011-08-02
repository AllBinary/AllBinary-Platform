var BROWSERUNSUPPORTED = "Your Browser Is Not Supported Yet.";

function initIsBrowserSupported()
{
   if(!document.getElementById)
   {
      alert(BROWSERUNSUPPORTED);
      return;
   }

   // && !document.getElementsByTagName && !(document.styleSheets && document.all)
   if(!document.styleSheets)
   {
      alert(BROWSERUNSUPPORTED);
      return;
   }

   if(document.styleSheets)
   {

   if(!document.styleSheets[0])
   {
      alert(BROWSERUNSUPPORTED);
      return;
   }

   if(!document.styleSheets[0].cssRules && !document.styleSheets[0].rules)
   {
      alert(BROWSERUNSUPPORTED);
      return;
   }

   if(!document.styleSheets[0].deleteRule && !document.styleSheets[0].removeRule)
   {
      alert(BROWSERUNSUPPORTED);
      return;
   }

   if(!document.styleSheets[0].insertRule && !document.styleSheets[0].addRule)
   {
      alert(BROWSERUNSUPPORTED);
      return;
   }

   }
}