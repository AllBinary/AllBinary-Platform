<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
   <xsl:import href="/template/generic/buttons/buttons.xsl" />   
   
<!--
AllBinary Open License Version 1
Copyright (c) 2011 AllBinary

By agreeing to this license you and any business entity you represent are
legally bound to the AllBinary Open License Version 1 legal agreement.

You may obtain the AllBinary Open License Version 1 legal agreement from
AllBinary or the root directory of AllBinary's AllBinary Platform repository.

Created By: Travis Berthelot
-->
   
   <xsl:output method="xml" indent="yes" />

   <xsl:template name="profileForm">
   
<form method="POST" action="%= PAGE %">

<table class="table" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" >
<input  type="hidden" name="%= EntryData.ENABLE %" />
  <tr>
    <td width="200">Prefix:</td>
    <td width="300"><input class="main" type="text" name="%= UserData.PREFIXNAME %" size="3" />
    <br />
    </td>
  </tr>
  <tr>
    <td width="200">First Name:</td>
    <td width="300"><input class="main" type="text" name="%= UserData.FIRSTNAME %" size="19" />
    </td>
  </tr>
  <tr>
    <td width="200">Middle Initial:</td>
    <td width="300"><input class="main" type="text" name="%= UserData.MIDDLENAME %" size="19" />
    </td>
  </tr>
  <tr>
    <td width="200">Last Name:</td>
    <td width="300"><input class="main" type="text" name="%= UserData.LASTNAME %" size="19" />
    </td>
  </tr>
  <tr>
    <td width="200">Suffix:</td>
    <td width="300"><input class="main" type="text" name="%= UserData.SUFFIXNAME %" size="3" />
    </td>
  </tr>
  <tr>
    <td width="200">User Name:</td>
    <td width="300"><input class="main" type="text" name="%= WeblisketSessionData.REMOVABLEUSERNAME %" size="19" />
    </td>
  </tr>
  <tr>
    <td width="200">Email Address:</td>
    <td width="300"><input class="main" type="text" name="%= UserData.MAINEMAIL %" size="19" />
    </td>
  </tr>
  <tr>
    <td width="200">Password:</td>
    <td width="300"><input class="main" type="password" name="%= WeblisketSessionData.REMOVABLEPASSWORD %" size="19" />
    </td>
  </tr>
  <tr>
    <td width="200">Phone:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.HOMEPHONE %" size="19" />
</td>
  </tr>  
</table>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      GLOBALS.REGISTER
   </xsl:with-param>
   <xsl:with-param name="command">
      NONE
   </xsl:with-param>
</xsl:call-template>

<p/>

Please fill in some or all of the fields below if you prefer not to be contacted through the email address you have provided.
<p/>

<table class="table" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" id="AutoNumber2">
  <tr>
    <td width="200">Company: </td>
    <td width="300">
<input class="main" type="text" name="%= UserData.COMPANY %" size="19" />
</td>
  </tr>
  <tr>
    <td width="200">Position at Company:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.POSITIONATCOMPANY %" size="19" />
</td>
  </tr>
  <tr>
    <td width="200">Second Email Address:</td>
    <td width="300"><input class="main" type="text" name="%= UserData.SECONDARYEMAIL %" size="19" />
    </td>
  </tr>
  <tr>
    <td width="200">Cell Phone:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.CELLPHONE %" size="19" />
</td>
  </tr>
  <tr>
    <td width="200">Work Phone:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.WORKPHONE %" size="19" />
</td>
  </tr>
  <tr>
    <td width="200">Other Contact:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.OTHERCONTACT %" size="19" />
</td>
  </tr>
  <tr>
    <td width="200">Electronic Device:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.ELECTRONICDEVICE %" size="19" />
</td>
  </tr>
  <tr>
    <td width="200">Fax:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.FAX %" size="19" />
</td>
  </tr>
  <tr>
    <td width="200">Password Reminder:</td>
    <td width="300">
<input class="main" type="text" name="%= UserData.SECRET %" size="19" />
</td>
  </tr>
</table>

<xsl:call-template name="submitMenuButtonItem">
   <xsl:with-param name="name">
      GLOBALS.REGISTER
   </xsl:with-param>
   <xsl:with-param name="command">
      NONE
   </xsl:with-param>
</xsl:call-template>

</form>

<p/>

</div>
</div>

    </xsl:template>

</xsl:stylesheet>