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
   //includes
   final String THEMECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + STYLEDIR + "themes/themes.xsl";

   HashMap viewNameKeyAndTemplateCustomizerStoreHashMap = new HashMap();
   
   //Insert pages included in other pages
   viewNameKeyAndTemplateCustomizerStoreHashMap.put(THEMEVIEWNAME,THEMECUSTOMIZERTEMPLATEFILE);
%>
