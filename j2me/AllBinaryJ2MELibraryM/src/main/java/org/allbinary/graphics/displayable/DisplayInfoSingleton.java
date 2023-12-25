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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.graphics.SpacialStrings;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.logic.string.CommonLabels;
import org.allbinary.util.BasicArrayList;

public class DisplayInfoSingleton
{

    private static final DisplayInfoSingleton SINGLETON = new DisplayInfoSingleton();
    
    public static final DisplayInfoSingleton getInstance()
    {
        return SINGLETON;
    }

    private final SpacialStrings spacialStrings = SpacialStrings.getInstance();
    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final String ORIENTATION = "ORIENTATION";
    public final String ADJUSTING_FOR_SCALING_IN_PORTRAIT = "Adjusting for Scaling in portrait display ratio: ";
    public final String ADJUSTING_FOR_SCALING_IN_LANDSCAPE = "Adjusting for Scaling in landscape display ratio: ";
    
    private int[] last = new int[2];
    private int[] lastHalf = new int[2];
    private int[] full = new int[2];

    private int top;
    private int left;
    private int xOffset;
    private int yOffset;

    private float scaleLargestTo = 640;
    //640
    /*
    Test Android Scaling resolutions
        284
        320
        480
        720
        1024
        1920
        2550
        3000
        4000
    */
    
    public final int WIDTH = 0;
    public final int HEIGHT = 1;

    private BaseScalable scalableListener = new BaseScalable();
    
    private float displayRatio;
    private float ratio;

    public int[] getLastHalf()
    {
        return lastHalf;
    }

    public int[] getLast()
    {
        return last;
    }

    /**
     * @return the fullWidth
     */
    public int[] getFull()
    {
        return full;
    }

    /**
     * @return the scaleLargestTo
     */
    public float getScaleLargestTo()
    {
        return scaleLargestTo;
    }

    /**
     * @param scaleLargestTo the scaleLargestTo to set
     */
    public void setScaleLargestTo(final int scaleLargestTo)
    {
        this.scaleLargestTo = scaleLargestTo;
    }

    private final String SET_LAST_SIZE_METHOD_NAME = "setLastSize";
    
    public void setLastSize(int aLastWidth, int aLastHeight, final String reason)
    {
        if(this.full[WIDTH] != aLastWidth || this.full[HEIGHT] != aLastHeight)
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append(CommonLabels.getInstance().START_LABEL).append(reason).toString(), this, SET_LAST_SIZE_METHOD_NAME));
            
            final int aFullWidth = aLastWidth;
            final int aFullHeight = aLastHeight;
        
            LogUtil.put(LogFactory.getInstance(new StringMaker()
                    .append(" aFullWidth: ").append(aFullWidth)
                    .append(" aFullHeight: ").append(aFullHeight)
                    .append(this.toString())
                    .toString(), this, SET_LAST_SIZE_METHOD_NAME));

            //LogUtil.put(LogFactory.getInstance("Changing", this, SET_LAST_SIZE_METHOD_NAME));

            final OperatingSystemInterface operatingSystemInterface
                    = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

            if(operatingSystemInterface.isOverScan())
            {
                aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
                aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;
            }

            if(operatingSystemInterface.isScalable())
            {                
                if(this.isPortrait(aLastWidth, aLastHeight))
                {
                    if(aLastHeight > scaleLargestTo)
                    {
                        this.displayRatio = scaleLargestTo / aLastHeight;
                        this.ratio = aLastHeight / scaleLargestTo;
                        LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.ADJUSTING_FOR_SCALING_IN_PORTRAIT).append(displayRatio).toString(), this, SET_LAST_SIZE_METHOD_NAME));
                        aLastWidth = (int) (aLastWidth * displayRatio);
                        aLastHeight = (int) (aLastHeight * displayRatio);
                        this.scalableListener.scale(ratio);
                    }
                }else
                {
                    if(aLastWidth > scaleLargestTo)
                    {
                        this.displayRatio = scaleLargestTo / aLastWidth;
                        this.ratio = aLastWidth / scaleLargestTo;
                        LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.ADJUSTING_FOR_SCALING_IN_LANDSCAPE).append(displayRatio).toString(), this, SET_LAST_SIZE_METHOD_NAME));
                        aLastWidth = (int) (aLastWidth * displayRatio);
                        aLastHeight = (int) (aLastHeight * displayRatio);
                        this.scalableListener.scale(ratio);
                    }                    
                }
            }

            LogUtil.put(LogFactory.getInstance(new StringMaker()
                    .append("aLastWidth: ").append(aLastWidth)
                    .append(" aLastHeight: ").append(aLastHeight)
                    .toString(), this, SET_LAST_SIZE_METHOD_NAME));

            this.xOffset = aFullWidth - aLastWidth;
            this.yOffset = aFullHeight - aLastHeight;
            
