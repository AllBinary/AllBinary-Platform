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
package allbinary.media.graphics.geography.map.racetrack.drop;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

import org.allbinary.layer.LayerInterfaceFactoryInterface;


public class RaceTrackAdjacentDropLayerFactory
{
   private static RaceTrackAdjacentDropLayerFactory DROP_LAYER_FACTORY = new RaceTrackAdjacentDropLayerFactory();
   
   private BasicArrayList list = new BasicArrayList();

   private RaceTrackAdjacentDropLayerFactory()
   {
   }

   public void clear()
   {
       list.clear();
   }
   
   public static RaceTrackAdjacentDropLayerFactory getInstance()
   {
      return DROP_LAYER_FACTORY;
   }
   
   public int getSize()
   {
      return list.size();
   }
   
   /*
   private LayerInterfaceFactoryInterface getInstance(int index)
   {
      return (LayerInterfaceFactoryInterface) list.get(index);
   }
   */
   
   private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
   
   public LayerInterfaceFactoryInterface getRandomInstance()
   {
      return (LayerInterfaceFactoryInterface) basicArrayListUtil.getRandom(list);
   }

   public void add(LayerInterfaceFactoryInterface layerInterfaceFactoryInterface)
   {
      list.add(layerInterfaceFactoryInterface);
   }
}
