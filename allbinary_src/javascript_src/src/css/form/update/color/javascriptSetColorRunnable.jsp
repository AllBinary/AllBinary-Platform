//Set the color for the previously set div tag in (move and set color selector function)
function setColor(formName, color)
{
   //log("setColor: formName: " + formName + " Color:" + color);

   progressBar.set(0);
   progressBar.show(0);
   setTimeout("setColorTimeout('" + formName + "', '" + color + "')", THREADSTARTWAIT);
}

function setColorTimeout(formName, color)
{
   //if progress bar not shown then try again
   if(!progressBar.isVisible())
   {
      setColor(formName, color);
   }

   //log("setColorTimeout: Color:" + color);

      var style = "background-color:" + color + "; width:60px; height:16px;";
      //log("setColorTimeout: style:" + style);
      //selectedColorTextInputTag[formName].setAttribute('value', color);

      //log("setColorTimeout: selectedColorInputTag[formName]:" + selectedColorInputTag[formName]);
      selectedColorInputTag[formName].setAttribute('value', color);
      
      //log("setColorTimeout: selectedColorDivTag[formName]:" + selectedColorDivTag[formName]);
      ///selectedColorDivTag[formName].setAttribute('style', style);
      setDomNodeStyle(selectedColorDivTag[formName], style);

      var htmlInputElement = selectedColorInputTag[formName];

      //selectedCssProperty[formName] + 
      var name = htmlInputElement.name;
      var value = htmlInputElement.value;

      var rootCssProperty = 
         new CssProperty(getPropertyName(name), value);
      
      var rootCssElementAndProperty = 
         new CssElementAndProperty(getElementName(name), rootCssProperty);

      //log("setColorTimeout: rootCssElementAndProperty:" + rootCssElementAndProperty.toString());

      //Create an array of the changed css element and child elements
      var cssElementAndPropertyArray = 
         getChangedCssElementAndPropertyArray(
            selectedRootCssElementTree[formName], rootCssElementAndProperty);

      progressBar.set(15);

      var size = cssElementAndPropertyArray.length;
      for(var index = 0; index < size; index++)
      {
         var cssElementAndProperty = cssElementAndPropertyArray[index];
         //log("setColorTimout: cssElementAndProperty.toString(): " + cssElementAndProperty.toString());
         setStyleRule(cssElementAndProperty);
         progressBar.add(7);
      }

      updatePreviewStyle(document.getElementById(previewId));

   progressBar.hide();
}
