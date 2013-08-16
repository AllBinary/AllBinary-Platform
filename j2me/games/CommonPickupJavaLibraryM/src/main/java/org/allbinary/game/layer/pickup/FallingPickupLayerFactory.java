package org.allbinary.game.layer.pickup;

import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerFactoryInterface;

public class FallingPickupLayerFactory 
   implements AllBinaryLayerFactoryInterface
{
   public AllBinaryLayer getInstance() 
      throws Exception
   {
       return new FallingPickupLayer();
   }
}
