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
   
   final String WEBLISKETTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "weblisket/weblisket.xsl";
   final String FREEBLISKETTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "freeblisket/freeblisket.xsl";
   final String ADVERTISINGTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "advertising/advertising.xsl";
   
   final String APPDEVELOPMENTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "appdev/appdev.xsl";
   final String DBDEVELOPMENTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "dbdev/dbdev.xsl";
   final String EXAMPLESTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "examples/examples.xsl";
  
   final String J2MEDEVELOPMENTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "j2medev/j2medev.xsl";
   final String WEBDEVELOPMENTTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "webdev/webdev.xsl";
   final String WEBEXAMPLESTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "webexamples/webexamples.xsl";
   final String WEBHOSTINGTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "webhosting/webhosting.xsl";
   final String BASICWEBHOSTINGCOMPARISONTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "basicwebhostingcomparison/basicwebhostingcomparison.xsl";
   final String DEDICATEDWEBHOSTINGCOMPARISONTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "dedicatedwebhostingcomparison/dedicatedwebhostingcomparison.xsl";
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(WEBLISKETPAGE,WEBLISKETTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(FREEBLISKETPAGE,FREEBLISKETTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(ADVERTISINGPAGE,ADVERTISINGTEMPLATEFILE);

   viewNameKeyAndCompoundTemplateValueHashMap.put(APPLICATIONDEVELOPMENTPAGE,APPDEVELOPMENTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(DATABASEDEVELOPMENTPAGE,DBDEVELOPMENTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(EXAMPLESPAGE,EXAMPLESTEMPLATEFILE);
   
   viewNameKeyAndCompoundTemplateValueHashMap.put(J2MEDEVELOPMENTPAGE,J2MEDEVELOPMENTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(WEBDEVELOPMENTPAGE,WEBDEVELOPMENTTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(WEBEXAMPLESPAGE,WEBEXAMPLESTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(WEBHOSTINGPAGE,WEBHOSTINGTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(BASICWEBHOSTINGCOMPARISONPAGE,BASICWEBHOSTINGCOMPARISONTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(DEDICATEDWEBHOSTINGCOMPARISONPAGE,DEDICATEDWEBHOSTINGCOMPARISONTEMPLATEFILE);

   //Other Includes
   final String WEBHOSTINGBODYTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "/include/webhostingbody/webhostingbody";
   
   viewNameKeyAndTemplateValueHashMap.put(WEBHOSTINGBODYPAGE,WEBHOSTINGBODYTEMPLATEFILE);

   final String INDEXBODYTEMPLATEFILE = TEMPLATEDIR + BODYDIR + "/include/indexbody/indexbody";
   
   viewNameKeyAndTemplateValueHashMap.put(INDEXBODYPAGE,INDEXBODYTEMPLATEFILE);
%>
