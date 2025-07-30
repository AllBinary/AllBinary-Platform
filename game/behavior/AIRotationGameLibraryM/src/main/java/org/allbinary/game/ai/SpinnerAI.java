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
package org.allbinary.game.ai;

import javax.microedition.lcdui.Canvas;

import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class SpinnerAI extends BasicAI
{

   private boolean isEven;
   //private int currentAngle = 90;
   //private IndexedAnimation rotationAnimationInterface;
   private int[] direction;

   //int fireAngles[],
   public SpinnerAI(int[] direction, AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);

      this.direction = direction;
      //RotationAnimationInterfaceCompositeInterface rotationAnimationInterfaceCompositeInterface = (RotationAnimationInterfaceCompositeInterface) this.getOwnerLayerInterface();
     // this.rotationAnimationInterface = rotationAnimationInterfaceCompositeInterface.getRotationAnimationInterface();
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
   {
      if (isEven)
      {
         super.processAI(this.direction[0]);
         isEven = false;
      } else
      {
         super.processAI(Canvas.KEY_NUM1);
         isEven = true;
      }


      //Shoot only down
      /*
      if(this.rotationAnimationInterface.getAngleInfoP().getAngle() > 90 &&
      this.rotationAnimationInterface.getAngleInfoP().getAngle() < 180)
      {
      super.processAI(Canvas.KEY_NUM1);
      }
       */
   }
}