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

<div id="internalCampaignsAdminTabbedPane1" class="tab" >
   
<form method="POST" action="internalCampaignsAdminInternalCampaignsTab.jsp" >

<advertisement:campaigns 
   command="<%= GLOBALS.VIEW %>" 
   storeName="<%= STORENAME %>" 
   xsl="<%= CAMPAIGNSXSL %>" />
   <br/><br/>

   <input class="wizardsubmit" type="submit" 
      value="Continue" name="AdminCommand" />
</form>

</div>
