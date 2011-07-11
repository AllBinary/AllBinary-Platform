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
   <xsl:output method="html"/>

   <xsl:template match="/html" >   
      <xsl:for-each select="en" >
         <xsl:for-each select="US" >
   
            <xsl:for-each select="STOREFRONT_NAME" >

         <xsl:variable name="command" select="REQUEST/AdminCommand/value" />       

      <xsl:variable name="storeCurrentHostNameName" select="STOREFRONT_CURRENTHOSTNAME/name" />
      <xsl:variable name="storeCurrentHostNameValue" select="STOREFRONT_CURRENTHOSTNAME/value" />

      <xsl:variable name="storeCurrentHostNamePathName" select="STOREFRONT_CURRENTHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHostNamePathValue" select="STOREFRONT_CURRENTHOSTNAMEPATH/value" />
   
      <xsl:variable name="storeCurrentHomeHostNameName" select="STOREFRONT_CURRENTHOMEHOSTNAME/name" />
      <xsl:variable name="storeCurrentHomeHostNameValue" select="STOREFRONT_CURRENTHOMEHOSTNAME/value" />

      <xsl:variable name="storeCurrentHomeHostNamePathName" select="STOREFRONT_CURRENTHOMEHOSTNAMEPATH/name" />
      <xsl:variable name="storeCurrentHomeHostNamePathValue" select="STOREFRONT_CURRENTHOMEHOSTNAMEPATH/value" />

      <xsl:variable name="storeNameName" select="STOREFRONT_NAME/name" />
      <xsl:variable name="storeNameValue" select="STOREFRONT_NAME/value" />

      <xsl:variable name="storeHomeHostNameName" select="STOREFRONT_HOMEHOSTNAME/name" />
      <xsl:variable name="storeHomeHostNameValue" select="STOREFRONT_HOMEHOSTNAME/value" />

      <xsl:variable name="storeHomeHostNamePathName" select="STOREFRONT_HOMEHOSTNAMEPATH/name" />
      <xsl:variable name="storeHomeHostNamePathValue" select="STOREFRONT_HOMEHOSTNAMEPATH/value" />

      <xsl:variable name="storeHostNameName" select="STOREFRONT_HOSTNAME/name" />
      <xsl:variable name="storeHostNameValue" select="STOREFRONT_HOSTNAME/value" />

      <xsl:variable name="storeHostNamePathName" select="STOREFRONT_HOSTNAMEPATH/name" />
      <xsl:variable name="storeHostNamePathValue" select="STOREFRONT_HOSTNAMEPATH/value" />

      <xsl:variable name="storeTestHomeHostNameName" select="STOREFRONT_TESTHOMEHOSTNAME/name" />
      <xsl:variable name="storeTestHomeHostNameValue" select="STOREFRONT_TESTHOMEHOSTNAME/value" />

      <xsl:variable name="storeTestHomeHostNamePathName" select="STOREFRONT_TESTHOMEHOSTNAMEPATH/name" />
      <xsl:variable name="storeTestHomeHostNamePathValue" select="STOREFRONT_TESTHOMEHOSTNAMEPATH/value" />

      <xsl:variable name="storeTestHostNameName" select="STOREFRONT_TESTHOSTNAME/name" />
      <xsl:variable name="storeTestHostNameValue" select="STOREFRONT_TESTHOSTNAME/value" />

      <xsl:variable name="storeTestHostNamePathName" select="STOREFRONT_TESTHOSTNAMEPATH/name" />
      <xsl:variable name="storeTestHostNamePathValue" select="STOREFRONT_TESTHOSTNAMEPATH/value" />

      <xsl:variable name="storeImagePathName" select="STOREFRONT_IMAGEPATH/name" />
      <xsl:variable name="storeImagePathValue" select="STOREFRONT_IMAGEPATH/value" />

      <xsl:variable name="storeStaticPathName" select="STOREFRONT_STATICPATH/name" />
      <xsl:variable name="storeStaticPathValue" select="STOREFRONT_STATICPATH/value" />
      
      <xsl:variable name="storeCategoryPathName" select="STOREFRONT_CATEGORYPATH/name" />
      <xsl:variable name="storeCategoryPathValue" select="STOREFRONT_CATEGORYPATH/value" />

      <xsl:variable name="storeInventoryControlName" select="STOREFRONT_INVENTORYCONTROL/name" />
      <xsl:variable name="storeInventoryControlValue" select="STOREFRONT_INVENTORYCONTROL/value" />

      <xsl:variable name="storeOutName" select="STOREFRONT_OUT/name" />
      <xsl:variable name="storeOutValue" select="STOREFRONT_OUT/value" />

      <xsl:variable name="storeAgeName" select="STOREFRONT_AGE/name" />
      <xsl:variable name="storeAgeValue" select="STOREFRONT_AGE/value" />

      <xsl:variable name="storeSaleName" select="STOREFRONT_SALE/name" />
      <xsl:variable name="storeSaleValue" select="STOREFRONT_SALE/value" />

      <xsl:variable name="storeNotificationName" select="STOREFRONT_NOTIFICATION/name" />
      <xsl:variable name="storeNotificationValue" select="STOREFRONT_NOTIFICATION/value" />

      <xsl:variable name="storeOrderNotificationName" select="STOREFRONT_ORDERNOTIFICATION/name" />
      <xsl:variable name="storeOrderNotificationValue" select="STOREFRONT_ORDERNOTIFICATION/value" />

      <xsl:variable name="storeOrderCancelNotificationName" select="STOREFRONT_ORDERCANCELNOTIFICATION/name" />
      <xsl:variable name="storeOrderCancelNotificationValue" select="STOREFRONT_ORDERCANCELNOTIFICATION/value" />
      
      <xsl:variable name="storeSubStoresName" select="STOREFRONT_SUBSTORES/name" />
      <xsl:variable name="storeSubStoresValue" select="STOREFRONT_SUBSTORES/value" />

      <xsl:variable name="storeTagLocationName" select="STOREFRONT_TAGLOCATION/name" />
      <xsl:variable name="storeTagLocationValue" select="STOREFRONT_TAGLOCATION/value" />

      <xsl:variable name="storePackageLocationName" select="STOREFRONT_PACKAGELOCATION/name" />
      <xsl:variable name="storePackageLocationValue" select="STOREFRONT_PACKAGELOCATION/value" />

      <xsl:variable name="storeFtpName" select="STOREFRONT_FTP/name" />
      <xsl:variable name="storeFtpValue" select="STOREFRONT_FTP/value" />
      
      <xsl:variable name="storeFtpPathName" select="STOREFRONT_FTPPATH/name" />
      <xsl:variable name="storeFtpPathValue" select="STOREFRONT_FTPPATH/value" />

      <xsl:variable name="storeFtpUserNameName" select="STOREFRONT_FTPUSERNAME/name" />
      <xsl:variable name="storeFtpUserNameValue" select="STOREFRONT_FTPUSERNAME/value" />

      <xsl:variable name="storeFtpPasswordName" select="STOREFRONT_FTPPASSWORD/name" />
      <xsl:variable name="storeFtpPasswordValue" select="STOREFRONT_FTPPASSWORD/value" />

      <xsl:variable name="storeTestFtpName" select="STOREFRONT_TESTFTP/name" />
      <xsl:variable name="storeTestFtpValue" select="STOREFRONT_TESTFTP/value" />
      
      <xsl:variable name="storeTestFtpPathName" select="STOREFRONT_TESTFTPPATH/name" />
      <xsl:variable name="storeTestFtpPathValue" select="STOREFRONT_TESTFTPPATH/value" />

      <xsl:variable name="storeTestFtpUserNameName" select="STOREFRONT_TESTFTPUSERNAME/name" />
      <xsl:variable name="storeTestFtpUserNameValue" select="STOREFRONT_TESTFTPUSERNAME/value" />

      <xsl:variable name="storeTestFtpPasswordName" select="STOREFRONT_TESTFTPPASSWORD/name" />
      <xsl:variable name="storeTestFtpPasswordValue" select="STOREFRONT_TESTFTPPASSWORD/value" />

      <xsl:variable name="timeCreatedName" select="STOREFRONT_TIMECREATED/name" />
      <xsl:variable name="timeCreatedValue" select="STOREFRONT_TIMECREATED/value" />

      <xsl:variable name="lastModifiedName" select="STOREFRONT_LASTMODIFIED/name" />
      <xsl:variable name="lastModifiedValue" select="STOREFRONT_LASTMODIFIED/value" />
                        
