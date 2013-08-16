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
package allbinary.game.layer.drop;

import org.allbinary.game.layer.pickup.PickedUpLayerInterfaceFactoryInterface;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;


public class DropLayerFactory 
{
   private static final DropLayerFactory DROP_LAYER_FACTORY = new DropLayerFactory();
   
   private final BasicArrayList list = new BasicArrayList();

   private DropLayerFactory()
   {
   }

   public void clear()
   {
       list.clear();
   }
   
   public static DropLayerFactory getInstance()
   {
      return DROP_LAYER_FACTORY;
   }
   
   public int getSize()
   {
      return list.size();
   }
   
   /*
   private PickedUpLayerInterfaceFactoryInterface getInstance(int index)
   {
      return (PickedUpLayerInterfaceFactoryInterface) list.get(index);
   }
   */
   
   private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
   
   public PickedUpLayerInterfaceFactoryInterface getRandomInstance()
   {
      return (PickedUpLayerInterfaceFactoryInterface) basicArrayListUtil.getRandom(list);
   }

   public void add(PickedUpLayerInterfaceFactoryInterface layerInterfaceFactoryInterface)
   {
      list.add(layerInterfaceFactoryInterface);
   }
   
   public BasicArrayList getList() {
       return list;
       }   
}
