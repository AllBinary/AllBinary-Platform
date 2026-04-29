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
        return WeaponLayerArrayLayerCircularStaticPool.instance;
    }

    private final int MAX = 5;
    private CircularIndexUtil circularIndexUtil = CircularIndexUtil.createInstance(this.MAX);

    private Object[][][] ALL_WEAPONLAYER_ARRAY = new Object[4][][];

    public void init()
    {
        Object[][] WEAPONLAYER_ARRAY = new Object[this.MAX * 2][];
        Object[][] TWO_WEAPONLAYER_ARRAY = new Object[this.MAX][];
        Object[][] THREE_WEAPONLAYER_ARRAY = new Object[this.MAX][];

        for (int index = 0; index < this.MAX; index++)
        {
            WEAPONLAYER_ARRAY[index] = new Object[1];
            TWO_WEAPONLAYER_ARRAY[index] = new Object[2];
            THREE_WEAPONLAYER_ARRAY[index] = new Object[3];
        }
        this.ALL_WEAPONLAYER_ARRAY[1] = WEAPONLAYER_ARRAY;
        this.ALL_WEAPONLAYER_ARRAY[2] = TWO_WEAPONLAYER_ARRAY;
        this.ALL_WEAPONLAYER_ARRAY[3] = THREE_WEAPONLAYER_ARRAY;
    }

    public synchronized WeaponLayer[] getInstanceArray(int size) throws Exception
    {
        final WeaponLayer[] weaponLayerArray = (WeaponLayer[]) this.ALL_WEAPONLAYER_ARRAY[size][this.circularIndexUtil.getIndex()];
        // Object[] objectArray = (Object[]) ALL_WEAPONLAYER_ARRAY[size];
        // WeaponLayer[] weaponLayerArray = (WeaponLayer[]) objectArray[index];

        // this.logUtil.putF("WeaponLayer[] Length: " +
        // weaponLayerArray.length, this, // commonStrings.GET_INSTANCE);

        this.circularIndexUtil.next();

        return weaponLayerArray;
    }

}