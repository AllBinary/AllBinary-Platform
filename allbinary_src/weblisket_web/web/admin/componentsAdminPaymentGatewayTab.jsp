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

<div id="componentsAdminTabbedPane1" class="tab" >

<payment:gatewayViewExisting
   command="<%= GLOBALS.VIEW %>"
   xsl="<%= EXISTINGPAYMENTGATEWAYSXSL %>" />

<payment:gatewayViewUnused
   command="<%= GLOBALS.VIEW %>"
   xsl="<%= NEWPAYMENTGATEWAYSXSL %>" />
</div>
