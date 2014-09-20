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
import org.allbinary.math.NoDecimalTrigTable;

public class StraightMultiProjectileWeaponPart extends BasicWeaponPart {

   private WeaponLayerCircularPool weaponLayerCircularStaticPool;
   private int total;

   public StraightMultiProjectileWeaponPart(
      Animation animationInterface,
      WeaponLayerCircularPool weaponLayerCircularStaticPool) {
      super(animationInterface);
      
      this.total = 2;
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }

   public StraightMultiProjectileWeaponPart(
           Animation animationInterface,
           AllBinaryLayer sourceLayerInterface,
           WeaponLayerCircularPool weaponLayerCircularStaticPool,
           int total, WeaponProperties weaponProperties, ScoreableInterface scoreableInterface, RelativeRelationship relativeRelationship) {
      super(animationInterface, sourceLayerInterface, weaponProperties, scoreableInterface, relativeRelationship);

      this.total = total;
      this.weaponLayerCircularStaticPool = weaponLayerCircularStaticPool;
   }

   private final NoDecimalTrigTable noDecimalTrigTable = NoDecimalTrigTable.getInstance();
   
   public void process(AllBinaryLayerManager allbinaryLayerManager,
           short angle, short otherAngle, WeaponProperties weaponProperties, ScoreableInterface scoreableInterface)
           throws Exception {
       AllBinaryLayer sourceLayerInterface = this.getOwnerLayerInterface();
      int reducedWidth = (sourceLayerInterface.getWidth() * 8 / 10);
      int halfWidth = (reducedWidth >> 1);
      //int halfHeight = sourceLayerInterface.getHalfHeight();

      int x = this.relativeRelationship.getX();
      int y = this.relativeRelationship.getY();

      //???????
      //WeaponLayer[] weaponLayerArray = WeaponLayerArrayLayerCircularStaticPool.getInstance(total);

      int increment = reducedWidth / (this.total - 1);
      int next = -halfWidth;
      for (int index = 0; index < total; index++) {
          
         int beamX = (int) ((next * noDecimalTrigTable.sin(angle))) / noDecimalTrigTable.SCALE;
         int beamY = -(int) ((next * noDecimalTrigTable.cos(angle))) / noDecimalTrigTable.SCALE;

         WeaponLayer weaponLayer =
                 weaponLayerCircularStaticPool.getInstance(
                 sourceLayerInterface, x + beamX, y + beamY, 0, 
                 angle, otherAngle,  
                 weaponProperties, scoreableInterface);

         allbinaryLayerManager.append(weaponLayer);

         next += increment;
      }
   }
}
