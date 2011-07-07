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
package allbinary.game.physics.velocity;


public class VelocityProperties extends BasicVelocityProperties implements VelocityInterface
{
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
      this.getVelocityXBasicDecimal().set(this.getMaxForwardVelocity() * multiply);
   }

   public void setMaxYVelocity(int multiply)
   {
      this.getVelocityYBasicDecimal().set(this.getMaxForwardVelocity() * multiply);
   }
*/
   public int getMaxForwardVelocity()
   {
      return maxForwardVelocity;
   }

   public void setMaxForwardVelocity(int maxForwardVelocity)
   {
      this.maxForwardVelocity = maxForwardVelocity;
   }

   public int getMaxReverseVelocity()
   {
      return maxReverseVelocity;
   }

   public void setMaxReverseVelocity(int maxReverseVelocity)
   {
      this.maxReverseVelocity = maxReverseVelocity;
   }

   public void limitMaxXYForwardVelocity()
   {
      this.limitMaxXYVelocity(this.getMaxForwardVelocity());
   }

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
   
   public void limitXYToForwardAndReverseMaxVelocity()
   {
      //LogUtil.put(LogFactory.getInstance("Limit Velocity", this, "limitMaxVelocity"));
      this.limitMaxXYForwardVelocity();
      this.limitMaxXYReverseVelocity();
   }

   //Not very realistic but it keeps the game sane since the physics is bull
   public void limitMaxXYVelocity(int maxVelocity)
   {
       this.limitMaxXVelocity(maxVelocity);
       this.limitMaxYVelocity(maxVelocity);
   }

   public void limitMaxPositiveYVelocity(int maxVelocity)
   {
       if (this.getVelocityYBasicDecimal().getUnscaled() > maxVelocity)
       {
          this.getVelocityYBasicDecimal().set(maxVelocity);
       }
   }

   public void limitMaxNegativeYVelocity(int maxVelocity)
   {
       if (this.getVelocityYBasicDecimal().getUnscaled() < -maxVelocity)
       {
          this.getVelocityYBasicDecimal().set(-maxVelocity);
       }
   }
   
   public void limitMaxYVelocity(int maxVelocity)
   {
       this.limitMaxPositiveYVelocity(maxVelocity);
       this.limitMaxNegativeYVelocity(maxVelocity);
   }
   
   public void limitMaxXVelocity(int maxVelocity)
   {
      if (this.getVelocityXBasicDecimal().getUnscaled() > maxVelocity)
      {
         this.getVelocityXBasicDecimal().set(maxVelocity);
      }

      if (this.getVelocityXBasicDecimal().getUnscaled() < -maxVelocity)
      {
         this.getVelocityXBasicDecimal().set(-maxVelocity);
      }
   }
   
   public boolean isOverXYMaxVelocity(int maxVelocity)
   {
      if (this.getVelocityXBasicDecimal().getUnscaled() > maxVelocity)
      {
         return true;
      }

      if (this.getVelocityXBasicDecimal().getUnscaled() < -maxVelocity)
      {
         return true;
      }

      if (this.getVelocityYBasicDecimal().getUnscaled() > maxVelocity)
      {
         return true;
      }

      if (this.getVelocityYBasicDecimal().getUnscaled() < -maxVelocity)
      {
         return true;
      }
      return false;
   }
   
   public void setVelocity(long magnitude, short angle)
   {  
      super.setVelocity(magnitude, angle);
      this.limitXYToForwardAndReverseMaxVelocity();
   }

   public void addVelocity(long magnitude, short angle)
   {  
      super.addVelocity(magnitude, angle);
      this.limitXYToForwardAndReverseMaxVelocity();
   }
}