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
package org.allbinary.game.layer.pickup;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerFactoryInterface;

public class FallingPickupLayerFactory 
   implements AllBinaryLayerFactoryInterface
{
   public AllBinaryLayer getInstance() 
      throws Exception
   {
       return new FallingPickupLayer();
   }
}
