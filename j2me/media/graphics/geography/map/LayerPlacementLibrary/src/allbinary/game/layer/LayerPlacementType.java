/*
 *Copyright (c) 2007 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: Oct 21, 2007, 11:11:23 AM
 *
 *
 *Modified By         When       ?
 *
 */

package allbinary.game.layer;


public class LayerPlacementType
{    
   private int type;

   protected LayerPlacementType(int type)
   {
      this.type = type;
   }

   public int getType()
   {
      return type;
   }

   protected void setType(int type)
   {
      this.type = type;
   }
}