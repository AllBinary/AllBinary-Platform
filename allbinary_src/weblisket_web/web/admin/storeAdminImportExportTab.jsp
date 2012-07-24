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
   
<div id="storeAdminTabbedPane1" class="tab" >

<form method="POST" action="<%= STOREADMINPAGE %>">
<input class="text" type="hidden" value="<%= storeName %>" 
   name="<%= StoreFrontData.NAME %>"
<input type="hidden" 
   value="<%= IMPORTEXPORTTAB %>" 
   name="<%= TAB %>">

<input class="submit" type="submit" value="<%= GLOBALS.BACKUP %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="<%= GLOBALS.RESTORE %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</div>
