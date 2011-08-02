//Scroll to name attribute
var lastChangeTime = 0;
function abScrollTo(nameAttr)
{
   var currentDate = new Date();
   var currentTime = currentDate.getTime();

   if(lastChangeTime < currentTime - 100)
   {
      lastChangeTime = currentTime;
      //log("Current Location: " + window.location);
      window.location = "#" + nameAttr;
      //log("New Location: " + window.location);
   }
}
