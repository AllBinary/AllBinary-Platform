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
package org.allbinary.game.layer.pickup.points;

import org.allbinary.animation.FeaturedAnimationInterfaceFactoryInterfaceFactory;
import org.allbinary.game.configuration.GameConfigurationUtil;
import org.allbinary.game.layer.pickup.IconLayerFactory;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactory;
import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.game.layer.pickup.PickedUpLayerTypeFactory;
import org.allbinary.game.layer.pickup.PickupProcessorInterface;
import org.allbinary.layer.AllBinaryLayer;

public class Points30kPickedUpLayerInterfaceFactory extends PickedUpLayerInterfaceFactory
   implements PickupProcessorInterface
{
   private static PickedUpLayerInterfaceFactoryInterface pickedUpLayerInterfaceFactoryInterface;
   
   public static void init()
      throws Exception
   {
      pickedUpLayerInterfaceFactoryInterface = new Points30kPickedUpLayerInterfaceFactory();
   }
   
   private PointsLayerCircularStaticPool pool = 
      new PointsLayerCircularStaticPool(
          new PointsLayerFactory(GameConfigurationUtil.getInstance().getCompetitionValue() * 30000), 1
      );
   
   private Points30kPickedUpLayerInterfaceFactory()
      throws Exception
   {
      super(PickedUpLayerTypeFactory.getInstance().POINTS, 
    		  IconLayerFactory.getInstance(
              FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(PointsResources.getInstance().RESOURCE_3).getInstance(0), 10, 10),
              FeaturedAnimationInterfaceFactoryInterfaceFactory.getInstance().get(PointsResources.getInstance().RESOURCE_3).getInstance(0));
   }

   public int getTotal()
   {
      return 1;
   }
   
   public static PickedUpLayerInterfaceFactoryInterface getInstance()
   {
      return pickedUpLayerInterfaceFactoryInterface;
   }

   public void process(AllBinaryLayer sourceLayerInterface) throws Exception
   {
      pool.visit(sourceLayerInterface);
   }
}