//            LogUtil.put(LogFactory.getInstance(new StringMaker()
//                    .append("xOffset: ").append(this.xOffset)
//                    .append(" yOffset: ").append(this.yOffset)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME));
            
            this.left = this.scalableListener.getLeft(this.xOffset);
            this.top = this.scalableListener.getTop(this.yOffset);

//            LogUtil.put(LogFactory.getInstance(new StringMaker()
//                    .append("left: ").append(this.left)
//                    .append(" top: ").append(this.top)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME));
            
            this.full[WIDTH] = aFullWidth;
            this.full[HEIGHT] = aFullHeight;

            last[WIDTH] = aLastWidth;
            lastHalf[WIDTH] = (last[WIDTH] >> 1);
            last[HEIGHT] = aLastHeight;
            lastHalf[HEIGHT] = (last[HEIGHT] >> 1);

            this.add(SET_LAST_SIZE_METHOD_NAME);
        }
    }

    public boolean isPortrait(
            int lastWidth, int lastHeight)
    {
        if(lastHeight > lastWidth)
        {
            return true;
        }else
        {
            return false;
        }
    }

    public boolean isPortrait()
    {
        return this.isPortrait(this.last[WIDTH], this.last[HEIGHT]);
    }

    private final DisplayChangeEvent displayChangeEvent = new DisplayChangeEvent(this);

    private final String FIRE_METHOD_NAME = "fire";

    private final BasicArrayList list = new BasicArrayList();

    private void add(final String reason)
    {
        //PreLogUtil.put("Display Change Event").append(this.toString(), this, commonStrings.UPDATE);
        LogUtil.put(LogFactory.getInstance(new StringMaker().append("reason: ").append(reason).toString(), this, FIRE_METHOD_NAME));
        LogUtil.put(LogFactory.getInstance(this.toString(), this, FIRE_METHOD_NAME));
        list.add(reason);
    }

    public void process() {
        try
        {
            if(list.size() > 0) {
                DisplayChangeEventHandler.getInstance().fireEvent(displayChangeEvent);
            }
            list.clear();
        }catch(Exception e)
        {
            PreLogUtil.put(commonStrings.EXCEPTION, this, FIRE_METHOD_NAME, e);
        }
    }
    
    public void update(final Displayable displayable, final String reason)
    {
        int aLastWidth = displayable.getWidth();
        int aLastHeight = displayable.getHeight();

        final int aFullWidth = aLastWidth;
        final int aFullHeight = aLastHeight;
        
        LogUtil.put(LogFactory.getInstance(new StringMaker()
                .append(CommonLabels.getInstance().START_LABEL).append(reason)
                .append(" aLastWidth: ").append(aLastWidth)
                .append(" aLastHeight: ").append(aLastHeight)
                .append(commonSeps.SPACE)
                .append(this.toString())
                .toString(), this, commonStrings.UPDATE));

        if(aLastWidth > 0 && aLastHeight > 0)
        {
            //The getters fire and set on change by calling the setters of this class
            //This does not actually get called often as the displayable width and height are usually the last value
            //Not really sure if this ever happens?
            if(this.last[WIDTH] != aLastWidth || this.last[HEIGHT] != aLastHeight)
            {
                LogUtil.put(LogFactory.getInstance(
                        new StringMaker().append("Updating from Orientation Change")
                        .toString(), this, commonStrings.UPDATE));

                final OperatingSystemInterface operatingSystemInterface
                        = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

                if(operatingSystemInterface.isOverScan())
                {
                    aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
                    aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;
                }

                if(operatingSystemInterface.isScalable())
                {
                    if(this.isPortrait(aLastWidth, aLastHeight))
                    {
                        if(aLastHeight > scaleLargestTo)
                        {
                            this.displayRatio = scaleLargestTo / aLastHeight;
                            this.ratio = aLastHeight / scaleLargestTo;
                            LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.ADJUSTING_FOR_SCALING_IN_PORTRAIT).append(displayRatio).toString(), this, commonStrings.UPDATE));
                            aLastWidth = (int) (aLastWidth * displayRatio);
                            aLastHeight = (int) (aLastHeight * displayRatio);
                            this.scalableListener.scale(ratio);
                        }
                    }else
                    {
                        if(aLastWidth > scaleLargestTo)
                        {
                            this.displayRatio = scaleLargestTo / aLastWidth;
                            this.ratio = aLastWidth / scaleLargestTo;
                            LogUtil.put(LogFactory.getInstance(new StringMaker().append(this.ADJUSTING_FOR_SCALING_IN_LANDSCAPE).append(displayRatio).toString(), this, commonStrings.UPDATE));
                            aLastWidth = (int) (aLastWidth * displayRatio);
                            aLastHeight = (int) (aLastHeight * displayRatio);
                            this.scalableListener.scale(ratio);
                        }
                    }
                }

                LogUtil.put(LogFactory.getInstance(
                        new StringMaker().append("Updating from Orientation Change -")
                        .append(" aLastWidth: ").append(aLastWidth)
                        .append(" aLastHeight: ").append(aLastHeight)
                        .toString(), this, commonStrings.UPDATE));

                this.xOffset = aFullWidth - aLastWidth;
                this.yOffset = aFullHeight - aLastHeight;
                
