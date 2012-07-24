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
Drop, Create, Import/Restore, and Export/Backup
 */
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
