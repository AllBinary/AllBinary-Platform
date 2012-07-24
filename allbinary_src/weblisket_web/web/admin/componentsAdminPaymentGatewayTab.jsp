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

<div id="componentsAdminTabbedPane1" class="tab" >

<payment:gatewayViewExisting
   command="<%= GLOBALS.VIEW %>"
   xsl="<%= EXISTINGPAYMENTGATEWAYSXSL %>" />

<payment:gatewayViewUnused
   command="<%= GLOBALS.VIEW %>"
   xsl="<%= NEWPAYMENTGATEWAYSXSL %>" />
</div>
