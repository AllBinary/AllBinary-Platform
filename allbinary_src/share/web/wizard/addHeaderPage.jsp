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
//Try Again
%>
<HTML>
<HEAD>
<%@ include file="globals.jsp" %>
<TITLE>Store Wizard - Add Header - Step 3 of <%= TOTALSTEPS %></TITLE>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor="<%= BACKGROUNDCOLOR %>" >

<%@ include file="header.jsp" %>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
<jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<div class="wizardmainHeading" >
Add Header - Step 3 of <%= TOTALSTEPS %><p />
<div class="wizardmain" >

<%
   final String ATTEMPTS = "attempts";
   Integer attempts = (Integer) session.getAttribute(ATTEMPTS);
   if(attempts == null)
   {
      attempts = new Integer(1);
      session.setAttribute(ATTEMPTS,attempts);
   }
   else
   {
      session.setAttribute(ATTEMPTS,new Integer(attempts.intValue()+1));
   }
%>

<%
if(attempts.intValue() > 1)
{
%>
<workflow:basic 
   name="<%= storeName + commonStrings.SPACE + GLOBALS.INSERT + commonStrings.SPACE + HEADERPAGE + commonStrings.SPACE + CustomizerTransformInfoData.NAME %>" >
   <jsp:forward page="<%= ADDTEMPLATETHEMEPAGE %>" >
      <jsp:param name="AdminCommand" value="Edit" />
   </jsp:forward>
</workflow:basic>
<%
}
%>

<%
//note: won't display request info since multipartrequest is used.
//hack
%>
<form method="POST" action="<%= ADDHEADERPAGE %>" enctype="multipart/form-data" >

<workflow:basic
   name="<%= storeName + commonStrings.SPACE + GLOBALS.NEW + commonStrings.SPACE + HEADERPAGE + commonStrings.SPACE + CustomizerTransformInfoData.NAME %>" />

<p/>
<input type="hidden" name="<%= GLOBALS.ADMINCOMMAND %>" value="<%= ADDHEADERPAGE %>" />

<input class="wizardsubmit" type="submit" name="" value="Continue" />
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
