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
package org.allbinary.game.graphics.hud;

import javax.microedition.lcdui.Graphics;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.handler.BasicEventHandler;

public class BasicHud_1 //implements DisplayChangeEventListener
{
    
   private int location;
   private int direction;
   
   private int bufferZone;
   private int maxWidth;
   private int maxHeight;
   
   private HudGraphicsPosition hudGraphicsPosition;
   
   private int x;
   private int y;

   private final BasicColorSetUtil basicSetColorUtil = 
       BasicColorSetUtil.getInstance();
   
   private BasicColor basicColor;
   private int color;

   public BasicHud_1(int location, int direction,
       int maxHeight, int maxWidth, int bufferZone)
       throws Exception
   {
       this(location, direction, maxHeight, maxWidth, bufferZone, 
               BasicColorFactory.getInstance().WHITE);
   }
   
   public BasicHud_1(int location, int direction,
           int maxHeight, int maxWidth, int bufferZone, BasicColor basicColor)
           throws Exception
       {
      this.setLocation(location);
      this.setDirection(direction);
      
      this.setBufferZone(bufferZone);
      this.setMaxWidth(maxWidth);
      this.setMaxHeight(maxHeight);
      
      this.onDisplayChangeEvent(null);
      
      this.setBasicColor(basicColor);
      this.color = basicColor.intValue();      
   }
   
   public int getLocation()
   {
      return this.location;
   }

   public int getDirection()
   {
      return this.direction;
   }
   
   /*
   public HudGraphicsPosition getHudGraphicsPosition(Graphics graphics)
           throws Exception
   {
      return this.getHudGraphicsPosition(this.displayInfoSingleton.getLastWidth(), graphics.getClipHeight());
      return this.getHudGraphicsPosition(this.displayInfoSingleton.getLastWidth(), this.displayInfoSingleton.getLastHeight());
   }
   */

   protected HudGraphicsPosition getHudGraphicsPosition(int width, int height)
           throws Exception
   {
      int x = 0;
      int y = 0;
      int anchor = 0;

      BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();
      
      if(basicHudFactory.BOTTOMLEFT == this.getLocation())
      {
         x = bufferZone + 2;
         y = height - maxHeight - bufferZone;
         anchor = Graphics.BOTTOM & Graphics.LEFT;
      }
      else
      if(basicHudFactory.BOTTOMRIGHT == this.getLocation())
      {
         x = width - maxWidth;
         y = height - maxHeight - bufferZone;
         anchor = Graphics.BOTTOM & Graphics.RIGHT;
      }
      else
      if(basicHudFactory.TOPLEFT == this.getLocation())
      {
         x = bufferZone + 2;
         y = bufferZone + 5;
         anchor = Anchor.TOP_LEFT;
      }
      else
          if(basicHudFactory.TOPRIGHT == this.getLocation())
          {
             x = width - maxWidth;
             y = bufferZone + 5;
             anchor = Graphics.TOP & Graphics.RIGHT;
          }
          else
              if(basicHudFactory.TOPCENTER == this.getLocation())
              {
                  x = ((width - maxWidth) / 2);
                 y = bufferZone + 5;
                 anchor = Graphics.TOP & Graphics.HCENTER;
              }
              else
                  if(basicHudFactory.BOTTOMCENTER == this.getLocation())
                  {
                      x = ((width - maxWidth) / 2);
                     y = height - maxHeight - bufferZone;
                     anchor = Graphics.BOTTOM & Graphics.HCENTER;
                  }
                  else
                      if(basicHudFactory.ABSOLUTE == this.getLocation())
                  {
                     x = maxHeight;
                     y = maxWidth;
                     anchor = 0;
                  }
      
      return new HudGraphicsPosition(this.getPoint(x, y), anchor);
   }

   public void onEvent(AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(BasicEventHandler.PERFORMANCE_MESSAGE, this);
   }
   
