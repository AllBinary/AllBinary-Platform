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
package allbinary.media.graphics.geography.map;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import abcs.logic.java.bool.BooleanFactory;
import allbinary.animation.Animation;
import allbinary.animation.text.TextAnimation;
import allbinary.game.layer.AllBinaryTiledLayer;
import allbinary.graphics.GPoint;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.font.MyFont;
import allbinary.logic.math.BasicDecimal;

public class GeographicMapCellHistory
{
   private final BasicArrayList list;
   private final BasicArrayList visitedList;
   private final String MISSED_INFO = "Missed";

   private int totalVisited;

   private final Animation animation = new TextAnimation(MISSED_INFO);
   
   public GeographicMapCellHistory()
   {
      this.list = new BasicArrayList();
      this.visitedList = new BasicArrayList();
      this.init();
   }

   public int getTotalVisited()
   {
      return this.totalVisited;
   }

   public int getTotalNotVisited()
   {
       return this.getSize() - this.totalVisited;
   }

   public int getSize()
   {
      return this.list.size();
   }

   public void track(BasicArrayList list)
   {
      int size = list.size();
      this.list.ensureCapacity(size);
      this.visitedList.ensureCapacity(size);
      for (int index = 0; index < size; index++)
      {
         GeographicMapCellPosition geographicMapCellPosition =
            (GeographicMapCellPosition) list.get(index);
         this.track(geographicMapCellPosition);
      }
   }

   public void track(GeographicMapCellPosition geographicMapCellPosition)
   {
      //Don't allow duplicates
      if (!this.list.contains(geographicMapCellPosition))
      {
         this.list.add(geographicMapCellPosition);
         this.visitedList.add(BooleanFactory.getInstance().FALSE);
      }
   //LogUtil.put(LogFactory.getInstance("Position: " + geographicMapCellPosition, this, "track"));
   }

   public BasicArrayList getTracked()
   {
      return this.list;
   }
   
   /*
   public GeographicMapCellPosition getRandom()
   {
      return (GeographicMapCellPosition) BasicArrayListUtil.getRandom(this.list);
   }
    * */

   public GeographicMapCellPosition getFirstUnvisited()
   {
      BasicArrayList localList = this.list;
      BasicArrayList localVisitedList = this.visitedList;
      int size = localVisitedList.size();
      for (int index = 0; index < size; index++)
      {
         Boolean value = (Boolean) this.visitedList.get(index);
         if (value == BooleanFactory.getInstance().FALSE)
         {
            return (GeographicMapCellPosition) localList.get(index);
         }
      }

      return (GeographicMapCellPosition) localList.get(0);
   }

   public int getFirstUnvisitedIndex()
   {
      BasicArrayList localVisitedList = this.visitedList;
      int size = localVisitedList.size();
      for (int index = 0; index < size; index++)
      {
         Boolean value = (Boolean) this.visitedList.get(index);
         if (value == BooleanFactory.getInstance().FALSE)
         {
            return index;
         }
      }

      return 0;
   }

   public BasicArrayList getInPathButNotTracked(BasicArrayList pathList)
   {
      BasicArrayList inPathButNotTrackedList = new BasicArrayList();

      BasicArrayList localList = this.list;

      int size = pathList.size();
      for (int index = 0; index < size; index++)
      {
         GeographicMapCellPosition geographicMapCellPosition =
            (GeographicMapCellPosition) pathList.get(index);

         if (!localList.contains(geographicMapCellPosition))
         {
            inPathButNotTrackedList.add(geographicMapCellPosition);
         }
      }
      return inPathButNotTrackedList;
   }

   public boolean isVisited(GeographicMapCellPosition geographicMapCellPosition)
   {
      int index = this.list.indexOf(geographicMapCellPosition);
      if (index != -1)
      {
         Boolean value = (Boolean) this.visitedList.get(index);
         if (value == BooleanFactory.getInstance().TRUE)
         {
            return true;
         }
      }
      else
      {
         //LogUtil.put(LogFactory.getInstance(geographicMapCellPosition.toString() + " not being tracked", this, "visit"));
      }
      return false;
   }
   
