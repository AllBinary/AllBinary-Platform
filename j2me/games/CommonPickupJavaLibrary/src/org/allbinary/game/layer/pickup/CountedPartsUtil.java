package org.allbinary.game.layer.pickup;

import allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import allbinary.game.part.CountedLayerInterfaceFactoryPart;
import allbinary.game.part.PartInterface;

public class CountedPartsUtil
{
    private static final CountedPartsUtil instance = new CountedPartsUtil();

    public static CountedPartsUtil getInstance()
    {
        return instance;
    }

    public CountedLayerInterfaceFactoryPart getPartForSlot(
            CollidableDestroyableDamageableLayer collidableDestroyableDamageableLayer, int slotIndex) throws Exception
    {
        int currentSlot = 0;

        PartInterface[] partInterfaceArray = collidableDestroyableDamageableLayer.getPartInterfaceArray();
        
        for (int index = collidableDestroyableDamageableLayer.getPickupBehavior().getCountedIndex(); index < partInterfaceArray.length; index++)
        {
            CountedLayerInterfaceFactoryPart nextCountedLayerInterfaceFactory = 
                    (CountedLayerInterfaceFactoryPart) partInterfaceArray[index];

            if (nextCountedLayerInterfaceFactory.getTotal() > 0)
            {
                if (currentSlot == slotIndex)
                {
                    return nextCountedLayerInterfaceFactory;
                }
                else
                {
                    currentSlot++;
                }
            }
        }
        return null;
    }
}
