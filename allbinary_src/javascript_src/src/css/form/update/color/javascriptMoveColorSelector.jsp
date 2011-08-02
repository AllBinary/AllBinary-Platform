function moveAndSetColorProperty(formName, rootCssElementTreeId, nameSpaceId)
{
      //move and display/hide color selector
      var colorSelectorDivTag = document.getElementById(colorSelectorId + formName + 'Div');
      
      var newSelectedColorTag = document.getElementById(colorSelectorId + formName + nameSpaceId);

      selectedColorDivTag[formName] = document.getElementById(selectedColorId + formName + nameSpaceId + "Div");
      //log("moveAndSetColorProperty: selectedColorDivTag[formName]:" + selectedColorDivTag[formName]);
      
      selectedColorInputTag[formName] = document.getElementById(selectedColorId + formName + nameSpaceId + "Input");
      //log("moveAndSetColorProperty: new selectedColorInputTag[formName]:" + selectedColorInputTag[formName]);
      
      //selectedColorTextInputTag[formName] = document.getElementById(selectedColorId + formName + nameSpaceId + "TextInput");
      selectedCssProperty[formName] = getPropertyName(nameSpaceId);

      selectedRootCssElementTree[formName] = rootCssElementTreeId;
      //log("moveAndSetColorProperty selectedRootCssElementTree[formName]: " + selectedRootCssElementTree[formName]);

      if(selectedColorTag[formName] == null)
      {
         //log("append and display");
         selectedColorTag[formName] = newSelectedColorTag;
         selectedColorTag[formName].appendChild(colorSelectorDivTag);
         display(colorSelectorDivTag);
      }
      else
      if(selectedColorTag[formName] == newSelectedColorTag)
      {
         //log("toggle display/hide");
         toggleVisibility(colorSelectorDivTag);
      }
      else
      {
         //log("move and display");
         //
         //remove from existing
         selectedColorTag[formName].removeChild(colorSelectorDivTag);
         selectedColorTag[formName] = newSelectedColorTag;
         selectedColorTag[formName].appendChild(colorSelectorDivTag);
         display(colorSelectorDivTag);
      }
}