   public boolean visit(GeographicMapCellPosition geographicMapCellPosition)
   {
      int index = this.list.indexOf(geographicMapCellPosition);
      if (index != -1)
      {
         Boolean value = (Boolean) this.visitedList.get(index);
         
         Boolean TRUE = BooleanFactory.getInstance().TRUE;
         if (value != TRUE)
         {
            this.visitedList.set(index, TRUE);
            this.totalVisited++;
            return true;
         }
      }
      else
      {
         //LogUtil.put(LogFactory.getInstance(geographicMapCellPosition.toString() + " not being tracked", this, "visit"));
      }
      return false;
   }

   /*
   public Boolean isVisited(
   GeographicMapCellPosition geographicMapCellPosition)
   throws Exception
   {
   int index = this.list.indexOf(geographicMapCellPosition);
   if (index != -1)
   {
   Boolean isCellVisitedBoolean = (Boolean) this.visitedList.get(index);
   return isCellVisitedBoolean;
   }
   else
   {
   return falseBoolean;
   }
   }
    */

   public boolean isAllVisited() throws Exception
   {
       if (this.totalVisited == this.getSize() - 1)
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   public boolean isAllVisited2() throws Exception
   {
       if (this.totalVisited == this.getSize())
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public boolean isVisited(BasicDecimal basicDecimal)
      throws Exception
   {
      int size = this.getSize();

      int numberRequired = (size << basicDecimal.getScaledFactor()) / (int) basicDecimal.getUnscaled();

      //int numberNotVisited = 0;

      int numberNotVisited = this.getSize() - 1 - this.totalVisited;

      /*
      LogUtil.put(LogFactory.getInstance(
         "Total Visited: " + (size - numberNotVisited) +
         " out of " + size + " Number Required: " + numberRequired,
         this, "isMostlyVisited"));
      */

      if (size - numberNotVisited > numberRequired)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public void reset() throws Exception
   {
      BasicArrayList localVisitedList = this.visitedList;
      Boolean localFalseBoolean = BooleanFactory.getInstance().FALSE;
      int size = localVisitedList.size();
      for (int index = size - 1; index >= 0; index--)
      {
         localVisitedList.set(index, localFalseBoolean);
      }

      this.totalVisited = 0;
   }

   private final int height = 2 * MyFont.getInstance().DEFAULT_CHAR_HEIGHT;
   private int halfWidth = 0;
   
   private void paintNotVisited(
      Graphics graphics, AllBinaryTiledLayer tiledLayer, GPoint point)
   {
      int x = point.getX() - tiledLayer.getX();
      int y = point.getY() - tiledLayer.getY();
      //graphics.fillArc(x, y, tiledLayer.getCellWidth(), tiledLayer.getCellHeight(), 0, Angle.THREE_SIXTY);
      if(halfWidth == 0)
      {
          this.halfWidth = (graphics.getFont().stringWidth(MISSED_INFO) >> 1);
      }

      this.animation.paint(graphics, x + halfWidth,
            //(tiledLayer.getCellHeight() >> 1)
              y + (height));

   /*
   if(currentTime - lastTime > 2000)
   {
   //LogUtil.put(LogFactory.getInstance("x: " + x, this, "paintNotVisited"));
   //LogUtil.put(LogFactory.getInstance("y: " + y, this, "paintNotVisited"));
   lastTime = currentTime;
   }
    */
   }
   
   private final int RED = BasicColorFactory.getInstance().RED.intValue();
   
   public void paintNotVisited(Graphics graphics, BasicGeographicMap geographicMapInterface)
   {
      try
      {
         graphics.setColor(RED);

         BasicArrayList localVisitedList = this.visitedList;
         //Boolean localFalseBoolean = this.falseBoolean;
         int size = localVisitedList.size();

         GeographicMapCellPosition geographicMapCellPosition;
         Boolean isCellVisitedBoolean;

         for (int index = size; --index >= 0;)
         {
             geographicMapCellPosition = 
                 (GeographicMapCellPosition) list.get(index);

             isCellVisitedBoolean = (Boolean) localVisitedList.get(index);
            //this.isVisited(geographicMapCellPosition);

            if (!isCellVisitedBoolean.booleanValue())
            {
               this.paintNotVisited(
                  graphics,
                  geographicMapInterface.getAllBinaryTiledLayer(),
                  geographicMapCellPosition.getPoint());
            }
         }

      }
      catch (Exception e)
      {
         LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "paintNotVisited", e));
      }
   }
   
   public void init()
   {
      this.list.clear();
      this.visitedList.clear();
      this.totalVisited = 0;
   }
}
