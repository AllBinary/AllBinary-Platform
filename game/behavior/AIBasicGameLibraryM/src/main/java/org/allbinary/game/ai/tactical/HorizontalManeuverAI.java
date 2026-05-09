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
package org.allbinary.game.ai.tactical;

import javax.microedition.lcdui.Canvas;

import org.allbinary.game.ai.BasicAI;
import org.allbinary.game.input.GameInput;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class HorizontalManeuverAI extends BasicAI
{
	protected int currentRelativeAngle = 0;
   private int currentSpeed;

   public HorizontalManeuverAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);

      this.currentSpeed = 5;
   }

   @Override
   public void processAI(AllBinaryLayerManager allBinaryLayerManager) 
           throws Exception
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();

      //int keyDirection = -1;
      int x = ownerLayerInterface.getXP();

      //this.logUtil.putF("Angle: " + currentAngle + " X: " + x + " Y: " + y, this, this.commonStrings.PROCESS);
      if (ownerLayerInterface.getXP() - this.currentSpeed <= 0)
      {
         this.reverse();
         this.drop();
      }
      
      if (ownerLayerInterface.getX2() + this.currentSpeed > DisplayInfoSingleton.getInstance().getLastWidth())
      {

         this.reverse();
         this.accelerate();
         this.drop();
      }

      if (this.currentRelativeAngle == 0)
      {
         x += this.currentSpeed;
      } else if (this.currentRelativeAngle == 180)
      {
         x -= this.currentSpeed;
      }

      //super.processAI(Canvas.UP);
      ownerLayerInterface.setPosition(x, ownerLayerInterface.getYP(), ownerLayerInterface.getZP());

      if (this.currentRelativeAngle == 0)
      {
    	  super.processKeyAI(Canvas.KEY_NUM0);
      } else if (this.currentRelativeAngle == 180)
      {
         super.processKeyAI(Canvas.KEY_POUND);
      }
   }
   
   protected void reverse()
   {
      if (this.currentRelativeAngle == 180)
      {
         this.currentRelativeAngle = 0;
      } else if (this.currentRelativeAngle == 0)
      {
         this.currentRelativeAngle = 180;
      }
   }

   private void accelerate()
   {
      if (this.currentSpeed < 20)
      {
         this.currentSpeed++;
      }
   }

   private void drop()
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
       
      int y = ownerLayerInterface.getYP();
      if (ownerLayerInterface.getY2() + ownerLayerInterface.getHeight() > DisplayInfoSingleton.getInstance().getLastHeight())
      {
         y = 0;
      } else
      {
         y += ownerLayerInterface.getHeight() + 1;
      }
      ownerLayerInterface.setPosition(ownerLayerInterface.getXP(), y, ownerLayerInterface.getZP());
   }
}