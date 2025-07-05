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
