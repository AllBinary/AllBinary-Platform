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
   final String DARTACCESSORIESCATEGORIESTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "accessories/accessories.xsl";
   final String DARTSCATEGORIESTEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "darts/darts.xsl";
   final String DARTSTATISTICSSOFTWARETEMPLATEFILE = TEMPLATEDIR + BODYDIR + CUSTOMDIR + "software/software.xsl";
  
   viewNameKeyAndCompoundTemplateValueHashMap.put(DARTACCESSORIESCATEGORIESPAGE,DARTACCESSORIESCATEGORIESTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(DARTSCATEGORIESPAGE,DARTSCATEGORIESTEMPLATEFILE);
   viewNameKeyAndCompoundTemplateValueHashMap.put(DARTSTATISTICSSOFTWAREPAGE,DARTSTATISTICSSOFTWARETEMPLATEFILE);
%>
