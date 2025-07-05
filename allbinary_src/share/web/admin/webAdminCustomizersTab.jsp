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
<div id="webAdminTabbedPane1" class="tab" >

Customizers:
<form method="POST" action="<%= WEBADMINPAGE %>" >

<customizers:view 
   storeName="<%= storeName %>" 
   templateFile="<%= CUSTOMIZERSVIEWTEMPLATEFILE %>" />

<p/>
<input type="hidden" 
   value="<%= CUSTOMIZERSTAB %>" 
   name="<%= TAB %>">
<input class="submit" type="submit" 
   value="<%= EDIT %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</div>
