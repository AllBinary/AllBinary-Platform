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
package org.allbinary.game.layer.weapon;

import org.allbinary.util.CircularIndexUtil;

public class WeaponLayerArrayLayerCircularStaticPool
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final WeaponLayerArrayLayerCircularStaticPool instance = 
        new WeaponLayerArrayLayerCircularStaticPool();

    public static WeaponLayerArrayLayerCircularStaticPool getInstance()
    {
        return instance;
    }

    private final int MAX = 5;
    private CircularIndexUtil circularIndexUtil = CircularIndexUtil.getInstance(MAX);

    private Object[][][] ALL_WEAPONLAYER_ARRAY = new Object[4][][];

    public void init()
    {
        Object[][] WEAPONLAYER_ARRAY = new Object[MAX * 2][];
        Object[][] TWO_WEAPONLAYER_ARRAY = new Object[MAX][];
        Object[][] THREE_WEAPONLAYER_ARRAY = new Object[MAX][];

        for (int index = 0; index < MAX; index++)
        {
            WEAPONLAYER_ARRAY[index] = new Object[1];
            TWO_WEAPONLAYER_ARRAY[index] = new Object[2];
            THREE_WEAPONLAYER_ARRAY[index] = new Object[3];
        }
        ALL_WEAPONLAYER_ARRAY[1] = WEAPONLAYER_ARRAY;
        ALL_WEAPONLAYER_ARRAY[2] = TWO_WEAPONLAYER_ARRAY;
        ALL_WEAPONLAYER_ARRAY[3] = THREE_WEAPONLAYER_ARRAY;
    }

    public synchronized WeaponLayer[] getInstance(int size) throws Exception
    {
        WeaponLayer[] weaponLayerArray = (WeaponLayer[]) ALL_WEAPONLAYER_ARRAY[size][circularIndexUtil.getIndex()];
        // Object[] objectArray = (Object[]) ALL_WEAPONLAYER_ARRAY[size];
        // WeaponLayer[] weaponLayerArray = (WeaponLayer[]) objectArray[index];

        // logUtil.put("WeaponLayer[] Length: " +
        // weaponLayerArray.length, this,
        // commonStrings.GET_INSTANCE);

        circularIndexUtil.next();

        return weaponLayerArray;
    }

}