<TABLE class="table" width="500" >
<TR>
<TD width="%40">Name: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeNameName}"  value="{$storeNameValue}" />
</TD></TR>
<TR><TD width="%40">Home HostName: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeHomeHostNameName}" value="{$storeHomeHostNameValue}"/>
</TD></TR>
<TR><TD width="%40">Home HostName Path: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeHomeHostNamePathName}" value="{$storeHomeHostNamePathValue}"/>
</TD></TR>
<TR><TD width="%40">Hostname: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeHostNameName}" value="{$storeHostNameValue}"/>
</TD></TR>
<TR><TD width="%40">Hostname Path: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeHostNamePathName}" value="{$storeHostNamePathValue}"/>
</TD></TR>
<TR>
<TD width="%40">Image Path: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeImagePathName}" value="{$storeImagePathValue}"/>
</TD>
</TR><TR>
<TD width="%40">Static Path: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeStaticPathName}" value="{$storeStaticPathValue}"/>
</TD>
</TR><TR>
<TD width="%40">Category Path: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeCategoryPathName}" value="{$storeCategoryPathValue}"/>
</TD>
</TR>
<TR><TD width="%40">Inventory Control: </TD>
<TD width="%60">
<SELECT class="select" name="{$storeInventoryControlName}" >
<OPTION>
   <xsl:value-of select="$storeInventoryControlValue" 
      disable-output-escaping="yes" />
