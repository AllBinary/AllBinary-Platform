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


<div id="componentsAdminTabbedPane2" class="tab" >

<a href="#target1" onclick="selectTab('advertisementAdmin',1)" id="link1" class="tablink" >Internal Campaigns</a>
<a href="#target2" onclick="selectTab('advertisementAdmin',2)" id="link2" class="tablink" >Affiliate Program</a>
<a href="#target3" onclick="selectTab('advertisementAdmin',3)" id="link3" class="tablink" >Advertisements</a>

<%@ include file="advertisementAdminInternalCampaignsTab.jsp" %>
<%@ include file="advertisementAdminAffiliateProgramsTab.jsp" %>
<%@ include file="advertisementAdminAdvertisementsTab.jsp" %>

</div>