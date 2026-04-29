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

import org.allbinary.AndroidUtil;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.graphics.displayable.event.LastDisplayChangeEventHandler;
import org.allbinary.graphics.threed.SWTJOGLProcessor;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.os.GenericOperatingSystem;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.string.CommonStrings;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

public class DisplayInfoSingleton
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final DisplayInfoSingleton SINGLETON = new DisplayInfoSingleton();
    
    public static final DisplayInfoSingleton getInstance()
    {
        return DisplayInfoSingleton.SINGLETON;
    }

    private final CommonLabels commonLabels = CommonLabels.getInstance();
    private final CommonSeps commonSeps = CommonSeps.getInstance();
    private final CommonStrings commonStrings = CommonStrings.getInstance();
    
    public final String ORIENTATION = "ORIENTATION";
    public final String ADJUSTING_FOR_SCALING_IN_PORTRAIT = "Adjusting for Scaling in portrait display ratio: ";
    public final String ADJUSTING_FOR_SCALING_IN_LANDSCAPE = "Adjusting for Scaling in landscape display ratio: ";
    
    private int[] last = new int[4];
    private int[] lastHalf = new int[4];
    private int[] full = new int[4];
    
    private int top;
    private int left;
    private int xOffset;
    private int yOffset;

    private float scaleLargestTo;
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
    public final int CUSTOM_WIDTH = 2;
    public final int CUSTOM_HEIGHT = 3;

    private BaseScalable scalableListener = new BaseScalable();
    
    private float displayRatio;
    private float ratio = 1.0f;

    private DisplayInfoSingleton() {
        if(AndroidUtil.isAndroid()) {
            this.scaleLargestTo = (float) 640;
        } else {
            this.scaleLargestTo = (float) 1080;
        }
    }
    
    public int[] getLastHalf()
    {
        return this.lastHalf;
    }

    public int[] getLast()
    {
        return this.last;
    }

    /**
     * @return the fullWidth
     */
    public int[] getFull()
    {
        return this.full;
    }

    /**
     * @return the scaleLargestTo
     */
    public float getScaleLargestTo()
    {
        return this.scaleLargestTo;
    }

    /**
     * @param scaleLargestTo the scaleLargestTo to set
     */
    public void setScaleLargestTo(final int scaleLargestTo)
    {
        this.scaleLargestTo = (float) scaleLargestTo;
    }

    private final String SET_LAST_SIZE_METHOD_NAME = "setLastSize";
    private final String FULL_WIDTH = " FullWidth: ";
    private final String FULL_HEIGHT = " FullHeight: ";
    private final String LAST_WIDTH = "LastWidth: ";
    private final String LAST_HEIGHT = " LastHeight: ";
    
    public void setLastSize(final int aLastWidth, final int aLastHeight, final String reason)
    {
        if(this.full[this.WIDTH] != aLastWidth || this.full[this.HEIGHT] != aLastHeight)
        {
            this.setLastSizeForce(aLastWidth, aLastHeight, reason);
        }
    }

    private void setLastSizeForce(int aLastWidth, int aLastHeight, final String reason) {            
        final StringMaker stringMaker = new StringMaker();
        this.logUtil.putF(stringMaker.append(CommonLabels.getInstance().START_LABEL).append(reason).toString(), this, this.SET_LAST_SIZE_METHOD_NAME);

        final int aFullWidth = aLastWidth;
        final int aFullHeight = aLastHeight;

        stringMaker.delete(0, stringMaker.length());
        this.logUtil.putF(stringMaker
            .append(this.FULL_WIDTH).appendint(aFullWidth)
            .append(this.FULL_HEIGHT).appendint(aFullHeight)
            .append(this.toString())
            .toString(), this, this.SET_LAST_SIZE_METHOD_NAME);

        //this.logUtil.putF("Changing", this, SET_LAST_SIZE_METHOD_NAME);
        final GenericOperatingSystem operatingSystemInterface
            = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

        if (operatingSystemInterface.isOverScan()) {
            aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
            aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;
        }

        if (operatingSystemInterface.isScalable()) {
            if (this.isPortraitWH(aLastWidth, aLastHeight)) {
                if (aLastHeight > this.scaleLargestTo) {
                    this.displayRatio = this.scaleLargestTo / aLastHeight;
                    this.ratio = aLastHeight / this.scaleLargestTo;
                    stringMaker.delete(0, stringMaker.length());
                    this.logUtil.putF(stringMaker.append(this.ADJUSTING_FOR_SCALING_IN_PORTRAIT).appendfloat(this.displayRatio).toString(), this, this.SET_LAST_SIZE_METHOD_NAME);
                    aLastWidth = (int) (aLastWidth * this.displayRatio);
                    aLastHeight = (int) (aLastHeight * this.displayRatio);
                    this.scalableListener.scale((float) this.ratio);
                } else {
                    //this.logUtil.putF("Reset scaling", this, commonStrings.UPDATE);
                    this.ratio = 1.0f;
                    this.scalableListener.scale((float) this.ratio);
                }
            } else {
                if (aLastWidth > this.scaleLargestTo) {
                    this.displayRatio = this.scaleLargestTo / aLastWidth;
                    this.ratio = aLastWidth / this.scaleLargestTo;
                    stringMaker.delete(0, stringMaker.length());
                    this.logUtil.putF(stringMaker.append(this.ADJUSTING_FOR_SCALING_IN_LANDSCAPE).appendfloat(this.displayRatio).toString(), this, this.SET_LAST_SIZE_METHOD_NAME);
                    aLastWidth = (int) (aLastWidth * this.displayRatio);
                    aLastHeight = (int) (aLastHeight * this.displayRatio);
                    this.scalableListener.scale((float) this.ratio);
                } else {
                    //this.logUtil.putF("Reset scaling", this, commonStrings.UPDATE);
                    this.ratio = 1.0f;
                    this.scalableListener.scale((float) this.ratio);
                }
            }
        }

        stringMaker.delete(0, stringMaker.length());
        this.logUtil.putF(stringMaker
            .append(this.LAST_WIDTH).appendint(aLastWidth)
            .append(this.LAST_HEIGHT).appendint(aLastHeight)
            .toString(), this, this.SET_LAST_SIZE_METHOD_NAME);

        this.xOffset = aFullWidth - aLastWidth;
        this.yOffset = aFullHeight - aLastHeight;

//            stringMaker.delete(0, stringMaker.length());
//            this.logUtil.putF(stringMaker
//                    .append("xOffset: ").append(this.xOffset)
//                    .append(" yOffset: ").append(this.yOffset)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME);
        this.left = this.scalableListener.getLeft(this.xOffset);
        this.top = this.scalableListener.getTop(this.yOffset);

//            stringMaker.delete(0, stringMaker.length());
//            this.logUtil.putF(stringMaker
//                    .append("left: ").append(this.left)
//                    .append(" top: ").append(this.top)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME);
        this.full[this.WIDTH] = aFullWidth;
        this.full[this.HEIGHT] = aFullHeight;

        this.last[this.WIDTH] = aLastWidth;
        this.lastHalf[this.WIDTH] = (this.last[this.WIDTH] >> 1);
        this.last[this.HEIGHT] = aLastHeight;
        this.lastHalf[this.HEIGHT] = (this.last[this.HEIGHT] >> 1);
        
        SWTJOGLProcessor.getInstance().setCustom(aLastWidth, aLastHeight, this.ratio);
        
        this.add(this.SET_LAST_SIZE_METHOD_NAME);
    }
    
    public boolean isPortraitWH(final int lastWidth, final int lastHeight)
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
        return this.isPortraitWH(this.last[this.WIDTH], this.last[this.HEIGHT]);
    }

    public final DisplayChangeEvent displayChangeEvent = new DisplayChangeEvent(this);

    private final String FIRE_METHOD_NAME = "fire";
    private final String REASON = "reason: ";
    private final String UPDATE_FROM_ORIENTATION_CHANGE = "Updating from Orientation Change ";

    private final BasicArrayList list = new BasicArrayListD();

    public void add(final String reason)
    {
        //PreLogUtil.putF("Display Change Event").append(this.toString(), this, commonStrings.UPDATE);
        final StringMaker stringMaker = new StringMaker();
        this.logUtil.putF(stringMaker.append(this.REASON).append(reason).toString(), this, this.FIRE_METHOD_NAME);
        stringMaker.delete(0, stringMaker.length());
        this.logUtil.putF(this.toStringAppend(stringMaker), this, this.FIRE_METHOD_NAME);
        this.list.add(reason);
    }

    public void process() {
        try
        {
            if(this.list.size() > 0) {
                this.processForced();
            }
            this.list.clear();
        }catch(Exception e)
        {
            PreLogUtil.putOE(this.commonStrings.EXCEPTION, this, this.FIRE_METHOD_NAME, e);
        }
    }

    public void processForced() {
        try
        {
            final SWTJOGLProcessor swtJOGLProcessor = SWTJOGLProcessor.getInstance();
            swtJOGLProcessor.clear();
            DisplayChangeEventHandler.getInstance().fireEvent(this.displayChangeEvent);
            LastDisplayChangeEventHandler.getInstance().fireEvent(this.displayChangeEvent);
            swtJOGLProcessor.onSurfaceChanged();
        }catch(Exception e)
        {
            PreLogUtil.putOE(this.commonStrings.EXCEPTION, this, this.FIRE_METHOD_NAME, e);
        }
        
    }
    
    public void update(final Displayable displayable, final String reason)
    {
        int aLastWidth = displayable.getWidth();
        int aLastHeight = displayable.getHeight();

        final int aFullWidth = aLastWidth;
        final int aFullHeight = aLastHeight;
        
        final StringMaker stringMaker = new StringMaker();
        this.logUtil.putF(stringMaker
                .append(CommonLabels.getInstance().START_LABEL).append(reason)
                .append(this.LAST_WIDTH).appendint(aLastWidth)
                .append(this.LAST_HEIGHT).appendint(aLastHeight)
                .append(this.commonSeps.SPACE)
                .append(this.toString())
                .toString(), this, this.commonStrings.UPDATE);

        if(aLastWidth > 0 && aLastHeight > 0)
        {
            //The getters fire and set on change by calling the setters of this class
            //This does not actually get called often as the displayable width and height are usually the last value
            //Not really sure if this ever happens?
            if(this.last[this.WIDTH] != aLastWidth || this.last[this.HEIGHT] != aLastHeight)
            {
                stringMaker.delete(0, stringMaker.length());        
                this.logUtil.putF(stringMaker.append(this.UPDATE_FROM_ORIENTATION_CHANGE).toString(), this, this.commonStrings.UPDATE);

                final GenericOperatingSystem operatingSystemInterface
                        = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

                if(operatingSystemInterface.isOverScan())
                {
                    aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
                    aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;
                }

                if(operatingSystemInterface.isScalable())
                {
                    //this.logUtil.putF("Found Scalable OS", this, commonStrings.UPDATE);
                    
                    if(this.isPortraitWH(aLastWidth, aLastHeight))
                    {
                        //this.logUtil.putF("Found Portrait Orientation", this, commonStrings.UPDATE);
                        
                        if(aLastHeight > this.scaleLargestTo)
                        {
                            this.displayRatio = this.scaleLargestTo / aLastHeight;
                            this.ratio = aLastHeight / this.scaleLargestTo;
       
                            stringMaker.delete(0, stringMaker.length()); 
                            this.logUtil.putF(stringMaker.append(this.ADJUSTING_FOR_SCALING_IN_PORTRAIT).appendfloat(this.displayRatio).toString(), this, this.commonStrings.UPDATE);
                            aLastWidth = (int) (aLastWidth * this.displayRatio);
                            aLastHeight = (int) (aLastHeight * this.displayRatio);
                            this.scalableListener.scale((float) this.ratio);
                        } else {
                            //this.logUtil.putF("Reset scaling", this, commonStrings.UPDATE);
                            this.ratio = 1.0f;
                            this.scalableListener.scale((float) this.ratio);
                        }
                    }else
                    {
                        this.logUtil.putF("Found Landscape Orientation", this, this.commonStrings.UPDATE);
                        
                        if(aLastWidth > this.scaleLargestTo)
                        {   
                            this.displayRatio = this.scaleLargestTo / aLastWidth;
                            this.ratio = aLastWidth / this.scaleLargestTo;
                            
                            stringMaker.delete(0, stringMaker.length());
                            this.logUtil.putF(stringMaker.append(this.ADJUSTING_FOR_SCALING_IN_LANDSCAPE).appendfloat(this.displayRatio).toString(), this, this.commonStrings.UPDATE);
                            aLastWidth = (int) (aLastWidth * this.displayRatio);
                            aLastHeight = (int) (aLastHeight * this.displayRatio);
                            this.scalableListener.scale((float) this.ratio);
                        } else {
                            //this.logUtil.putF("Reset scaling", this, commonStrings.UPDATE);
                            this.ratio = 1.0f;
                            this.scalableListener.scale((float) this.ratio);
                        }
                    }
                }

                stringMaker.delete(0, stringMaker.length());        
                this.logUtil.putF(
                        stringMaker.append(this.UPDATE_FROM_ORIENTATION_CHANGE)
                        .append(this.LAST_WIDTH).appendint(aLastWidth)
                        .append(this.LAST_HEIGHT).appendint(aLastHeight)
                        .toString(), this, this.commonStrings.UPDATE);

                this.xOffset = aFullWidth - aLastWidth;
                this.yOffset = aFullHeight - aLastHeight;
                
//                stringMaker.delete(0, stringMaker.length());
//                this.logUtil.putF(stringMaker
//                    .append("xOffset: ").append(this.xOffset)
//                    .append(" yOffset: ").append(this.yOffset)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME);
            
                this.left = this.scalableListener.getLeft(this.xOffset);
                this.top = this.scalableListener.getTop(this.yOffset);

//                stringMaker.delete(0, stringMaker.length());
//                this.logUtil.putF(stringMaker
//                    .append("left: ").append(this.left)
//                    .append(" top: ").append(this.top)
//                    .toString(), this, SET_LAST_SIZE_METHOD_NAME);

                this.full[this.WIDTH] = aFullWidth;
                this.full[this.HEIGHT] = aFullHeight;
                
                this.last[this.WIDTH] = aLastWidth;
                this.lastHalf[this.WIDTH] = (this.last[this.WIDTH] >> 1);

                this.last[this.HEIGHT] = aLastHeight;
                this.lastHalf[this.HEIGHT] = (this.last[this.HEIGHT] >> 1);

                SWTJOGLProcessor.getInstance().setCustom(aLastWidth, aLastHeight, this.ratio);
                
                this.add(this.commonStrings.UPDATE);
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
        return this.toStringAppend(stringBuffer);
    }
    
    public String toStringAppend(final StringMaker stringBuffer)
    {
        stringBuffer.append(this.DISPLAY_INFO);
        stringBuffer.append(this.FULL);
        stringBuffer.append(this.commonLabels.WIDTH_LABEL);
        stringBuffer.appendint(this.full[this.WIDTH]);
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.FULL);
        stringBuffer.append(this.commonLabels.HEIGHT_LABEL);
        stringBuffer.appendint(this.full[this.HEIGHT]);
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.LAST);
        stringBuffer.append(this.commonLabels.WIDTH_LABEL);
        stringBuffer.appendint(this.last[this.WIDTH]);
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.LAST);
        stringBuffer.append(this.commonLabels.HEIGHT_LABEL);
        stringBuffer.appendint(this.last[this.HEIGHT]);
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.LAST_HALF);
        stringBuffer.append(this.commonLabels.WIDTH_LABEL);
        stringBuffer.appendint(this.lastHalf[this.WIDTH]);
        stringBuffer.append(this.commonSeps.SPACE);
        stringBuffer.append(this.LAST_HALF);
        stringBuffer.append(this.commonLabels.HEIGHT_LABEL);
        stringBuffer.appendint(this.lastHalf[this.HEIGHT]);

        return stringBuffer.toString();
    }

    //Replace the methods below at some point with Object versions
    /**
     * @return the top
     */
    public int getTop()
    {
        return this.top;
    }

    /**
     * @return the left
     */
    public int getLeft()
    {
        return this.left;
    }

    public int getLastWidth()
    {
        return this.last[this.WIDTH];
    }

    public int getLastHeight()
    {
        return this.last[this.HEIGHT];
    }

    public int getLastHalfWidth()
    {
        return this.lastHalf[this.WIDTH];
    }

    public int getLastHalfHeight()
    {
        return this.lastHalf[this.HEIGHT];
    }

    public int getCustomLastWidth()
    {
        return this.last[this.CUSTOM_WIDTH];
    }

    public int getCustomLastHeight()
    {
        return this.last[this.CUSTOM_HEIGHT];
    }
    
    public int getRawLastWidth()
    {
        return this.last[this.WIDTH];
    }

    public int getRawLastHeight()
    {
        return this.last[this.HEIGHT];
    }
    
    public void setCustom(final int width, final int height) {

        this.last[this.CUSTOM_WIDTH] = width;
        this.lastHalf[this.CUSTOM_WIDTH] = (this.last[this.CUSTOM_WIDTH] >> 1);
        this.last[this.CUSTOM_HEIGHT] = height;
        this.lastHalf[this.CUSTOM_HEIGHT] = (this.last[this.CUSTOM_HEIGHT] >> 1);
    }
    
    public void setScalableListener(BaseScalable scalableListener)
    {
        this.scalableListener = scalableListener;
    }

    public ScalableListener getScalableListener() {
        return this.scalableListener;
    }

    /**
     * @return the ratio
     */
    public float getRatio()
    {
        return this.ratio;
    }

    /**
     * @return the displayRatio
     */
    public float getDisplayRatio()
    {
        return this.displayRatio;
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
        return this.xOffset;
    }

    /**
     * @return the yOffset
     */
    public int getyOffset()
    {
        return this.yOffset;
    }
}
