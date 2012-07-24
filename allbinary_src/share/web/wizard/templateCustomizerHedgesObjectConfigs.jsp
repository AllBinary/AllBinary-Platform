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

   //hedges
   final String HEADERTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/headerTemplateCustomizerFormObjectConfig.xml";
   /*
   final String LEFTHEDGETEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "left/leftObjectConfig.xml";   
   final String RIGHTHEDGETEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "right/rightObjectConfig.xml";   
   final String FOOTERTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "footer/copyrightObjectConfig.xml";   

   //windows in hedges
   final String CATEGORYLISTTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + CATEGORYDIR + "categoryListObjectConfig.xml";
   final String PRODUCTSEARCHTEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + PRODUCTSDIR + "productSearchObjectConfig.xml";
   
   final String CUSTOMPAGETEMPLATECUSTOMIZEROBJECTCONFIGFILE = TEMPLATECUSTOMIZERDIR + CUSTOMDIR + "customPageObjectConfig.xml";
*/   

   //Custom Object Configs 
   HashMap viewNameKeyAndCustomizerHedgeObjectConfigsHashMap = new HashMap();

   //Insert Hedges
   viewNameKeyAndCustomizerHedgeObjectConfigsHashMap.put(HEADERPAGE,HEADERTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
/*      
   viewNameKeyAndCustomizerHedgeObjectConfigsHashMap.put(LEFTHEDGEPAGE,LEFTHEDGETEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndCustomizerHedgeObjectConfigsHashMap.put(RIGHTHEDGEPAGE,RIGHTHEDGETEMPLATECUSTOMIZEROBJECTCONFIGFILE);
   viewNameKeyAndCustomizerHedgeObjectConfigsHashMap.put(FOOTERPAGE,FOOTERTEMPLATECUSTOMIZEROBJECTCONFIGFILE);
*/
%>
