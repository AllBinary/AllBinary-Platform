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
import allbinary.math.NoDecimalTrigTable;

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
           short angle, WeaponProperties weaponProperties, ScoreableInterface scoreableInterface)
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
                 sourceLayerInterface, x + beamX, y + beamY, angle, 
                 weaponProperties, scoreableInterface);

         allbinaryLayerManager.append(weaponLayer);

         next += increment;
      }
   }
}
