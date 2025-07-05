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
<HTML>
<HEAD>
<TITLE>Product Management - Store Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<div class="mainHeading">
<p><%= storeName %> - Product Management - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + PRODUCTMANAGERPAGE %>">Help</a></p>
<div class="main">

<%
String ADDPRODUCT = "Add";
String VIEWALLPRODUCTS = "View All";
String REMOVEPRODUCT = "Remove";
String VIEWPRODUCT = "View";
String EDITPRODUCT = "Edit";
String UPDATEPRODUCT = "Update";

String PRODUCTSEARCH = "Search";
String PREVIEWPRODUCTSEARCH = "Preview";

Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.PRODUCTMANAGER);
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<%
   //for special multipart request
%>
<admin:inventory 
   command="<%= GLOBALS.UPDATE %>" 
   storeName="<%= storeName %>" 
   xsl="<%= IGNOREXMLXSL %>" >
   
   <ecommerce:inventory isSelected="true" 
      command="<%= GLOBALS.UPDATE %>" 
      storeName="<%= storeName %>" />
      
</admin:inventory>

<%
   if(command!=null && storeName!=null)
   {
      if(command.compareTo(UPDATEPRODUCT)==0)
      {
   //for special multipart request above
%>
<%
      }
      else
      if(command.compareTo(ADDPRODUCT)==0)
      {
%>
<jsp:forward page="<%= CATEGORYSELECTIONPAGE %>" />
<%
      }
      else
      if(command.compareTo(EDITPRODUCT)==0)
      {
%>
<form method="POST" action="<%= PRODUCTMANAGERPAGE %>" enctype="multipart/form-data" >
<admin:inventory 
   command="<%= GLOBALS.EDIT %>" 
   storeName="<%= storeName %>" 
   xsl="<%= BASICITEMINPUTFORMXSL %>" />
<br>
   <input class="submit" type="submit" 
      value="<%= UPDATEPRODUCT %>" name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>
<%
      }
      else
      if(command.compareTo(REMOVEPRODUCT)==0)
      {
%>
<admin:inventory 
   command="<%= GLOBALS.DELETE %>" 
   storeName="<%= storeName %>" 
   xsl="<%= IGNOREXMLXSL %>" >
   
   <ecommerce:inventory isSelected="true" 
      command="<%= GLOBALS.DELETE %>" 
      storeName="<%= storeName %>" />
      
</admin:inventory>
<%
      }
      else
      if(command.compareTo(VIEWALLPRODUCTS)==0)
      {
%>

<generic:inventory 
   command="<%= GLOBALS.VIEW %>"
   storeName="<%= storeName %>" 
   xsl="<%= INVENTORYXSL %>" />

<%
      }
      else
      if(command.compareTo(PRODUCTSEARCH)==0)
      {
//shows admin product listing
%>
<generic:inventory 
   command="<%= GLOBALS.SEARCH %>" 
   storeName="<%= storeName %>" 
   xsl="<%= INVENTORYSEARCHXSL %>" />
<%
      }
   }
%>

<form method="POST" action="<%= PRODUCTMANAGERPAGE %>">

<input type="hidden" value="<%= ADDPRODUCT %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="Add" />
</form>

<br/>

<form method="POST" action="<%= PRODUCTMANAGERPAGE %>">
Product ID(s): <input type="text" name="<%= BasicItemData.ID %>" size="11">
<input type="hidden" value="<%= REMOVEPRODUCT %>" name="<%= GLOBALS.ADMINCOMMAND %>">


<input class="submit" type="submit" value="Remove" />
</form>

<br/>

<form method="POST" action="<%= PRODUCTMANAGERPAGE %>">
Product ID(s): <input type="text" name="<%= BasicItemData.ID %>" size="11">

<input type="hidden" value="<%= EDITPRODUCT %>" name="<%= GLOBALS.ADMINCOMMAND %>">


<input class="submit" type="submit" value="Edit" />
</form>

<br/>

<form method="POST" action="<%= PRODUCTMANAGERPAGE %>">
<input type="hidden" name="<%= SearchData.PAGE %>" value="0" />

<input type="hidden" name="<%= SearchData.SORTBY %>" value="AlphaNumeric" />
<input type="hidden" name="<%= SearchData.ORDER %>" value="Ascending" />

Keywords:
<% final String ZERO_INDEX = "[0]"; %>
<input class="text"
   type="hidden" name="<%= SearchData.COLUMNNAME + ZERO_INDEX %>" value="<%= BasicItemData.KEYWORDS %>" />
<input class="text"
   type="text" name="<%= SearchData.COLUMNVALUE + ZERO_INDEX %>" />

<br/><br/><br/>

Length:
<SELECT class="select" name="<%= SearchData.LENGTH %>" >
<OPTION value="5" >5</OPTION>
<OPTION value="10" selected="yes" >10</OPTION>
<OPTION value="20" >20</OPTION>
<OPTION value="30" >30</OPTION>
</SELECT>

<br/><br/><br/>
<input type="hidden" value="<%= PRODUCTSEARCH %>" name="<%= GLOBALS.ADMINCOMMAND %>" />

<input class="submit" type="submit" value="Search" />
</form>

<form method="POST" action="<%= PRODUCTMANAGERPAGE %>">

<input type="hidden" value="<%= VIEWALLPRODUCTS %>" name="<%= GLOBALS.ADMINCOMMAND %>">
<input class="submit" type="submit" value="View All" />
</form>
</p>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>