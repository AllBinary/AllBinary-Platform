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
   final String INCLUDESCLASSPATH = "includes" + ".";
   final String STYLECLASSPATH = INCLUDESCLASSPATH + "style";
   //final String CSSSTYLETEXTCLASSPATH = STYLECLASSPATH + ".css";
   final String CSSSTYLEFORMCLASSPATH = STYLECLASSPATH + ".css.form";
   final String CSSSTYLERETAILTEMPLATECLASSPATH = STYLECLASSPATH + ".css.template.retail";
   final String GLOBALSCLASSPATH = INCLUDESCLASSPATH + "globals";
   final String METASCLASSPATH = INCLUDESCLASSPATH + "meta";
   final String JAVASCRIPTCLASSPATH = INCLUDESCLASSPATH + "javascript";
   
   //Insert pages included in other pages
   final String CSSSTYLEONEPOSTCLASSPATH = "Css";
   final String GLOBALSPOSTCLASSPATH = "Globals";
   final String METASPOSTCLASSPATH = "Meta";
   final String JAVASCRIPTPOSTCLASSPATH = "Javascript";
   
   //Insert Includes
   
   //pre
   HashMap viewNameKeyAndCustomizerIncludesClassPathsPreHashMap = new HashMap();

   //Insert pages included in other pages
   //viewNameKeyAndCustomizerIncludesClassPathsPreHashMap.put(CSSPAGE, CSSSTYLEONECLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPreHashMap.put(ADVANCEDCSSPAGE, CSSSTYLERETAILTEMPLATECLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPreHashMap.put(BASICCSSPAGE, CSSSTYLERETAILTEMPLATECLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPreHashMap.put(GLOBALSPAGE, GLOBALSCLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPreHashMap.put(METASPAGE, METASCLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPreHashMap.put(JAVASCRIPTPAGE, JAVASCRIPTCLASSPATH);

   //post
   HashMap viewNameKeyAndCustomizerIncludesClassPathsPostHashMap = new HashMap();

   //Insert pages included in other pages
   viewNameKeyAndCustomizerIncludesClassPathsPostHashMap.put(ADVANCEDCSSPAGE, CSSSTYLEONEPOSTCLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPostHashMap.put(BASICCSSPAGE, CSSSTYLEONEPOSTCLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPostHashMap.put(GLOBALSPAGE, GLOBALSPOSTCLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPostHashMap.put(METASPAGE, METASPOSTCLASSPATH);
   viewNameKeyAndCustomizerIncludesClassPathsPostHashMap.put(JAVASCRIPTPAGE, JAVASCRIPTPOSTCLASSPATH);
%>