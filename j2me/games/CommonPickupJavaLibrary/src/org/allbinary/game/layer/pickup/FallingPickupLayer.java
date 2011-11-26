package org.allbinary.game.layer.pickup;

import allbinary.game.layer.pickup.PickupLayer;
import allbinary.game.tick.TickableInterface;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.view.ViewPosition;

public class FallingPickupLayer extends PickupLayer implements
        TickableInterface
{
    public FallingPickupLayer(String username, int actorSessionId)
            throws Exception
    {
        super(username, actorSessionId, new ViewPosition());
    }

    public FallingPickupLayer() throws Exception
    {
        super(new ViewPosition());
    }

    /*
     * public FallingPickupLayer( int total,
     * PickedUpLayerInterfaceFactoryInterface
     * pickedUpLayerInterfaceFactoryInterface, int x, int y) throws Exception {
     * super(total, pickedUpLayerInterfaceFactoryInterface, x, y); }
     */

    /*
     * public AnimationPickupLayer(int total,
     * PickedUpLayerInterfaceFactoryInterface
     * pickedUpLayerInterfaceFactoryInterface, int x, int y) throws Exception {
     * super(total, pickedUpLayerInterfaceFactoryInterface,
     * pickedUpLayerInterfaceFactoryInterface.getAnimationInterface(), new
     * Rectangle(PointFactory.getInstance(x, y), 10, 10), new ViewPosition()); }
     */

    public void processTick(AllBinaryLayerManager allBinaryLayerManager)
    {
        this.setPosition(this.x, this.y + 1);
    }

    public boolean implmentsTickableInterface()
    {
        return true;
    }
}
