<?xml version="1.0" encoding="UTF-8" ?>

<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template name="storefrontGlobals"
                  xmlns:jsp="http://java.sun.com/JSP/Page"
    >

<jsp:scriptlet>
   
   URLGLOBALS.setWebappPath(request.getRealPath("/"));

   WebappClassLoaderInfo.setLoader(getClass().getClassLoader());

            <xsl:for-each select="STOREFRONT_NAME" >
               <xsl:variable name="storeNameName" select="STOREFRONT_NAME/name" />
               <xsl:variable name="storeNameValue" select="STOREFRONT_NAME/value" />
   String STORENAME = "<xsl:value-of select="$storeNameValue" />";
            </xsl:for-each>

   BasicStoreFrontInterface storeFrontInterface = 
      BasicStoreFrontFactory.getInstance(STORENAME);

   String HOMEHOSTNAME = 
      storeFrontInterface.getCurrentHomeHostName() + 
      storeFrontInterface.getCurrentHomeHostNamePath();

   final String LOCATION = 
      storeFrontInterface.getCurrentHostName() + 
      storeFrontInterface.getCurrentHostNamePath();

   String HOSTNAMEPATH = storeFrontInterface.getCurrentHostNamePath();

   final String STATICPATH = storeFrontInterface.getStaticPath();

   final String STOREWIZARDPAGE = LOCATION + "wizard/start.jsp";
</jsp:scriptlet>
   
   </xsl:template>

</xsl:stylesheet>
