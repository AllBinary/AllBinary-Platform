<?xml version="1.0" encoding="UTF-8"?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:jutil="/WEB-INF/jutil.tld" xmlns:admin="/WEB-INF/admin.tld" xmlns:ecommerce="/WEB-INF/ecommerce.tld" xmlns:generic="/WEB-INF/generic.tld" xmlns:payment="/WEB-INF/payment.tld" xmlns:view="/WEB-INF/transform.tld" xmlns:transformInfoObjectConfig="/WEB-INF/transformInfoObjectConfig.tld" version="1.2">
<jsp:directive.page contentType="text/html"/>
<HTML>
<HEAD>
<TITLE>Retail1 - JspError</TITLE>
</HEAD>
<jutil:element name="BODY">
<jsp:scriptlet>
/*
 *Copyright (c) 2002-2004 AllBinary.
 *License information: http://www.weblisket.com/license.html
 */
</jsp:scriptlet>
<jsp:directive.page isErrorPage="true"/>
<div class="mainHeading">
<p>Jsp Error</p>
<div class="main">
      Please notify the site administrator.   
   </div>
</div>
<jsp:directive.page import="java.io.*"/>
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
</jutil:element>
</HTML>
</jsp:root>
