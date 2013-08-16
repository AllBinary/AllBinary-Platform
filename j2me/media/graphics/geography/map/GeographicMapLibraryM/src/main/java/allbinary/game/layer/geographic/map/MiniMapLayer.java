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
package allbinary.game.layer.geographic.map;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import abcs.logic.communication.log.ForcedLogUtil;
import allbinary.animation.vector.CircleFilledAnimation;
import allbinary.game.layer.AllBinaryTiledLayer;
import allbinary.graphics.GPoint;
import allbinary.graphics.PointFactory;
import allbinary.graphics.Rectangle;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.color.ColorCompositeInterface;
import allbinary.layer.AllBinaryLayer;
import allbinary.logic.basic.util.event.AllBinaryEventObject;
import allbinary.logic.basic.util.event.handler.BasicEventHandler;
import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.media.graphics.geography.map.BasicGeographicMapCellPositionFactory;
import allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import allbinary.media.graphics.geography.map.event.GeographicMapCellPositionEvent;
import allbinary.media.graphics.geography.map.event.GeographicMapCellPositionEventHandler;
import allbinary.media.graphics.geography.map.event.GeographicMapCellPositionEventListenerInterface;
import allbinary.view.ViewPosition;

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
      ViewPosition viewPosition) throws Exception
   {
      super(new Rectangle(PointFactory.getInstance().getInstance(0, 13),
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
       ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
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
      //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, "onEvent"));

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
