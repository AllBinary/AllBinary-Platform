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