   public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
   {
       try
       {
           //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "onDisplayChangeEvent"));
           //LogUtil.put(LogFactory.getInstance(commonStrings.START_LABEL).append(DisplayInfoSingleton.getInstance().toString(), this, "onDisplayChangeEvent"));
           
           DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

           this.hudGraphicsPosition = this.getHudGraphicsPosition(
                   displayInfo.getLastWidth(), displayInfo.getLastHeight());
           
           x = this.hudGraphicsPosition.getPoint().getX();
           this.setY(this.hudGraphicsPosition.getPoint().getY());
       }
       catch(Exception e)
       {
           final CommonStrings commonStrings = CommonStrings.getInstance();
           LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "onDisplayChangeEvent", e));
       }
   }
   
   protected GPoint getPoint(int x, int y)
   {
       return PointFactory.getInstance().getInstance(x, y);
   }

   public int getBufferZone()
   {
      return bufferZone;
   }

   public void setBufferZone(int bufferZone)
   {
      this.bufferZone = bufferZone;
   }

   public int getMaxWidth()
   {
      return maxWidth;
   }

   public void setMaxWidth(int maxWidth)
   {
      this.maxWidth = maxWidth;
   }

   public int getMaxHeight()
   {
      return maxHeight;
   }

   public void setMaxHeight(int maxHeight)
   {
      this.maxHeight = maxHeight;
   }

   public void setLocation(int location)
   {
      this.location = location;
   }

   public void setDirection(int direction)
   {
      this.direction = direction;
   }

   public void paint(Graphics graphics, String string, String string2, int offset)
   {
       this.basicSetColorUtil.setBasicColor(graphics, getBasicColor());

      graphics.drawString(string,
              x, //getHudGraphicsPosition().getPoint().getX().intValue(),
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              hudGraphicsPosition.getAnchor());
      graphics.drawString(string2,
              x + offset, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              hudGraphicsPosition.getAnchor());
   }

   public void paint(Graphics graphics, String string, String string2, int offset, int offset2)
   {
      graphics.setColor(this.getColor());
      graphics.drawString(string,
              x + offset, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              hudGraphicsPosition.getAnchor());
      graphics.drawString(string2,
              x + offset2, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              hudGraphicsPosition.getAnchor());
   }

   public void paint(Graphics graphics, 
           char[] charArray, int offset, int len, 
           char[] charArray2, int offset2, int len2, 
           int xOffset, int xOffset2)
   {
       this.basicSetColorUtil.setBasicColor(graphics, getBasicColor());
       
       int y = getY();
       
       graphics.drawChars(charArray,
               offset, len,
          x + xOffset, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          hudGraphicsPosition.getAnchor());

       graphics.drawChars(charArray2,
               offset2, len2,
          x + xOffset2, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          hudGraphicsPosition.getAnchor());
   }
   
   public void paint(Graphics graphics, 
           char[] charArray, int offset, int len, 
           char[] charArray2, int offset2, int len2, 
           int xOffset)
   {
       this.basicSetColorUtil.setBasicColor(graphics, getBasicColor());

       int y = getY();
       
       graphics.drawChars(charArray,
               offset, len,
          x, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          hudGraphicsPosition.getAnchor());

       graphics.drawChars(charArray2,
               offset2, len2,
          x + xOffset, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          hudGraphicsPosition.getAnchor());
   }
   
   public void paint(Graphics graphics, char[] charArray, int offset, int len)
   {
       this.basicSetColorUtil.setBasicColor(graphics, getBasicColor());

       int y = getY();
       
      graphics.drawChars(charArray,
              offset, len,
         x, //getHudGraphicsPosition().getPoint().getX().intValue(),
         y, //getHudGraphicsPosition().getPoint().getY().intValue(),
         hudGraphicsPosition.getAnchor());
   }
   
   protected int offsetY;
   
   public void paint(Graphics graphics, String string)
   {
       this.basicSetColorUtil.setBasicColor(graphics, getBasicColor());

       int y = getY() + offsetY;
       
      graphics.drawString(string,
         x, //getHudGraphicsPosition().getPoint().getX().intValue(),
         y, //getHudGraphicsPosition().getPoint().getY().intValue(),
         hudGraphicsPosition.getAnchor());
   }

   protected HudGraphicsPosition getHudGraphicsPosition()
   {
      return hudGraphicsPosition;
   }

    /**
     * @return the color
     */
    public int getColor()
    {
        return color;
    }

    /**
     * @return the x
     */
    public int getX()
    {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x)
    {
        this.x = x;
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColor()
    {
        return basicColor;
    }

    protected void setY(int y)
    {
        this.y = y;
    }

    protected int getY()
    {
        return y;
    }
   
}
