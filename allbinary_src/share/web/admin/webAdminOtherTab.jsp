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
<div id="webAdminTabbedPane6" class="tab" >

<%= WEBLISKETFEATURE %>

<table class="main" cellspacing="2"  cellpadding="2">
<tr>
<td>
<A class="cloak" HREF="<%= AUCTIONSPAGE %>"  >
<div ID="auctions" class="menuItems"
   onMouseOver="changeBackgroundColor('auctions','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('auctions','<%= BUTTONCOLOR %>');return true;" >
Auctions
</div></A></td>
</tr>

<tr>
<td>
<A class="cloak" HREF="<%= BLOGSPAGE %>"  >
<div ID="blogs" class="menuItems"
   onMouseOver="changeBackgroundColor('blogs','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('blogs','<%= BUTTONCOLOR %>');return true;" >
Blogs
</div></A></td>
</tr>

<tr>
<td>
<A class="cloak" HREF="<%= CHATSPAGE %>"  >
<div ID="chats" class="menuItems"
   onMouseOver="changeBackgroundColor('chats','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('chats','<%= BUTTONCOLOR %>');return true;" >
Chats
</div></A></td>
</tr>

<tr>
<td>
<A class="cloak" HREF="<%= FORUMSPAGE %>"  >
<div ID="forums" class="menuItems"
   onMouseOver="changeBackgroundColor('forums','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('forums','<%= BUTTONCOLOR %>');return true;" >
Forums
</div></A></td>
</tr>

<tr>
<td>
<A class="cloak" HREF="<%= WIKISPAGE %>"  >
<div ID="wikis" class="menuItems"
   onMouseOver="changeBackgroundColor('wikis','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('wikis','<%= BUTTONCOLOR %>');return true;" >
Wikis
</div></A></td>
</tr>

</table>
</div>
