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
package org.allbinary.game.part.weapon;

import org.allbinary.animation.Animation;
import org.allbinary.game.combat.weapon.WeaponProperties;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.weapon.WeaponLayer;
import org.allbinary.game.layer.weapon.WeaponLayerCircularPool;
import org.allbinary.game.score.ScoreableInterface;
import org.allbinary.graphics.RelativeRelationship;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

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
           RelativeRelationship relativeRelationship) 
   {
      super(animationInterface, 
              sourceLayerInterface, 
              weaponProperties, 
              relativeRelationship
              );

      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;      
   }

   public void process(AllBinaryLayerManager allbinaryLayerManager,
           short angle, short otherAngle, 
           WeaponProperties weaponProperties, 
           ScoreableInterface scoreableInterface)
           throws Exception 
   {
      final WeaponLayer weaponLayer =
              weaponLayerCircularStaticPool.getInstance(
              this.getOwnerLayerInterface(), 
              this.relativeRelationship.getX(), 
              this.relativeRelationship.getY(),
              this.relativeRelationship.getZ(),
              angle, otherAngle,  
              weaponProperties, scoreableInterface);

      allbinaryLayerManager.append(weaponLayer);
   }
}
