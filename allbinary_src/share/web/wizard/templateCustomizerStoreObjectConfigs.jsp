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
   final String THEMECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + STYLEDIR + "cssCustomizerObjectConfig.xml";
   //"theme/themeCustomizerObjectConfig.xml";

   //Custom Object Configs 
   HashMap viewNameKeyAndCustomizerStoreObjectConfigsHashMap = new HashMap();

   //Insert pages included in other pages
   viewNameKeyAndCustomizerStoreObjectConfigsHashMap.put(THEMEVIEWNAME,THEMECUSTOMIZEROBJECTCONFIGFILE);
%>
