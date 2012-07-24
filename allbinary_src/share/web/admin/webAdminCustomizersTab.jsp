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
<div id="webAdminTabbedPane1" class="tab" >

Customizers:
<form method="POST" action="<%= WEBADMINPAGE %>" >

<customizers:view 
   storeName="<%= storeName %>" 
   templateFile="<%= CUSTOMIZERSVIEWTEMPLATEFILE %>" />

<p/>
<input type="hidden" 
   value="<%= CUSTOMIZERSTAB %>" 
   name="<%= TAB %>">
<input class="submit" type="submit" 
   value="<%= EDIT %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</div>
