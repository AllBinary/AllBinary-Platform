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
