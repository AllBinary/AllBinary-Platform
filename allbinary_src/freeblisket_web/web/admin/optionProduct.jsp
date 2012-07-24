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
<%@ page import="allbinary.business.user.commerce.inventory.item.option.BasicOptionItemData" %>
<HTML>
<HEAD>
<TITLE>Option Product Creation - Store Admin</TITLE>

<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
String ADDPRODUCT = "Add Product";

Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.PRODUCTMANAGER);

String ADD_OPTION_WITH_JAVASCRIPT = "AddOption";
String ADD_PRODUCT_WITH_JAVASCRIPT = "AddProduct";
String OPTION_SELECTION_JAVASCRIPT = "RemoveOption";
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Basic Option Product Creation - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + BASICOPTIONPRODUCTPAGE %>">Help</a></p>
<div class="main">

<%= WEBLISKETFEATURE %>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>