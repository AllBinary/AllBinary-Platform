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
package org.allbinary.util;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashtableUtil
{
    private static final HashtableUtil instance = new HashtableUtil();

    public static HashtableUtil getInstance()
    {
        return instance;
    }

    public void putAll(Hashtable fromHashtable, Hashtable hashtable)
    {
        final Enumeration enumeration = fromHashtable.keys();
        while (enumeration.hasMoreElements())
        {
            Object key = enumeration.nextElement();
            Object value = hashtable.get(key);

            if(value == null)
            {
                //PreLogUtil.put("key: " + key, instance, "putAll");
            }
            else
            {
                hashtable.put(key, value);
            }
        }
    }

    public Object[] getKeysAsArray(Hashtable hashtable)
    {
        //return hashtable.keySet().toArray();

        Object[] objectArray = new Object[hashtable.size()];

        int index = 0;
        final Enumeration enumeration = hashtable.keys();
        while (enumeration.hasMoreElements())
        {
            objectArray[index++] = enumeration.nextElement();
        }

        return objectArray;
    }

    public BasicArrayList getKeysAsList(Hashtable hashtable)
    {
        //throw new IllegalArgumentException("Should not use - to much object creation");
        //return hashtable.keySet().toArray();

        final BasicArrayList list = new BasicArrayList(hashtable.size());
        
        final Enumeration enumeration = hashtable.keys();
        while(enumeration.hasMoreElements())
        {
            list.add(enumeration.nextElement());
        }
        
        return list;
    }

}
