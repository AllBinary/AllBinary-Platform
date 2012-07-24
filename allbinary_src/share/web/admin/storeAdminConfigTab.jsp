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

<div id="storeAdminTabbedPane3" class="tab" >

Store Config:
<form method="POST" action="<%= STOREADMINPAGE %>" >
   <admin:storefront
      command="<%= GLOBALS.EDIT %>"
      storeName="<%= storeName %>"
      xsl="<%= IGNOREXMLXSL %>" >
      <admin:storefront
         command="<%= GLOBALS.EDIT %>"
         storeName="<%= storeName %>"
         xsl="<%= EDITSTOREFRONTXSL %>" />
   </admin:storefront>         
<p/>
<input type="hidden" 
   value="<%= STORETAB %>" 
   name="<%= TAB %>">
<input class="submit" type="submit" 
   value="<%= UPDATE %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>
</div>
