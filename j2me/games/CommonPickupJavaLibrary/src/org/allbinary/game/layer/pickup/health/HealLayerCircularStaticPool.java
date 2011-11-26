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

package org.allbinary.game.layer.pickup.health;

import allbinary.game.health.HealthInterface;
import allbinary.game.health.HealthInterfaceCompositeInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerCircularPool;
import allbinary.layer.AllBinaryLayerFactoryInterface;

public class HealLayerCircularStaticPool extends AllBinaryLayerCircularPool
{
    public HealLayerCircularStaticPool(AllBinaryLayerFactoryInterface allbinaryLayerFactoryInterface,
            int total) throws Exception
    {
       super.init(allbinaryLayerFactoryInterface, total);
    }
   
    public void visit(AllBinaryLayer sourceLayerInterface)
          throws Exception
    {
        HealLayer healLayer = (HealLayer) this.getNextInstance();
        
        HealthInterfaceCompositeInterface healthInterfaceCompositeInterface =
                (HealthInterfaceCompositeInterface) sourceLayerInterface;
        HealthInterface healthInterface = healthInterfaceCompositeInterface.getHealthInterface();

        healLayer.visit(healthInterface);
        
        //return healLayer;
    }
          
}
