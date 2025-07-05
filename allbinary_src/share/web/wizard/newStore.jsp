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
   <ecommerce:storefronts isSelected="true" command="<%= GLOBALS.INSERT %>" />

   <ecommerce:authentication 
      command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
      userName="<%= userName %>" 
      password="<%= password %>" 
      roles="<%= roles %>" >

<% 
   storeName = (String) session.getAttribute(StoreFrontData.NAME);
%>

<%
   //insert templates
   Iterator templateTypesIter = templateTypesVector.iterator();
   while(templateTypesIter.hasNext())
   {
      String templateType = (String) templateTypesIter.next();
      String currentTemplateByTemplateType = GENERICTEMPLATEFILE;
      if(templateType.indexOf(TransformInfosData.PREVIEW) >= 0)
      {
         currentTemplateByTemplateType = PREVIEWGENERICTEMPLATEFILE;
      }

      String currentViewFile = STOREFRONTCONTEXTGENERATORVIEWOBJECTFILE;
      if(templateType.indexOf(TransformInfosData.PREVIEW) >= 0)
      {
         currentViewFile = PREVIEWSTOREFRONTCONTEXTGENERATORVIEWOBJECTFILE;
      }

      Set set = templateTypeViewInfoHashMap.keySet();
      Iterator iter = set.iterator();
      while(iter.hasNext())
      {
         String viewName = (String) iter.next();
         TransformInfoProperties transformInfoProperties = 
            (TransformInfoProperties) templateTypeViewInfoHashMap.get(viewName);
         //objectFile=transformInfoProperty.getViewFile() 
         
%>
<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + templateType + commonStrings.SPACE + transformInfoProperties.getName() + commonStrings.SPACE + GeneratorTransformInfoData.NAME %>"
   objectFile="<%= currentViewFile %>"
   objectConfigFile="<%= TEMPLATEDIR + TYPEDIR + transformInfoProperties.getObjectConfigFile() + GENERICTEMPLATEOBJECTCONFIG %>"
   templateFile="<%= TEMPLATEDIR + TYPEDIR + transformInfoProperties.getTemplateFile() + currentTemplateByTemplateType %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>

<transform:info
   command="<%= GLOBALS.INSERT %>"
   storeName="<%= storeName %>"
   name="<%= storeName + templateType + commonStrings.SPACE + transformInfoProperties.getName() %>"
   objectFile="<%= GENERICVIEWOBJECTFILE %>"
   objectConfigFile="<%= TEMPLATEDIR + TYPEDIR + transformInfoProperties.getObjectConfigFile() + GENERICTEMPLATEOBJECTCONFIG %>" 
   templateFile="<%= TEMPLATEDIR + TYPEDIR + transformInfoProperties.getTemplateFile() + currentTemplateByTemplateType %>"
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>
<%
      }
   }
%>

      <jsp:forward page="<%= TEMPLATETYPEPAGE %>" />
      </ecommerce:authentication>   
   
