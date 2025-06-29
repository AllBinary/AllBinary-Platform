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
package org.allbinary.media.graphics.geography.map;

import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.animation.Animation;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.text.TextAnimation;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.logic.math.BasicDecimal;

public class GeographicMapCellHistory
{
    private final BooleanFactory booleanFactory = BooleanFactory.getInstance();
    
   private final BasicArrayList list;
   private final BasicArrayList visitedList;
   private final String MISSED_INFO = "Missed";
   
   private final Animation animation = new TextAnimation(MISSED_INFO, AnimationBehavior.getInstance());

   private int totalVisited;

   private int halfWidth = 0;

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

   public void track(final BasicArrayList list)
   {
      final int size = list.size();
      this.list.ensureCapacity(size);
      this.visitedList.ensureCapacity(size);
      GeographicMapCellPosition geographicMapCellPosition;
      for (int index = 0; index < size; index++)
      {
         geographicMapCellPosition =
            (GeographicMapCellPosition) list.get(index);
         this.track(geographicMapCellPosition);
      }
   }

   public void track(final GeographicMapCellPosition geographicMapCellPosition)
   {
      //Don't allow duplicates
      if (!this.list.contains(geographicMapCellPosition))
      {
         this.list.add(geographicMapCellPosition);
         this.visitedList.add(booleanFactory.FALSE);
      }
   //LogUtil.put(LogFactory.getInstance("Position: " + geographicMapCellPosition, this, "track"));
   }

   public BasicArrayList getTracked()
   {
      return this.list;
   }

   public BasicArrayList getVisited()
   {
      return this.visitedList;
   }
   
   /*
   public GeographicMapCellPosition getRandom()
   {
      return (GeographicMapCellPosition) BasicArrayListUtil.getRandom(this.list);
   }
    * */

   public GeographicMapCellPosition getAfterIfNotLast(GeographicMapCellPosition geographicMapCellPosition) {
       
       final BasicArrayList localList = this.list;
       
       final int index = localList.indexOf(geographicMapCellPosition);
       if(localList.size() > index + 1) {
           return (GeographicMapCellPosition) localList.get(index + 1);
       }
       
       return geographicMapCellPosition;
   }
   
   public GeographicMapCellPosition getFirstUnvisited()
   {
      final BasicArrayList localList = this.list;
      final BasicArrayList localVisitedList = this.visitedList;
      final int size = localVisitedList.size();
      
      Boolean value;
      for (int index = 0; index < size; index++)
      {
         value = (Boolean) this.visitedList.get(index);
         if (value == booleanFactory.FALSE)
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
      Boolean value;
      for (int index = 0; index < size; index++)
      {
         value = (Boolean) this.visitedList.get(index);
         if (value == booleanFactory.FALSE)
         {
            return index;
         }
      }

      return 0;
   }

   public BasicArrayList getInPathButNotTracked(BasicArrayList pathList)
   {
      final BasicArrayList inPathButNotTrackedList = new BasicArrayList();

      final BasicArrayList localList = this.list;

      final int size = pathList.size();
      GeographicMapCellPosition geographicMapCellPosition;
      for (int index = 0; index < size; index++)
      {
         geographicMapCellPosition =
            (GeographicMapCellPosition) pathList.get(index);

         if (!localList.contains(geographicMapCellPosition))
         {
            inPathButNotTrackedList.add(geographicMapCellPosition);
         }
      }
      return inPathButNotTrackedList;
   }

   public boolean isVisited(final GeographicMapCellPosition geographicMapCellPosition)
   {
      int index = this.list.indexOf(geographicMapCellPosition);
      if (index != -1)
      {
         Boolean value = (Boolean) this.visitedList.get(index);
         if (value == booleanFactory.TRUE)
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
   
   public boolean visit(final GeographicMapCellPosition geographicMapCellPosition)
   {
      final int index = this.list.indexOf(geographicMapCellPosition);
      Boolean value;
      if (index != -1)
      {
         value = (Boolean) this.visitedList.get(index);
         
         Boolean TRUE = booleanFactory.TRUE;
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

   public boolean isVisited(final BasicDecimal basicDecimal)
      throws Exception
   {
      final int size = this.getSize();

      final int numberRequired = (size << basicDecimal.getScaledFactor()) / (int) basicDecimal.getUnscaled();

      //int numberNotVisited = 0;

      final int numberNotVisited = this.getSize() - 1 - this.totalVisited;

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
      final BasicArrayList localVisitedList = this.visitedList;
      final Boolean localFalseBoolean = booleanFactory.FALSE;
      int size = localVisitedList.size();
      for (int index = size - 1; index >= 0; index--)
      {
         localVisitedList.set(index, localFalseBoolean);
      }

      this.totalVisited = 0;
   }
   
   private void paintNotVisited(
      final Graphics graphics, final AllBinaryTiledLayer tiledLayer, final GPoint point)
   {
      final int x = point.getX() - tiledLayer.getX();
      final int y = point.getY() - tiledLayer.getY();
      //graphics.fillArc(x, y, tiledLayer.getCellWidth(), tiledLayer.getCellHeight(), 0, Angle.THREE_SIXTY);
      if(halfWidth == 0)
      {
          this.halfWidth = (graphics.getFont().stringWidth(MISSED_INFO) >> 1);
      }

      final MyFont myFont = MyFont.getInstance();
      final int height = 2 * myFont.DEFAULT_CHAR_HEIGHT;
      
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
   
   public void paintNotVisited(final Graphics graphics, final BasicGeographicMap geographicMapInterface)
   {
      try
      {
         graphics.setColor(RED);

         final BasicArrayList localVisitedList = this.visitedList;
         //Boolean localFalseBoolean = this.falseBoolean;
         final int size = localVisitedList.size();

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
          final CommonStrings commonStrings = CommonStrings.getInstance();
         LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "paintNotVisited", e));
      }
   }
   
   public void init()
   {
      this.list.clear();
      this.visitedList.clear();
      this.totalVisited = 0;
   }
}
