<jsp:root 
   xmlns:jsp="http://java.sun.com/JSP/Page" 
   xmlns:jutil="/WEB-INF/jutil.tld"
   xmlns:admin="/WEB-INF/admin.tld"   
   xmlns:ecommerce="/WEB-INF/ecommerce.tld"   
   xmlns:generic="/WEB-INF/generic.tld"
   xmlns:payment="/WEB-INF/payment.tld"
   xmlns:transform="/WEB-INF/transform.tld"
   xmlns:transformInfoObjectConfig="/WEB-INF/transformInfoObjectConfig.tld"
   version="1.2">
<jsp:directive.page contentType="text/html"/>
<jsp:directive.page isErrorPage="true" />

<HTML>
<HEAD>
<TITLE>Licensing Error Page</TITLE>
</HEAD>
<BODY COLOR="#ffffff" >
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
</BODY>
</HTML>
</jsp:root>