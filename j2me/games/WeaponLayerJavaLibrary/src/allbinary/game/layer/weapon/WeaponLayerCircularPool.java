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
package allbinary.game.layer.weapon;

import allbinary.game.combat.weapon.WeaponProperties;
import allbinary.game.score.ScoreableInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerCircularPool;

public class WeaponLayerCircularPool extends AllBinaryLayerCircularPool
{
    public WeaponLayerCircularPool()
    {

    }
    
    public WeaponLayer getInstance(
            AllBinaryLayer sourceLayerInterface, int x, int y, short angle,
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
            LogUtil.put(LogFactory.getInstance("TWB Exception: WeaponLayer Pool Is Too Small For: " + weaponLayer, this, CommonStrings.getInstance().GET_INSTANCE));
        }
        */

        weaponLayer.init(x, y);
        weaponLayer.init(sourceLayerInterface, angle, weaponProperties, scoreable);

        return weaponLayer;
    }
}
