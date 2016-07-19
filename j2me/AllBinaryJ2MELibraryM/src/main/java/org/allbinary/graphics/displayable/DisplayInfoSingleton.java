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
package org.allbinary.graphics.displayable;

import javax.microedition.lcdui.Displayable;

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.graphics.SpacialStrings;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;

public class DisplayInfoSingleton {

    private static final DisplayInfoSingleton SINGLETON = new DisplayInfoSingleton();

    public final String ORIENTATION = "ORIENTATION";

    private int[] last = new int[2];
    private int[] lastHalf = new int[2];
    private int[] full = new int[2];

    private int top;
    private int left;

    private int scaleLargestTo = 720;

    public final int WIDTH = 0;
    public final int HEIGHT = 1;

    public static final DisplayInfoSingleton getInstance() {
        return SINGLETON;
    }

    public int[] getLastHalf() {
        return lastHalf;
    }

    public int[] getLast() {
        return last;
    }

    /**
     * @return the fullWidth
     */
    public int[] getFull() {
        return full;
    }

    /**
     * @return the scaleLargestTo
     */
    public int getScaleLargestTo()
    {
        return scaleLargestTo;
    }

    /**
     * @param scaleLargestTo the scaleLargestTo to set
     */
    public void setScaleLargestTo(int scaleLargestTo)
    {
        this.scaleLargestTo = scaleLargestTo;
    }
    
