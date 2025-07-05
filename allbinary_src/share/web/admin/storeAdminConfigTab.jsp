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
