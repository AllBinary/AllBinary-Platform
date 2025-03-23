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
import org.allbinary.string.CommonStrings;

//Note: if request is destroyed or messed up this will not work. Use fileRequestParams
public class RequestParams
{

    private Map map;

    public RequestParams()
    {
        LogUtil.put(LogFactory.getInstance("New RequestParams Size: 0", this, "Constructor()"));
    }

    public RequestParams(HttpServletRequest request)
    {
        map = request.getParameterMap();

        LogUtil.put(LogFactory.getInstance("RequestParams Size: " + this.getMap().keySet().size(), this, "Constructor()"));
    }

    public RequestParams(PageContext pageContext)
    {
        map = pageContext.getRequest().getParameterMap();

        LogUtil.put(LogFactory.getInstance("Request Params Size: " + this.getMap().keySet().size(), this, "Constructor()"));
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
                String[] values = (String[]) map.get(key);

                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(KEY);
                stringBuffer.append(key);
                stringBuffer.append(VALUE);
                stringBuffer.append(values[0]);

                LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "toXmlNode(document)"));

                node.appendChild(ModDomHelper.createNameValueNodes(
                        document, RequestData.PARAMETER, new String(key), new String(values[0])));
            }
            return node;
        }catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "toXmlNode(document)", e));

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

        StringBuffer stringBuffer = new StringBuffer();

        while(keyIter.hasNext())
        {
            String key = (String) keyIter.next();

            //Object[] value = (Object[]) map.get(key);

            //LogUtil.put(LogFactory.getInstance("Class: " + value.getClass().getName(), this, "toHashMap()"));
            //stringBuffer.delete(0, stringBuffer.length());

            //stringBuffer.append(KEY);
            //stringBuffer.append(key);
            //stringBuffer.append(VALUE);
            //stringBuffer.append(value[0]);

            //LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "toHashMap()"));

            //At some point they made them all String[] but you never know if they will go back or what
            String[] values = (String[]) map.get(key);
            hashMap.put(new String(key), new String(values[0]));

            /*
             if(value instanceof String)
             {
             hashMap.put(new String(key), new String((String) value));
             }
             else
             if(value instanceof String[])
             {
             String[] values = (String[]) map.get(key);
             hashMap.put(new String(key), new String(values[0]));
             }
             */
        }

        stringBuffer.delete(0, stringBuffer.length());

        stringBuffer.append("RequestParams: ");
        stringBuffer.append(this.map.toString());
        stringBuffer.append("\ntoHashMap: ");
        stringBuffer.append(hashMap.toString());

        LogUtil.put(LogFactory.getInstance(stringBuffer.toString(), this, "toHashMap()"));

        return hashMap;
    }
}
