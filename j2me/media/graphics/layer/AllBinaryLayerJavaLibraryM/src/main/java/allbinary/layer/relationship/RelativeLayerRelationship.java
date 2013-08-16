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
package allbinary.layer.relationship;

import org.allbinary.util.BasicArrayList;

import allbinary.graphics.GPoint;
import allbinary.graphics.PointFactory;
import allbinary.graphics.RelativeRelationship;
import allbinary.layer.AllBinaryLayer;

public class RelativeLayerRelationship 
   extends RelativeRelationship
{
   private AllBinaryLayer layer;
   
   public RelativeLayerRelationship(AllBinaryLayer layer, int x, int y) throws Exception
   {
	   this(layer, PointFactory.getInstance().getInstance(x, y), null);
   }
	
   public RelativeLayerRelationship(AllBinaryLayer layer, GPoint point, BasicArrayList typesAllowedList)
   {
      super(point, typesAllowedList);
      
      this.layer = layer;
   }
   
   public int getX()
   {
      return this.layer.getX() + this.x;
   }
   
   public int getY()
   {
      return this.layer.getY() + this.y;
   }

   public int getZ()
   {
      return this.layer.getZ() + this.z;
   }
   
   /*
   public Angle[] getAngleArray()
   {
      return angleArray;
   } 
    */
      
}
