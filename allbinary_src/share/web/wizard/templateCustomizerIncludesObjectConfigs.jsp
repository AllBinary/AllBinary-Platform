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
