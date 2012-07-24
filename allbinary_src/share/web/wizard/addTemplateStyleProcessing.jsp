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
   final String selectedView = " css" + commonStrings.SPACE + CustomizerTransformInfoData.NAME;
%>

<%
   //define tab commands
   final String ADVANCEDSTYLETAB = "AdvancedStyleTab";
   final String BASICSTYLETAB = "BasicStyleTab";

   if(command!=null && tabCommand!=null)
   {
      if(tabCommand.compareTo(BASICSTYLETAB)==0 || 
         tabCommand.compareTo(ADVANCEDSTYLETAB)==0)
      {
         if(command.compareTo("Continue")==0)
         {
            //currently data is set and the viewinfo is updated in the db instead of updating existing data 
            //(new implementation may allow multilple customizers to manipulate a single view)
%>
<workflow:basic name="<%= storeName + commonStrings.SPACE + GLOBALS.INSERT + commonStrings.SPACE + BASIC + selectedView %>" >
   <jsp:forward page="<%= ADDDEFAULTDATAFROMGENERICCUSTOMIZERSPAGE %>" />
</workflow:basic>
<%
         }
      }
   }
%>
