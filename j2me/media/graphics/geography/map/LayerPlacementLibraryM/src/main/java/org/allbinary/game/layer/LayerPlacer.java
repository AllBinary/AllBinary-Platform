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
package org.allbinary.game.layer;

import java.util.Enumeration;
import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.LayerInterfaceFactory;
import org.allbinary.layer.LayerInterfaceVisitor;

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

      //J2ME does not have this so just leave it out for now
      //Set set = hashtable.keySet();
      //Object[] objectArray = set.toArray();
      //int size = set.size();
      //for(int index = 0; index < size; index++)
      //{
         //GPoint point = (GPoint) objectArray[index];
      
      Enumeration enumeration = hashtable.keys();
      while (enumeration.hasMoreElements())
      {
         GPoint point = (GPoint) enumeration.nextElement();

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