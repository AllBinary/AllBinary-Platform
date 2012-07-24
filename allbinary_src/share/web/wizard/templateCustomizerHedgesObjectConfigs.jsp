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
