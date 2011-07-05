/*
 * AllBinary Open License Version 1
 * Copyright (c) 2011 AllBinary
 *
 * Created By: Travis Berthelot
 * Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 * Modified By         When       ?
 *
 */

package allbinary.game.layer;

import java.util.Enumeration;
import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import allbinary.layer.Layer;
import allbinary.logic.math.SmallIntegerSingletonFactory;

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
