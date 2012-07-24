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
<%@ page import="allbinary.business.user.inventory.item.download.DownloadItemData" %>
<HTML>
<HEAD>
<TITLE>Downloadable Product Creation - Store Admin</TITLE>
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
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Downloadable Product Creation - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + DOWNLOADPRODUCTPAGE %>">Help</a></p>
<div class="main">

<%
   if(command!=null && storeName!=null)
   {
      if(command.compareTo(ADDPRODUCT)==0)
      {
%>
<form method="POST" action="<%= ADDPRODUCTPAGE %>">
<br>
<input class="submit" type="submit" 
   value="Add Different Type Of Product" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>
<form method="POST" action="<%= CATEGORYSELECTIONPAGE %>">
<br>
<input class="submit" type="submit" 
   value="Add Product With A Different Category" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

<p>
<ecommerce:inventory isSelected="true" 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>" 
/>
</p>

<%
      }
   }
%>

* = Required Fields<br>

<form method="POST" action="<%= DOWNLOADPRODUCTPAGE %>" enctype="multipart/form-data" >
<input type="hidden" name="<%= BasicItemData.NUMBER %>" size="11" value="0" >
<input type="hidden" name="<%= BasicItemData.WEIGHT %>" size="11" value="0" >
<input type="hidden" name="<%= BasicItemData.INBASKETS %>" size="11" value="0">
<input type="hidden" name="<%= BasicItemData.NEWORUSED %>" size="11" value="New">
<input type="hidden" name="<%= BasicItemData.CUSTOMS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.DOWNLOADS %>" size="50" value="1" >
<input type="hidden" name="<%= BasicItemData.GROUPS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.OPTIONS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.PERMISSIONS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.SPECIALS %>" size="50" value="0" >

<p>
<table class="main" border="0" cellpadding="2" cellspacing="2" style="border-collapse: collapse" bordercolor="#111111" width="550" >
   <tr>
      <td width="51%" valign="top" >*Enabled:</td>
      <td width="49%"><select class="text" name='<%= EntryData.ENABLE %>' >
                         <OPTION>Yes</OPTION>
                         <OPTION>No</OPTION>
                      </SELECT>
      </td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Unique ID:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.ID %>" size="18" value='<%= new allbinary.helpers.generators.ProductIdGenerator().getNext() %>' ></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >*Price:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.PRICE %>" size="18"></td>
   </tr> 
   <tr>
      <td width="51%" valign="top" >*Summary:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.SUMMARY %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >*Description:</td>
      <td width="49%"><textarea name="<%= BasicItemData.DESCRIPTION %>" rows="10" cols="48" wrap="virtual" ></textarea></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Keywords:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.KEYWORDS %>" size="50"></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Category:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.CATEGORY %>" size="50" value="<%= category %>" ></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Type:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.TYPE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >*License File:</td>
      <td width="49%"><input class="text" type="text" name="<%= DownloadItemData.LICENSE_FILE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >*Download File:</td>
      <td width="49%"><input class="text" type="text" name="<%= DownloadItemData.FILE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >*Expiration:</td>
      <td width="49%"><input class="text" type="text" name="<%= DownloadItemData.EXPIRATION %>" size="50" value="drop down" ></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Retries:</td>
      <td width="49%"><input class="text" type="text" name="<%= DownloadItemData.RETRIES %>" size="50"></td>   
   </tr>
</table>
</p>

<table class="main" border="0" cellpadding="2" cellspacing="2" style="border-collapse: collapse" bordercolor="#111111" width="550" >
   <tr>
      <td width="51%" valign="top" >Small Image:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.SMALLIMAGE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Medium Image:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.MEDIUMIMAGE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Large Image:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.LARGEIMAGE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Distributor:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.DISTRIBUTOR %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >ID Used By Distributor:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.IDUSEDBYDISTRIBUTOR %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Produced By:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.PRODUCEDBY %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Production Date:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.PRODUCTIONDATE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >First Production Date:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.STARTPRODUCTIONDATE %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >Comment:</td>
      <td width="49%"><textarea name="<%= BasicItemData.COMMENT %>" rows="10" cols="48" wrap="virtual" ></textarea></td>
   </tr>
</table>

<br>
<input class="submit" type="submit" 
   value="<%= ADDPRODUCT %>" name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>
<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>