<%
/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
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
<%@ include file="globals.jsp" %>
<TITLE>Website Wizard - Installation - Step 1 of <%= TOTALSTEPS %></TITLE>
<%@ include file="javascript.jsp" %>

<script language="JavaScript">

<%@ include file="/javascript/support/javascriptIsBrowserSupported.jsp" %>

function init()
{
   initIsBrowserSupported();
}

</script>

<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor="<%= BACKGROUNDCOLOR %>" onload="init();" >

<%@ include file="header.jsp" %>

<%
   final String ATTEMPTS = "attempts";
   Integer attempts = (Integer) session.getAttribute(ATTEMPTS);
   if(attempts == null)
   {
      session.setAttribute(ATTEMPTS,new Integer(1));
   }
   else
   {
      session.setAttribute(ATTEMPTS,new Integer(attempts.intValue()+1));
   }

   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);
%>

<%
//The store wizard makes the following assumptions
//1.  The new store managers email is used for order notifactions
//2.  The store name is used as the subpath for the store since this 
//creates a store on the checkout server.
//Note: in the future a customer may purchase webhosting or weblisket at 
//anytime from AllBinary and move the virtual store to its own domain name.
//3.  The store manager uses email for other notification
//4.  FTP is not required since it is on the checkout server.
//5.  generic store tags are used - generic package is default

   String homeHostName
      = (String) request.getScheme() + "://" + 
           request.getServerName() + ":" + 
           new Integer(request.getServerPort()).toString() + 
           request.getContextPath() + "/";

   //verify that license was agreed to
   boolean isLicenseAgreedTo = false;
   String licenseAgreement = request.getParameter(GLOBALS.LICENSEAGREEMENT);
   if(licenseAgreement!=null && licenseAgreement.compareTo("on")==0)
   {
      isLicenseAgreedTo=true;
   }
%>
<div class="wizardmainHeading" >                
Store Wizard - Start Page - Step 1 of <%= TOTALSTEPS %><p>
<div class="wizardmain" >

<%
if(attempts!=null && attempts.intValue() > 0)
{
   if(isLicenseAgreedTo)
   {
   //add new storemanager and storefront to database
%>

<% 
   //If the xml file is not there an exception will occur
      HashMap templateTypeViewInfoHashMap = 
         new TransformInfoPropertiesDocument(
            URLGLOBALS.getWebappPath() + 
            "wizard/genericTemplatesConfig.xml").toTransformInfoPropertiesHashMap();
%>

<admin:user
   command="<%= GLOBALS.INSERT %>" 
   role="<%= BasicUserRole.STOREMANAGER.toString() %>"
   xsl="<%= IGNOREXMLXSL %>" >
   <br />
   <admin:storefront
      command="<%= GLOBALS.INSERT %>" 
      storeName="<%= storeName %>" 
      xsl="<%= IGNOREXMLXSL %>" >

      <ecommerce:user
         command="<%= GLOBALS.INSERT %>" 
         role="<%= BasicUserRole.STOREMANAGER.toString() %>" 
         enable="No" 
         isSelected="true" >
      </ecommerce:user>

<%@ include file="newStore.jsp" %>

   </admin:storefront>
</admin:user>

<admin:user
   command="<%= GLOBALS.UPDATE %>" 
   role="<%= BasicUserRole.STOREMANAGER.toString() %>"
   xsl="<%= IGNOREXMLXSL %>" >
   <br />
   <admin:storefront
      command="<%= GLOBALS.INSERT %>" 
      storeName="<%= storeName %>" 
      xsl="<%= IGNOREXMLXSL %>" >
      
      <ecommerce:user
         command="<%= GLOBALS.UPDATE %>" 
         role="<%= BasicUserRole.STOREMANAGER.toString() %>" 
         isSelected="true" >
      </ecommerce:user>

<%@ include file="newStore.jsp" %>

   </admin:storefront>
</admin:user>
<%
   }
   else
   {
%>
If you agree to the license below you must check the box at the end of the<br>
page to continue the installation.<p />
<%
   }
}
%>

<form method="POST" action="<%= STARTPAGE %>">
<table class="wizardtable" >

<tr>
<td>First Name:</td>
<td><input class="wizardtext" type="text" name="<%= UserData.FIRSTNAME %>" value=""></td>
</tr>

<tr>
<td>Last Name:</td>
<td><input class="wizardtext" type="text" name="<%= UserData.LASTNAME %>" value=""></td>
</tr>

<tr>
<td>Store Name:</td>
<td><input class="wizardtext" type="text" name="<%= StoreFrontData.NAME %>" size="30" value=""></td>
</tr>

<tr>
<td>Email Address:</td>
<td><input class="wizardtext" type="text" name="<%= UserData.MAINEMAIL %>" size="30" value="" /></td>
</tr>

<tr>
<td>User Name:</td>
<td><input class="wizardtext" type="text" name="<%= WeblisketSessionData.REMOVABLEUSERNAME %>" size="30" value=""></td>
</tr>

<tr>
<td>Password:</td>
<td><input class="wizardtext" type="password" name="<%= WeblisketSessionData.REMOVABLEPASSWORD %>" size="30"></td>
</tr>

<input type="hidden" size="30" 
   name="<%= UserData.PERMISSIONS %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.SUBSTORES %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TAGLOCATION %>" 
   value="" />
      
<input type="hidden" size="30" 
   name="<%= StoreFrontData.PACKAGELOCATION %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.HOMEHOSTNAME %>" 
   value="<%= homeHostName %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.HOMEHOSTNAMEPATH %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.HOSTNAME %>" 
   value="<%= homeHostName %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.HOSTNAMEPATH %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTHOMEHOSTNAME %>" 
   value="<%= homeHostName %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTHOMEHOSTNAMEPATH %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTHOSTNAME %>" 
   value="<%= homeHostName %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTHOSTNAMEPATH %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.IMAGEPATH %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.STATICPATH %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.CATEGORYPATH %>" 
   value="<%= StoreFrontData.NAME %>" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.INVENTORYCONTROL %>" 
   value="Off" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.PACKAGELOCATION %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.FTP %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.FTPPATH %>" 
   value="" />
   
<input type="hidden" size="30" 
   name="<%= StoreFrontData.FTPUSERNAME %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.FTPPASSWORD %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTFTP %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTFTPPATH %>" 
   value="" />
   
<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTFTPUSERNAME %>" 
   value="" />

<input type="hidden" size="30" 
   name="<%= StoreFrontData.TESTFTPPASSWORD %>" 
   value="" />

</table>

<TEXTAREA class="wizardsmallText" rows='18' cols='105' readonly >
<%
LineReader license = new LineReader(URLGLOBALS.getWebappPath() + "/licenses/license.txt");
while(license.hasNext())
{
   String licenseString = license.next();
   if(licenseString.length() > 1)
   {
%>
   <%= licenseString.substring(0, licenseString.length()).toString() %>
<%
   }
}
%>
</TEXTAREA>

<br/>If you agree to the license agreement please check the box
<input class="wizardtext" type="checkbox" name="<%= GLOBALS.LICENSEAGREEMENT %>" >

<p/>
<input class="wizardsubmit" type="submit" value="Continue" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>