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
/*
 * Create the default xml and add it to the views that correspond with the add customizer views
 */

   customizerSet = 
      viewNameKeyAndTemplateCustomizerBodiesValueHashMap.keySet();
   customizerIter = customizerSet.iterator();
   while(customizerIter.hasNext())
   {
      String nextPage = (String) customizerIter.next();
      Iterator viewPrefixIter = viewPrefixVector.iterator();
      while(viewPrefixIter.hasNext())
      {
         String nextViewPrefix = (String) viewPrefixIter.next();
         if(nextViewPrefix.compareTo(GLOBALS.NEW) == 0)
         {
%>
<workflow:basic 
   name="<%= storeName + commonStrings.SPACE + nextViewPrefix + commonStrings.SPACE + nextPage + commonStrings.SPACE + TitleData.VIEWNAMEKEY + commonStrings.SPACE + BodyData.VIEWNAMEKEY + commonStrings.SPACE + CustomizerTransformInfoData.NAME %>" />
<%
         }
      }
   }
%>

<%
final String STORENEWGLOBALSCUSTOMIZER = 
   storeName + commonStrings.SPACE + GLOBALS.NEW + commonStrings.SPACE + GLOBALSPAGE  + commonStrings.SPACE + CustomizerTransformInfoData.NAME;

final String STORENEWMETACUSTOMIZER = 
   storeName + commonStrings.SPACE + GLOBALS.NEW + commonStrings.SPACE + METASPAGE + commonStrings.SPACE + CustomizerTransformInfoData.NAME;
%>

<workflow:basic name="<%= STORENEWGLOBALSCUSTOMIZER %>" />
<workflow:basic name="<%= STORENEWMETACUSTOMIZER %>" />

</BODY>
</HTML>
