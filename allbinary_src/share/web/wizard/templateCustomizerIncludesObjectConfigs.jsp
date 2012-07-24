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
   final String CSSCUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssCustomizerObjectConfig.xml";

   /*
   //includes
   final String CSSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssObjectConfig.xml";
   final String JAVASCRIPTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "script/javascriptObjectConfig.xml";
   final String GLOBALSTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "globals/globalsObjectConfig.xml";
   final String METASTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "metatags/metatagsObjectConfig.xml";
   */

   //Custom Object Configs 
   HashMap viewNameKeyAndCustomizerIncludesObjectConfigsHashMap = new HashMap();
   
   //Insert pages included in other pages
   viewNameKeyAndCustomizerIncludesObjectConfigsHashMap.put(ADVANCEDCSSPAGE,CSSCUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndCustomizerIncludesObjectConfigsHashMap.put(BASICCSSPAGE,CSSCUSTOMIZEROBJECTCONFIGFILE);
 /*
   viewNameKeyAndCustomizerIncludesObjectConfigsHashMap.put(GLOBALSPAGE,GLOBALSTEMPLATECUSTOMIZEROBJECTCONFIGFILE);

   viewNameKeyAndCustomizerIncludesObjectConfigsHashMap.put(METASPAGE,METASTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndCustomizerIncludesObjectConfigsHashMap.put(JAVASCRIPTPAGE,JAVASCRIPTTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
*/
%>
