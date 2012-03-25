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

import allbinary.graphics.PointFactory;
import allbinary.layer.AllBinaryLayer;

public class MiddleRelativeLayerRelationship 
   extends RelativeLayerRelationship
{
   public MiddleRelativeLayerRelationship(AllBinaryLayer layer) throws Exception
   {
      super(layer, PointFactory.getInstance().getInstance(
         layer.getHalfWidth(), 
         layer.getHalfHeight()), null);
   }
}
