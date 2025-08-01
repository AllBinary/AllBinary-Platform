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
package org.allbinary.resources;

import java.util.Hashtable;

/**
 *
 * @author User
 */
public class KeyValueFactory
{
    private static final KeyValueFactory instance = new KeyValueFactory();

    /**
     * @return the instance
     */
    public static KeyValueFactory getInstance()
    {
        return instance;
    }
    
    public final Hashtable MAP = new Hashtable();
    
    public String get(String key) {
        
        final String value = (String) MAP.get((Object) key);

        if(value == null)
        {
            return key;
        }
        return value;
    }
}
