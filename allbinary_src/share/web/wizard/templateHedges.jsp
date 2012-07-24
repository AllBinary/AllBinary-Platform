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
   final String HEADERALLTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarAll";
   final String HEADERCUSTOMEREXISTINGTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarCustomerExisting";
   final String HEADERPRODUCTSTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarProducts";
   final String HEADERSERVICESTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarServices";
   final String HEADERSERVICESCLIENTAREATEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarServicesClientArea";
   
   final String HEADERSERVICESSEPARATEWIDETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarServicesSeparateWide";
   final String HEADERSERVICESTOGETHERWIDETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarServicesTogetherWide";

   final String HEADERSERVICESSEPARATETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarServicesSeparate";
   final String HEADERSERVICESTOGETHERTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarServicesTogether";
   
   final String HEADERSUBSCRIPTIONTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarSubscription";
   final String HEADERSUPPORTTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "header/topbarSupport";
      
   final String LEFTALLHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "left/leftAll";
   final String LEFTCUSTOMERHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "left/leftCustomer";
   final String LEFTPRODUCTSHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "left/leftProducts";
   final String LEFTSERVICESHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "left/leftServices";
   final String LEFTSUBSCRIPTIONHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "left/leftSubscription";
   final String LEFTSUPPORTHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "left/leftSupport";
   
   final String RIGHTALLHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "right/rightAll";
   final String RIGHTCUSTOMERHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "right/rightCustomer";
   final String RIGHTPRODUCTSHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "right/rightProducts";
   final String RIGHTSERVICESHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "right/rightServices";
   final String RIGHTSUBSCRIPTIONHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "right/rightSubscription";
   final String RIGHTSUPPORTHEDGETEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "right/rightSupport";
   
   final String FOOTERTEMPLATEFILE = TEMPLATEDIR + HEDGEDIR + "footer/copyright";   
   
   //includes
   final String CSSTEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "style/css";
   //final String CSSSTYLEONETEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "style/cssStyleOne";
   //final String CSSSTYLETWOTEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "style/cssStyleTwo";
   //final String CSSSTYLETHREETEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "style/cssStyleThree";
   //final String CSSSTYLEFOURTEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "style/cssStyleFour";
   //final String CSSSTYLEFIVETEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "style/cssStyleFive";
   final String JAVASCRIPTTEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "script/javascript";
   final String GLOBALSTEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "globals/globals";
   final String METASTEMPLATEFILE = TEMPLATEDIR + INCLUDESDIR + "metatags/metatags";
   
   HashMap viewNameKeyAndTemplateValueHashMap = new HashMap();
   //Insert Hedges

   viewNameKeyAndTemplateValueHashMap.put(HEADERALLPAGE,HEADERALLTEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERCUSTOMEREXISTINGPAGE,HEADERCUSTOMEREXISTINGTEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERPRODUCTSPAGE,HEADERPRODUCTSTEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERSERVICESPAGE,HEADERSERVICESTEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERSERVICESCLIENTAREAPAGE,HEADERSERVICESCLIENTAREATEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERSERVICESSEPARATEWIDEPAGE,HEADERSERVICESSEPARATEWIDETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERSERVICESTOGETHERWIDEPAGE,HEADERSERVICESTOGETHERWIDETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERSERVICESSEPARATEPAGE,HEADERSERVICESSEPARATETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERSERVICESTOGETHERPAGE,HEADERSERVICESTOGETHERTEMPLATEFILE);   
   viewNameKeyAndTemplateValueHashMap.put(HEADERSUBSCRIPTIONPAGE,HEADERSUBSCRIPTIONTEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(HEADERSUPPORTPAGE,HEADERSUPPORTTEMPLATEFILE);
   
   viewNameKeyAndTemplateValueHashMap.put(LEFTALLHEDGEPAGE,LEFTALLHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(LEFTCUSTOMERHEDGEPAGE,LEFTCUSTOMERHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(LEFTPRODUCTSHEDGEPAGE,LEFTPRODUCTSHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(LEFTSERVICESHEDGEPAGE,LEFTSERVICESHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(LEFTSUBSCRIPTIONHEDGEPAGE,LEFTSUBSCRIPTIONHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(LEFTSUPPORTHEDGEPAGE,LEFTSUPPORTHEDGETEMPLATEFILE);
      
   viewNameKeyAndTemplateValueHashMap.put(RIGHTALLHEDGEPAGE,RIGHTALLHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(RIGHTCUSTOMERHEDGEPAGE,RIGHTCUSTOMERHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(RIGHTPRODUCTSHEDGEPAGE,RIGHTPRODUCTSHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(RIGHTSERVICESHEDGEPAGE,RIGHTSERVICESHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(RIGHTSUBSCRIPTIONHEDGEPAGE,RIGHTSUBSCRIPTIONHEDGETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(RIGHTSUPPORTHEDGEPAGE,RIGHTSUPPORTHEDGETEMPLATEFILE);
   
   viewNameKeyAndTemplateValueHashMap.put(FOOTERPAGE,FOOTERTEMPLATEFILE);
   
   //Insert pages included in other pages
   viewNameKeyAndTemplateValueHashMap.put(CSSPAGE,CSSTEMPLATEFILE);
   //viewNameKeyAndTemplateValueHashMap.put(CSSSTYLEONEPAGE,CSSSTYLEONETEMPLATEFILE);
   //viewNameKeyAndTemplateValueHashMap.put(CSSSTYLETWOPAGE,CSSSTYLETWOTEMPLATEFILE);
   //viewNameKeyAndTemplateValueHashMap.put(CSSSTYLETHREEPAGE,CSSSTYLETHREETEMPLATEFILE);
   //viewNameKeyAndTemplateValueHashMap.put(CSSSTYLEFOURPAGE,CSSSTYLEFOURTEMPLATEFILE);
   //viewNameKeyAndTemplateValueHashMap.put(CSSSTYLEFIVEPAGE,CSSSTYLEFIVETEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(GLOBALSPAGE,GLOBALSTEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(METASPAGE,METASTEMPLATEFILE);
   viewNameKeyAndTemplateValueHashMap.put(JAVASCRIPTPAGE,JAVASCRIPTTEMPLATEFILE);
   
   //Special Payment Gateway Pages Here
   //viewNameKeyAndTemplateValueHashMap.put(PAGE,TEMPLATEFILE);
%>