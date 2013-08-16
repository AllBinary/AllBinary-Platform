package allbinary.game.layer.pickup;

import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactory;
import org.allbinary.game.layer.pickup.CountedPickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerType;
import org.allbinary.game.layer.pickup.PickedUpLayerTypeFactory;
import org.allbinary.game.layer.pickup.PickupProcessorInterface;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.communication.log.PreLogUtil;
import allbinary.game.health.HealthInterfaceCompositeInterface;
import allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import allbinary.game.part.CountedLayerInterfaceFactoryPart;

public class PickupBehavior implements PickupBehaviorInterface
{
    private final int countedIndex;
    
    private final CollidableDestroyableDamageableLayer ownerLayerInterface;

    public PickupBehavior(CollidableDestroyableDamageableLayer ownerLayerInterface, int countedIndex)
    {
        this.ownerLayerInterface = ownerLayerInterface;
        
        this.countedIndex = countedIndex;
    }
    
    public void doPickup(PickupProcessorInterface pickupProcessorInterface)
    {
        try
        {
        	//PreLogUtil.put(pickupProcessorInterface.toString(), this, "doPickup(PickupProcessorInterface)");
        	
        	//Don't process non-weapon pickups if dead
            HealthInterfaceCompositeInterface healthInterfaceCompositeInterface = 
                    (HealthInterfaceCompositeInterface) this.ownerLayerInterface;
            
            if(healthInterfaceCompositeInterface.getHealthInterface().isAlive())
            {
            	//PreLogUtil.put("process", this, "doPickup(PickupProcessorInterface)");
            	
            	pickupProcessorInterface.process(ownerLayerInterface);
            }
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "doPickup", e));
        }
    }
    
    public void doPickup(PickedUpLayerInterface pickupLayerInterface)
    {
        try
        {
            PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface = 
                    pickupLayerInterface.getPickedUpLayerInterfaceFactoryInterface();

            PickedUpLayerType pickedUpLayerType = 
                    pickedUpLayerInterfaceFactoryInterface.getPickedUpLayerType();

            PickedUpLayerTypeFactory pickedUpLayerTypeFactory = 
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
            }
            else
            {
                this.doPickup((PickupProcessorInterface) pickedUpLayerInterfaceFactoryInterface);
            }

            pickupLayerInterface.setPickedUp();
        }
        catch (Exception e)
        {
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "doPickup"));
        	PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, "doPickup", e);
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
        int size = this.ownerLayerInterface.getPartInterfaceArray().length;
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
        return null;
    }

    public CountedLayerInterfaceFactoryPart getCountedLayerInterfaceFactoryForSlot(
            int slotIndex) throws Exception
    {
        int currentSlot = 0;
        int size = this.ownerLayerInterface.getPartInterfaceArray().length;

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
        return null;
    }

    public int getCountedIndex()
    {
        return countedIndex;
    }
}
