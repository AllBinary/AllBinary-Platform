<%
/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/29/02
 *
 *
 *Modified By         When       ?
 *
 */
%>

<%
/*
Inserting include template customizer views
 */

   customizerSet = 
      viewNameKeyAndTemplateCustomizerIncludesHashMap.keySet();
   customizerIter = customizerSet.iterator();
   while(customizerIter.hasNext())
   {
      String nextPage = (String) customizerIter.next();
      String nextTemplateFile = (String) 
         viewNameKeyAndTemplateCustomizerIncludesHashMap.get(nextPage);
      //String nextObjectConfigFile = (String) 
      //   viewNameKeyAndCustomizerPageObjectConfigsHashMap.get(nextPage);
      String nextPreClassPath = (String)
         viewNameKeyAndCustomizerIncludesClassPathsPreHashMap.get(nextPage);
      String nextPostClassPath = (String)
         viewNameKeyAndCustomizerIncludesClassPathsPostHashMap.get(nextPage);
%>

<%
/*
Inserting customization form views - they allow the user to modify the generic 
 *content in a generic template by setting the xml.
 */
//generate view files on the fly using page string with generated and final prefix and final postfix
//Customizer
//objectConfigFile="%= nextObjectConfigFile %"

//objectFile="%= STOREFRONTCONTEXTCLASSPATH + "customizer." + nextClassPath + "." + nextViewPrefix + nextPage + "ValidationView" %"

      Iterator viewPrefixIter = viewPrefixVector.iterator();
      while(viewPrefixIter.hasNext())
      {
         String nextViewPrefix = (String) viewPrefixIter.next();
         
         //Future implementation will allow the removal of the second tag 
         //and allow the processing tag to display errors and form together
         String currentTemplateFile = nextTemplateFile;
         if(nextViewPrefix.compareTo(GLOBALS.INSERT)==0 ||
            nextViewPrefix.compareTo(GLOBALS.UPDATE)==0 ||
            nextViewPrefix.compareTo(GLOBALS.DELETE)==0)
            currentTemplateFile = IGNOREXMLXSL;

         String currentObjectConfig = 
   (String) viewNameKeyAndCustomizerIncludesObjectConfigsHashMap.get(nextPage);
         if(currentObjectConfig==null)
            currentObjectConfig = GENERICCUSTOMIZEROBJECTCONFIGFILE;

   final String VALIDATION_VIEW = "ValidationView";
%>

<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + nextViewPrefix + commonStrings.SPACE + nextPage + commonStrings.SPACE + CustomizerTransformInfoData.NAME %>"
   objectFile="<%= CUSTOMIZERSCLASSPATH + nextPreClassPath + commonStrings.PERIOD + nextViewPrefix + nextPostClassPath + VALIDATION_VIEW %>"
   objectConfigFile="<%= currentObjectConfig %>"
   type="<%= InputOutputTypeData.DB %>"
   templateFile="<%= currentTemplateFile %>" >
</transform:info>

<%
      }
   }
%>