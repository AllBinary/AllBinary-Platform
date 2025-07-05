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

<a href="#target1" onclick="selectTab('componentsAdmin',1)" id="componentsAdminLink1" class="tablink" >Payment Gateways</a>
<a href="#target2" onclick="selectTab('componentsAdmin',2)" id="componentsAdminLink2" class="tablink" >Advertisements</a>
<a href="#target3" onclick="selectTab('componentsAdmin',3)" id="componentsAdminLink3" class="tablink" >Shipping</a>
<a href="#target4" onclick="selectTab('componentsAdmin',4)" id="componentsAdminLink5" class="tablink" >Taxes</a>
<a href="#target5" onclick="selectTab('componentsAdmin',5)" id="componentsAdminLink6" class="tablink" >Other</a>

<%@ include file="componentsAdminPaymentGatewayTab.jsp" %>
<%@ include file="componentsAdminAdvertisementAdminTab.jsp" %>
<%@ include file="componentsAdminShippingTab.jsp" %>
<%@ include file="componentsAdminTaxesTab.jsp" %>
<%@ include file="componentsAdminOtherTab.jsp" %>

<div id="componentsAdminTabbedPane5" >
Other Components For Blisket E-Commerce Family&trade;:

Rules
Wizards
Topology
Partners
Languages
Workflows
Clustering
Tax Filing
Warehousing
Custom Items
Drop Shipment
Manufacturing
Site Map/Graph
Load Balancing
Code Generators
External Hardware
Templating Engines
Third Party - Amazon, Ebay, and more
Much Much More...

</div>

</div>
