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


<div id="componentsAdminTabbedPane2" class="tab" >

<a href="#target1" onclick="selectTab('advertisementAdmin',1)" id="link1" class="tablink" >Internal Campaigns</a>
<a href="#target2" onclick="selectTab('advertisementAdmin',2)" id="link2" class="tablink" >Affiliate Program</a>
<a href="#target3" onclick="selectTab('advertisementAdmin',3)" id="link3" class="tablink" >Advertisements</a>

<%@ include file="advertisementAdminInternalCampaignsTab.jsp" %>
<%@ include file="advertisementAdminAffiliateProgramsTab.jsp" %>
<%@ include file="advertisementAdminAdvertisementsTab.jsp" %>

</div>