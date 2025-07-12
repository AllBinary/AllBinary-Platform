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
package org.allbinary.logic.communication.http.request;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;

public class AbeHttpRequestInfo
{
    private final String NAME = "AbeHttpRequestInfo";

    private final AbeHttpRequestInfoData abeHttpRequestInfoData = 
               AbeHttpRequestInfoData.getInstance();
    private final CommonSeps commonSeps = CommonSeps.getInstance();
    
   private String httpUserAgent;
   private String remoteAddress;     
   private String remoteHost;
   private String remoteHostByAddr; 
   private String remotePort;   
   private String requestedFilePath;

   public AbeHttpRequestInfo(HashMap hashMap)
   {	   
       StringUtil stringUtil = StringUtil.getInstance();
           
      this.httpUserAgent = stringUtil.getInstance(
         (String) hashMap.get(abeHttpRequestInfoData.HTTP_USER_AGENT));

      this.remoteAddress = stringUtil.getInstance(
         (String) hashMap.get(abeHttpRequestInfoData.REMOTE_ADDRESS));

      this.remoteHost = stringUtil.getInstance(
         (String) hashMap.get(abeHttpRequestInfoData.REMOTE_HOST));

      this.remoteHostByAddr = stringUtil.getInstance(
         (String) hashMap.get(abeHttpRequestInfoData.REMOTE_HOST_BY_ADDRESS));

      this.remotePort = stringUtil.getInstance(
         (String) hashMap.get(abeHttpRequestInfoData.REMOTE_PORT));

      this.requestedFilePath = stringUtil.getInstance(
         (String) hashMap.get(abeHttpRequestInfoData.REQUEST_FILE_PATH));
   }
   
   public AbeHttpRequestInfo(HttpServletRequest httpServletRequest)
   {
      final StringUtil stringUtil = StringUtil.getInstance();
      this.httpUserAgent = stringUtil.EMPTY_STRING;
      Enumeration enumuration = httpServletRequest.getHeaderNames();
      while(enumuration.hasMoreElements())
      {
         String key = (String) enumuration.nextElement();
         String value = httpServletRequest.getHeader(key);
         if(key.indexOf("user") >= 0)
            this.httpUserAgent = "key: " + key + " value: " + value;
      }

      /*
      httpServletRequest.getAuthType()
      httpServletRequest.getCharacterEncoding()
      httpServletRequest.getContentLength()
      httpServletRequest.getContentType()
      httpServletRequest.getContextPath();
      httpServletRequest.getLocale()
      httpServletRequest.getPathInfo()
      httpServletRequest.getPathTranslated()
      httpServletRequest.getProtocol()
      httpServletRequest.getQueryString()
      */
      this.remoteHostByAddr = stringUtil.EMPTY_STRING;
      this.remoteAddress = httpServletRequest.getRemoteAddr();
      this.remoteHost = httpServletRequest.getRemoteHost();
      /*
      httpServletRequest.getRemoteUser()
      httpServletRequest.getScheme()
      httpServletRequest.getServerName()
      httpServletRequest.getServerPort()
      */
      this.requestedFilePath = httpServletRequest.getServletPath();
      this.remotePort = Integer.toString(httpServletRequest.getRemotePort());
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      hashMap.put(abeHttpRequestInfoData.HTTP_USER_AGENT, this.httpUserAgent);
      hashMap.put(abeHttpRequestInfoData.REMOTE_ADDRESS, this.remoteAddress);
      hashMap.put(abeHttpRequestInfoData.REMOTE_HOST, this.remoteHost);
      hashMap.put(abeHttpRequestInfoData.REMOTE_HOST_BY_ADDRESS, this.remoteHostByAddr);
      hashMap.put(abeHttpRequestInfoData.REMOTE_PORT, this.remotePort);
      hashMap.put(abeHttpRequestInfoData.REQUEST_FILE_PATH, this.requestedFilePath);

      return hashMap;
   }

   public Vector toVector()
   {
      Vector vector = new Vector();

      vector.add(this.httpUserAgent);
      vector.add(this.remoteAddress);
      vector.add(this.remoteHost);
      vector.add(this.remoteHostByAddr);
      vector.add(this.remotePort);
      vector.add(this.requestedFilePath);

      return vector;
   }
   
   public String toString()
   {
       return new StringBuilder()
               .append(this.NAME)
               .append(this.commonSeps.COLON_SEP)
               .append(abeHttpRequestInfoData.HTTP_USER_AGENT)
               .append(this.commonSeps.COLON_SEP)
               .append(this.httpUserAgent)
               .append(this.commonSeps.SPACE)
               .append(abeHttpRequestInfoData.REMOTE_ADDRESS)
               .append(this.commonSeps.COLON_SEP)
               .append(this.remoteAddress)
               .append(this.commonSeps.SPACE)
               .append(abeHttpRequestInfoData.REMOTE_HOST)
               .append(this.commonSeps.COLON_SEP)
               .append(this.remoteHost)
               .append(this.commonSeps.SPACE)
               .append(abeHttpRequestInfoData.REMOTE_HOST_BY_ADDRESS)
               .append(this.commonSeps.COLON_SEP)
               .append(this.remoteHostByAddr)
               .append(this.commonSeps.SPACE)
               .append(abeHttpRequestInfoData.REMOTE_PORT)
               .append(this.commonSeps.COLON_SEP)
               .append(this.remotePort)
               .append(this.commonSeps.SPACE)
               .append(abeHttpRequestInfoData.REQUEST_FILE_PATH)
               .append(this.commonSeps.COLON_SEP)
               .append(this.requestedFilePath).toString();
   }
}
