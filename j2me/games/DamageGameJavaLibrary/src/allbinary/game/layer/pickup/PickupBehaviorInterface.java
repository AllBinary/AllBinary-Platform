package allbinary.game.layer.pickup;

import org.allbinary.game.layer.pickup.PickedUpLayerInterface;
import org.allbinary.game.layer.pickup.PickupProcessorInterface;

public interface PickupBehaviorInterface
{
    void doPickup(PickupProcessorInterface pickupProcessorInterface) throws Exception;
    void doPickup(PickedUpLayerInterface pickupLayerInterface);
}
