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
 *Date: Sep 29, 2007, 7:09:17 AM
 *
 *
 *Modified By         When       ?
 *
 */

package org.allbinary.game.layer.pickup.life;

import allbinary.game.life.Life;
import allbinary.game.life.LifeInterfaceCompositeInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerCircularPool;
import allbinary.layer.AllBinaryLayerFactoryInterface;

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
