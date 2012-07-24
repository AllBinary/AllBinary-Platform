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
