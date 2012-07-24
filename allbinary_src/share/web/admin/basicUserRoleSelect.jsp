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

<br/>
<tr>
<td>
Role: 
</td>
<td>
<select class="text" name="<%= UserRoleData.NAME %>" >
<%
basicUserRoleIter = basicUserRoleVector.iterator();
while(basicUserRoleIter.hasNext())
{
   BasicUserRole basicUserRole = (BasicUserRole) basicUserRoleIter.next();
   if(!basicUserRole.equals(BasicUserRole.INSTALLER))
   {
%>
<option value="<%= basicUserRole.getRole() %>" ><%= basicUserRole.getRole() %></option>
<%
   }
}
%>
</select>
</td>
</tr>

<br/>
