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
   //Insert pages included in other pages
   final String THEMEPACKAGE = "includes.style.theme";
   
   //Insert pages included in other pages
   final String THEMETEMPLATEPOSTCLASSPATH = "Theme";
   
   //Insert Includes
   
   //pre
   HashMap viewNameKeyAndCustomizerStoreClassPathsPreHashMap = new HashMap();

   //Insert pages included in other pages
   viewNameKeyAndCustomizerStoreClassPathsPreHashMap.put(THEMEVIEWNAME, THEMEPACKAGE);

   //post
   HashMap viewNameKeyAndCustomizerStoreClassPathsPostHashMap = new HashMap();

   //Insert pages included in other pages
   viewNameKeyAndCustomizerStoreClassPathsPostHashMap.put(THEMEVIEWNAME, THEMETEMPLATEPOSTCLASSPATH);
%>