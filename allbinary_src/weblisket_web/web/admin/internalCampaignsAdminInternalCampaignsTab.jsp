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
