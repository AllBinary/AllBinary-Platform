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
<TITLE>Category Selection - Store Admin</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<%@ include file="storeTopBar.jsp" %>
<%@ include file="storeTopBar2.jsp" %>
<%
String SELECTCATEGORY = "Select Category";

String FLASHVARS = "FlashVars";

/*
byte byteArray[] = session.getId().getBytes();

StringBuffer sessionId = new StringBuffer();

for(int index = 0; index < byteArray.length; index++)
{
   Integer nextByte = new Integer(byteArray[index]);
   String nextByteString = "%" + nextByte.toHexString(nextByte.intValue());
   sessionId.append(nextByteString);
}
sessionId.toString()
*/

BasicStoreFrontInterface storeFrontInterface = 
   BasicStoreFrontFactory.getInstance().getInstance(storeName);

String WEBLISKETHOSTANDPATH = 
   storeFrontInterface.getCurrentHostName();

String JSESSIONID = "JSESSIONID=" + session.getId();
String ROOTURL = "rootUrl=" + WEBLISKETHOSTANDPATH + "/admin/";
//String ROOTURL = "rootUrl=http://www.securedcheckoutserver.com:8080/weblisket/admin/";
String STOREKEY = "storeName=";

Vector roles = new Vector();
roles.add(BasicUserRole.ADMINISTRATOR);
roles.add(BasicUserRole.STOREMANAGER);
roles.add(BasicUserRole.PRODUCTMANAGER);

//Location = %= WEBLISKETHOSTANDPATH %
%>
<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="mainHeading">
<p><%= storeName %> - Category Selection - <a href="<%= STOREADMINFREEBLISKETONLINEHELP + CATEGORYSELECTIONPAGE %>">Help</a></p>
<div class="main">

<OBJECT classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
 codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0"
 WIDTH="480" HEIGHT="640" id="categoryTree" ALIGN="" >
 <param name="allowScriptAccess" value="sameDomain" />
 <param name="movie" value="categoryTree.swf" />
 <param name="quality" value="high" />
 <param name="bgcolor" value="#ffffff" />
 <param name="<%= FLASHVARS %>" 
        VALUE="<%= JSESSIONID %>&<%= ROOTURL %>&<%= STOREKEY + storeName %>" />

 <EMBED <%= FLASHVARS %>="<%= JSESSIONID %>&<%= ROOTURL %>&<%= STOREKEY + storeName %>" 
  src="categoryTree.swf" quality="high" bgcolor="#FFFFFF"
  WIDTH="480" HEIGHT="640" NAME="categoryTree" ALIGN=""
  allowScriptAccess="sameDomain" TYPE="application/x-shockwave-flash" 
  PLUGINSPAGE="http://www.macromedia.com/go/getflashplayer" >
 </EMBED>

</OBJECT>
<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>