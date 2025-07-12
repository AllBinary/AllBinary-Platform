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

package org.allbinary.game.layer;

import java.util.Enumeration;
import java.util.Hashtable;

import org.allbinary.layer.Layer;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.util.BasicArrayList;

/**
 *
 * TWB - SmallIntegerSingletonFactory may not be needed
 */
public class LevelLayersFinder {
    
    private static final LevelLayersFinder instance = new LevelLayersFinder();

    public static LevelLayersFinder getInstance()
    {
        return instance;
    }

    public BasicArrayList get(Hashtable hashtable)
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        BasicArrayList list = new BasicArrayList();

        Enumeration enumeration = hashtable.keys();

        while(enumeration.hasMoreElements())
        {
            Hashtable layerHashtable = (Hashtable) hashtable.get(enumeration.nextElement());

            Integer integer = (Integer) layerHashtable.get(Layer.ID);

            Integer cachedInteger = smallIntegerSingletonFactory.getInstance(integer.intValue());

            if(!list.contains(cachedInteger))
            {
                list.add(cachedInteger);
            }
        }

        return list;
    }
}
