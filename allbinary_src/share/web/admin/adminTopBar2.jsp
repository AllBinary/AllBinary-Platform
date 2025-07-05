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
//Drop, Create, Import/Restore, and Export/Backup
%>
<div ID="topbar" class="adminTopBar2" >

<table cellspacing="2"  cellpadding="2">
<tr>

<td>
<A class="cloak" HREF="<%= AdminPageData.CONFIG %>"  >
<div ID="admin" class="topMenuItems"
   onMouseOver="changeBackgroundColor('admin','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('admin','<%= BUTTONCOLOR %>');return true;" >
Config
</div>
</A>
</td>

<td>
<A class="cloak" HREF="<%= AdminPageData.TABLES %>"  >
<div ID="admin1" class="topMenuItems"
   onMouseOver="changeBackgroundColor('admin1','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('admin1','<%= BUTTONCOLOR %>');return true;" >
Database
</div>
</A>
</td>

<td>
<A class="cloak" HREF="<%= AdminPageData.USERMANAGER %>"  >
<div ID="admin2" class="topMenuItems"
   onMouseOver="changeBackgroundColor('admin2','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('admin2','<%= BUTTONCOLOR %>');return true;" >
Users
</div>
</A>
</td>

<td>
<div ID="admin3" class="topMenuItems"
   onMouseOver="changeBackgroundColor('admin3','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('admin3','<%= BUTTONCOLOR %>');return true;" >
</div>
</td>

<td>
<div ID="admin4" class="topMenuItems"
   onMouseOver="changeBackgroundColor('admin4','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('admin4','<%= BUTTONCOLOR %>');return true;" >
</div>
</td>

</tr>
</table>
</div>
