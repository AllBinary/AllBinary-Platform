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
package allbinary.game.layer.weapon.mine;

import java.util.Hashtable;

import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactory;
import org.allbinary.game.layer.pickup.IconLayerFactory;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerTypeFactory;

import allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import allbinary.game.combat.weapon.WeaponProperties;
import allbinary.game.layer.weapon.WeaponLayerCircularPool;
import allbinary.game.score.ScoreableInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.LayerInterfaceFactoryInterface;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class MineCountedPickedUpLayerInterfaceFactory 
    extends CountedPickedUpLayerInterfaceFactory 
    implements LayerInterfaceFactoryInterface
{
    private final WeaponLayerCircularPool weaponLayerCircularStaticPool = 
        MineLayerCircularStaticPool.getInstance();

    private static PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface;

    public static void init() throws Exception
    {
        pickedUpLayerInterfaceFactoryInterface = new MineCountedPickedUpLayerInterfaceFactory();
    }

    private MineCountedPickedUpLayerInterfaceFactory() throws Exception
    {
        super(PickedUpLayerTypeFactory.getInstance().MINE, 
                IconLayerFactory.getInstance(
                FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance()
                        .get(MineWeaponResources.getInstance().DROP_ICON_RESOURCE).getInstance(), 10, 10),
                        FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance()
                        .get(MineWeaponResources.getInstance().DROP_RESOURCE).getInstance()
                        );
    }
    
    public static PickedUpLayerInterfaceFactoryInterface getInstance()
    {
        return pickedUpLayerInterfaceFactoryInterface;
    }

    public int getTotal()
    {
        return 3;
    }

    private final WeaponProperties weaponProperties = new WeaponProperties(0, 8000, (short) 10);
    
    private final SmallIntegerSingletonFactory smallIntegerSingletonFactory = SmallIntegerSingletonFactory.getInstance();
    
    public AllBinaryLayer getInstance(Hashtable hashtable, int x, int y)
            throws Exception
    {
        AllBinaryLayer sourceLayerInterface = (AllBinaryLayer) 
            hashtable.get(smallIntegerSingletonFactory.getInstance(0));

        Integer angle = (Integer) hashtable.get(smallIntegerSingletonFactory.getInstance(1));

        return this.weaponLayerCircularStaticPool.getInstance(
                sourceLayerInterface, x, y, (short) angle.intValue(),
                weaponProperties,
                (ScoreableInterface) sourceLayerInterface);
    }
}