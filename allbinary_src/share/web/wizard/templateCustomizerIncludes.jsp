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
   //includes
   final String ADVANCEDCSSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssAdvanced.xsl";
   final String BASICCSSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssBasic.xsl";
   //final String CSSSTYLEONECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssStyleOne.xsl";
   //final String CSSSTYLETWOCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssStyleTwo.xsl";
   //final String CSSSTYLETHREECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssStyleThree.xsl";
   //final String CSSSTYLEFOURCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssStyleFour.xsl";
   //final String CSSSTYLEFIVECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "style/cssStyleFive.xsl";

   final String JAVASCRIPTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "script/javascript.xsl";
   final String GLOBALSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "globals/globals.xsl";
   final String METASCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + INCLUDESDIR + "metatags/metatags.xsl";

   HashMap viewNameKeyAndTemplateCustomizerIncludesHashMap = new HashMap();
   
   //Insert pages included in other pages
   viewNameKeyAndTemplateCustomizerIncludesHashMap.put(ADVANCEDCSSPAGE,ADVANCEDCSSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerIncludesHashMap.put(BASICCSSPAGE,BASICCSSCUSTOMIZERTEMPLATEFILE);
   //viewNameKeyAndTemplateCustomizerIncludesHashMap.put(CSSSTYLEONEPAGE,CSSSTYLEONECUSTOMIZERTEMPLATEFILE);
   //viewNameKeyAndTemplateCustomizerIncludesHashMap.put(CSSSTYLETWOPAGE,CSSSTYLETWOCUSTOMIZERTEMPLATEFILE);
   //viewNameKeyAndTemplateCustomizerIncludesHashMap.put(CSSSTYLETHREEPAGE,CSSSTYLETHREECUSTOMIZERTEMPLATEFILE);
   //viewNameKeyAndTemplateCustomizerIncludesHashMap.put(CSSSTYLEFOURPAGE,CSSSTYLEFOURCUSTOMIZERTEMPLATEFILE);
   //viewNameKeyAndTemplateCustomizerIncludesHashMap.put(CSSSTYLEFIVEPAGE,CSSSTYLEFIVECUSTOMIZERTEMPLATEFILE);

   viewNameKeyAndTemplateCustomizerIncludesHashMap.put(GLOBALSPAGE,GLOBALSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerIncludesHashMap.put(METASPAGE,METASCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerIncludesHashMap.put(JAVASCRIPTPAGE,JAVASCRIPTCUSTOMIZERTEMPLATEFILE);
%>