//                LogUtil.put(LogFactory.getInstance(new StringMaker()
//                    .append("xOffset: ").append(this.xOffset)
//                    .append(" yOffset: ").append(this.yOffset)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME));
            
                this.left = this.scalableListener.getLeft(this.xOffset);
                this.top = this.scalableListener.getTop(this.yOffset);

//                LogUtil.put(LogFactory.getInstance(new StringMaker()
//                    .append("left: ").append(this.left)
//                    .append(" top: ").append(this.top)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME));

                this.full[WIDTH] = aFullWidth;
                this.full[HEIGHT] = aFullHeight;
                
                last[WIDTH] = aLastWidth;
                lastHalf[WIDTH] = (last[WIDTH] >> 1);

                last[HEIGHT] = aLastHeight;
                lastHalf[HEIGHT] = (last[HEIGHT] >> 1);

                this.add(commonStrings.UPDATE);
                return;
            }
        }
    }

    private final String DISPLAY_INFO = "Display Info: ";
    private final String FULL = "full";
    private final String LAST = "last";
    private final String LAST_HALF = "lastHalf";

    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(DISPLAY_INFO);
        stringBuffer.append(FULL);
        stringBuffer.append(spacialStrings.WIDTH_LABEL);
        stringBuffer.append(full[WIDTH]);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(FULL);
        stringBuffer.append(spacialStrings.HEIGHT_LABEL);
        stringBuffer.append(full[HEIGHT]);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(LAST);
        stringBuffer.append(spacialStrings.WIDTH_LABEL);
        stringBuffer.append(last[WIDTH]);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(LAST);
        stringBuffer.append(spacialStrings.HEIGHT_LABEL);
        stringBuffer.append(last[HEIGHT]);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(LAST_HALF);
        stringBuffer.append(spacialStrings.WIDTH_LABEL);
        stringBuffer.append(lastHalf[WIDTH]);
        stringBuffer.append(commonSeps.SPACE);
        stringBuffer.append(LAST_HALF);
        stringBuffer.append(spacialStrings.HEIGHT_LABEL);
        stringBuffer.append(lastHalf[HEIGHT]);

        return stringBuffer.toString();
    }

    //Replace the methods below at some point with Object versions
    /**
     * @return the top
     */
    public int getTop()
    {
        return top;
    }

    /**
     * @return the left
     */
    public int getLeft()
    {
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

    public void setScalableListener(BaseScalable scalableListener)
    {
        this.scalableListener = scalableListener;
    }

    public ScalableListener getScalableListener() {
        return scalableListener;
    }

    /**
     * @return the ratio
     */
    public float getRatio()
    {
        return ratio;
    }

    /**
     * @return the displayRatio
     */
    public float getDisplayRatio()
    {
        return displayRatio;
    }
    
    public void setOffset(int left, int top)
    {
        this.left = left;
    }

    /**
     * @return the xOffset
     */
    public int getxOffset()
    {
        return xOffset;
    }

    /**
     * @return the yOffset
     */
    public int getyOffset()
    {
        return yOffset;
    }
}
