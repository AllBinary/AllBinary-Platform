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
   final String HEADERCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/header.xsl";

   final String HEADERALLCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarAll.xsl";
   final String HEADERCUSTOMEREXISTINGCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarCustomerExisting.xsl";
   final String HEADERPRODUCTSCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarProducts.xsl";
   final String HEADERSERVICESCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarServices.xsl";
   final String HEADERSERVICESCLIENTAREACUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarServicesClientArea.xsl";
   final String HEADERSERVICESSEPARATEWIDECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarServicesSeparateWide.xsl";
   final String HEADERSERVICESTOGETHERWIDECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarServicesTogetherWide.xsl";
   final String HEADERSERVICESSEPARATECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarServicesSeparate.xsl";
   final String HEADERSERVICESTOGETHERCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarServicesTogether.xsl";
   final String HEADERSUBSCRIPTIONCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarSubscription.xsl";
   final String HEADERSUPPORTCUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "header/topbarSupport.xsl";
 
   final String LEFTALLHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "left/leftAll.xsl";
   final String LEFTCUSTOMERHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "left/leftCustomer.xsl";
   final String LEFTPRODUCTSHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "left/leftProducts.xsl";
   final String LEFTSERVICESHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "left/leftServices.xsl";
   final String LEFTSUBSCRIPTIONHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "left/leftSubscription.xsl";
   final String LEFTSUPPORTHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "left/leftSupport.xsl";
   
   final String RIGHTALLHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "right/rightAll.xsl";
   final String RIGHTCUSTOMERHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "right/rightCustomer.xsl";
   final String RIGHTPRODUCTSHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "right/rightProducts.xsl";
   final String RIGHTSERVICESHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "right/rightServices.xsl";
   final String RIGHTSUBSCRIPTIONHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "right/rightSubscription.xsl";
   final String RIGHTSUPPORTHEDGECUSTOMIZERTEMPLATEFILE = TEMPLATECUSTOMIZERDIR + HEDGEDIR + "right/rightSupport.xsl";

   HashMap viewNameKeyAndTemplateCustomizerHedgesHashMap = new HashMap();
   //Insert Hedges
   
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERPAGE,HEADERCUSTOMIZERTEMPLATEFILE);
   /*
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERALLPAGE,HEADERALLCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERCUSTOMEREXISTINGPAGE,HEADERCUSTOMEREXISTINGCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERPRODUCTSPAGE,HEADERPRODUCTSCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERSERVICESPAGE,HEADERSERVICESCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERSERVICESCLIENTAREAPAGE,HEADERSERVICESCLIENTAREACUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERSUBSCRIPTIONPAGE,HEADERSUBSCRIPTIONCUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(HEADERSUPPORTPAGE,HEADERSUPPORTCUSTOMIZERTEMPLATEFILE);
   
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(LEFTALLHEDGEPAGE,LEFTALLHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(LEFTCUSTOMERHEDGEPAGE,LEFTCUSTOMERHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(LEFTPRODUCTSHEDGEPAGE,LEFTPRODUCTSHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(LEFTSERVICESHEDGEPAGE,LEFTSERVICESHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(LEFTSUBSCRIPTIONHEDGEPAGE,LEFTSUBSCRIPTIONHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(LEFTSUPPORTHEDGEPAGE,LEFTSUPPORTHEDGECUSTOMIZERTEMPLATEFILE);
      
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(RIGHTALLHEDGEPAGE,RIGHTALLHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(RIGHTCUSTOMERHEDGEPAGE,RIGHTCUSTOMERHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(RIGHTPRODUCTSHEDGEPAGE,RIGHTPRODUCTSHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(RIGHTSERVICESHEDGEPAGE,RIGHTSERVICESHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(RIGHTSUBSCRIPTIONHEDGEPAGE,RIGHTSUBSCRIPTIONHEDGECUSTOMIZERTEMPLATEFILE);
   viewNameKeyAndTemplateCustomizerHedgesHashMap.put(RIGHTSUPPORTHEDGEPAGE,RIGHTSUPPORTHEDGECUSTOMIZERTEMPLATEFILE);
   */
%>