    public void setLastSize(int aLastWidth, int aFullWidth,
            int aLastHeight, int aFullHeight, String reason) {
        
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + reason, this, "setLastSize"));

            LogUtil.put(LogFactory.getInstance(
                    new StringBuilder()
                            .append(" aLastWidth: ").append(aLastWidth)
                            .append(" aLastHeight: ").append(aLastHeight)
                            .append(" aFullWidth: ").append(aFullWidth)
                            .append(" aFullHeight: ").append(aFullHeight)
                            .append(this.toString())
                            .toString(), this, "setLastSize"));
        
        if (this.last[WIDTH] != aLastWidth || this.last[HEIGHT] != aLastHeight ||
                this.full[WIDTH] != aFullWidth || this.full[HEIGHT] != aFullHeight) {
            
            LogUtil.put(LogFactory.getInstance("Changing", this, "setLastSize"));
            
            OperatingSystemInterface operatingSystemInterface
                    = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

            if (operatingSystemInterface.isOverScan()) {
                aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
                aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;                
            }

            /*
            if(this.isPortrait(aFullWidth, aFullHeight))
            {
                if(this.full[HEIGHT] > scaleLargestTo)
                {
                    LogUtil.put(LogFactory.getInstance("Adjusting for Scaling", this, "setLast"));
                    final float displayRatio = scaleLargestTo / aFullWidth;
                    aLastWidth = (int) (aLastWidth * displayRatio);
                    aLastHeight = (int) (aLastHeight * displayRatio);
                }
            }else
            {
                if(this.full[WIDTH] > scaleLargestTo)
                {
                    LogUtil.put(LogFactory.getInstance("Adjusting for Scaling", this, "setLast"));
                    final float displayRatio = scaleLargestTo / aFullHeight;
                    aLastWidth = (int) (aLastWidth * displayRatio);
                    aLastHeight = (int) (aLastHeight * displayRatio);
                }
            }
            */

            LogUtil.put(LogFactory.getInstance(
                    new StringBuilder()
                            .append("aLastWidth: ").append(aLastWidth)
                            .append(" aLastHeight: ").append(aLastHeight)
                            .toString(), this, "setLastSize"));
            
            this.left = (aFullWidth - aLastWidth) >> 1;
            this.top = (aFullHeight - aLastHeight) >> 1;

            this.full[WIDTH] = aFullWidth;
            this.full[HEIGHT] = aFullHeight;
            
            last[WIDTH] = aLastWidth;
            lastHalf[WIDTH] = (last[WIDTH] >> 1);
            last[HEIGHT] = aLastHeight;
            lastHalf[HEIGHT] = (last[HEIGHT] >> 1);

            this.fire("setLast");
        }
    }

    public boolean isPortrait(
            int lastWidth, int lastHeight) {
        if (lastHeight > lastWidth) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPortrait() {
        return this.isPortrait(this.last[WIDTH], this.last[HEIGHT]);
    }

    private final DisplayChangeEvent displayChangeEvent = new DisplayChangeEvent(this);

    private final String FIRE_METHOD_NAME = "fire";
    
    private void fire(String reason) {
        try {
            LogUtil.put(LogFactory.getInstance("reason: " + reason, this, FIRE_METHOD_NAME));
            LogUtil.put(LogFactory.getInstance(this.toString(), this, FIRE_METHOD_NAME));
            //PreLogUtil.put("Display Change Event" + this.toString(), this, CommonStrings.getInstance().UPDATE);
            DisplayChangeEventHandler.getInstance().fireEvent(displayChangeEvent);
        } catch (Exception e) {
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, FIRE_METHOD_NAME, e);
        }
    }

    public void update(Displayable displayable, String reason) {
        int aLastWidth = displayable.getWidth();
        int aLastHeight = displayable.getHeight();
        int aFullWidth = this.full[WIDTH];
        int aFullHeight = this.full[HEIGHT];
        
        LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START_LABEL + reason, this, "update"));

                LogUtil.put(LogFactory.getInstance(
                        new StringBuilder()
                        .append("aLastWidth: ").append(aLastWidth)
                        .append(" aLastHeight: ").append(aLastHeight)
                        .append(" aFullWidth: ").append(aFullWidth)
                        .append(" aFullHeight: ").append(aFullHeight)
                        .append(this.toString())
                        .toString(), this, "update"));
        
        if(aLastWidth > 0 && aLastHeight > 0)
        {
            //The getters fire and set on change by calling the setters of this class
            if(this.last[WIDTH] != aLastWidth || this.last[HEIGHT] != aLastHeight
                    || this.full[WIDTH] != aFullWidth || this.full[HEIGHT] != aFullHeight)
            {

                LogUtil.put(LogFactory.getInstance(
                        new StringBuilder().append("Updating from Orientation Change")
                        .toString(), this, "update"));

                OperatingSystemInterface operatingSystemInterface
                        = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

                if(operatingSystemInterface.isOverScan())
                {
                    aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
                    aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;
                }

                /*
                 if(operatingSystemInterface.isScalable())
                 {
                 if(this.isPortrait(aFullWidth, aFullHeight))
                 {
                 if(aFullWidth > scaleLargestTo)
                 {
                 LogUtil.put(LogFactory.getInstance("Adjusting for Scaling", this, "setLast"));
                 final float displayRatio = scaleLargestTo / aFullWidth;
                 aLastWidth = (int) (aLastWidth * displayRatio);
                 aLastHeight = (int) (aLastHeight * displayRatio);
                 }
                 }else
                 {
                 if(aFullWidth > scaleLargestTo)
                 {
                 LogUtil.put(LogFactory.getInstance("Adjusting for Scaling", this, "setLast"));
                 final float displayRatio = scaleLargestTo / aFullHeight;
                 aLastWidth = (int) (aLastWidth * displayRatio);
                 aLastHeight = (int) (aLastHeight * displayRatio);
                 }
                 }
                 }
                 */
                LogUtil.put(LogFactory.getInstance(
                        new StringBuilder().append("Updating from Orientation Change -")
                        .append(" aLastWidth: ").append(aLastWidth)
                        .append(" aLastHeight: ").append(aLastHeight)
                        .toString(), this, "update"));

                this.left = (this.full[WIDTH] - aLastWidth) >> 1;
                this.top = (this.full[HEIGHT] - aLastHeight) >> 1;

                this.full[HEIGHT] = this.nextFullHeight;
                this.full[WIDTH] = this.nextFullWidth;

                last[WIDTH] = aLastWidth;
                lastHalf[WIDTH] = (last[WIDTH] >> 1);

                last[HEIGHT] = aLastHeight;
                lastHalf[HEIGHT] = (last[HEIGHT] >> 1);

                this.fire(CommonStrings.getInstance().UPDATE);
            }
        }
    }
    
    private final String DISPLAY_INFO = "Display Info: ";
    private final String FULL = "full";
    private final String LAST = "last";
    private final String LAST_HALF = "lastHalf";

    public String toString() {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(DISPLAY_INFO);
        stringBuffer.append(FULL);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(full[WIDTH]);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(FULL);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(full[HEIGHT]);
        stringBuffer.append(LAST);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(last[WIDTH]);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(LAST);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(last[HEIGHT]);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(LAST_HALF);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(lastHalf[WIDTH]);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(LAST_HALF);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(lastHalf[HEIGHT]);

        return stringBuffer.toString();
    }

    //Replace the methods below at some point with Object versions
    /**
     * @return the top
     */
    public int getTop() {
        return top;
    }

    /**
     * @return the left
     */
    public int getLeft() {
        return left;
    }

    public int getLastWidth()
    {
        return this.last[WIDTH];
    }

    public int getLastHeight()
    {
        return this.last[HEIGHT];
    }
    
    public int getLastHalfWidth()
    {
        return this.lastHalf[WIDTH];
    }

    public int getLastHalfHeight()
    {
        return this.lastHalf[HEIGHT];
    }
    
    private int nextFullWidth;
    private int nextFullHeight;
    public void setNewFull(int fullWidth, int fullHeight)
    {
        LogUtil.put(LogFactory.getInstance(new StringBuilder().append("fullWidth: ").append(fullWidth).append(" fullHeight: ").append(fullHeight).toString(), this, "setNewFull"));
        this.nextFullWidth = fullWidth;
        this.nextFullHeight = fullHeight;
    }
    
}
