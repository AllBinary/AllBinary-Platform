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
<%@ include file="globals.jsp" %>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
   <jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<%
//Generate preview files included by others
%>

<transform:generic 
   storeName="<%= storeName %>" 
   name="<%= storeName + commonStrings.SPACE + TransformInfosData.PREVIEW + commonStrings.SPACE + GeneratorTransformInfoData.NAME %>" >
</transform:generic >

<TITLE>Store Wizard - Style Selection - Step 5 of <%= TOTALSTEPS %></TITLE>
<%@ include file="javascript.jsp" %>
<script language="JavaScript">


<%@ include file="/javascript/support/javascriptSupport.jsp" %>

<%@ include file="/javascript/string/javascriptStringIgnoreCase.jsp" %>

<%@ include file="/javascript/helpers/scroll/javascriptScroll.jsp" %>
<%@ include file="/javascript/helpers/thread/runnable/javascriptRunnableData.jsp" %>

<%@ include file="/javascript/log/javascriptLog.jsp" %>

<%@ include file="/javascript/widgets/tabs/javascriptTabs.jsp" %>
<%@ include file="/javascript/widgets/progress/javascriptProgressBar.jsp" %>

<%@ include file="/javascript/css/javascriptCssIncludes.jsp" %>
<%@ include file="/javascript/css/form/javascriptCssFormIncludes.jsp" %>
<%@ include file="/javascript/css/visibility/javascriptVisibility.jsp" %>

var progressBar = new ProgressBar();

//add new css element
//add new css property ~ rule
//remove css element
//remove css property ~ rule

function init()
{
   initSupport();
   initTabs('addTemplateStyle',1,1);
}
</script>

<%@ include file="css.jsp" %>

<%
//Generate default CSS for style setup page
%>
<transform:generic
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + CSSPAGE %>"
   templateFile="<%= CSSVIEWTEMPLATE %>" />

</HEAD>
<BODY class="body" bgcolor="<%= BACKGROUNDCOLOR %>" onload="init();" >

<%@ include file="header.jsp" %>

<%
   final String mainBackground = "mainBackground";
%>

<div class="wizardmainHeading" >
Style Selection - Step 4 of <%= TOTALSTEPS %><p/>
<div class="wizardmain" >

<%@ include file="addTemplateStyleProcessing.jsp" %>

<%
   final String HIDDENCSSCHILDVIEWTEMPLATE = 
      "template/generic/includes/style/css/hiddenCssElementChildList.xsl";
%>
<transform:generic
   storeName="<%= storeName %>"
   name="<%= storeName + commonStrings.SPACE + CSSPAGE %>"
   templateFile="<%= HIDDENCSSCHILDVIEWTEMPLATE %>" />

<a href="#target1" onclick="selectTab('addTemplateStyle',1);" id="addTemplateStyleLink1" class="wizardtablink" >Styler</a>

<div id="addTemplateStyleTabbedPane1" class="wizardtab" >
<br/>

      <div class="wizardsequenceText"
         onClick="toggleVisibilityByElementId('previewHelp');toggleVisibilityByElementId('elementPreview');return true;" >
         >>Click here to toggle between help and style elements in the preview body below.<<
         <br/>
      </div>

<%
//Generate indexPreview and return result to client no file
//This index file uses a custom object config to use the preview template
%>
<%
//Generate indexPreview and return result to client no file
//This index file uses a custom object config to use the preview template
final String includePage = "/" + storeName + "/" + INDEXPAGE + "Preview.jsp";
%>
<jsp:include page="<%= includePage %>" />
<br/>

<form method="POST" action="addTemplateStyle.jsp" >

<input type="hidden" 
   value="BasicStyleTab" 
   name="TAB_COMMAND" />

<input class="wizardsubmit" type="submit" value="Continue" 
   name="AdminCommand" />
<br/><br/>

<workflow:basic 
   name="<%= storeName + commonStrings.SPACE + GLOBALS.EDIT + commonStrings.SPACE + BASIC + selectedView %>" />

<input class="wizardsubmit" type="submit" value="Continue" 
   name="AdminCommand" />

</form>

<br/>
</div>

<br/>
<%@ include file="copyright.jsp" %>
</div>
</div>

<div id="wizardProgressBar" class="wizardProgressBar" >
Please Wait...
</div>

</BODY>
</HTML>
