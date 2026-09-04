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

import org.allbinary.layer.Layer;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.util.ABHashtable;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import org.allbinary.util.EnumerationUtil;

/**
 *
 * TWB - SmallIntegerSingletonFactory may not be needed
 */
public class LevelLayersFinder {
    
    private static final LevelLayersFinder instance = new LevelLayersFinder();

    public static LevelLayersFinder getInstance()
    {
        return LevelLayersFinder.instance;
    }

    private final EnumerationUtil enumerationUtil = EnumerationUtil.getInstance();
    
    public BasicArrayList get(final ABHashtable hashtable)
    {
        final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
        
        final BasicArrayList list = new BasicArrayListD();

        final Enumeration enumeration = hashtable.keys();

        ABHashtable layerHashtableCanBeNull;
        Integer integerCanBeNull;
        Integer cachedIntegerCanBeNull;
        while(this.enumerationUtil.hasMoreElements(enumeration))
        {
            layerHashtableCanBeNull = (ABHashtable) hashtable.get((Object) this.enumerationUtil.nextElement(enumeration));

            integerCanBeNull = (Integer) layerHashtableCanBeNull.get((Object) Layer.ID);

            cachedIntegerCanBeNull = smallIntegerSingletonFactory.getAt(integerCanBeNull.intValue());

            if(!list.contains(cachedIntegerCanBeNull))
            {
                list.add(cachedIntegerCanBeNull);
            }
        }

        return list;
    }
}
