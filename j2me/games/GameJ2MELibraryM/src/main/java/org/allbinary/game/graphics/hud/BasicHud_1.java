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

import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;

public class BasicHud_1 //implements DisplayChangeEventListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    
   private int location;
   private int direction;
   
   private int bufferZone;
   private int maxWidth;
   private int maxHeight;
   
   private HudGraphicsPosition hudGraphicsPosition = HudGraphicsPosition.NULL_HUD_GRAPHICS_POSITION;
   
   private int x;
   private int y;

   private final BasicColorSetUtil basicSetColorUtil = 
       BasicColorSetUtil.getInstance();
   
   private BasicColor basicColor = BasicColorFactory.getInstance().BLACK;
   private int color;

   public BasicHud_1(int location, int direction,
           int maxHeight, int maxWidth, int bufferZone, BasicColor basicColor)
           throws Exception
       {
      this.setLocation(location);
      this.setDirection(direction);
      
      this.setBufferZone(bufferZone);
      this.setMaxWidth(maxWidth);
      this.setMaxHeight(maxHeight);
      
      this.onDisplayChangeEvent(DisplayInfoSingleton.getInstance().displayChangeEvent);
      
      this.setBasicColorP(basicColor);
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

   protected HudGraphicsPosition getHudGraphicsPositionWH(int width, int height)
           throws Exception
   {
      int x = 0;
      int y = 0;
      int anchor = 0;

      BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();
      
      if(basicHudFactory.BOTTOMLEFT == this.getLocation())
      {
         x = this.bufferZone + 2;
         y = height - this.maxHeight - this.bufferZone;
         anchor = Graphics.BOTTOM & Graphics.LEFT;
      }
      else
      if(basicHudFactory.BOTTOMRIGHT == this.getLocation())
      {
         x = width - this.maxWidth;
         y = height - this.maxHeight - this.bufferZone;
         anchor = Graphics.BOTTOM & Graphics.RIGHT;
      }
      else
      if(basicHudFactory.TOPLEFT == this.getLocation())
      {
         x = this.bufferZone + 2;
         y = this.bufferZone + 5;
         anchor = Anchor.TOP_LEFT;
      }
      else
          if(basicHudFactory.TOPRIGHT == this.getLocation())
          {
             x = width - this.maxWidth;
             y = this.bufferZone + 5;
             anchor = Graphics.TOP & Graphics.RIGHT;
          }
          else
              if(basicHudFactory.TOPCENTER == this.getLocation())
              {
                  x = ((width - this.maxWidth) / 2);
                 y = this.bufferZone + 5;
                 anchor = Graphics.TOP & Graphics.HCENTER;
              }
              else
                  if(basicHudFactory.BOTTOMCENTER == this.getLocation())
                  {
                      x = ((width - this.maxWidth) / 2);
                     y = height - this.maxHeight - this.bufferZone;
                     anchor = Graphics.BOTTOM & Graphics.HCENTER;
                  }
                  else
                      if(basicHudFactory.ABSOLUTE == this.getLocation())
                  {
                     x = this.maxHeight;
                     y = this.maxWidth;
                     anchor = 0;
                  }
      
      return new HudGraphicsPosition(this.getPoint(x, y), anchor);
   }

   public void onEvent(final AllBinaryEventObject eventObject)
   {
       ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
   }
   
   public void onDisplayChangeEvent(final DisplayChangeEvent displayChangeEvent)
   {
       try
       {
           //this.logUtil.putF(this.commonStrings.START_LABEL).append(DisplayInfoSingleton.getInstance().toString(), this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);
           
           final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

           this.hudGraphicsPosition = this.getHudGraphicsPositionWH(
                   displayInfo.getLastWidth(), displayInfo.getLastHeight());
           
           this.x = this.hudGraphicsPosition.getPoint().getX();
           this.setY(this.hudGraphicsPosition.getPoint().getY());
       }
       catch(Exception e)
       {
           final CommonStrings commonStrings = CommonStrings.getInstance();
           this.logUtil.put(commonStrings.EXCEPTION, this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT, e);
       }
   }
   
   protected GPoint getPoint(final int x, final int y)
   {
       return PointFactory.getInstance().createXY(x, y);
   }

   public int getBufferZone()
   {
      return this.bufferZone;
   }

   public void setBufferZone(final int bufferZone)
   {
      this.bufferZone = bufferZone;
   }

   public int getMaxWidth()
   {
      return this.maxWidth;
   }

   public void setMaxWidth(final int maxWidth)
   {
      this.maxWidth = maxWidth;
   }

   public int getMaxHeight()
   {
      return this.maxHeight;
   }

   public void setMaxHeight(final int maxHeight)
   {
      this.maxHeight = maxHeight;
   }

   public void setLocation(final int location)
   {
      this.location = location;
   }

   public void setDirection(final int direction)
   {
      this.direction = direction;
   }

   public void paintSSO(final Graphics graphics, final String string, final String string2, final int offset)
   {
       this.basicSetColorUtil.setBasicColorP(graphics, getBasicColorP());

      graphics.drawString(string,
              this.x, //getHudGraphicsPosition().getPoint().getX().intValue(),
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              this.hudGraphicsPosition.getAnchor());
      graphics.drawString(string2,
              this.x + offset, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              this.hudGraphicsPosition.getAnchor());
   }

   public void paintSSOO(Graphics graphics, String string, String string2, int offset, int offset2)
   {
      graphics.setColor(this.getColor());
      graphics.drawString(string,
              this.x + offset, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              this.hudGraphicsPosition.getAnchor());
      graphics.drawString(string2,
              this.x + offset2, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
              getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
              this.hudGraphicsPosition.getAnchor());
   }

   public void paintDXY(Graphics graphics,
                        char[] charArray, int offset, int len,
                        char[] charArray2, int offset2, int len2,
                        int xOffset, int xOffset2)
   {
       this.basicSetColorUtil.setBasicColorP(graphics, getBasicColorP());
       
       int y = this.getY();
       
       graphics.drawChars(charArray,
               offset, len,
          this.x + xOffset, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          this.hudGraphicsPosition.getAnchor());

       graphics.drawChars(charArray2,
               offset2, len2,
          this.x + xOffset2, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          this.hudGraphicsPosition.getAnchor());
   }
   
   public void paintDX(Graphics graphics,
                       char[] charArray, int offset, int len,
                       char[] charArray2, int offset2, int len2,
                       int xOffset)
   {
       this.basicSetColorUtil.setBasicColorP(graphics, getBasicColorP());

       int y = this.getY();
       
       graphics.drawChars(charArray,
               offset, len,
          this.x, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          this.hudGraphicsPosition.getAnchor());

       graphics.drawChars(charArray2,
               offset2, len2,
          this.x + xOffset, //getHudGraphicsPosition().getPoint().getX().intValue(),
          y, //getHudGraphicsPosition().getPoint().getY().intValue(),
          this.hudGraphicsPosition.getAnchor());
   }
   
   public void paintOffsetAndLength(Graphics graphics, char[] charArray, int offset, int len)
   {
       this.basicSetColorUtil.setBasicColorP(graphics, getBasicColorP());

       int y = this.getY();
       
      graphics.drawChars(charArray,
              offset, len,
         this.x, //getHudGraphicsPosition().getPoint().getX().intValue(),
         y, //getHudGraphicsPosition().getPoint().getY().intValue(),
         this.hudGraphicsPosition.getAnchor());
   }
   
   protected int offsetY;
   
   public void paint(Graphics graphics, String string)
   {
       this.basicSetColorUtil.setBasicColorP(graphics, getBasicColorP());

       int y = this.getY() + this.offsetY;
       
      graphics.drawString(string,
         this.x, //getHudGraphicsPosition().getPoint().getX().intValue(),
         y, //getHudGraphicsPosition().getPoint().getY().intValue(),
         this.hudGraphicsPosition.getAnchor());
   }

   protected HudGraphicsPosition getHudGraphicsPosition()
   {
      return this.hudGraphicsPosition;
   }

    /**
     * @return the color
     */
    public int getColor()
    {
        return this.color;
    }

    /**
     * @return the x
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x)
    {
        this.x = x;
    }

    public void setBasicColorP(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColorP()
    {
        return this.basicColor;
    }

    protected void setY(int y)
    {
        this.y = y;
    }

    protected int getY()
    {
        return this.y;
    }
   
}
