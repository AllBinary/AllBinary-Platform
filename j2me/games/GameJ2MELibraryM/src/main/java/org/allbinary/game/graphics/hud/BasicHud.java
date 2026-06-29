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

import org.allbinary.J2MEUtil;
import org.allbinary.graphics.Anchor;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.color.BasicColorSetUtil;
import org.allbinary.graphics.displayable.CanvasStrings;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.font.MyFontProcessor;
import org.allbinary.graphics.font.UpdateMyFontInterface;
import org.allbinary.graphics.font.UpdateMyFontProcessor;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import org.allbinary.logic.util.event.EventStrings;
import org.allbinary.string.CommonStrings;

public class BasicHud implements UpdateMyFontInterface //DisplayChangeEventListener
{

    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final CommonStrings commonStrings = CommonStrings.getInstance();
    protected final CanvasStrings canvasStrings = CanvasStrings.getInstance();
    protected final BasicColorSetUtil basicSetColorUtil = BasicColorSetUtil.getInstance();

    private final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

    protected final MyFontProcessor updateMyFontProcessor = new UpdateMyFontProcessor(this);
    protected MyFontProcessor myFontProcessor = updateMyFontProcessor;

    private int location;
    private int direction;

    private int bufferZone;
    private int bufferZoneY;

    private HudGraphicsPosition hudGraphicsPosition = HudGraphicsPosition.NULL_HUD_GRAPHICS_POSITION;

    private int x;
    private int y;

    private BasicColor basicColor = BasicColorFactory.getInstance().BLACK;
    private int color;
    protected int updateMaxWidth;
    protected int updateMaxHeight;

    protected int offsetY;
    
    public BasicHud(final int location, final int direction, final int bufferZone, final BasicColor basicColor) {

        this.setLocation(location);
        this.setDirection(direction);
        this.setBufferZone(bufferZone);

        this.onDisplayChangeEvent(DisplayInfoSingleton.getInstance().displayChangeEvent);

        this.setBasicColorP(basicColor);
        this.color = basicColor.intValue();
    }

