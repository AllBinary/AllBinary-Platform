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
<TITLE>Basic Product Creation - Store Admin</TITLE>
<%@ page import="javax.imageio.ImageIO" %>

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
<p><%= storeName %> - Basic Product Creation - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + BASICPRODUCTPAGE %>">Help</a></p>
<div class="main">

<%
//Inventory Tag Checks for page command
%>
<admin:inventory 
   command="<%= GLOBALS.INSERT %>" 
   storeName="<%= storeName %>" 
   xsl="<%= IGNOREXMLXSL %>" >

   <ecommerce:inventory isSelected="true" 
      command="<%= GLOBALS.INSERT %>" 
      storeName="<%= storeName %>" />

<form method="POST" action="<%= ADDPRODUCTPAGE %>">
<br><input class="largeSubmit" type="submit" value="Add Different Type Of Product" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>
<form method="POST" action="<%= CATEGORYSELECTIONPAGE %>">
<br><input class="largeSubmit" type="submit" value="Add Product With A Different Category" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</admin:inventory>

* = Required Fields<br>

<form method="POST" action="<%= BASICPRODUCTPAGE %>" enctype="multipart/form-data" >
<input type="hidden" name="<%= BasicItemData.INBASKETS %>" size="11" value="0">
<input type="hidden" name="<%= BasicItemData.CUSTOMS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.DOWNLOADS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.GROUPS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.OPTIONS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.PERMISSIONS %>" size="50" value="0" >
<input type="hidden" name="<%= BasicItemData.SPECIALS %>" size="50" value="0" >

<p>
<table class="main" border="0" cellpadding="2" cellspacing="2" style="border-collapse: collapse" bordercolor="#111111" width="500" >
   <tr>
      <td width="51%" valign="top" >*Enabled:</td>
      <td width="49%"><SELECT class="text" name='<%= EntryData.ENABLE %>' >
                         <OPTION>Yes</OPTION>
                         <OPTION>No</OPTION>
                      </SELECT>
      </td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Condition:</td>
      <td width="49%"><SELECT class="text" name='<%= BasicItemData.NEWORUSED %>' >
                         <OPTION>New</OPTION>
                         <OPTION>Used</OPTION>
                      </SELECT>
      </td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Unique ID:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.ID %>" size="18" value='<%= new ProductIdGenerator().getNext() %>' ></td> 
   </tr>
   <tr>
      <td width="51%" valign="top" >*Price:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.PRICE %>" size="18"></td>
   </tr> 
   <tr>
      <td width="51%" valign="top" >*Number:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.NUMBER %>" size="18"></td>
   </tr> 
   <tr>
      <td width="51%" valign="top" >*Weight:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.WEIGHT %>" size="18"></td>
   </tr>
   <tr>
      <td width="51%" valign="top" >*Summary:</td>
      <td width="49%"><input class="text" type="text" name="<%= BasicItemData.SUMMARY %>" size="50"></td>   
   </tr>
   <tr>
      <td width="51%" valign="top" >*Description:</td>
      <td width="49%"><textarea class="textarea" name="<%= BasicItemData.DESCRIPTION %>" rows="10" cols="48" wrap="virtual" ></textarea></td>
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
</table>
</p>

<%
StringMaker fileTypesBuffer = new StringMaker();

String fileTypes[] = ImageIO.getReaderFormatNames();
//fileTypesBuffer.append(" Readers: ");
for(int index2 = 0; index2 < fileTypes.length; index2++)
{
   fileTypesBuffer.append(fileTypes[index2] + commonStrings.SPACE);
}

StringMaker supportedMediaTypeBuffer = new StringMaker();
Set set = MediaData.toHashMap().keySet();
Iterator iter = set.iterator();
while(iter.hasNext())
{
   String mediaDataName = (String) iter.next();
   supportedMediaTypeBuffer.append(mediaDataName + commonStrings.SPACE);
}

/*
String fileTypes[] = ImageIO.getWriterFormatNames();
fileTypesBuffer.append(" Writers: ");
for(int index = 0; index < fileTypes.length; index++)
{
   fileTypesBuffer.append(fileTypes[index] + commonStrings.SPACE);
}
*/
%>
Resized Image Types Include: <%= fileTypesBuffer.toString() %><br><br>
Other Supported Media May Not Resize: <%= supportedMediaTypeBuffer.toString() %><br><br>
<table class="main" border="0" cellpadding="2" cellspacing="2" style="border-collapse: collapse" bordercolor="#111111" width="500" >
   <tr>
      <td width="51%" valign="top" >Image:</td>
      <td width="49%"><input class="text" type="file" name="<%= BasicItemData.IMAGE %>" size="37"></td>   
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
      <td width="49%"><textarea class="textarea" name="<%= BasicItemData.COMMENT %>" rows="10" cols="48" wrap="virtual" ></textarea></td>
   </tr>
</table>

<br><input class="submit" type="submit" value="<%= ADDPRODUCT %>" name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>
<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>