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
<%@ include file="globals.jsp" %>
<TITLE>Store Wizard - Template Type Selection - Step 2 of <%= TOTALSTEPS %></TITLE>
<%@ include file="javascript.jsp" %>

<script language="JavaScript">

<%@ include file="/javascript/helpers/thread/runnable/javascriptRunnableData.jsp" %>

<%@ include file="/javascript/log/javascriptLog.jsp" %>

<%@ include file="/javascript/widgets/tabs/javascriptTabs.jsp" %>

</script>

<%@ include file="css.jsp" %>
</HEAD>
<BODY bgcolor="<%= BACKGROUNDCOLOR %>" onload="initDivs();" >

<%@ include file="header.jsp" %>

<ecommerce:authentication 
   command="<%= GLOBALS.PROCESSBODYIFNOTAUTHENTICATED %>" 
   roles="<%= roles %>" >
   <jsp:forward page="<%= LOGOUTPAGE %>" />
</ecommerce:authentication>

<%@ include file="selectTemplateTypeProcessing.jsp" %>

<div class="wizardmainHeading" >
Template Type Selection - Step 2 of <%= TOTALSTEPS %><p/>
<div class="wizardmain" >

<%
   HashMap templateTypeViewInfoHashMap = 
      new TransformInfoPropertiesDocument(
         URLGLOBALS.getWebappPath() + "wizard/genericTemplatesConfig.xml").toTransformInfoPropertiesHashMap();
%>

<script language="JavaScript">
<%
   int index = 2;
   Set set = templateTypeViewInfoHashMap.keySet();
   Iterator iter = set.iterator();
   while(iter.hasNext())
   {
      String viewName = (String) iter.next();
      TransformInfoProperties transformInfoProperties = 
         (TransformInfoProperties) templateTypeViewInfoHashMap.get(viewName);

      String label = transformInfoProperties.getLabel();
      if(label.compareTo("Retail") == 0)
      {
%>
       selectToDivMap['<%= storeName + transformInfoProperties.getName() %>'] = 1;
<%
      }
      else
      {
%>
       selectToDivMap['<%= storeName + transformInfoProperties.getName() %>'] = <%= index %>;
<%
         index++;
      }
   }
%>

</script>

<form method="POST" action="<%= TEMPLATETYPEPAGE %>" >
Website Focus:
<SELECT class="wizardselect" name="<%= TransformInfoData.NAME %>" 
   onkeyup="showDivWithIdFromHtmlElementValue(this);return true;"
   onchange="showDivWithIdFromHtmlElementValue(this);return true;" >

<%
   index = 2;
   set = templateTypeViewInfoHashMap.keySet();
   iter = set.iterator();
   while(iter.hasNext())
   {
      String viewName = (String) iter.next();
      TransformInfoProperties transformInfoProperties = 
         (TransformInfoProperties) templateTypeViewInfoHashMap.get(viewName);

      String label = transformInfoProperties.getLabel();

      if(label != null)
      {
      if(label.compareTo("Retail") == 0)
      {
%>
<OPTION SELECTED value="<%= storeName + transformInfoProperties.getName() %>" >
<%= label %>
</OPTION>
<%
      }
      else
      //if(viewName.indexOf("Retail") > 0 && viewName.indexOf("Subscription") < 0)
      //if(label.compareTo("Retail Together With Services") == 0 ||
        // label.compareTo("Retail Together With Support") == 0)
      {
%>
<OPTION value="<%= storeName + transformInfoProperties.getName() %>" >
<%= label %>
</OPTION>
<%
         index++;
      }
      }
      else
      {
%>
Error: Missing Template Type Labels
<%
      }
   }
%>

</SELECT>
<p/>

<input class="wizardsubmit" type="hidden" value="<%= TEMPLATECOMMAND %>" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
<input class="wizardsubmit" type="submit" value="Continue" 
   name="<%= GLOBALS.ADMINCOMMAND %>" />
</form>

<p />
<div class="wizardheading" >Description:</div>
<p />

<%
   index = 2;
   set = templateTypeViewInfoHashMap.keySet();
   iter = set.iterator();
   while(iter.hasNext())
   {
      String viewName = (String) iter.next();
      TransformInfoProperties transformInfoProperties = 
         (TransformInfoProperties) templateTypeViewInfoHashMap.get(viewName);

      String label = transformInfoProperties.getLabel();

      if(label.compareTo("Retail") == 0)
      {
%>
<div id="description1" >
   <%= transformInfoProperties.getDescription() %>
</div>
<%
      }
      else
      {
%>
<div id="description<%= index %>" >
   <%= transformInfoProperties.getDescription() %>
</div>
<%
         index++;
      }
   }
%>

<p />

<div class="wizardheading" >Notes:</div><br />

You are not stuck with your template type selection.  You may select 
another anytime after the installation and modify your website to meet 
your changing needs.  Processing for this step usually takes about 1 minute. 
Please be patient.<br /><br />

<%@ include file="copyright.jsp" %>
</div>
</div>

</BODY>
</HTML>
