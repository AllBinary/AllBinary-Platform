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
package org.allbinary.graphics.canvas.transition.progress;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import org.allbinary.image.GameFeatureImageCacheFactory;
import org.allbinary.image.PreResourceImageUtil;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.ForcedLogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.exception.ExceptionUtil;
import org.allbinary.animation.Animation;
import org.allbinary.animation.NullAnimationFactory;

import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.MainFeatureFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;
import org.allbinary.graphics.displayable.event.DisplayChangeEventListener;
import org.allbinary.image.ImageCacheFactory;
import org.allbinary.media.image.ImageScaleUtil;
import org.allbinary.logic.util.event.AllBinaryEventObject;
import android.app.Activity;
import org.allbinary.animation.AnimationBehavior;
import org.allbinary.animation.image.ImageAnimation;
import org.allbinary.data.resource.ResourceUtil;
import org.allbinary.logic.util.event.EventStrings;

public class AndroidBasicTitleProgressBar 
extends ProgressCanvas
implements DisplayChangeEventListener
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    public static final String RESOURCE = "ProgressImage";
    
    private ShowTitleProgressBarRunnable showTitleProgressBarRunnable;

    private DismissTitleProgressBarRunnable dismissTitleProgressBarRunnable;

    private TitleProgressBarPortionSetProgressRunnable progressDialogPortionSetProgressRunnable;

    private TitleProgressBarSetProgressRunnable progressDialogSetProgressRunnable;

    //SimpleProgressActivityInterface
    private Activity midletActivity;

    private int portion = 0;

    private Image[] IMAGE = new Image[4];

    private Image image;

    private Animation animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
    
    private static int background;

    // private boolean painted = false;;
    
    protected AndroidBasicTitleProgressBar(String title, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(title, backgroundBasicColor, foregroundBasicColor);
        this.init();
        
        DisplayChangeEventHandler.getInstance().addListener(this);
    }

    private void init()
    {
        try
        {
            if (AndroidBasicTitleProgressBar.background != 0)
            {
                ResourceUtil.getInstance().addResource(RESOURCE,
                        new Integer(AndroidBasicTitleProgressBar.background));

                GameFeatureImageCacheFactory.init();

                this.image = ImageCacheFactory.getInstance().get(RESOURCE);
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        }
    }

    public void init(Activity activity)
    {
        try
        {
            if (this.midletActivity != activity)
            {
                this.midletActivity = activity;

                this.showTitleProgressBarRunnable = new ShowTitleProgressBarRunnable(
                        this.midletActivity, this);

                this.dismissTitleProgressBarRunnable = new DismissTitleProgressBarRunnable(
                        this.midletActivity, this);

                this.progressDialogSetProgressRunnable = new TitleProgressBarSetProgressRunnable(
                        this.midletActivity, this);

                this.progressDialogPortionSetProgressRunnable = new TitleProgressBarPortionSetProgressRunnable(
                        this.midletActivity, this);

                this.loadProgressImages();

            }

            this.updateCurrent();

        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.INIT, e);
        }
    }

    private void updateCurrent()
    {
        try
        {
            if(!this.isBackground())
            {
                this.animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
            }
            else
            if (AndroidBasicTitleProgressBar.background != 0)
            {
                DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

                Image currentImage;

                if (displayInfo.isPortrait())
                {
                    currentImage = this.getImage(0);
                }
                else
                {
                    currentImage = this.getImage(2);
                }

                if (currentImage == null)
                {
                    this.animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
                }
                else
                {
                    this.animation = new ImageAnimation(currentImage, AnimationBehavior.getInstance());
                }
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e);
        }
    }

    public void onEvent(AllBinaryEventObject eventObject)
    {
        ForcedLogUtil.log(EventStrings.getInstance().PERFORMANCE_MESSAGE, this);
    }

    public void onDisplayChangeEvent(DisplayChangeEvent displayChangeEvent)
    {
        try
        {
            this.loadProgressImages();
            this.updateCurrent();
        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION_LABEL + ExceptionUtil.getInstance().getStackTrace(e), this, this.canvasStrings.ON_DISPLAY_CHANGE_EVENT);
            this.animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        }
    }

    private void loadProgressImages()
    {
        try
        {
            DisplayInfoSingleton displayInfo = DisplayInfoSingleton
                    .getInstance();

            int lastWidth = displayInfo.getLastWidth();
            int lastHeight = displayInfo.getLastHeight();

            if (displayInfo.isPortrait(lastWidth, lastHeight))
            {
                this.setImages(0, lastWidth, lastHeight);
            }
            else
            {
                this.setImages(2, lastWidth, lastHeight);
            }

        }
        catch (IllegalArgumentException e)
        {
            logUtil.put(
                    "IllegalArgumentException "
                            + ExceptionUtil.getInstance().getStackTrace(e), this,
                    "loadProgressImages");
            this.animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        }
        catch (Exception e)
        {
            logUtil.put(
                    commonStrings.EXCEPTION_LABEL
                            + ExceptionUtil.getInstance().getStackTrace(e), this,
                    "loadProgressImages");
            this.animation = NullAnimationFactory.getFactoryInstance().getInstance(0);
        }
    }
    
    public boolean isInitialized()
    {
        if (this.midletActivity != null)// && this.midletActivity != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void start()
    {
        try
        {
            logUtil.put(commonStrings.START, this, commonStrings.START_METHOD_NAME);

            super.start();
            this.midletActivity.runOnUiThread(showTitleProgressBarRunnable);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.START_METHOD_NAME, e);
        }
    }

    public void end()
    {
        try
        {
            logUtil.put(commonStrings.START, this, commonStrings.END_METHOD_NAME);
            this.midletActivity.runOnUiThread(dismissTitleProgressBarRunnable);
            super.end();
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.END_METHOD_NAME, e);
        }

    }

    public void addEarlyPortion(int value, String text, int index)
    {
        try
        {
            // logUtil.put(commonStrings.START, this, ADD_PORTION);
            this.portion = value;
            super.addEarlyPortion(value, text, index);

            if(this.midletActivity != null)
            {
                this.midletActivity.runOnUiThread(progressDialogPortionSetProgressRunnable);
            }
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, ADD_PORTION, e);
        }

    }
    
    public void addPortion(int value, String text, int index)
    {
        try
        {
            // logUtil.put(commonStrings.START, this, ADD_PORTION);
            this.portion = value;
            super.addPortion(value, text, index);

            this.midletActivity.runOnUiThread(progressDialogPortionSetProgressRunnable);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, ADD_PORTION, e);
        }

    }
    
    public void addPortion(int value, String text)
    {
        try
        {
            // logUtil.put(commonStrings.START, this, ADD_PORTION);
            this.portion = value;
            super.addPortion(value, text);

            this.midletActivity.runOnUiThread(progressDialogPortionSetProgressRunnable);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, ADD_PORTION, e);
        }

    }

    protected void setValue(int value)
    {
        try
        {
            super.setValue(value);

            this.midletActivity.runOnUiThread(progressDialogSetProgressRunnable);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, "setValue", e);
        }

    }

    public void waitUntilDisplayed()
    {

    }

    
    //If portrait to landscape where to change during this call before 
    //I made added the dimension as params it could throw and IllegalArgument Exceptoin
    private void setImages(int index, int lastWidth, int lastHeight)
    throws Exception
    {
        if (this.image != null)
        {
            if (Features.getInstance().isFeature(
                    MainFeatureFactory.getInstance().FULL_SCREEN))
            {
                if (this.IMAGE[index] == null)
                {
                    this.IMAGE[index] = ImageScaleUtil.getInstance().createImage(
                            ImageCacheFactory.getInstance(), this.image,
                            lastWidth, this.image.getWidth(), lastHeight - 20,
                            this.image.getHeight(), false);
                }
            }
            else
            {
                int nextIndex = index + 1;
                if (this.IMAGE[nextIndex] == null)
                {
                    this.IMAGE[nextIndex] = ImageScaleUtil.getInstance().createImage(
                            ImageCacheFactory.getInstance(), this.image,
                            lastWidth, this.image.getWidth(), lastHeight - 28,
                            this.image.getHeight(), false);
                }
            }
        }
    }

    public void initOpenGL(Graphics graphics) throws Exception
    {
        logUtil.put(commonStrings.START, this, commonStrings.INIT);
        
        this.image = GameFeatureImageCacheFactory.getInstance().get(RESOURCE);
        
        PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();

        int size = this.IMAGE.length;
        for(int index = 0; index < size; index++)
        {
            if(this.IMAGE[index] != null)
            {
                this.IMAGE[index] = preResourceImageUtil.encapsulate(this.IMAGE[index]);
            }
        }
        
        this.updateCurrent();
    }

    public void update(Graphics graphics) throws Exception
    {
        logUtil.put(commonStrings.START, this, commonStrings.UPDATE);
        
        this.initOpenGL(graphics);
        
        this.image = GameFeatureImageCacheFactory.getInstance().get(RESOURCE);
        
        PreResourceImageUtil preResourceImageUtil = PreResourceImageUtil.getInstance();
        
        int size = this.IMAGE.length;
        for(int index = 0; index < size; index++)
        {
            if(this.IMAGE[index] != null)
            {
                preResourceImageUtil.update(graphics, this.IMAGE[index]);
            }
        }
    }
    
    private Image getImage(int index) throws Exception
    {
        Image image = null;

        if (Features.getInstance().isFeature(MainFeatureFactory.getInstance().FULL_SCREEN))
        {     
            image = this.IMAGE[index];
        }
        else
        {
            image = this.IMAGE[index + 1];
        }

        return image;
    }
    
    public void paint2(Graphics graphics)
    {
        try
        {
            //logUtil.put(StringUtil.getInstance().EMPTY_STRING, this, canvasStrings.PAINT);
            
            // Only show background when not loading in the background
            animation.paint(graphics, 0, 20);

            super.paint2(graphics);
        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, canvasStrings.PAINT, e);
        }
    }

    public static void setBackground(int background)
    {
        AndroidBasicTitleProgressBar.background = background;
    }

    protected void setBackground(boolean background)
    {
        super.setBackground(background);
        
        this.updateCurrent();
    }
    
    protected void setPortion(int portion)
    {
        this.portion = portion;
    }

    protected int getPortion()
    {
        return portion;
    }
}
