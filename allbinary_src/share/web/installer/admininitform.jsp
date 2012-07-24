<%
 final String smtpGuess = "mail.";
%>

<form method="POST" action="<%= ADMININITPAGE %>">
Administrator - A User With All Privilages

<table class="table">
<tr>
<td><input class="text" type="hidden" name="<%= UserData.FIRSTNAME %>" value="Admin" /></td>
</tr>
<tr>
<td><input class="text" type="hidden" name="<%= UserData.LASTNAME %>" value="Admin" /></td>
</tr>
<tr>
<td>User Name:</td>
<td><input class="text" type="text" name="<%= WeblisketSessionData.REMOVABLEUSERNAME %>" size="30"></td>
</tr>
<tr>
<td>Password:</td>
<td><input class="text" type="password" name="<%= WeblisketSessionData.REMOVABLEPASSWORD %>" size="30"></td>
</tr>
<tr>
<td>Email:</td>
<td><input class="text" type="text" name="<%= UserData.MAINEMAIL %>" size="30"></td>
</tr>
</table>
<br/>
Email Server Configuration - Info For Outgoing Email Server<br/><br/>
<table class="table">
<tr>
<td>Account Name:</td>
<td><input class="text" type="text" size="30" name="<%= EmailServerConfigurationData.ACCOUNT %>" value="" /></td>
</tr>
<tr>
<td>Password:</td>
<td><input class="text" type="password" size="30" name="<%= EmailServerConfigurationData.PASSWORD %>" value="" /></td>
</tr>
<td>Outgoing Email/SMTP Server:</td>
<td><input class="text" type="text" size="30" name="<%= EmailServerConfigurationData.SERVER %>" value="<%= smtpGuess %>" /></td>
</tr>
</table>
<p/>
Note: Account name is used for email server authentication and is <br/>
usually an email address and could be the same as a user.<br/><br/><br/><br/>
<input class="text" type="hidden" name="AdminCommand" value="AdminInit" >
<input class="submit" type="submit" value="Continue" >
</form>
