<%
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 * 
 */
%>

<%
/*
Inserting page template customizer views
 */

   customizerSet = 
      viewNameKeyAndTemplateCustomizerPagesHashMap.keySet();
   customizerIter = customizerSet.iterator();
   while(customizerIter.hasNext())
   {
      String nextPage = (String) customizerIter.next();
      String nextTemplateFile = (String) 
         viewNameKeyAndTemplateCustomizerPagesHashMap.get(nextPage);
      //String nextObjectConfigFile = (String) 
      //   viewNameKeyAndCustomizerPageObjectConfigsHashMap.get(nextPage);
      String nextPreClassPath = (String)
         viewNameKeyAndCustomizerPagesClassPathsPreHashMap.get(nextPage);
      String nextPostClassPath = (String)
         viewNameKeyAndCustomizerPagesClassPathsPostHashMap.get(nextPage);
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
   (String) viewNameKeyAndCustomizerPagesObjectConfigsHashMap.get(nextPage);
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
