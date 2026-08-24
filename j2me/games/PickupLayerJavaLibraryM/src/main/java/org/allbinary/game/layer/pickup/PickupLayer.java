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

import jsinterop.annotations.JsType;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;
import org.allbinary.game.collision.CollidableAlwaysPickupNeverCollideBehaviorFactory;
import org.allbinary.game.combat.destroy.DestroyedLayerProcessor;
import org.allbinary.game.identification.BasicGroupFactory;
import org.allbinary.game.multiplayer.layer.MultiPlayerGameLayer;
import org.allbinary.game.multiplayer.layer.RemoteInfo;
import org.allbinary.graphics.Rectangle;
import org.allbinary.image.opengles.OpenGLSurfaceChangedInterface;
import org.allbinary.view.ViewPositionBase;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class PickupLayer 
   extends MultiPlayerGameLayer
   implements PickedUpLayerInterface, PickupableInterface
{
   private PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface = CountedPickedUpLayerInterfaceFactory.NULL_COUNTED_PICKUP_LAYER_FACTORY;
   private boolean destroyed;
   private Animation animationInterface = NullAnimationFactory.getFactoryInstance().getInstance(0);

   @JsConstructor
   public PickupLayer(
           final String name, final RemoteInfo remoteInfo, final int total,
           final PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface,
           final Animation animationInterface, final Rectangle rectangle, final ViewPositionBase viewPosition)
      throws Exception
   {
      super(remoteInfo, BasicGroupFactory.getInstance().NONE_ARRAY, name, rectangle, viewPosition);

      //this.setCollidableInferface(new CollidableAlwaysPickupNeverCollideBehavior(this, true));
      this.setCollidableInferface(CollidableAlwaysPickupNeverCollideBehaviorFactory.getInstance().createBehavior());
      
      this.setLayerWidth(10);
      this.setLayerHeight(10);
      
      this.init(pickedUpLayerInterfaceFactoryInterface, animationInterface);
   }

   @JsMethod
   public void init(
      final PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface,
      final Animation animationInterface)
   {
      this.pickedUpLayerInterfaceFactoryInterface = pickedUpLayerInterfaceFactoryInterface;
      this.animationInterface = animationInterface;
      this.setDestroyed(false);
   }

   @JsMethod
   public void initXYZ(int x, int y, int z)
   {
      this.setPosition(x, y, z);
   }

   @Override
   @JsMethod
   public void paint(Graphics graphics)
   {
       final ViewPositionBase viewPosition = this.getViewPosition();
       final int viewX = viewPosition.getX();
       final int viewY = viewPosition.getY();

       this.animationInterface.paintXY(graphics, viewX, viewY);
   }

   @Override
   @JsMethod
   public void paintThreed(Graphics graphics)
   {
       final ViewPositionBase viewPosition = this.getViewPosition();
       final int viewX = viewPosition.getX();
       final int viewY = viewPosition.getY();

       this.animationInterface.paintThreedXYZ(graphics, viewX, viewY, 3);
   }
   
   @Override
   @JsMethod
   public PickedUpLayerInterfaceFactoryInterface getPickedUpLayerInterfaceFactoryInterface()
   {
      return this.pickedUpLayerInterfaceFactoryInterface;
   }

   @Override
   @JsMethod
   public void setPickedUp()
   {
      this.setDestroyed(true);
   }

   @Override
   @JsMethod
   public boolean isDestroyed()
   {
      return this.destroyed;
   }

   @JsMethod
   public void setDestroyed(boolean destroyed)
   {
      this.destroyed = destroyed;
      if (this.isDestroyed())
      {
         DestroyedLayerProcessor.getInstance().add(this);
      }
   }

   @Override
   @JsMethod
   public void damage(int damage, int damageType)
   {
   }

   @Override
   @JsMethod
   public int getDamage(int damageType)
   {
      return 0;
   }
   
   @Override
   @JsMethod
   public void set(GL gl) throws Exception
   {
       //OpenGLSurfaceChangedInterface
       OpenGLSurfaceChangedInterface openGLSurfaceChangedInterface = 
       (OpenGLSurfaceChangedInterface) this.animationInterface;        

       openGLSurfaceChangedInterface.set(gl);
   }   
}
