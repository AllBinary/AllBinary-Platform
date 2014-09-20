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
import org.allbinary.math.AngleFactory;

public class ArcedMultiBeamWeaponPart extends BasicWeaponPart {

   private WeaponLayerCircularPool weaponLayerCircularStaticPool;
   private int total;

   public ArcedMultiBeamWeaponPart(Animation animationInterface,
      WeaponLayerCircularPool weaponLayerCircularStaticPool) {
      super(animationInterface);
      this.total = 2;
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }

   public ArcedMultiBeamWeaponPart(
      Animation animationInterface,
      AllBinaryLayer sourceLayerInterface, 
           WeaponLayerCircularPool weaponLayerCircularStaticPool,
           int total, WeaponProperties weaponProperties, ScoreableInterface scoreableInterface, RelativeRelationship relativeRelationship) {
      super(animationInterface, sourceLayerInterface, weaponProperties, scoreableInterface, relativeRelationship);

      this.total = total;
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }

   private final int TOTAL_ANGLE = AngleFactory.getInstance().TOTAL_ANGLE;
   
   public void process(AllBinaryLayerManager allbinaryLayerManager,
           short angle, short otherAngle, 
           WeaponProperties weaponProperties, ScoreableInterface scoreableInterface)
           throws Exception {
      //WeaponLayer[] weaponLayerArray = WeaponLayerArrayLayerCircularStaticPool.getInstance(total);

      short increment = (short) (
              (this.total - 1) / TOTAL_ANGLE
              );
      short minAngle = (short) (
              angle - ((increment * total) >> 1)
              );
      
      int next = 0;
      for (int index = 0; index < total; index++) {

         WeaponLayer weaponLayer =
                 weaponLayerCircularStaticPool.getInstance(
                 this.getOwnerLayerInterface(), 
                 this.relativeRelationship.getX(), 
                 this.relativeRelationship.getY(),
                 0,
                 (short) (minAngle + increment),
                 otherAngle, 
                 weaponProperties, scoreableInterface);

         allbinaryLayerManager.append(weaponLayer);

         next += increment;
      }
   }
}
