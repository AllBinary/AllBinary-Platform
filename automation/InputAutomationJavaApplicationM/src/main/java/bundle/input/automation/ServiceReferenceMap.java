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
package bundle.input.automation;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.ServiceReference;

import org.allbinary.logic.string.StringMaker;

public class ServiceReferenceMap implements Map
{
    private ServiceReference serviceReference;
    
    public ServiceReferenceMap(ServiceReference serviceReference)
    {
        this.serviceReference = serviceReference;
    }
    
    public int size()
    {
        return -1;
    }

    public boolean isEmpty()
    {
        return false;
    }

    public boolean containsKey(Object key)
    {
        return false;
    }

    public boolean containsValue(Object value)
    {
         return false;
    }

    public Object get(Object key)
    {
        return this.serviceReference.getProperty((String) key);
    }

    public Object put(Object key, Object value)
    {
        return null;
    }

    public Object remove(Object key)
    {
        return null;
    }

    public void putAll(Map m)
    {
       
    }

    public void clear()
    {
        
    }

    public Set keySet()
    {
        //Set set
        String[] keyStringArray = serviceReference.getPropertyKeys();
        return null;
    }

    public Collection<Object> values()
    {
        return null;
    }

    public Set entrySet()
    {
        return null;
    }
    
    public static String toString(Map map)
    {
        StringMaker stringBuffer = new StringMaker();
        /*
        for(int index2 = 0; index2 < map.keySet().length; index2++)
        {
            stringBuffer.append(keyStringArray[index2]);
            stringBuffer.append(" = ");
            Object value = serviceReference.getProperty(keyStringArray[index2]);
            if(value instanceof String)
            {
                stringBuffer.append(value);
            }
            else
                if(value instanceof String[])
                {
                String[] stringArray = (String[]) value;
                for(int index3 = 0; index3 < stringArray.length; index3++)
                {
                    stringBuffer.append(stringArray[index3]);
                    stringBuffer.append(", ");
                }
                }
            stringBuffer.append(" ");
        }
        */
        return stringBuffer.toString();
    }    
}
