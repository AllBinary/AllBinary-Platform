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

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.allbinary.data.tree.dom.ModDomHelper;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

//Note: if request is destroyed or messed up this will not work. Use fileRequestParams
public class RequestParams
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    
    private Map map;

    public RequestParams()
    {
        logUtil.put("New RequestParams Size: 0", this, this.commonStrings.CONSTRUCTOR);
    }

    public RequestParams(HttpServletRequest request)
    {
        map = request.getParameterMap();

        logUtil.put("RequestParams Size: " + this.getMap().keySet().size(), this, this.commonStrings.CONSTRUCTOR);
    }

    public RequestParams(PageContext pageContext)
    {
        map = pageContext.getRequest().getParameterMap();

        logUtil.put("Request Params Size: " + this.getMap().keySet().size(), this, this.commonStrings.CONSTRUCTOR);
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
            final Node node = document.createElement(RequestData.REQUEST);

            final StringMaker stringBuffer = new StringMaker();
                        
            final Set keys = map.keySet();
            final Object[] keyArray = keys.toArray();
            final int size = keyArray.length;

            for (int i = 0; i < size; i++)
            {
                String key = (String) keyArray[i];
                String[] values = (String[]) map.get(key);

                stringBuffer.delete(0, stringBuffer.length());

                stringBuffer.append(KEY);
                stringBuffer.append(key);
                stringBuffer.append(VALUE);
                stringBuffer.append(values[0]);

                logUtil.put(stringBuffer.toString(), this, "toXmlNode(document)");

                node.appendChild(ModDomHelper.createNameValueNodes(
                        document, RequestData.PARAMETER, new String(key), new String(values[0])));
            }
            return node;
        }catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "toXmlNode(document)", e);

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
        Object[] keyArray = keys.toArray();
        int size = keyArray.length;
        StringMaker stringBuffer = new StringMaker();

        for (int i = 0; i < size; i++)
        {
            String key = (String) keyArray[i];

            //Object[] value = (Object[]) map.get(key);

            //logUtil.put("Class: " + value.getClass().getName(), this, "toHashMap()");
            //stringBuffer.delete(0, stringBuffer.length());

            //stringBuffer.append(KEY);
            //stringBuffer.append(key);
            //stringBuffer.append(VALUE);
            //stringBuffer.append(value[0]);

            //logUtil.put(stringBuffer.toString(), this, "toHashMap()");

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

        logUtil.put(stringBuffer.toString(), this, "toHashMap()");

        return hashMap;
    }
}
