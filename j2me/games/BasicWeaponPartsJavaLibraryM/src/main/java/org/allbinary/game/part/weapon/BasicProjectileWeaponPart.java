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
import org.allbinary.game.score.NoScoreable;
import org.allbinary.game.score.ScoreableInterface;
import org.allbinary.graphics.RelativeRelationship;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class BasicProjectileWeaponPart extends BasicWeaponPart  
{
   public static BasicProjectileWeaponPart createPart(final Animation animationInterface, final WeaponLayerCircularPool weaponLayerCircularStaticPool) {
      return new BasicProjectileWeaponPart(animationInterface, AllBinaryLayer.NULL_ALLBINARY_LAYER, weaponLayerCircularStaticPool, WeaponProperties.NULL_WEAPON_PROPERTIES, NoScoreable.getInstance(), RelativeRelationship.NULL_RELATIVE_RELATIONSHIP);
   }

   private WeaponLayerCircularPool weaponLayerCircularStaticPool;

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

   @Override
   public void processScore(final AllBinaryLayerManager allbinaryLayerManager,
                            final short angle, short otherAngle,
                            final WeaponProperties weaponProperties,
                            final ScoreableInterface scoreableInterface)
           throws Exception 
   {
      final WeaponLayer weaponLayer =
              this.weaponLayerCircularStaticPool.getInstance(
              this.getOwnerLayerInterface(), 
              this.relativeRelationship.getX(), 
              this.relativeRelationship.getY(),
              this.relativeRelationship.getZ(),
              (int) angle, 
              (int) otherAngle,  
              weaponProperties, scoreableInterface);

      allbinaryLayerManager.append(weaponLayer);
   }
}
