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
package org.allbinary.game.layer;

/**
 *
 * @author user
 */
public class Aggression {

   private Integer ram;
   private Integer projectile;
   private Integer drops;
   
   public Aggression(Integer ram, Integer projectile, Integer drops)
   {
      this.setRam(ram);
      this.setProjectile(projectile);
      this.setDrops(drops);
   }
   
   public Integer getRam()
   {
      return ram;
   }

   public void setRam(Integer ram)
   {
      this.ram = ram;
   }

   public Integer getProjectile()
   {
      return projectile;
   }

   public void setProjectile(Integer projectile)
   {
      this.projectile = projectile;
   }

   public Integer getDrops()
   {
      return drops;
   }

   public void setDrops(Integer drops)
   {
      this.drops = drops;
   }

}