    @Override
    public void updateMeasurement(final Graphics graphics) {
        try {

//            this.logUtil.putF(new StringMaker().append(this.commonStrings.START).append(this.canvasStrings.FD_WIDTH).appendint(this.updateMaxWidth).append(this.canvasStrings.FD_HEIGHT).appendint(this.updateMaxHeight).toString(), this, this.canvasStrings.UPDATE_MEASUREMENT);

            this.hudGraphicsPosition = this.getHudGraphicsPositionWH(
                this.displayInfo.getLastWidth(), this.displayInfo.getLastHeight(), this.updateMaxWidth, this.updateMaxHeight);

            this.x = this.hudGraphicsPosition.getPoint().getX();
            this.setY(this.hudGraphicsPosition.getPoint().getY());
        } catch (Exception e) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT, e);
        }

        this.myFontProcessor = MyFontProcessor.getInstance();
    }
    
    public int getLocation() {
        return this.location;
    }

    public int getDirection() {
        return this.direction;
    }

    /*
   public HudGraphicsPosition getHudGraphicsPosition(Graphics graphics)
           throws Exception
   {
        return this.getHudGraphicsPosition(graphics.getClipWidth(), graphics.getClipHeight());
        return this.getHudGraphicsPosition(this.displayInfoSingleton.getLastWidth(), this.displayInfoSingleton.getLastHeight());

   }
     */
    protected HudGraphicsPosition getHudGraphicsPositionWH(final int width, final int height, final int maxWidth, final int maxHeight)
        throws Exception {
        
        int x = 0;
        int y = 0;
        int anchor = 0;

        final BasicHudFactory basicHudFactory = BasicHudFactory.getInstance();

        if (basicHudFactory.BOTTOMLEFT == this.getLocation()) {
            x = this.bufferZone + 2;
            y = height - maxHeight - this.bufferZone;
            anchor = Graphics.BOTTOM & Graphics.LEFT;
        } else if (basicHudFactory.BOTTOMRIGHT == this.getLocation()) {
            x = width - maxWidth;
            y = height - maxHeight - this.bufferZone;
            anchor = Graphics.BOTTOM & Graphics.RIGHT;
        } else if (basicHudFactory.TOPLEFT == this.getLocation()) {
            x = this.bufferZone + 2;
            y = this.bufferZoneY;
            anchor = Anchor.TOP_LEFT;
        } else if (basicHudFactory.TOPRIGHT == this.getLocation()) {
            x = width - maxWidth;
            y = this.bufferZoneY;
            anchor = Graphics.TOP & Graphics.RIGHT;
        } else if (basicHudFactory.TOPCENTER == this.getLocation()) {
            x = ((width - maxWidth) / 2);
            y = this.bufferZoneY;
            anchor = Graphics.TOP & Graphics.HCENTER;
        } else if (basicHudFactory.BOTTOMCENTER == this.getLocation()) {
            x = ((width - maxWidth) / 2);
            y = height - maxHeight - this.bufferZone;
            anchor = Graphics.BOTTOM & Graphics.HCENTER;
        } else if (basicHudFactory.ABSOLUTE == this.getLocation()) {
            x = maxHeight;
            y = maxWidth;
            anchor = 0;
        }

        return new HudGraphicsPosition(this.getPoint(x, y), anchor);
    }

    public void onEvent(final AllBinaryEventObject eventObject) {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    public void onDisplayChangeEvent(final DisplayChangeEvent displayChangeEvent) {
        
            //this.logUtil.putF(this.commonStrings.START, this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);
//            this.logUtil.putF(new StringMaker().append(this.commonStrings.START).append(DisplayInfoSingleton.getInstance().toString()).toString(), this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);
        
        this.myFontProcessor = this.updateMyFontProcessor;
    }

    protected GPoint getPoint(final int x, final int y) {
        return PointFactory.getInstance().createXY(x, y);
    }

    public int getBufferZone() {
        return this.bufferZone;
    }

    public void setBufferZone(final int bufferZone) {
        this.bufferZone = bufferZone;

        if (J2MEUtil.isHTML()) {
            this.bufferZoneY = this.bufferZone;
        } else {
            this.bufferZoneY = this.bufferZone + 5;
        }
    }

    public void setLocation(final int location) {
        this.location = location;
    }

    public void setDirection(final int direction) {
        this.direction = direction;
    }

    protected HudGraphicsPosition getHudGraphicsPosition() {
        return this.hudGraphicsPosition;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return this.color;
    }

    /**
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @param x the x to set
     */
    public void setX(final int x) {
        this.x = x;
    }

    public void setBasicColorP(final BasicColor basicColor) {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColorP() {
        return this.basicColor;
    }

    protected void setY(final int y) {
        this.y = y;
    }

    protected int getY() {
        return this.y;
    }

    public void paintSSO(final Graphics graphics, final String string, final String string2, final int offset) {
        
        this.myFontProcessor.process(graphics);
        
        this.basicSetColorUtil.setBasicColorP(graphics, this.getBasicColorP());

        graphics.drawString(string,
            this.x, //getHudGraphicsPosition().getPoint().getX().intValue(),
            this.getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
            this.hudGraphicsPosition.getAnchor());
        graphics.drawString(string2,
            this.x + offset, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
            this.getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
            this.hudGraphicsPosition.getAnchor());
    }

    public void paintSSOO(final Graphics graphics, final String string, final String string2, final int offset, final int offset2) {
        
        this.myFontProcessor.process(graphics);
        
        graphics.setColor(this.getColor());
        graphics.drawString(string,
            this.x + offset, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
            this.getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
            this.hudGraphicsPosition.getAnchor());
        graphics.drawString(string2,
            this.x + offset2, //getHudGraphicsPosition().getPoint().getX().intValue() + offset,
            this.getY(), //getHudGraphicsPosition().getPoint().getY().intValue(),
            this.hudGraphicsPosition.getAnchor());
    }

    public void paintDXY(final Graphics graphics,
        final char[] charArray, final int offset, final int len,
        final char[] charArray2, final int offset2, final int len2,
        final int xOffset, final int xOffset2) {
        
        this.myFontProcessor.process(graphics);
        
        this.basicSetColorUtil.setBasicColorP(graphics, this.getBasicColorP());

        final int y = this.getY();

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

    public void paintDX(final Graphics graphics,
        final char[] charArray, final int offset, final int len,
        final char[] charArray2, final int offset2, final int len2,
        final int xOffset) {
        
        this.myFontProcessor.process(graphics);
        
        this.basicSetColorUtil.setBasicColorP(graphics, this.getBasicColorP());

        final int y = this.getY();

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

    public void paintOffsetAndLength(final Graphics graphics, final char[] charArray, final int offset, final int len) {
        
        this.myFontProcessor.process(graphics);
        
        this.basicSetColorUtil.setBasicColorP(graphics, this.getBasicColorP());

        final int y = this.getY();

        graphics.drawChars(charArray,
            offset, len,
            this.x, //getHudGraphicsPosition().getPoint().getX().intValue(),
            y, //getHudGraphicsPosition().getPoint().getY().intValue(),
            this.hudGraphicsPosition.getAnchor());
    }

    public void paint(final Graphics graphics, final String string) {
        
        this.myFontProcessor.process(graphics);
        
        this.basicSetColorUtil.setBasicColorP(graphics, this.getBasicColorP());

        final int y = this.getY() + this.offsetY;

        graphics.drawString(string,
            this.x, //getHudGraphicsPosition().getPoint().getX().intValue(),
            y, //getHudGraphicsPosition().getPoint().getY().intValue(),
            this.hudGraphicsPosition.getAnchor());
    }

}
