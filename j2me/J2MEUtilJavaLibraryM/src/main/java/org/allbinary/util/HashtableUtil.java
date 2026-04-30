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
import org.allbinary.logic.NullUtil;

public class HashtableUtil
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final HashtableUtil instance = new HashtableUtil();

    public static HashtableUtil getInstance()
    {
        return HashtableUtil.instance;
    }

    private final EnumerationUtil enumerationUtil = EnumerationUtil.getInstance();
    
    public void putAll(final Hashtable fromHashtable, final Hashtable hashtable)
    {
        final Enumeration enumeration = fromHashtable.keys();
        Object keyCanBeNull;
        Object valueCanBeNull;
        while (this.enumerationUtil.hasMoreElements(enumeration))
        {
            keyCanBeNull = this.enumerationUtil.nextElement(enumeration);
            valueCanBeNull = hashtable.get(keyCanBeNull);

            if(valueCanBeNull == null)
            {
                //PreLogUtil.put("key: " + key, this, "putAll");
            }
            else
            {
                hashtable.put(keyCanBeNull, valueCanBeNull);
            }
        }
    }

    public Object[] getKeysAsArray(final Hashtable hashtable)
    {
        //return hashtable.keySet().toArray();

        final Object[] objectArray = new Object[hashtable.size()];

        int index = 0;
        final Enumeration enumeration = hashtable.keys();
        while (this.enumerationUtil.hasMoreElements(enumeration))
        {
            objectArray[index++] = this.enumerationUtil.nextElement(enumeration);
        }

        return objectArray;
    }

    public BasicArrayList getKeysAsList(final Hashtable hashtable)
    {
        //throw new IllegalArgumentException("Should not use - to much object creation");
        //return hashtable.keySet().toArray();

        final BasicArrayList list = new BasicArrayListS(hashtable.size());
        
        final Enumeration enumeration = hashtable.keys();
        while(this.enumerationUtil.hasMoreElements(enumeration))
        {
            list.add(this.enumerationUtil.nextElement(enumeration));
        }
        
        return list;
    }

}
