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
package org.allbinary.game.layer.geographic.map;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.animation.vector.CircleFilledAnimation;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.Rectangle;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.ColorCompositeInterface;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.event.GeographicMapCellPositionEvent;
import org.allbinary.media.graphics.geography.map.event.GeographicMapCellPositionEventHandler;
import org.allbinary.media.graphics.geography.map.event.GeographicMapCellPositionEventListenerInterface;
import org.allbinary.view.ViewPosition;

public class MiniMapLayer 
extends AllBinaryLayer 
implements GeographicMapCellPositionEventListenerInterface
{
   //private final SimpleGeographicMap simpleGeographicMap;
   protected final AllBinaryTiledLayer allBinaryTiledLayer;
   private final BasicGeographicMapCellPositionFactory geographicMapCellPositionFactory;
   
   private final BasicArrayList list;
   private final BasicArrayList basicColorList;
   private final BasicArrayList positionList;

   private CircleFilledAnimation animationInterface;
   
   public MiniMapLayer(BasicGeographicMap geographicMapInterface,
       //GeographicMapCellPositionFactory geographicMapCellPositionFactory,
      //ViewPosition can only be static or setPosition will need to be in paint
      ViewPosition viewPosition) throws Exception
   {
      super(new Rectangle(PointFactory.getInstance().getInstance(0, viewPosition.getY()),
         geographicMapInterface.getAllBinaryTiledLayer().getWidth(),
         geographicMapInterface.getAllBinaryTiledLayer().getHeight()),
         viewPosition);

      //this.simpleGeographicMap = simpleGeographicMap;
      this.allBinaryTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
      this.geographicMapCellPositionFactory =
          geographicMapInterface.getGeographicMapCellPositionFactory();

      this.animationInterface = new CircleFilledAnimation(
              this.allBinaryTiledLayer.getCellWidth(), 
              this.allBinaryTiledLayer.getCellHeight(), BasicColorFactory.getInstance().WHITE);
      
      // allBinaryTiledLayer.setPosition(0, this.getY());

      //LogUtil.put(LogFactory.getInstance("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx Alpha: " + this.bufferedImage.getBitmap().getPixel(0,0), this, "updateBufferedImage"));

      this.list = new BasicArrayList();
      this.basicColorList = new BasicArrayList();
      this.positionList = new BasicArrayList();

      //GeographicMapCellPositionFactory geographicMapCellPositionFactory =
        // geographicMapInterface.getGeographicMapCellPositionFactory();

      GeographicMapCellPositionEventHandler.getInstance().addListener(this);

      this.init();
   }

   protected void init() throws Exception
   {
      allBinaryTiledLayer.setPosition(this.x, this.y, this.z);
   }

   public void onEvent(AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
      //this.onGeographicMapCellPositionEvent((GeographicMapCellPositionEvent) eventObject);
   }

   public synchronized void onRemoveGeographicMapCellPositionEvent(GeographicMapCellPositionEvent geographicMapCellPositionEvent) 
   throws Exception
   {
       ColorCompositeInterface colorCompositeInterface =
           (ColorCompositeInterface) geographicMapCellPositionEvent.getSource();

       int index = this.list.indexOf(colorCompositeInterface);

       if(index >= 0)
       {
           this.list.remove(index);
           this.basicColorList.remove(index);
           this.positionList.remove(index);
       }
   }
   
   public synchronized void onGeographicMapCellPositionEvent(
      GeographicMapCellPositionEvent geographicMapCellPositionEvent)
      throws Exception
   {
      //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "onEvent"));

       ColorCompositeInterface colorCompositeInterface =
           (ColorCompositeInterface) geographicMapCellPositionEvent.getSource();

      int layerIndex = -1;

      int size = list.size();
      for (int index = 0; index < size; index++)
      {
          ColorCompositeInterface nextColorCompositeInterface = (ColorCompositeInterface) list.get(index);
         if (nextColorCompositeInterface == colorCompositeInterface)
         {
            layerIndex = index;
            break;
         }
      }

      GeographicMapCellPosition geographicMapCellPosition =
         geographicMapCellPositionFactory.getInstance(
         geographicMapCellPositionEvent.getGeographicMapCellPosition());
      
      GPoint point = geographicMapCellPosition.getPoint();
      if (layerIndex == -1)
      {
         this.list.add(colorCompositeInterface);
         
         BasicColor basicColor = colorCompositeInterface.getBasicColor();
         
         this.basicColorList.add(basicColor);
         this.positionList.add(point);
         layerIndex = this.basicColorList.size() - 1;
      }
      else
      {
         //update
         this.positionList.set(layerIndex, point);
      }
   }

   //private final int clearColor = BasicColor.CLEAR_COLOR.intValue();
   public void paintDots(Graphics graphics)
   {
      BasicArrayList localPositionList = this.positionList;
      BasicArrayList localBasicColorList = this.basicColorList;

      int length = localPositionList.size();

      //int localX;
      //int localY;

      GPoint point;
      BasicColor basicColor;
      
      for (int index = length; --index >= 0;)
      {
          point = (GPoint) localPositionList.get(index);
          basicColor = (BasicColor) localBasicColorList.get(index);

         graphics.setColor(basicColor.intValue());

         //localX = point.getX();
         //localY = point.getY();

         this.animationInterface.setBasicColor(basicColor);
         this.animationInterface.paint(graphics, 
                 //localX 
                 point.getX() + this.x, 
                 //localY 
                 point.getY() + this.y);

      //LogUtil.put(LogFactory.getInstance("X: " + x + " Y: " + y + " Color: " + color, this, "updateBufferedImage"));
      }

   }

   public void paint(Graphics graphics)
   {
      allBinaryTiledLayer.paint(graphics);
      this.paintDots(graphics);
   }
}
