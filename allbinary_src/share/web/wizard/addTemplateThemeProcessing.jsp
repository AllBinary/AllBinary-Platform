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
   final String selectedView = " Theme" + commonStrings.SPACE + CustomizerTransformInfoData.NAME;
%>

<%
   if(command!=null)
   {
         if(command.compareTo("Continue")==0)
         {
            //currently data is set and the viewinfo is updated in the db instead of updating existing data 
            //(new implementation may allow multilple customizers to manipulate a single view)
%>
<workflow:basic name="<%= storeName + commonStrings.SPACE + GLOBALS.INSERT + selectedView %>" >
   <jsp:forward page="<%= ADDTEMPLATESTYLEPAGE %>" />
</workflow:basic>
<%
      }
   }
%>
