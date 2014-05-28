package allbinary.game.layer.special;

import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.pickup.PickedUpLayerInterface;

import abcs.logic.basic.NotImplemented;
import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.game.collision.CollidableInterfaceCompositeInterface;
import allbinary.game.collision.CollisionType;
import allbinary.game.collision.CollisionTypeFactory;

public class CollidableCharacterNoDamageBehavior
extends CollidableDestroyableDamageableBehavior 
{
    public CollidableCharacterNoDamageBehavior(CollidableCompositeLayer ownerLayer, boolean collidable)
    {
        super(ownerLayer, collidable);
    }

    public void collide(CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("collidableLayer", this, "collide"));        
        CollisionTypeFactory collisionTypeFactory = CollisionTypeFactory.getInstance();
        CollisionType collisionType = collidableInterfaceCompositeInterface.getCollidableInferface().getCollisionTypeWith(this.ownerLayer);

        if (collisionType == collisionTypeFactory.PICKUP)
        {
            ((CollidableDestroyableDamageableLayer) this.ownerLayer).getPickupBehavior().doPickup(
                    (PickedUpLayerInterface) collidableInterfaceCompositeInterface);
        }
        else
        {
            //TWB - Characters currently are unable to collide
            //super.collide(collidableInterfaceCompositeInterface);
        }        
    }

    public void collide(CollidableInterfaceCompositeInterface collidableInterfaceCompositeInterface)
        throws Exception
    {
        //LogUtil.put(LogFactory.getInstance("collideInterface", this, "collide"));
        ForcedLogUtil.log(NotImplemented.NAME, this);
    }
}
