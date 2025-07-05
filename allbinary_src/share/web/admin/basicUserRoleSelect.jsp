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
