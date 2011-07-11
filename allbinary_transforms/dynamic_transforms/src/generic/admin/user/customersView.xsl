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

      <xsl:for-each select="USERS_NAME" >
            
<xsl:if test="not(USER_NAME)" >
<div class="main">
No Customers To List.
</div>
</xsl:if>

<p>Customers</p>
<div class="main">

<table class="table" border="1" cellpadding="0" cellspacing="0" width="500" >

<tr valign="top" >

<td>
<b>User Name</b>
</td>      

<td>
<b>Last Name</b>
</td>

<td>
<b>First Name</b>
</td>

<td>
<b>Main Email</b>
</td>

</tr>
         
      <xsl:for-each select="USER_NAME" >
      
         <xsl:variable name="userNameName" select="CUSTOMER_USER_NAME/name" />
         <xsl:variable name="userNameValue" select="CUSTOMER_USER_NAME/value" />
         
         <xsl:variable name="prefixNameName" select="CUSTOMER_PREFIX_NAME/name" />
         <xsl:variable name="prefixNameValue" select="CUSTOMER_PREFIX_NAME/value" />
         
         <xsl:variable name="firstNameName" select="CUSTOMER_FIRST_NAME/name" />
         <xsl:variable name="firstNameValue" select="CUSTOMER_FIRST_NAME/value" />
         
         <xsl:variable name="lastNameName" select="CUSTOMER_LAST_NAME/name" />
         <xsl:variable name="lastNameValue" select="CUSTOMER_LAST_NAME/value" />
         
         <xsl:variable name="middleNameName" select="CUSTOMER_MIDDLE_NAME/name" />
         <xsl:variable name="middleNameValue" select="CUSTOMER_MIDDLE_NAME/value" />
         
         <xsl:variable name="suffixNameName" select="CUSTOMER_SUFFIX_NAME/name" />
         <xsl:variable name="suffixNameValue" select="CUSTOMER_SUFFIX_NAME/value" />
         
         <xsl:variable name="companyName" select="CUSTOMER_COMPANY/name" />
         <xsl:variable name="companyValue" select="CUSTOMER_COMPANY/value" />
         
         <xsl:variable name="positionAtCompanyName" select="CUSTOMER_POSITION_AT_COMPANY/name" />
         <xsl:variable name="positionAtCompanyValue" select="CUSTOMER_POSITION_AT_COMPANY/value" />
         
         <xsl:variable name="mainEmailName" select="CUSTOMER_MAIN_EMAIL/name" />
         <xsl:variable name="mainEmailValue" select="CUSTOMER_MAIN_EMAIL/value" />
         
         <xsl:variable name="secondaryEmailName" select="CUSTOMER_SECONDARY_EMAIL/name" />
         <xsl:variable name="secondaryEmailValue" select="CUSTOMER_SECONDARY_EMAIL/value" />
         
         <xsl:variable name="homePhoneName" select="CUSTOMER_HOME_PHONE/name" />
         <xsl:variable name="homePhoneValue" select="CUSTOMER_HOME_PHONE/value" />
         
         <xsl:variable name="cellPhoneName" select="CUSTOMER_CELL_PHONE/name" />
         <xsl:variable name="cellPhoneValue" select="CUSTOMER_CELL_PHONE/value" />
         
         <xsl:variable name="workPhoneName" select="CUSTOMER_WORK_PHONE/name" />
         <xsl:variable name="workPhoneValue" select="CUSTOMER_WORK_PHONE/value" />
         
         <xsl:variable name="otherContactName" select="CUSTOMER_OTHER_CONTACT/name" />
         <xsl:variable name="otherContactValue" select="CUSTOMER_OTHER_CONTACT/value" />
         
         <xsl:variable name="electronicDeviceName" select="CUSTOMER_ELECTRONIC_DEVICE/name" />
         <xsl:variable name="electronicDeviceValue" select="CUSTOMER_ELECTRONIC_DEVICE/value" />
         
         <xsl:variable name="faxName" select="CUSTOMER_FAX/name" />
         <xsl:variable name="faxValue" select="CUSTOMER_FAX/value" />
         
         <xsl:variable name="secretName" select="CUSTOMER_SECRET/name" />
         <xsl:variable name="secretValue" select="CUSTOMER_SECRET/value" />
         
         <xsl:variable name="roleName" select="ROLE/name" />
         <xsl:variable name="roleValue" select="ROLE/value" />
                  
         <xsl:variable name="timeCreatedName" select="ENTRY_TIMECREATED/name" />
         <xsl:variable name="timeCreatedValue" select="ENTRY_TIMECREATED/value" />
         
         <xsl:variable name="lastModifiedName" select="ENTRY_LASTMODIFIED/name" />
         <xsl:variable name="lastModifiedValue" select="ENTRY_LASTMODIFIED/value" />
         
         <xsl:variable name="enableName" select="ENTRY_ENABLE/name" />
         <xsl:variable name="enableValue" select="ENTRY_ENABLE/value" />
         
         <xsl:variable name="passwordName" select="CUSTOMER_PASSWORD/name" />

<tr valign="top" >

<td>
<a href="customerManager.jsp?AdminCommand=Edit&#38;{$userNameName}={$userNameValue}" >
<xsl:value-of select="$userNameValue" disable-output-escaping="yes" />
</a>
</td>      

<td>
<xsl:value-of select="$lastNameValue" disable-output-escaping="yes" />
</td>

<td>
<xsl:value-of select="$firstNameValue" disable-output-escaping="yes" />
</td>

<td>
<xsl:value-of select="$mainEmailValue" disable-output-escaping="yes" />
</td>

</tr>

      </xsl:for-each>
</table>      
<br></br>

</div>

         </xsl:for-each>
      </xsl:for-each>
   </xsl:for-each>      
   
   </xsl:template>
</xsl:stylesheet> 
