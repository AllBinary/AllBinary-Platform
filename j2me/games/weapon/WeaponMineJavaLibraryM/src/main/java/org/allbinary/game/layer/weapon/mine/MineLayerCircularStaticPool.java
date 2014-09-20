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
package org.allbinary.game.layer.weapon.mine;

import org.allbinary.game.layer.weapon.WeaponLayerCircularPool;
import org.allbinary.view.ViewPositionFactoryInterface;

public class MineLayerCircularStaticPool {

	private static WeaponLayerCircularPool WEAPON_LAYER_CIRCULAR_STATIC_POOL = 
		new WeaponLayerCircularPool();

    public static void init(ViewPositionFactoryInterface viewPositionFactoryInterface) throws Exception
    {
    	MineCountedPickedUpLayerInterfaceFactory.init();
    	WEAPON_LAYER_CIRCULAR_STATIC_POOL.init(new MineLayerFactory(viewPositionFactoryInterface), 20);
    }

    public static WeaponLayerCircularPool getInstance()
    {
    	return WEAPON_LAYER_CIRCULAR_STATIC_POOL;
    }
}
