//Updates a color for the dynamic css when user manually enters a color
function setColorFromTextInput(formName, nameSpaceId)
{
   if (document.getElementById)
   {
      var newSelectedColorTextInputTag = document.getElementById('selectedColor' + formName + nameSpaceId + "TextInput");
      var color = newSelectedColorTextInputTag.getAttribute("value");      
      if(color.length == 7)
      {
         selectedColorDivTag[formName] = document.getElementById('selectedColor' + formName + nameSpaceId + "Div");
         selectedColorInputTag[formName] = document.getElementById('selectedColor' + formName + nameSpaceId + "Input");
         selectedColorTextInputTag[formName] = newSelectedColorTextInputTag;

         var style="background-color:" + color + ";";
         selectedColorDivTag[formName].setAttribute("style", style);
         selectedColorInputTag[formName].setAttribute("value", color);
         selectedColorTextInputTag[formName].setAttribute("value", color);
      }
      else
      {
         selectedColorTextInputTag[formName] = newSelectedColorTextInputTag;
         selectedColorTextInputTag[formName].setAttribute("value", "len: " + color.length + " " + color);
      }
    }
}