</OPTION>
<OPTION>Yes</OPTION>
<OPTION>No</OPTION>
</SELECT>
</TD></TR>
<TR><TD width="%40">Out: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeOutName}" value="{$storeOutValue}"/>
</TD></TR>
<TR><TD width="%40">Age: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeAgeName}" value="{$storeAgeValue}"/>
</TD></TR>
<TR><TD width="%40">Sale: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeSaleName}" value="{$storeSaleValue}"/>
</TD></TR>
<TR><TD width="%40">Notification: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeNotificationName}" value="{$storeNotificationValue}"/>
</TD></TR>
<TR><TD width="%40">Order: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeOrderNotificationName}" value="{$storeOrderNotificationValue}"/>
</TD></TR>
<TR><TD width="%40">Order Cancel: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeOrderCancelNotificationName}" value="{$storeOrderCancelNotificationValue}"/>
</TD></TR>

<input class="text" type="hidden" size="38" name="{$storeTagLocationName}" value=""/>
<input class="text" type="hidden" size="38" name="{$storePackageLocationName}" value="generic"/>

<TR><TD width="%40">FTP: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeFtpName}" value="{$storeFtpValue}"/>
</TD></TR>
<TR><TD width="%40">FTP Username: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeFtpUserNameName}" value="{$storeFtpUserNameValue}"/>
</TD></TR>
<TR><TD width="%40">FTP Password: </TD>
<TD width="%60">
<input class="text" type="text" size="38" name="{$storeFtpPasswordName}" value="{$storeFtpPasswordValue}"/> 
</TD></TR>
</TABLE>
         
      </xsl:for-each>
      </xsl:for-each>
      </xsl:for-each>            
                  
   </xsl:template>
</xsl:stylesheet> 
