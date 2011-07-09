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
package allbinary.game.layer.pickup;

import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.pickup.PickedUpLayerInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickupableInterface;

import allbinary.animation.Animation;
import allbinary.game.collision.CollidableAlwaysPickupNeverCollideBehaviorFactory;
import allbinary.game.combat.destroy.DestroyedLayerProcessor;
import allbinary.game.identification.BasicGroupFactory;
import allbinary.game.layer.special.CollidableDestroyableDamageableLayer;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.view.ViewPosition;

public class PickupLayer 
   extends CollidableDestroyableDamageableLayer
   implements PickedUpLayerInterface, PickupableInterface
{
   private PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface;
   private boolean destroyed;
   private Animation animationInterface;

   public PickupLayer(ViewPosition viewPosition) throws Exception
   {
      super(BasicGroupFactory.getInstance().NONE, new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), viewPosition);
      
      //this.setCollidableInferface(new CollidableAlwaysPickupNeverCollideBehavior(this, true));
      this.setCollidableInferface(CollidableAlwaysPickupNeverCollideBehaviorFactory.getInstance());
      
      this.setWidthImpl(10);
      this.setHeightImpl(10);
   }

   public PickupLayer(
      int total,
      PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface,
      Animation animationInterface, Rectangle rectangle, ViewPosition viewPosition)
      throws Exception
   {
      super(BasicGroupFactory.getInstance().NONE, rectangle, viewPosition);

      //this.setCollidableInferface(new CollidableAlwaysPickupNeverCollideBehavior(this, true));
      this.setCollidableInferface(CollidableAlwaysPickupNeverCollideBehaviorFactory.getInstance());
      
      this.setWidthImpl(10);
      this.setHeightImpl(10);
      
      this.init(pickedUpLayerInterfaceFactoryInterface, animationInterface);
   }

   public void init(
      PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface,
      Animation animationInterface)
   {
      this.pickedUpLayerInterfaceFactoryInterface = pickedUpLayerInterfaceFactoryInterface;
      this.animationInterface = animationInterface;
      this.setDestroyed(false);
   }

   public void init(int x, int y)
   {
      this.setPosition(x, y);
   }

   public void paint(Graphics graphics)
   {
       ViewPosition viewPosition = this.getViewPosition();
       int viewX = viewPosition.getX();
       int viewY = viewPosition.getY();

       this.animationInterface.paint(graphics, viewX, viewY);
   }

   public void paintThreed(Graphics graphics)
   {
       ViewPosition viewPosition = this.getViewPosition();
       int viewX = viewPosition.getX();
       int viewY = viewPosition.getY();

       this.animationInterface.paintThreed(graphics, viewX, viewY, 3);
   }
   
   public PickedUpLayerInterfaceFactoryInterface getPickedUpLayerInterfaceFactoryInterface()
   {
      return this.pickedUpLayerInterfaceFactoryInterface;
   }

   public void setPickedUp()
   {
      this.setDestroyed(true);
   }

   public boolean isDestroyed()
   {
      return destroyed;
   }

   public void setDestroyed(boolean destroyed)
   {
      this.destroyed = destroyed;
      if (this.isDestroyed())
      {
         DestroyedLayerProcessor.getInstance().add(this);
      }
   }

   public void damage(int damage, int damageType)
   {
   }

   public int getDamage(int damageType)
   {
      return 0;
   }
}
