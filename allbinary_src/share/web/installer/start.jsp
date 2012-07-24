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
<HTML>
<HEAD>
<TITLE>Installation - Start Page - Step 1</TITLE>
<%@ include file="globals.jsp" %>
<%@ include file="javascript.jsp" %>
<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor=<%= BACKGROUNDCOLOR %> >
<%@ include file="header.jsp" %>
<div class="text" >

<%
   String userName = request.getParameter(WeblisketSessionData.REMOVABLEUSERNAME);
   String password = request.getParameter(WeblisketSessionData.REMOVABLEPASSWORD);

Vector roles = new Vector();
roles.add(BasicUserRole.INSTALLER);
%>

<%
   //verify that license was agreed to
   boolean isLicenseAgreedTo = false;
   String licenseAgreement = request.getParameter(allbinary.globals.GLOBALS.LICENSEAGREEMENT);
   if(licenseAgreement!=null && licenseAgreement.compareTo("on")==0)
   {
      isLicenseAgreedTo=true;
   }

   //verify installation in path specified
   boolean isInstallationValid = false;
   String webappPath = URLGLOBALS.getWebappPath();
   if(webappPath!=null && webappPath.compareTo("")!=0)
   {
      Vector allInstallations = WeblisketFinder.findAll(webappPath);
      Iterator iter = allInstallations.iterator();
      while(iter.hasNext())
      {
         File file = (File) iter.next();
         String installationPath = WeblisketFinder.getInstallationPath(file);
         if(installationPath!=null)
         {
            isInstallationValid = true;
            break;
         }
      }
   }
   else
   {
%> 
   This error should not occur. Failed to get getRealPath() java not installed correctly. <br />
<%
   }
%>

Installation - Start Page - Step 1 - <a href="<%= INSTALLATIONHELPPAGE %>" target="<%= targetValue %>" >Help</a><p/>

<%
if(isLicenseAgreedTo)
{
   if(isInstallationValid)
   {
%> 
<ecommerce:fileauthentication 
   command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
   userName="<%= userName %>" 
   password="<%= password %>" 
   roles="<%= roles %>">
   <jsp:forward page="<%= INSTALLTYPEPAGE %>" />
</ecommerce:fileauthentication>
<%
   }
   else
   {
%>
   Error trying to verify the installation.  <br>
   If the Value below is null then the JSP Container does not meet 1+ JSP spec.<br>
   Value = <%= webappPath %><br>
   If the Value above is not null then the installation is corrupt. Please try the installaion again.<br>
<%
   }
}
else
{
%>
<ecommerce:fileauthentication 
   command="<%= WeblisketSessionData.INVALIDATESESSION %>" >     
</ecommerce:fileauthentication>

<ecommerce:fileauthentication 
   command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
   userName="<%= userName %>" 
   password="<%= password %>" 
   roles="<%= roles %>">
If you agree to the license below you must check the box at the end of the 
page to continue the installation.<p/>
</ecommerce:fileauthentication>
<%
}
%>   

<form method="POST" action="<%= STARTPAGE %>">
<table class="table">
<tr>
<td>User Name:</td>
<td><input class="text" type="text" name="<%= WeblisketSessionData.REMOVABLEUSERNAME %>" size="30" value="Installer"></td>
</tr>
<tr>
<td>Password:</td>
<td><input class="text" type="password" name="<%= WeblisketSessionData.REMOVABLEPASSWORD %>" size="30"></td>
</tr>
</table>
<p>
<TEXTAREA class="smallText" rows='18' cols='105' readonly >
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

<br>If you agree to the license agreement please check the box
<input class="text" type="checkbox" name="<%= GLOBALS.LICENSEAGREEMENT %>" >

<p>
<input class="submit" type="submit" value="Continue" name="<%= GLOBALS.ADMINCOMMAND %>">
</form>

</div>
<%@ include file="copyright.jsp" %>
</BODY>
</HTML>