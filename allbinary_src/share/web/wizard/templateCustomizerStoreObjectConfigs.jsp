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
<%
   final String THEMECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + STYLEDIR + "cssCustomizerObjectConfig.xml";
   //"theme/themeCustomizerObjectConfig.xml";

   //Custom Object Configs 
   HashMap viewNameKeyAndCustomizerStoreObjectConfigsHashMap = new HashMap();

   //Insert pages included in other pages
   viewNameKeyAndCustomizerStoreObjectConfigsHashMap.put(THEMEVIEWNAME,THEMECUSTOMIZEROBJECTCONFIGFILE);
%>
