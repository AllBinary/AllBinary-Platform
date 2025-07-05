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
<%@ include file="templateBodies.jsp" %>
<%
/*
Inserting compound views for page views
 */

   Set compoundSet = viewNameKeyAndCompoundTemplateValueHashMap.keySet();
   Iterator compoundIter = compoundSet.iterator();
   while(compoundIter.hasNext())
   {
      String nextPage = (String) compoundIter.next();
      String nextTemplateFile = (String) viewNameKeyAndCompoundTemplateValueHashMap.get(nextPage);
      String objectConfigFile = GENERICBODYTEMPLATEOBJECTCONFIGFILE;
%>

<%
/*
Inserting compound views for page views
 */
%>
<transform:info 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>" 
   name="<%= storeName + commonStrings.SPACE + nextPage %>"
   objectFile="<%= STORETEMPLATECOMPOUNDVIEWOBJECTFILE %>" 
   objectConfigFile="<%= GENERICCOMPOUNDTEMPLATEOBJECTCONFIGFILE %>" 
   templateFile=""
   data="" 
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>

<%
/*
Inserting body views for compound views
 */
%>
<transform:info 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>" 
   name="<%= storeName + commonStrings.SPACE + nextPage + commonStrings.SPACE + BodyData.VIEWNAMEKEY %>" 
   objectFile="<%= GENERICVIEWOBJECTFILE %>"   
   objectConfigFile="<%= objectConfigFile %>" 
   templateFile="<%= nextTemplateFile %>"
   data=""
   type="<%= InputOutputTypeData.DB %>" >
</transform:info>
<%
   }
%>

<%
/*
Inserting views for page views
 */

   Set set = viewNameKeyAndTemplateValueHashMap.keySet();
   Iterator iter = set.iterator();
   while(iter.hasNext())
   {
      String nextPage = (String) iter.next();
      String nextTemplateFile = (String) viewNameKeyAndTemplateValueHashMap.get(nextPage);
%>

<%
/*
Inserting views for page views
 *templateFile is ignored by GENERICVIEWOBJECTFILE
 */

      Iterator iterTemplateType = templateTypesVector.iterator();
      while(iterTemplateType.hasNext())
      {
         String nextTemplateType = (String) iterTemplateType.next();
         String templateType = "";
         if(nextTemplateType.indexOf(TransformInfosData.PREVIEW) >= 0)
         {
            templateType = "Preview";
         }

         final String XSL_EXTENSION = ".xsl";
%>
<transform:info 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>" 
   name="<%= storeName + commonStrings.SPACE + nextPage + nextTemplateType %>"
   objectFile="<%= GENERICVIEWOBJECTFILE %>" 
   objectConfigFile="" 
   templateFile="<%= nextTemplateFile + templateType + XSL_EXTENSION %>"
   data=""
   type="<%= InputOutputTypeData.DB %>" > 
</transform:info>
<%
      }
   }
%>
