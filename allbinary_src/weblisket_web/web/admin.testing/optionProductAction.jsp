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
<%@ taglib
       uri="/WEB-INF/ecommerce.tld"
       prefix="ecommerce"
%>
<%@ page import="org.allbinary.business.user.inventory.item.BasicOptionItemData" %>
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

<ecommerce:inventory isSelected="true" 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>"
/>

<jsp:forward page="<%= OPTIONPRODUCTPAGE %>" >
<jsp:param name="PRODUCT" value="ACTIONCOMPLETE" />
</jsp:forward>

</BODY>
</HTML>