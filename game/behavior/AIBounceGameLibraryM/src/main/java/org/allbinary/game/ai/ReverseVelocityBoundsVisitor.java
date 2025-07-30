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
package org.allbinary.game.ai;

import org.allbinary.bounds.BoundsVisitorInterface;
import org.allbinary.game.physics.velocity.VelocityInterface;
import org.allbinary.game.physics.velocity.VelocityInterfaceCompositeInterface;
import org.allbinary.layer.LayerInterface;
import org.allbinary.logic.communication.log.LogUtil;

public class ReverseVelocityBoundsVisitor implements BoundsVisitorInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();
    
   //private GPoint point;
   protected VelocityInterface velocityInterface;
   //private RotationAnimationInterface rotationAnimationInterface;

   private LayerInterface layerInterface;
   //GPoint point,
   public ReverseVelocityBoundsVisitor(LayerInterface layerInterface)
   {
       this.layerInterface = layerInterface;
      //this.point = point;
      
      //RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) layerInterface;
      //this.rotationAnimationInterface = rotationAnimationInterfaceCompositeInterface.getRotationAnimationInterface();
      
      VelocityInterfaceCompositeInterface velocityInterfaceCompositeInterface = 
              (VelocityInterfaceCompositeInterface) layerInterface;
      
      this.velocityInterface = (VelocityInterface)
          velocityInterfaceCompositeInterface.getVelocityProperties();
   }

   public int getX()
   {
       return this.layerInterface.getXP();
   }

   public int getY()
   {
       return this.layerInterface.getYP();
   }

   public void minX()
   {
      //logUtil.put(commonStrings.START, this, "minX");
      //this.refract();
      if(this.velocityInterface.getVelocityXBasicDecimal().getUnscaled() < 0)
      this.velocityInterface.getVelocityXBasicDecimal().multiply(-1);
      //logUtil.put("End: " + this.velocityInterface.toString(), this, "minX");
   }

   public void maxX()
   {
      //logUtil.put(commonStrings.START, this, "maxX");
      //this.refract();
      if(this.velocityInterface.getVelocityXBasicDecimal().getUnscaled() > 0)
      this.velocityInterface.getVelocityXBasicDecimal().multiply(-1);
      //logUtil.put("End: " + this.velocityInterface.toString(), this, "maxX");
   }

   public void minY()
   {
      //logUtil.put(commonStrings.START, this, "minY");
      //this.refract();
      if(this.velocityInterface.getVelocityYBasicDecimal().getUnscaled() < 0)
      this.velocityInterface.getVelocityYBasicDecimal().multiply(-1);
      
      //logUtil.put("End: " + this.velocityInterface.toString(), this, "minY");
   }

   public void maxY()
   {
      //logUtil.put(commonStrings.START, this, "maxY");
      //this.refract();
      if(this.velocityInterface.getVelocityYBasicDecimal().getUnscaled() > 0)
      this.velocityInterface.getVelocityYBasicDecimal().multiply(-1);
      //logUtil.put("End: " + this.velocityInterface.toString(), this, "maxY");
   }

   /*
   private void refract()
   {
      short angle = this.rotationAnimationInterface.getAngleInfoP().getAngle();
      angle += 180;
      this.rotationAnimationInterface.adjustFrame(angle);
   } 
   */  
}
