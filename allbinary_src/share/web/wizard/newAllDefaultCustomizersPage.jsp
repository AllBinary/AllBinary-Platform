<%
/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
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
