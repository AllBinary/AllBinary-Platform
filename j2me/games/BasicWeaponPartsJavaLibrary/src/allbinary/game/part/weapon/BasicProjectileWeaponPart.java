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
package allbinary.game.part.weapon;

import allbinary.animation.Animation;
import allbinary.game.combat.weapon.WeaponProperties;
import allbinary.game.layer.weapon.WeaponLayer;
import allbinary.game.layer.weapon.WeaponLayerCircularPool;
import allbinary.game.score.ScoreableInterface;
import allbinary.graphics.RelativeRelationship;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;

public class BasicProjectileWeaponPart extends BasicWeaponPart  
{
   private WeaponLayerCircularPool weaponLayerCircularStaticPool;

   public BasicProjectileWeaponPart(
      Animation animationInterface,
      WeaponLayerCircularPool weaponLayerCircularStaticPool) {
      
      super(animationInterface);
      
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }
   
   public BasicProjectileWeaponPart(
           Animation animationInterface,
           AllBinaryLayer sourceLayerInterface, 
           WeaponLayerCircularPool weaponLayerCircularStaticPool,
           WeaponProperties weaponProperties,  
           ScoreableInterface scoreableInterface, 
           RelativeRelationship relativeRelationship) 
   {
      super(animationInterface, 
              sourceLayerInterface, 
              weaponProperties, 
              scoreableInterface, 
              relativeRelationship
              );

      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;      
   }

   public void process(AllBinaryLayerManager allbinaryLayerManager,
           short angle, WeaponProperties weaponProperties, 
           ScoreableInterface scoreableInterface)
           throws Exception 
   {
      WeaponLayer weaponLayer =
              weaponLayerCircularStaticPool.getInstance(
              this.getOwnerLayerInterface(), 
              this.relativeRelationship.getX(), 
              this.relativeRelationship.getY(), 
              angle, weaponProperties, scoreableInterface);

      allbinaryLayerManager.append(weaponLayer);
   }
}
