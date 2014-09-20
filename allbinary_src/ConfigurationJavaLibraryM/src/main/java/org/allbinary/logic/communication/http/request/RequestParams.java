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

import org.allbinary.logic.communication.http.request.RequestData;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.data.tree.dom.ModDomHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//Note: if request is destroyed or messed up this will not work. Use fileRequestParams
public class RequestParams
{
   private Map map;
   
   public RequestParams()
   {
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
      {
         LogUtil.put(LogFactory.getInstance("New RequestParams Size: 0", this, "Constructor()"));
      }
   }
   
   public RequestParams(HttpServletRequest request)
   {
      map = request.getParameterMap();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
      {
         LogUtil.put(LogFactory.getInstance("RequestParams Size: " + this.getMap().keySet().size(), this, "Constructor()"));
      }
   }
   
   public RequestParams(PageContext pageContext)
   {
      map = pageContext.getRequest().getParameterMap();
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
      {
         LogUtil.put(LogFactory.getInstance("Request Params Size: " + this.getMap().keySet().size(), this, "Constructor()"));
      }
   }
   
   protected void setMap(Map map)
   {
      this.map = map;
   }

   protected Map getMap()
   {
      return this.map;
   }

   private final String KEY = "Key: ";
   private final String VALUE = " Value: ";
   
   public Node toXmlNode(Document document)
   {
      try
      {
         Node node = document.createElement(RequestData.REQUEST);
         
         Set keys = map.keySet();
         Iterator keyIter = keys.iterator();

         StringBuffer stringBuffer = new StringBuffer();
         
         while(keyIter.hasNext())
         {
            String key = (String) keyIter.next();
            String values[] = (String[]) map.get(key);
            
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
               org.allbinary.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
            {
            	stringBuffer.delete(0, stringBuffer.length());

            	stringBuffer.append(KEY);
            	stringBuffer.append(key);
            	stringBuffer.append(VALUE);
            	stringBuffer.append(values[0]);

               LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "toXmlNode(document)"));
            }
            
            node.appendChild(ModDomHelper.createNameValueNodes(
            document, RequestData.PARAMETER, new String(key), new String(values[0])));
         }
         return node;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "toXmlNode(document)", e));
         }
         //throw e;
         return null;
      }
   }
   
   //Converts request data from key-valueArray to key[index]=value
   //This makes it act like key->string hashmap since most cases
   //do not involve multiple values
   public HashMap toHashMap() throws Exception
   {
      HashMap hashMap = new HashMap();
      Set keys = map.keySet();
      Iterator keyIter = keys.iterator();
      
      while(keyIter.hasNext())
      {
         String key = (String) keyIter.next();
         
         //Object value = map.get(key);
         
         //if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         //abcs.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
         //{
           // LogUtil.put(LogFactory.getInstance("Class: " + value.getClass().getName(), this, "toHashMap()"));
         //}

         //At some point they made them all String[] but you never know if they will go back or what
         String values[] = (String[]) map.get(key);
         hashMap.put(new String(key), new String(values[0]));
         
         
         /*
         if(value instanceof String)
         {
        	 hashMap.put(new String(key), new String((String) value));
         }
         else
            if(value instanceof String[])
            {
               String values[] = (String[]) map.get(key);
               hashMap.put(new String(key), new String(values[0]));
            }
            */
      }
      
      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
      org.allbinary.logic.communication.log.config.type.LogConfigType.HTTPREQUEST))
      {
    	  StringBuffer stringBuffer = new StringBuffer();
    	  
    	  stringBuffer.append("RequestParams: ");
    	  stringBuffer.append(this.map.toString());
    	  stringBuffer.append("\ntoHashMap: ");
    	  stringBuffer.append(hashMap.toString());
 
         LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "toHashMap()"));
      }
      
      return hashMap;
   }
}
