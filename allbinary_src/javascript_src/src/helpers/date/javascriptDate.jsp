//Scroll to name attribute
function showTimeTest()
{
   var currentTime = "";
   var currentDate = new Date();
   
   if(!currentDate.getFullYear)
   {
      log("No Such Method: currentDate.getFullYear");
   }
   else
   {
      currentTime += currentDate.getFullYear();
   }

   if(!currentDate.getMonth)
   {
      log("No Such Method: currentDate.getMonth");
   }
   else
   {
      currentTime += currentDate.getMonth();
   }

   if(!currentDate.getDay)
   {
      log("No Such Method: currentDate.getDay");
   }
   else
   {
      currentTime += currentDate.getDay();
   }

   if(!currentDate.getHours)
   {
      log("No Such Method: currentDate.getHours");
   }
   else
   {
      currentTime += currentDate.getHours();
   }

   if(!currentDate.getMinutes)
   {
      log("No Such Method: currentDate.getMinutes");
   }
   else
   {
      currentTime += currentDate.getMinutes();
   }

   if(!currentDate.getSeconds)
   {
      log("No Such Method: currentDate.getSeconds");
   }
   else
   {
      currentTime += currentDate.getSeconds();
   }

   if(!currentDate.getMilliseconds)
   {
      log("No Such Method: currentDate.getMilliseconds");
   }
   else
   {
      currentTime += currentDate.getMilliseconds();
   }

   //log("Number: " + currentDate);
   //log("Date: " + currentDate.getDate());
   //log("Time: " + currentDate.getTime());
}
