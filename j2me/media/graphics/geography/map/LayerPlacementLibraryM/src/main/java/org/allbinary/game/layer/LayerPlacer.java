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

import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.LayerInterfaceFactory;
import org.allbinary.layer.LayerInterfaceVisitor;
import org.allbinary.util.BasicArrayList;

public class LayerPlacer
{
   protected final LayerInterfaceVisitor layerInterfaceVisitor;
   private final GPoint dimension;

   public LayerPlacer(final LayerInterfaceVisitor layerInterfaceVisitor, final GPoint dimension)
   {
      this.layerInterfaceVisitor = layerInterfaceVisitor;
      this.dimension = dimension;
   }

   public void process(final BasicArrayList list) throws Exception
   {
      final int size = list.size();
      LayerPlacementInterface layerPlacementInterface;
      for(int index = 0; index < size; index++)
      {
         layerPlacementInterface =
            (LayerPlacementInterface) list.objectArray[index];

         this.process(layerPlacementInterface);
      }
   }

   public void process(final LayerPlacementInterface layerPlacementInterface) throws Exception
   {
      final GPoint relativePoint = this.getPoint(layerPlacementInterface);

      final LayerInterfaceFactory layerInterfaceFactory = LayerInterfaceFactory.getInstance();
      
      final Hashtable hashtable = layerPlacementInterface.getInstance();

      //J2ME does not have this so just leave it out for now
      //Set set = hashtable.keySet();
      //Object[] objectArray = set.toArray();
      //int size = set.size();
      //for(int index = 0; index < size; index++)
      //{
         //GPoint point = (GPoint) objectArray[index];
      
      final Enumeration enumeration = hashtable.keys();
      GPoint point;
      Hashtable layerHashtable;
      int x;
      int y;
      int z;
      AllBinaryLayer layerInterface;

      while (enumeration.hasMoreElements())
      {
         point = (GPoint) enumeration.nextElement();

         layerHashtable = (Hashtable) hashtable.get(point);

         x = point.getX() + relativePoint.getX();
         y = point.getY() + relativePoint.getY();
         z = point.getZ() + relativePoint.getZ();

         layerInterface = layerInterfaceFactory.getInstance(layerHashtable, x, y, z);

         layerInterfaceVisitor.visit(layerInterface);
      }
   }

   public GPoint getPoint(final LayerPlacementInterface layerPlacementInterface)
      throws Exception
   {
      final LayerPlacementType layerPlacementType =
         layerPlacementInterface.getLayerType();

      if (layerPlacementType == LayerPlacementTypeFactory.getInstance().MAP)
      {
         final int width = layerPlacementInterface.getWidth();
         final int height = layerPlacementInterface.getHeight();
         final int x = ((dimension.getX() - width) / 2);
         final int y = ((dimension.getY() - height) / 2);

         return PointFactory.getInstance().getInstance(x, y);
      } else if (layerPlacementType == LayerPlacementTypeFactory.getInstance().UP)
      {
         final int width = layerPlacementInterface.getWidth();
         final int height = layerPlacementInterface.getHeight();
         final int x = ((dimension.getX() - width) / 2);
         final int y = -height;

         return PointFactory.getInstance().getInstance(x, y);
      } else
      {
         throw new Exception("PlacementType Not Recognized");
      }
   }
}