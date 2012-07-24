<div ID="sideBar" class="topBar" >
<table cellspacing="2"  cellpadding="2">
<tr>

<td>
<A class="cloak" HREF="<%= STOREADMINPAGE %>"  >
<div ID="storeAdmin" class="menuItems"
   onMouseOver="changeBackgroundColor('storeAdmin','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('storeAdmin','<%= BUTTONCOLOR %>');return true;" >
Store Admin
</div></A></td>

<td>
<A class="cloak" HREF="<%= WEBADMINPAGE %>"  >
<div ID="other" class="menuItems"
   onMouseOver="changeBackgroundColor('other','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('other','<%= BUTTONCOLOR %>');return true;" >
Customize
</div></A></td>

<td>
<A class="cloak" HREF="<%= STOREHELPPAGE %>"  >
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

<%
Vector adminRole = new Vector();
adminRole.add(BasicUserRole.ADMINISTRATOR);

Vector storeRole = new Vector();
storeRole.add(BasicUserRole.STOREMANAGER);
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
   roles="<%= adminRole %>" >
<td>
<A class="cloak" HREF="<%= AdminPageData.ADMIN %>"  >
<div ID="admin" class="menuItems"
   onMouseOver="changeBackgroundColor('admin','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('admin','<%= BUTTONCOLOR %>');return true;" >
Admin
</div></A></td>
</ecommerce:authentication>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
   roles="<%= storeRole %>" >
<td>
<div ID="admin" class="menuItems"
   onMouseOver="changeBackgroundColor('admin','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('admin','<%= BUTTONCOLOR %>');return true;" >
</div></td>
</ecommerce:authentication>

</tr>
</table></div>

