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
package org.allbinary.game.layer.weapon.mine;

import org.allbinary.physics.movement.NoMovementFactory;

import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.game.layer.weapon.SimpleWeaponLayer;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.view.ViewPosition;
import org.allbinary.view.event.ViewPositionEventHandler;
import org.allbinary.weapon.media.audio.DropWeaponSound;

public class MineLayer extends SimpleWeaponLayer
{   
    private static final String NAME = "MineLayer";
    
   //private static final BasicDecimal SPEED = new BasicDecimal(0);

   public MineLayer(ViewPosition viewPosition)
           throws Exception
   {
      super(NAME,
    		  NoMovementFactory.getInstance().getMovmentInstance(),
              //new BasicConstantVelocityMovement(SPEED),
              FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(
                      MineWeaponResources.getInstance().RESOURCE).getInstance(0),
         //new VectorRotationAnimation(MineVectorData.points, BasicColor.GREY),
         new Rectangle(PointFactory.getInstance().ZERO_ZERO, 10, 10),
         viewPosition);
   }
   
   /*
   public MineLayer(LayerInterface sourceLayerInterface, int x, int y, ViewPosition viewPosition, short angle, int damage, ScoreableInterface scoreable)
           throws Exception
   {
      super(sourceLayerInterface,
              NoMovementFactory,
         //new BasicConstantVelocityMovement(SPEED),
         new VectorRotationAnimation(MineVectorData.points, BasicColor.GREY),
         new Rectangle(PointFactory.getInstance(x + HALF_SIZEX, y + HALF_SIZEY), SIZEX, SIZEY),  
         angle, damage, scoreable);
      
      DropWeaponSound.getInstance().getPlayer().start();
   }
   */
   
   private final ViewPositionEventHandler viewPositionEventHandler = 
       ViewPositionEventHandler.getInstance();
   
   public void init(int x, int y, int z)
   {
      this.setPosition(x + 5, y + 5, z);

      this.viewPositionEventHandler.addListener((AllBinaryLayer) this);

      SecondaryPlayerQueueFactory.getInstance().add(
              DropWeaponSound.getInstance()
              );
   }
      
   public void damage(int damage, int damageType)
   {
      this.totalDamage = this.getInitDamage() + 1;
   }
   
   public int getDamage(int damageType)
   {
       super.getDamage(damageType);
       return this.getInitDamage();
   }
   
   public void setDestroyed(boolean destroyed)
   {
       super.setDestroyed(destroyed);
       
       if(this.isDestroyed())
       {
           this.viewPositionEventHandler.removeListener(this);
       }
   }
}