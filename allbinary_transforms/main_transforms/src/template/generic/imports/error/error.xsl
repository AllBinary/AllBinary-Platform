<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:jsp="http://java.sun.com/JSP/Page"
>

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

    <xsl:template name="errorOutput" >    
 
<jsp:directive.page import="java.io.*" />
<jsp:scriptlet>
 String msg = "None";
 String uri = "None";
 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 
 PrintWriter printWriter = new PrintWriter(byteArrayOutputStream, true); 
 if(exception!=null) exception.printStackTrace(printWriter);
 String stackTrace = byteArrayOutputStream.toString();
 if(exception!=null)
 {
  msg = exception.getMessage();
 }

 if(request!=null)
 {
  uri = request.getRequestURI();
 }
</jsp:scriptlet>

<p/>
Request URI: <br/>
<jsp:expression>uri</jsp:expression> 
<p/>

Exception Msg: <br/>
<jsp:expression>msg</jsp:expression> 
<p/>

Exception Stack: <br/>
<jsp:expression>stackTrace</jsp:expression> 
<p/>
    
    </xsl:template>

</xsl:stylesheet>