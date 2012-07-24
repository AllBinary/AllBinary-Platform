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
      Vector allInstallations = abcs.init.WeblisketFinder.findAll(webappPath);
      Iterator iter = allInstallations.iterator();
      while(iter.hasNext())
      {
         File file = (File) iter.next();
         String installationPath = abcs.init.WeblisketFinder.getInstallationPath(file);
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

<ecommerce:fileauthentication 
   command="<%= GLOBALS.PROCESSBODYIFAUTHENTICATED %>" 
   userName="<%= userName %>" 
   password="<%= password %>" 
   roles="<%= roles %>">

<%
if(isLicenseAgreedTo)
{
   if(isInstallationValid)
   {
%> 
   <jsp:forward page="<%= INSTALLTYPEPAGE %>" />    
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

If you agree to the license below you must check the box at the end of the 
page to continue the installation.<p/>
<%
}
%>   
</ecommerce:fileauthentication>

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