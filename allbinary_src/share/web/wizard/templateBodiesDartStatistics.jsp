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
   final String DARTACCESSORIESCATEGORIESTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "accessories/accessories.xsl";
   final String DARTSCATEGORIESTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "darts/darts.xsl";
   final String DARTSTATISTICSSOFTWARETEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "software/software.xsl";
  
   viewNameKeyAndCompoundTemplateValueHashMap.put(DARTACCESSORIESCATEGORIESPAGE,DARTACCESSORIESCATEGORIESTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(DARTSCATEGORIESPAGE,DARTSCATEGORIESTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(DARTSTATISTICSSOFTWAREPAGE,DARTSTATISTICSSOFTWARETEMPLATEFILE);
%>
