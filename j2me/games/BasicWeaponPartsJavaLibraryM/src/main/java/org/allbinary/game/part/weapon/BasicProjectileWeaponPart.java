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
      final Animation animationInterface,
      final WeaponLayerCircularPool weaponLayerCircularStaticPool) {
      
      super(animationInterface);
      
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }
   
   public BasicProjectileWeaponPart(
           final Animation animationInterface,
           final AllBinaryLayer sourceLayerInterface, 
           final WeaponLayerCircularPool weaponLayerCircularStaticPool,
           final WeaponProperties weaponProperties,  
           final ScoreableInterface scoreableInterface,
           final RelativeRelationship relativeRelationship) 
   {
      super(animationInterface, 
              sourceLayerInterface, 
              weaponProperties, 
              scoreableInterface,
              relativeRelationship
              );

      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;      
   }

   public void process(final AllBinaryLayerManager allbinaryLayerManager,
           final short angle, short otherAngle, 
           final WeaponProperties weaponProperties, 
           final ScoreableInterface scoreableInterface)
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
