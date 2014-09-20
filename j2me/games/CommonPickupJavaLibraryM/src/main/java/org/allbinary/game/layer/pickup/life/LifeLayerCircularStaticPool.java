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
package org.allbinary.game.layer.pickup.life;

import org.allbinary.game.life.Life;
import org.allbinary.game.life.LifeInterfaceCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerCircularPool;
import org.allbinary.layer.AllBinaryLayerFactoryInterface;

public class LifeLayerCircularStaticPool extends AllBinaryLayerCircularPool
{
    public LifeLayerCircularStaticPool(
       AllBinaryLayerFactoryInterface allbinaryLayerFactoryInterface,
       int total) 
       throws Exception
    {
       super.init(allbinaryLayerFactoryInterface, total);
    }
   
   public void visit(AllBinaryLayer sourceLayerInterface)
          throws Exception
    {
        LifeLayer lifeLayer = (LifeLayer) this.getNextInstance();
        
        LifeInterfaceCompositeInterface lifeInterfaceCompositeInterface =
                (LifeInterfaceCompositeInterface) sourceLayerInterface;
        Life lifeInterface = lifeInterfaceCompositeInterface.getLifeInterface();

        lifeLayer.visit(lifeInterface);
    }
}
