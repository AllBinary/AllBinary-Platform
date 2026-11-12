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

    private WeaponLayer[][] WEAPONLAYER_ARRAY;
    private WeaponLayer[][] TWO_WEAPONLAYER_ARRAY;
    private WeaponLayer[][] THREE_WEAPONLAYER_ARRAY;

    public void init()
    {
        final WeaponLayer[][] WEAPONLAYER_ARRAY = new WeaponLayer[this.MAX * 2][];
        final WeaponLayer[][] TWO_WEAPONLAYER_ARRAY = new WeaponLayer[this.MAX][];
        final WeaponLayer[][] THREE_WEAPONLAYER_ARRAY = new WeaponLayer[this.MAX][];
        
        for (int index = 0; index < this.MAX; index++)
        {
            WEAPONLAYER_ARRAY[index] = new WeaponLayer[1];
            TWO_WEAPONLAYER_ARRAY[index] = new WeaponLayer[2];
            THREE_WEAPONLAYER_ARRAY[index] = new WeaponLayer[3];
        }
        this.WEAPONLAYER_ARRAY = WEAPONLAYER_ARRAY;
        this.TWO_WEAPONLAYER_ARRAY = TWO_WEAPONLAYER_ARRAY;
        this.THREE_WEAPONLAYER_ARRAY = THREE_WEAPONLAYER_ARRAY;
    }

    private WeaponLayer[][] getWeaponLayerArrays(final int size)
    {
        if (size == 1)
        {
            return this.WEAPONLAYER_ARRAY;
        }
        else if (size == 2)
        {
            return this.TWO_WEAPONLAYER_ARRAY;
        }
        else if (size == 3)
        {
            return this.THREE_WEAPONLAYER_ARRAY;
        }
        throw new RuntimeException();
    }

    public WeaponLayer[] getInstanceArray(int size) throws Exception
    {
        final WeaponLayer[] weaponLayerArray = this.getWeaponLayerArrays(size)[this.circularIndexUtil.getIndex()];
        // Object[] objectArray = (Object[]) ALL_WEAPONLAYER_ARRAY[size];
        // WeaponLayer[] weaponLayerArray = (WeaponLayer[]) objectArray[index];

        // this.logUtil.putF("WeaponLayer[] Length: " +
        // weaponLayerArray.length, this, // commonStrings.GET_INSTANCE);

        this.circularIndexUtil.next();

        return weaponLayerArray;
    }

}