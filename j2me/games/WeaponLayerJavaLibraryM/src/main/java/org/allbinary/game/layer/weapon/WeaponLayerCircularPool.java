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

import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.score.ScoreableInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerCircularPool;

public class WeaponLayerCircularPool extends AllBinaryLayerCircularPool
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    public WeaponLayerCircularPool()
    {

    }
    
    public WeaponLayer getInstance(
            AllBinaryLayer sourceLayerInterface, int x, int y, int z, 
            short angle, short otherAngle,
            WeaponProperties weaponProperties, 
            ScoreableInterface scoreable
            )
            throws Exception
    {
        WeaponLayer weaponLayer = (WeaponLayer) super.getNextInstance();

        //TWB - In Game Loop
        /*
        if(!weaponLayer.isDestroyed())
        {
            logUtil.put("TWB Exception: WeaponLayer Pool Is Too Small For: " + weaponLayer, this, commonStrings.GET_INSTANCE);
        }
        */

        weaponLayer.init(x, y, z);
        weaponLayer.init(sourceLayerInterface, angle, otherAngle, weaponProperties, scoreable);

        return weaponLayer;
    }
}
