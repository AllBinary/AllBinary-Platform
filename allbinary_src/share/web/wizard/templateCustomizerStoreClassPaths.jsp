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