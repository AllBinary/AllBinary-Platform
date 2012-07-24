<div ID="topbar" class="adminTopBar" >

<table cellspacing="2"  cellpadding="2">
<tr>

<td>
<A class="cloak" HREF="<%= AdminPageData.STORES %>"  >
<div ID="stores" class="menuItems"
   onMouseOver="changeBackgroundColor('stores','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('stores','<%= BUTTONCOLOR %>');return true;" >
Stores
</div></A></td>

<td>
<A class="cloak" HREF="<%= AdminPageData.ADMIN %>"  >
<div ID="db" class="menuItems"
   onMouseOver="changeBackgroundColor('db','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('db','<%= BUTTONCOLOR %>');return true;" >
Admin
</div></A></td>

<td>
<A class="cloak" HREF="<%= AdminPageData.REPORTS %>"  >
<div ID="reports" class="menuItems"
   onMouseOver="changeBackgroundColor('reports','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('reports','<%= BUTTONCOLOR %>');return true;" >
Reports
</div></A></td>

<td>
<A class="cloak" HREF="<%= AdminPageData.HELP %>"  >
<div ID="help" class="topMenuItems"   
   onMouseOver="changeBackgroundColor('help','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('help','<%= BUTTONCOLOR %>');return true;" >
Help
</div></A></td>

<td>
<A class="cloak" HREF="<%= LOGOUTPAGE %>"  >
<div ID="logout" class="topMenuItems"
   onMouseOver="changeBackgroundColor('logout','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('logout','<%= BUTTONCOLOR %>');return true;" >
Logout
</div></A></td>

</tr>
</table>
</div>
