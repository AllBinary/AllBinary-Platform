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

import java.util.Hashtable;

import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactory;
import org.allbinary.game.layer.pickup.IconLayerFactory;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerTypeFactory;
import org.allbinary.game.layer.weapon.WeaponLayerCircularPool;
import org.allbinary.game.score.ScoreableInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;

public class MineCountedPickedUpLayerInterfaceFactory 
    extends CountedPickedUpLayerInterfaceFactory 
{
    private final WeaponLayerCircularPool weaponLayerCircularStaticPool = 
        MineLayerCircularStaticPool.getInstance();

    private static PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface = CountedPickedUpLayerInterfaceFactory.NULL_COUNTED_PICKUP_LAYER_FACTORY;

    public static void init() throws Exception
    {
        pickedUpLayerInterfaceFactoryInterface = new MineCountedPickedUpLayerInterfaceFactory();
    }

    private MineCountedPickedUpLayerInterfaceFactory() throws Exception
    {
        super(PickedUpLayerTypeFactory.getInstance().MINE, 
                IconLayerFactory.getInstance(
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance()
                        .get(MineWeaponResources.getInstance().DROP_ICON_RESOURCE).getInstance(0), 10, 10),
                        FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance()
                        .get(MineWeaponResources.getInstance().DROP_RESOURCE).getInstance(0)
                        );
    }
    
    public static PickedUpLayerInterfaceFactoryInterface getInstance()
    {
        return pickedUpLayerInterfaceFactoryInterface;
    }

    @Override
    public int getTotal()
    {
        return 3;
    }

    private final WeaponProperties weaponProperties = new WeaponProperties(0, 8000, (short) 10);
    
    private final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
    
    @Override
    public AllBinaryLayer getInstance(Hashtable hashtable, int x, int y, int z)
            throws Exception
    {
        AllBinaryLayer sourceLayerInterface = (AllBinaryLayer) 
            hashtable.get(smallIntegerSingletonFactory.getInstance(0));

        Integer angle = (Integer) hashtable.get(smallIntegerSingletonFactory.getInstance(1));

        return this.weaponLayerCircularStaticPool.getInstance(
                sourceLayerInterface, x, y, z, (int) angle.intValue(), 0,
                weaponProperties,
                (ScoreableInterface) sourceLayerInterface);
    }
}