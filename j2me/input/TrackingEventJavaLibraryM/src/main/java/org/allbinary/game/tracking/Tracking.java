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
package org.allbinary.game.tracking;

import javax.microedition.lcdui.Graphics;

public class Tracking 
{
    //private final DisplayInfoSingleton displayInfoSingleton = 
//            DisplayInfoSingleton.getInstance();
    
   /*
   private final int MAXTRACKING = 4;
   private final int UP = 0;
   private final int DOWN = 1;
   private final int LEFT = 2;
   private final int RIGHT = 3;
   private final int ARROWWIDTH = 10;
   private int trackingIndex;
   private int trackingX[];
   private int trackingY[];
   private int trackingWidth[];
   private int trackingHeight[];
    */
   
   public Tracking()
   {
      /*
      this.trackingIndex = 0;
      this.trackingX = new int[MAXTRACKING];
      this.trackingY = new int[MAXTRACKING];
      this.trackingWidth = new int[MAXTRACKING];
      this.trackingHeight = new int[MAXTRACKING];
       */
      
   }

   public void paint(Graphics graphics)
   {

      /*
      //Draw arrow in the general direction of other game objects
      graphics.setColor(BasicColors.GREEN.intValue());
      this.trackingIndex--;
      while(this.trackingIndex>=0)
      {
      //int clipWidth = graphics.getClipWidth();
      //int clipHeight = graphics.getClipHeight();
      int clipWidth = this.;
      int clipHeight = this.;
      int tracking = -1;
      int arrowX = 0;
      int arrowY = 0;
      int direction = 0;
      if(!CollisionManager.isCollision(trackingX[this.trackingIndex]
      {
      tracking[this.trackingIndex] =
      }
      if(tracking[this.trackingIndex] == UP)
      {
      arrowX = (clipWidth >> 1) - (ARROWWIDTH >> 1);
      arrowY = 4;
      direction = 0;
      }
      else
      if(tracking[this.trackingIndex] == DOWN)
      {
      arrowX =( clipWidth >> 1) - (ARROWWIDTH >> 1);
      arrowY = clipHeight - 4 - ARROWWIDTH;
      direction = 2;
      }
      else
      if(tracking[this.trackingIndex] == LEFT)
      {
      arrowX = 4;
      arrowY = (clipHeight >> 1) - (ARROWWIDTH >> 1);
      direction = 3;
      }
      else
      if(tracking[this.trackingIndex] == RIGHT)
      {
      arrowX = clipWidth - 4 - ARROWWIDTH;
      arrowY = (clipHeight >> 1) - (ARROWWIDTH >> 1);
      direction = 1;
      }
      else
      {
      //location error
      break;
      }
      for(int index = 0; index<this.arrowPoints.length-1; index++)
      {
      graphics.drawLine(
      (int) arrowPoints[direction][index][0]+arrowX,
      (int) arrowPoints[direction][index][1]+arrowY,
      (int) arrowPoints[direction][index+1][0]+arrowX,
      (int) arrowPoints[direction][index+1][1]+arrowY);
      }
      this.trackingIndex--;
      }
      this.trackingIndex = 0;
       */

   }
   
   /*
   public void track(MyLayer myLayer)
   {
   if(this.trackingIndex < MAXTRACKING)
   {
   //calculate distance first
   this.trackingX[this.trackingIndex] = myLayer.x;
   this.trackingY[this.trackingIndex] = myLayer.y;
   this.trackingWidth[this.trackingIndex] = myLayer.getWidth();
   this.trackingHeight[this.trackingIndex] = myLayer.getHeight();
   this.trackingIndex++;
   }
   }
    */
   
}
