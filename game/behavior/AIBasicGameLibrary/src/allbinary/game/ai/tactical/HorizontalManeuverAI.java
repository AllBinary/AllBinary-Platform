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
package allbinary.game.ai.tactical;

import javax.microedition.lcdui.Canvas;

import allbinary.game.ai.BasicAI;
import allbinary.game.input.GameInput;
import allbinary.graphics.displayable.DisplayInfoSingleton;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;

public class HorizontalManeuverAI extends BasicAI
{
	protected int currentRelativeAngle = 0;
   private int currentSpeed;

   public HorizontalManeuverAI(AllBinaryLayer ownerLayerInterface, GameInput gameInput)
   {
      super(ownerLayerInterface, gameInput);

      currentSpeed = 5;
   }

   public void processAI(AllBinaryLayerManager allBinaryLayerManager) 
           throws Exception
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();

      //int keyDirection = -1;
      int x = ownerLayerInterface.getX();

      //LogUtil.put(LogFactory.getInstance("Angle: " + currentAngle + " X: " + x + " Y: " + y, this, "processAI"));
      if (ownerLayerInterface.getX() - currentSpeed <= 0)
      {
         this.reverse();
         this.drop();
      }
      
      if (ownerLayerInterface.getX2() + currentSpeed > DisplayInfoSingleton.getInstance().getLastWidth())
      {

         this.reverse();
         this.accelerate();
         this.drop();
      }

      if (currentRelativeAngle == 0)
      {
         x += (currentSpeed);
      } else if (currentRelativeAngle == 180)
      {
         x -= (currentSpeed);
      }

      //super.processAI(Canvas.UP);
      ownerLayerInterface.setPosition(x, ownerLayerInterface.getY());

      if (currentRelativeAngle == 0)
      {
    	  super.processAI(Canvas.KEY_NUM0);
      } else if (currentRelativeAngle == 180)
      {
         super.processAI(Canvas.KEY_POUND);
      }
   }
   
   protected void reverse()
   {
      if (currentRelativeAngle == 180)
      {
         currentRelativeAngle = 0;
      } else if (currentRelativeAngle == 0)
      {
         currentRelativeAngle = 180;
      }
   }

   private void accelerate()
   {
      if (currentSpeed < 20)
      {
         currentSpeed++;
      }
   }

   private void drop()
   {
       AllBinaryLayer ownerLayerInterface = this.getOwnerLayerInterface();
       
      int y = ownerLayerInterface.getY();
      if (ownerLayerInterface.getY2() + ownerLayerInterface.getHeight() > DisplayInfoSingleton.getInstance().getLastHeight())
      {
         y = 0;
      } else
      {
         y += ownerLayerInterface.getHeight() + 1;
      }
      ownerLayerInterface.setPosition(ownerLayerInterface.getX(), y);
   }
}