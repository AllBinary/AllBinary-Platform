<HTML>
<HEAD>
<TITLE>Product Search Page</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>

</HEAD>
<BODY COLOR=#ffffff>
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<generic:inventory 
   command="<%= GLOBALS.SEARCH %>" 
   storeName="<%= storeName %>" 
   xsl="<%= INVENTORYXSL %>" />
</BODY>
</HTML>