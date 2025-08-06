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
package org.allbinary.game.layer.pickup;

import org.allbinary.game.health.HealthInterfaceCompositeInterface;
import org.allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import org.allbinary.game.part.CountedLayerInterfaceFactoryPart;
import org.allbinary.game.part.PartInterface;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.string.CommonStrings;

public class PickupBehavior implements PickupBehaviorInterface
{
    public static final PickupBehavior NULL_PICKUP_BEHAVIOR = new PickupBehavior(CollidableDestroyableDamageableLayer.NULL_COLLIDABLE_DESTROYABLE_DAMAGE_LAYER, 0);
    
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final int countedIndex;
    
    private final CollidableDestroyableDamageableLayer ownerLayerInterface;

    public PickupBehavior(final CollidableDestroyableDamageableLayer ownerLayerInterface, final int countedIndex)
    {
        this.ownerLayerInterface = ownerLayerInterface;
        
        this.countedIndex = countedIndex;
    }
    
    @Override
    public void doPickup(final PickupProcessorInterface pickupProcessorInterface)
    {
        try
        {
        	//PreLogUtil.put(pickupProcessorInterface.toString(), this, "doPickup(PickupProcessorInterface)");
        	
        	//Don't process non-weapon pickups if dead
            HealthInterfaceCompositeInterface healthInterfaceCompositeInterface = 
                    (HealthInterfaceCompositeInterface) this.ownerLayerInterface;
            
            if(healthInterfaceCompositeInterface.getHealthInterface().isAlive())
            {
            	//PreLogUtil.put(commonStrings.PROCESS, this, "doPickup(PickupProcessorInterface)");
            	
            	pickupProcessorInterface.process(ownerLayerInterface);
            }
        }
        catch(Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, "doPickup", e);
        }
    }
    
    @Override
    public void doPickup(PickedUpLayerInterface pickupLayerInterface)
    {
        try
        {
            final PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface = 
                    pickupLayerInterface.getPickedUpLayerInterfaceFactoryInterface();

            final PickedUpLayerType pickedUpLayerType = 
                    pickedUpLayerInterfaceFactoryInterface.getPickedUpLayerType();

            final PickedUpLayerTypeFactory pickedUpLayerTypeFactory = 
                    PickedUpLayerTypeFactory.getInstance();

            if (pickedUpLayerType == pickedUpLayerTypeFactory.BEAM
                    || pickedUpLayerType == pickedUpLayerTypeFactory.MINE
                    || pickedUpLayerType == pickedUpLayerTypeFactory.PROJECTILE
                    || pickedUpLayerType == pickedUpLayerTypeFactory.ENHANCEMENT)
            {
                this.add((CountedPickedUpLayerInterfaceFactoryInterface) pickedUpLayerInterfaceFactoryInterface);
            }
            else if (pickedUpLayerInterfaceFactoryInterface.getPickedUpLayerType() == pickedUpLayerTypeFactory.PART)
            {
                this.ownerLayerInterface.addPart(pickedUpLayerInterfaceFactoryInterface);
            } else if (pickedUpLayerType == pickedUpLayerTypeFactory.NONE) {
            } else {
                this.doPickup((PickupProcessorInterface) pickedUpLayerInterfaceFactoryInterface);
            }

            pickupLayerInterface.setPickedUp();
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            //logUtil.put(commonStrings.EXCEPTION, this, "doPickup");
            PreLogUtil.put(commonStrings.EXCEPTION, this, "doPickup", e);
        }
    }
    
    protected void add(
            CountedPickedUpLayerInterfaceFactoryInterface countedPickedUpLayerInterfaceFactoryInterface)
    {
        int partIndex = countedPickedUpLayerInterfaceFactoryInterface.getId() + this.countedIndex;
        this.add(countedPickedUpLayerInterfaceFactoryInterface, partIndex);
    }

    protected void add(
            CountedPickedUpLayerInterfaceFactoryInterface countedPickedUpLayerInterfaceFactoryInterface,
            int partIndex)
    {
        CountedLayerInterfaceFactoryPart countedLayerInterfaceFactory = (CountedLayerInterfaceFactoryPart) 
                this.ownerLayerInterface.getPartInterfaceArray()[partIndex];
        countedLayerInterfaceFactory.setTotal(countedLayerInterfaceFactory.getTotal() + countedPickedUpLayerInterfaceFactoryInterface.getTotal());
    }
    
    public CountedLayerInterfaceFactoryPart getFirstAvailableCountedLayerInterfaceFactory(
            PickedUpLayerType[] pickedUpLayerTypeArray)
    {
        final PartInterface[] partInterfaceArray = this.ownerLayerInterface.getPartInterfaceArray();
        final int size = partInterfaceArray.length;
        CountedLayerInterfaceFactoryPart nextCountedLayerInterfaceFactory;
        CountedPickedUpLayerInterfaceFactory countedPickedUpLayerInterfaceFactory;

        for (int index = this.countedIndex; index < size; index++)
        {
            nextCountedLayerInterfaceFactory = (CountedLayerInterfaceFactoryPart) 
                    this.ownerLayerInterface.getPartInterfaceArray()[index];

            countedPickedUpLayerInterfaceFactory = nextCountedLayerInterfaceFactory
                    .getCountedPickedUpLayerInterfaceFactory();

            for (int index2 = pickedUpLayerTypeArray.length; --index2 >= 0;)
            {
                if (countedPickedUpLayerInterfaceFactory.getPickedUpLayerType() == pickedUpLayerTypeArray[index2])
                {
                    // Skip over projectiles if in first place
                    /*
                     * if(countedPickedUpLayerInterfaceFactory.getPickedUpLayerType
                     * () == PickedUpLayerType.PROJECTILE &&
                     * this.inFirstPosition) {
                     * 
                     * } else
                     */
                    if (nextCountedLayerInterfaceFactory.getTotal() > 0)
                    {
                        return nextCountedLayerInterfaceFactory;
                    }
                }
            }
        }
        return CountedLayerInterfaceFactoryPart.NULL_COUNTED_LAYER_INTERFACE_FACTORY;
    }

    public CountedLayerInterfaceFactoryPart getCountedLayerInterfaceFactoryForSlot(
            int slotIndex) throws Exception
    {
        int currentSlot = 0;

        final PartInterface[] partInterfaceArray = this.ownerLayerInterface.getPartInterfaceArray();
        final int size = partInterfaceArray.length;

        CountedLayerInterfaceFactoryPart nextCountedLayerInterfaceFactory;

        for (int index = this.countedIndex; index < size; index++)
        {
            nextCountedLayerInterfaceFactory = (CountedLayerInterfaceFactoryPart) 
                    this.ownerLayerInterface.getPartInterfaceArray()[index];

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
        return CountedLayerInterfaceFactoryPart.NULL_COUNTED_LAYER_INTERFACE_FACTORY;
    }

    public int getCountedIndex()
    {
        return countedIndex;
    }
}
