var tabSections = new Array();
var selectToDivMap = new Array();

function selectTab(tabName, selectedIndex)
{
   var numberOfTabs = tabSections[tabName];
   var colourOfInactiveTab = "#0000AA";
   var colourOfActiveTab = "#DDDDDD";
   var colourOfInactiveLink = "#DDDDDD";
   var colourOfActiveLink = "#FFFFFF";

   if (document.getElementById)
   {
      for (index=1;index<numberOfTabs+1;index++)
      {
         var tabbedPaneElement = document.getElementById(tabName + 'TabbedPane' + index);
         if(tabbedPaneElement != null)
         {
            tabbedPaneElement.style.display='none';
         }

         var tabLinkElement = document.getElementById(tabName + 'Link' + index);
         if(tabLinkElement != null)
         {
            tabLinkElement.style.background=colourOfInactiveTab;
            tabLinkElement.style.color=colourOfInactiveLink;
         }
      }

      var selectedTabbedPaneElement = document.getElementById(tabName + 'TabbedPane' + selectedIndex);
      if(selectedTabbedPaneElement != null)
      {
         selectedTabbedPaneElement.style.display='block';
      }

      var selectedTabLinkElement = document.getElementById(tabName + 'Link'+ selectedIndex);
      if(selectedTabLinkElement != null)
      {
         selectedTabLinkElement.style.background=colourOfActiveTab;
         selectedTabLinkElement.style.color=colourOfActiveLink;
      }
   }
}

function initTabs(sectionName,startTab,numberOfTabs)
{
   tabSections[sectionName] = numberOfTabs;
   selectTab(sectionName,startTab);
}

function select(id)
{
   var numberOfDivs = 100;
   
   for (currentDiv=1; currentDiv<numberOfDivs; currentDiv++)
   {
      var currentDescriptionElement = document.getElementById('description' + currentDiv);
      if(currentDescriptionElement != null)
      {
         currentDescriptionElement.style.display='none';
      }
      else
      {
         break;
      }
   }

   var descriptionElement = document.getElementById('description' + id);
   if(descriptionElement != null)
   {
      descriptionElement.style.display='block';
   }
}

//Show the Div associated with the html select element value
function showDivWithIdFromHtmlElementValue(htmlElement)
{
   var value = selectToDivMap[htmlElement.value];
   select(value);
}

function initDivs()
{
   select(1);
}
