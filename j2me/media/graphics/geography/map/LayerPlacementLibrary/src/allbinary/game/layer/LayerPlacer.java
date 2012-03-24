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
package allbinary.game.layer;

import java.util.Hashtable;
import java.util.Set;

import org.allbinary.util.BasicArrayList;

import allbinary.graphics.GPoint;
import allbinary.graphics.PointFactory;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.LayerInterfaceFactory;
import allbinary.layer.LayerInterfaceVisitor;

public class LayerPlacer
{
   protected final LayerInterfaceVisitor layerInterfaceVisitor;
   private final GPoint dimension;

   public LayerPlacer(LayerInterfaceVisitor layerInterfaceVisitor,
      GPoint dimension)
   {
      this.layerInterfaceVisitor = layerInterfaceVisitor;
      this.dimension = dimension;
   }

   public void process(BasicArrayList list) throws Exception
   {
      int size = list.size();
      for(int index = 0; index < size; index++)
      {
         LayerPlacementInterface layerPlacementInterface =
            (LayerPlacementInterface) list.objectArray[index];

         this.process(layerPlacementInterface);
      }
   }

   public void process(LayerPlacementInterface layerPlacementInterface) throws Exception
   {
      GPoint relativePoint = this.getPoint(layerPlacementInterface);

      final LayerInterfaceFactory layerInterfaceFactory = LayerInterfaceFactory.getInstance();
      
      Hashtable hashtable = layerPlacementInterface.getInstance();

      Set set = hashtable.keySet();
      Object[] objectArray = set.toArray();
      
      int size = set.size();
      
      for(int index = 0; index < size; index++)
      {
         GPoint point = (GPoint) objectArray[index];

         Hashtable layerHashtable = (Hashtable) hashtable.get(point);

         int x = point.getX() + relativePoint.getX();
         int y = point.getY() + relativePoint.getY();
         int z = point.getZ() + relativePoint.getZ();

         AllBinaryLayer layerInterface = layerInterfaceFactory.getInstance(layerHashtable, x, y, z);

         layerInterfaceVisitor.visit(layerInterface);
      }
   }

   public GPoint getPoint(LayerPlacementInterface layerPlacementInterface)
      throws Exception
   {
      LayerPlacementType layerPlacementType =
         layerPlacementInterface.getLayerType();

      if (layerPlacementType == LayerPlacementTypeFactory.getInstance().MAP)
      {
         int width = layerPlacementInterface.getWidth();
         int height = layerPlacementInterface.getHeight();
         int x = ((dimension.getX() - width) / 2);
         int y = ((dimension.getY() - height) / 2);

         return PointFactory.getInstance().getInstance(x, y);
      } else if (layerPlacementType == LayerPlacementTypeFactory.getInstance().UP)
      {
         int width = layerPlacementInterface.getWidth();
         int height = layerPlacementInterface.getHeight();
         int x = ((dimension.getX() - width) / 2);
         int y = -height;

         return PointFactory.getInstance().getInstance(x, y);
      } else
      {
         throw new Exception("PlacementType Not Recognized");
      }
   }
}