<script language="JavaScript">

var optionEvent = "onClick";

var max = 10;
var numOfOptions = 0;
var value = "";
var product = "";
var selected = -1;

var selectedCategory = "";
var newCategory = "";

function changeBackgroundColor(tag,newColor)
{
   if(!document.getElementById) return;
   colorNode=document.getElementById(tag);
   colorNode.style.backgroundColor=newColor;
}

function setNewOptionValue(newValue)
{
   value = newValue;
}

function setProduct(newValue)
{
   product = newValue;
}

function setSelectedOption(optionNumber)
{
   selected = optionNumber;
}

function setSelectedCategory(category)
{
   selectedCategory = category;
}

function setNewCategory(category)
{
   newCategory = category;
}

function addOption(tag)
{
   if(!document.getElementById) return;
   var addNode = document.getElementById(tag);
  
   if(addNode)
   {
      var newOptionNode = document.createElement("OPTION");
      
      if(numOfOptions < max)
      {
         if(value)
         {
            newOptionNode.setAttribute(optionEvent,"setSelectedOption('" + numOfOptions + "');");
            newOptionNode.innerHTML = value + "=>" + product;
            addNode.appendChild(newOptionNode);
            numOfOptions++;
         }
      }
   }
}

function addCategory(tag)
{
   if(!document.getElementById) return;
   var addNode = document.getElementById(tag);
  
   if(addNode)
   {
      var newOptionNode = document.createElement("OPTION");
      
      if(value)
      {
         newOptionNode.setAttribute(optionEvent,"setSelectedCategory('" + newCategory + "');");
         newOptionNode.innerHTML = newCategory;
         addNode.appendChild(newOptionNode);      
      }
   }
}

function removeSelectedOption(tag)
{   
   if(!document.getElementById) return;

   var selectNode = document.getElementById(tag);
   var optionNodeList = selectNode.childNodes;

   if(selected!=-1)
   {   
      if(optionNodeList[selected])
      {      
         selectNode.removeChild(optionNodeList[selected]);
         numOfOptions--;
         selected=-1;

         var index = 0;
         while(optionNodeList[index])
         {
            optionNodeList[index].setAttribute(optionEvent,"setSelectedOption('" + index + "');");
            index++;
         }     
      }      
   }
}

function a(tag)
{
   if(!document.getElementById) return;

   var selectNode = document.getElementById(tag);
   var optionNodeList = selectNode.childNodes;

}

function clearOptions(tag)
{   
   if(!document.getElementById) return;

   var selectNode = document.getElementById(tag);
   var optionNodeList = selectNode.childNodes;

   while(optionNodeList[0])
   {
      selectNode.removeChild(optionNodeList[0]);      
   }
   numOfOptions = 0;
   selected = -1;
}

</script>