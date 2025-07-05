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
<TITLE>Add Products - Product Management - Store Admin</TITLE>
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
//Create Product Category
//Create Product Type
%>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<%
/*
String lastCategory = category;
if(lastCategory!=null || lastCategory.compareTo("")!=0)
{
   session.setAttribute(CategoryData.CATEGORY, category);
}
*/

//if(category==null || category.compareTo("")==0)
{

//CategoryData.CATEGORY
   category = (String) request.getParameter("category");
   session.setAttribute(CategoryData.NAME, category);
}
%>

<div class="mainHeading">
<p><%= storeName %> - Product Creation By Type - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + ADDPRODUCTPAGE %>">Help</a></p>
<div class="main">
<p>Select one of the following:</p>

<table class="main" cellspacing="2"  cellpadding="2">

<tr><td>
<A class="cloak" HREF="<%= BASICPRODUCTPAGE %>"  >
<div ID="basic" class="productMenuItems"
   onMouseOver="changeBackgroundColor('basic','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('basic','<%= BUTTONCOLOR %>');return true;" >
Basic Product
</div></A></td>
<td> - Toys, Electronics, Books, Clothing, And The Like...</td>
</tr>

<tr><td>
<A class="cloak" HREF="<%= DOWNLOADPRODUCTPAGE %>"  >
<div ID="download" class="productMenuItems"
   onMouseOver="changeBackgroundColor('download','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('download','<%= BUTTONCOLOR %>');return true;" >
Downloadable Product
</div></A></td>
<td> - Software, E-Books, Documents, Music, Video, and More...</td>
</tr>

<tr><td>
<A class="cloak" HREF="<%= BASICGROUPPRODUCTPAGE %>"  >
<div ID="group" class="productMenuItems"
   onMouseOver="changeBackgroundColor('group','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('group','<%= BUTTONCOLOR %>');return true;" >
Group Product
</div></A>
</td>
<td> - Creates A Single Product From More Than One Existing Product - Book Series, Video Series, Similar Products, and More...</td>
</tr>

<tr><td>
<A class="cloak" HREF="<%= BASICOPTIONPRODUCTPAGE %>"  >
<div ID="option" class="productMenuItems"
   onMouseOver="changeBackgroundColor('option','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('option','<%= BUTTONCOLOR %>');return true;" >
Option Product
</div></A></td>
<td> - Allows Single Product Listing For Multiple Items - Clothing Sizes and Colors etc...</td>
</tr>

<tr><td>
<A class="cloak" HREF="<%= PRODUCTPERMISSIONSPAGE %>"  >
<div ID="permissions" class="productMenuItems"
   onMouseOver="changeBackgroundColor('permissions','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('permissions','<%= BUTTONCOLOR %>');return true;" >
Add Permissions
</div></A></td>
<td> - Creates Special Pricing for a specific type of User - Wholesale, Distributor, and so on...</td>
</tr>

<tr><td>
<A class="cloak" HREF="<%= PRODUCTSPECIALSPAGE %>"  >
<div ID="specials" class="productMenuItems"
   onMouseOver="changeBackgroundColor('specials','<%= BUTTONOVERCOLOR %>');return true;"
   onMouseOut="changeBackgroundColor('specials','<%= BUTTONCOLOR %>');return true;" >
Add Specials
</div></A></td>
<td> - Creates Special Pricing for a given time period - 1 day, 1 week, 1 year, and so on...</td>
</tr>

</table>

<%@ include file="copyright.jsp" %>
</div>
</div>
</BODY>
</HTML>
