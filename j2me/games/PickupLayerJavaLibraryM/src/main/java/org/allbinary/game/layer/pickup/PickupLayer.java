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

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.game.layer.pickup.PickedUpLayerInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickupableInterface;
import org.allbinary.game.multiplayer.layer.MultiPlayerGameLayer;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;
import org.allbinary.animation.Animation;
import org.allbinary.game.collision.CollidableAlwaysPickupNeverCollideBehaviorFactory;
import org.allbinary.game.combat.destroy.DestroyedLayerProcessor;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.view.ViewPosition;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class PickupLayer 
   extends MultiPlayerGameLayer
   implements PickedUpLayerInterface, PickupableInterface
{
   private PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface;
   private boolean destroyed;
   private Animation animationInterface;

   public PickupLayer(final String name, final RemoteInfo remoteInfo, final ViewPosition viewPosition) throws Exception
   {
      super(remoteInfo, BasicGroupFactory.getInstance().NONE_ARRAY, name, new Rectangle(PointFactory.getInstance().ZERO_ZERO, 0, 0), viewPosition);
      
      //this.setCollidableInferface(new CollidableAlwaysPickupNeverCollideBehavior(this, true));
      this.setCollidableInferface(CollidableAlwaysPickupNeverCollideBehaviorFactory.getInstance());
      
      this.setLayerWidth(10);
      this.setLayerHeight(10);
   }

   public PickupLayer(
           final String name, final RemoteInfo remoteInfo, final int total,
           final PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface,
           final Animation animationInterface, final Rectangle rectangle, final ViewPosition viewPosition)
      throws Exception
   {
      super(remoteInfo, BasicGroupFactory.getInstance().NONE_ARRAY, name, rectangle, viewPosition);

      //this.setCollidableInferface(new CollidableAlwaysPickupNeverCollideBehavior(this, true));
      this.setCollidableInferface(CollidableAlwaysPickupNeverCollideBehaviorFactory.getInstance());
      
      this.setLayerWidth(10);
      this.setLayerHeight(10);
      
      this.init(pickedUpLayerInterfaceFactoryInterface, animationInterface);
   }

   public PickupLayer(final String name, final ViewPosition viewPosition) throws Exception
   {
      this(name, RemoteInfo.REMOTE_INFO, viewPosition);
   }

   public PickupLayer(final String name, final int total,
           final PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface,
           final Animation animationInterface, final Rectangle rectangle, final ViewPosition viewPosition) throws Exception
   {
      this(name, RemoteInfo.REMOTE_INFO,
              total, pickedUpLayerInterfaceFactoryInterface, 
              animationInterface, rectangle, viewPosition);
   }
   
   public void init(
      final PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface,
      final Animation animationInterface)
   {
      this.pickedUpLayerInterfaceFactoryInterface = pickedUpLayerInterfaceFactoryInterface;
      this.animationInterface = animationInterface;
      this.setDestroyed(false);
   }

   public void init(int x, int y, int z)
   {
      this.setPosition(x, y, z);
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
   
   public void set(GL gl) throws Exception
   {
       //OpenGLSurfaceChangedInterface
       OpenGLSurfaceChangedInterface openGLSurfaceChangedInterface = 
       (OpenGLSurfaceChangedInterface) this.animationInterface;        

       openGLSurfaceChangedInterface.set(gl);
   }   
}
