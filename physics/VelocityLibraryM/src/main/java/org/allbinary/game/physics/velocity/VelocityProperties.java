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
package org.allbinary.game.physics.velocity;

public class VelocityProperties 
extends BasicVelocityProperties 
implements VelocityInterface
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private int maxForwardVelocity;
   private int maxReverseVelocity;

   public VelocityProperties(int maxForwardVelocity, int maxReverseVelocity)
   {
      this.setMaxForwardVelocity(maxForwardVelocity);
      this.setMaxReverseVelocity(maxReverseVelocity);
   }

   /*   
   public void setMaxVelocity(Direction direction)
   {
      int magnitude = this.getMaxForwardVelocity();
      super.setVelocity(magnitude, direction);
   }

   public void setMaxVelocity(Angle angle)
   {
      int magnitude = this.getMaxForwardVelocity();
      super.setVelocity(magnitude, angle);
   }
 
   public void setMaxXVelocity(int multiply)
   {
      this.getVelocityXBasicDecimalP().set(this.getMaxForwardVelocity() * multiply);
   }

   public void setMaxYVelocity(int multiply)
   {
      this.getVelocityYBasicDecimalP().set(this.getMaxForwardVelocity() * multiply);
   }
*/
   
   @Override
   public int getMaxForwardVelocity()
   {
      return maxForwardVelocity;
   }

   @Override
   public void setMaxForwardVelocity(int maxForwardVelocity)
   {
      this.maxForwardVelocity = maxForwardVelocity;
   }

   @Override
   public int getMaxReverseVelocity()
   {
      return maxReverseVelocity;
   }

   @Override
   public void setMaxReverseVelocity(int maxReverseVelocity)
   {
      this.maxReverseVelocity = maxReverseVelocity;
   }

   @Override
   public void limitMaxXYForwardVelocity()
   {
      this.limitMaxXYVelocity(this.getMaxForwardVelocity());
   }

   @Override
   public void limitMaxXYReverseVelocity()
   {
      this.limitMaxXYVelocity(this.getMaxReverseVelocity());
   }

   public void limitMaxYForwardVelocity()
   {
      this.limitMaxYVelocity(this.getMaxForwardVelocity());
   }

   public void limitMaxYReverseVelocity()
   {
      this.limitMaxYVelocity(this.getMaxReverseVelocity());
   }

   public void limitMaxXForwardVelocity()
   {
      this.limitMaxXVelocity(this.getMaxForwardVelocity());
   }

   public void limitMaxXReverseVelocity()
   {
      this.limitMaxXVelocity(this.getMaxReverseVelocity());
   }
   
   public boolean isOverXYMaxForwardVelocity()
   {
      return this.isOverXYMaxVelocity(this.getMaxForwardVelocity());
   }

   public boolean isOverXYMaxReverseVelocity()
   {
      return this.isOverXYMaxVelocity(this.getMaxReverseVelocity());
   }   
 
   @Override
   public void limitXYToForwardAndReverseMaxVelocity()
   {
      //logUtil.put("Limit Velocity", this, "limitMaxVelocity");
      this.limitMaxXYForwardVelocity();
      this.limitMaxXYReverseVelocity();
   }

   //Not very realistic but it keeps the game sane since the physics is bull
   @Override
   public void limitMaxXYVelocity(int maxVelocity)
   {
       this.limitMaxXVelocity(maxVelocity);
       this.limitMaxYVelocity(maxVelocity);
   }

   public void limitMaxPositiveYVelocity(int maxVelocity)
   {
       if (this.velocityYBasicDecimal.getUnscaled() > maxVelocity)
       {
          this.velocityYBasicDecimal.set(maxVelocity);
       }
   }

   public void limitMaxNegativeYVelocity(int maxVelocity)
   {
       if (this.velocityYBasicDecimal.getUnscaled() < -maxVelocity)
       {
          this.velocityYBasicDecimal.set(-maxVelocity);
       }
   }
   
   public void limitMaxYVelocity(int maxVelocity)
   {
       this.limitMaxPositiveYVelocity(maxVelocity);
       this.limitMaxNegativeYVelocity(maxVelocity);
   }
   
   public void limitMaxXVelocity(int maxVelocity)
   {
      if (this.velocityXBasicDecimal.getUnscaled() > maxVelocity)
      {
         this.velocityXBasicDecimal.set(maxVelocity);
      }

      if (this.velocityXBasicDecimal.getUnscaled() < -maxVelocity)
      {
         this.velocityXBasicDecimal.set(-maxVelocity);
      }
   }
   
   public boolean isOverXYMaxVelocity(int maxVelocity)
   {
      if (this.velocityXBasicDecimal.getUnscaled() > maxVelocity)
      {
         return true;
      }

      if (this.velocityXBasicDecimal.getUnscaled() < -maxVelocity)
      {
         return true;
      }

      if (this.velocityYBasicDecimal.getUnscaled() > maxVelocity)
      {
         return true;
      }

      if (this.velocityYBasicDecimal.getUnscaled() < -maxVelocity)
      {
         return true;
      }
      return false;
   }

   @Override
   public void setVelocity(long magnitude, int angle, int otherAngle)
   {  
      super.setVelocity(magnitude, angle, otherAngle);
      this.limitXYToForwardAndReverseMaxVelocity();
   }

   @Override
   public void addVelocity(long magnitude, int angle, int otherAngle)
   {  
      super.addVelocity(magnitude, angle, otherAngle);
      this.limitXYToForwardAndReverseMaxVelocity();
   }
}