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

<div id="storeAdminTabbedPane4" class="tab" >

Warning: Admin access is required to open a store back up if it is Closed.<br />
<form method="POST" action="<%= STOREADMINPAGE %>">
<input class="text" type="hidden" value="<%= storeName %>" 
   name="<%= StoreFrontData.NAME %>"
<input type="hidden" 
   value="<%= OTHERTAB %>" 
   name="<%= TAB %>" />
<input class="submit" type="hidden" value="<%= StoreFrontData.CANCEL %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Close Store" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</div>
