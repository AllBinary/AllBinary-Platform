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