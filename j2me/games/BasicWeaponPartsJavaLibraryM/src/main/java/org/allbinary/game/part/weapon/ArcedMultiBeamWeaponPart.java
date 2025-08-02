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

   private final WeaponLayerCircularPool weaponLayerCircularStaticPool;
   private final int total;

   public ArcedMultiBeamWeaponPart(final Animation animationInterface,
      final WeaponLayerCircularPool weaponLayerCircularStaticPool) {
      super(animationInterface);
      this.total = 2;
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }

   public ArcedMultiBeamWeaponPart(
      final Animation animationInterface,
      final AllBinaryLayer sourceLayerInterface, 
           final WeaponLayerCircularPool weaponLayerCircularStaticPool,
           final int total, final WeaponProperties weaponProperties, final ScoreableInterface scoreableInterface, final RelativeRelationship relativeRelationship) {
      super(animationInterface, sourceLayerInterface, weaponProperties, scoreableInterface, relativeRelationship);

      this.total = total;
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }

   private final int TOTAL_ANGLE = (int) AngleFactory.getInstance().TOTAL_ANGLE;
   
   @Override
   public void process(final AllBinaryLayerManager allbinaryLayerManager,
           final short angle, final short otherAngle, 
           final WeaponProperties weaponProperties, final ScoreableInterface scoreableInterface)
           throws Exception {
      //WeaponLayer[] weaponLayerArray = WeaponLayerArrayLayerCircularStaticPool.getInstance(total);

      final short increment = (short) (
              (this.total - 1) / TOTAL_ANGLE
              );
      final short minAngle = (short) (
              angle - ((increment * total) >> 1)
              );
      
      int next = 0;
      WeaponLayer weaponLayer;
      for (int index = 0; index < total; index++) {

         weaponLayer =
                 weaponLayerCircularStaticPool.getInstance(
                 this.getOwnerLayerInterface(), 
                 this.relativeRelationship.getX(), 
                 this.relativeRelationship.getY(),
                 0,
                 (int) (minAngle + increment),
                 (int) otherAngle, 
                 weaponProperties, scoreableInterface);

         allbinaryLayerManager.append(weaponLayer);

         next += increment;
      }
   }
}
