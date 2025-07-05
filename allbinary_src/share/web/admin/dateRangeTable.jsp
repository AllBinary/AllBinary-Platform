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

final int LASTYEAR = 2024;
%>

<%@ include file="globals.jsp" %>

<table class="table" >
<tr>
<td>
</td>
<td>
Year
</td>
<td>
Month
</td>
<td>
Day
</td>
<td>
Hour
<td>
</tr>

<tr>
<td>
From:
</td>

<td>
<select class="text" name="<%= OrderHistoryData.FROMYEAR %>" >
<%
for(int nextYear = 2000; nextYear <= LASTYEAR;  nextYear++)
{
   %>
   <OPTION> <%= nextYear %> </OPTION>
   <%
}
%>
</SELECT>
</td>

<td>
<select class="text" name="<%= OrderHistoryData.FROMMONTH %>" >
<%
for(int month = 1; month <= 12;  month++)
{
   %>
   <OPTION value='<%= month-1 %>' > <%= month %> </OPTION>
   <%
}
%>
</SELECT>
</td>

<td>
<select class="text" name="<%= OrderHistoryData.FROMDAY %>" >
<%
for(int day = 1; day <= 31;  day++)
{
   %>
   <OPTION> <%= day %> </OPTION>
   <%
}
%>
</SELECT>
</td>

<td>
<select class="text" name="<%= OrderHistoryData.FROMHOUR %>" >
<%
for(int hour = 1; hour <= 24;  hour++)
{
   %>
   <OPTION> <%= hour %> </OPTION>
   <%
}
%>
</SELECT>
</td>
</tr>

<tr>
<td>
To:
</td>

<td>
<select class="text" name="<%= OrderHistoryData.TOYEAR %>" >
<%
for(int nextYear = 2002; nextYear <= LASTYEAR;  nextYear++)
{
   %>
   <OPTION> <%= nextYear %> </OPTION>
   <%
}
%>
</SELECT>
</td>

<td>
<select class="text" name="<%= OrderHistoryData.TOMONTH %>" >
<%
for(int month = 1; month <= 12;  month++)
{
   %>
   <OPTION value='<%= month-1 %>' > <%= month %> </OPTION>
   <%
}
%>
</SELECT>
</td>

<td>
<select class="text" name="<%= OrderHistoryData.TODAY %>" >
<%
for(int day = 1; day <= 31;  day++)
{
   %>
   <OPTION> <%= day %> </OPTION>
   <%
}
%>
</SELECT>
</td>

<td>
<select class="text" name="<%= OrderHistoryData.TOHOUR %>" >
<%
for(int hour = 1; hour <= 24;  hour++)
{
   %>
   <OPTION> <%= hour %> </OPTION>
   <%
}
%>
</SELECT>
</td>
</tr>
</table>