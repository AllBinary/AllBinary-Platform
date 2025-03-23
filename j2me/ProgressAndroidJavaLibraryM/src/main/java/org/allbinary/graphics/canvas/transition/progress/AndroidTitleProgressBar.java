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

import org.allbinary.android.activity.ProgressActivityInterface;
import org.allbinary.android.activity.SimpleProgressActivityInterface;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.graphics.color.BasicColor;

public class AndroidTitleProgressBar extends ProgressCanvas
{
    private ShowTitleProgressBarRunnable showTitleProgressBarRunnable = new ShowTitleProgressBarRunnable();

    private DismissTitleProgressBarRunnable dismissTitleProgressBarRunnable = new DismissTitleProgressBarRunnable();

    private TitleProgressBarPortionSetProgressRunnable progressDialogPortionSetProgressRunnable = new TitleProgressBarPortionSetProgressRunnable();

    private TitleProgressBarSetProgressRunnable progressDialogSetProgressRunnable = new TitleProgressBarSetProgressRunnable();

    //private MidletActivity midletActivity;
    private ProgressActivityInterface progressActivity;

    private int portion = 0;

    protected AndroidTitleProgressBar(String title, 
            BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        super(title, backgroundBasicColor, foregroundBasicColor);
    }

    public void init(SimpleProgressActivityInterface activity)
    {
      //  this.midletActivity = activity;
    }

    public void init(ProgressActivityInterface activity)
    {
        this.progressActivity = activity;
    }

    public boolean isInitialized()
    {
        if (this.progressActivity != null)// && this.midletActivity != null)
            return true;
        else
            return false;
    }

    public void start()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().START_METHOD_NAME));
            super.start();
            // this.midletActivity.startProgressActivity();
            this.progressActivity.runOnUiThread(showTitleProgressBarRunnable);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().START_METHOD_NAME, e));
        }

    }

    public void end()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, CommonStrings.getInstance().END_METHOD_NAME));
            this.progressActivity
                    .runOnUiThread(dismissTitleProgressBarRunnable);
            // this.progressActivity = null;
            super.end();
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().END_METHOD_NAME, e));
        }

    }

    public void addPortion(int value, String text, int index)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, ADD_PORTION));

            super.addPortion(value, text, index);
            this.portion = value;
            this.progressActivity.runOnUiThread(progressDialogPortionSetProgressRunnable);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, ADD_PORTION, e));
        }
    }
    
    public void addPortion(int value, String text)
    {
        try
        {
            //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, this, ADD_PORTION));

            super.addPortion(value, text);
            this.portion = value;
            this.progressActivity.runOnUiThread(progressDialogPortionSetProgressRunnable);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, ADD_PORTION, e));
        }
    }

    protected void setValue(int value)
    {
        try
        {
            super.setValue(value);

            this.progressActivity.runOnUiThread(progressDialogSetProgressRunnable);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "setValue", e));
        }

    }

    public void waitUntilDisplayed()
    {

    }

    public void paint(Graphics graphics)
    {
        // super.paint(graphics);
        // this.setDisplayed(true);
    }

    class TitleProgressBarSetProgressRunnable implements Runnable
    {
        public void run()
        {
            try
            {
                AndroidTitleProgressBar.this.progressActivity
                        .onTitleProgressBarSetProgress((int) AndroidTitleProgressBar.this
                                .getValue());
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
            }
        }
    }

    class TitleProgressBarPortionSetProgressRunnable implements Runnable
    {
        public void run()
        {
            try
            {
                AndroidTitleProgressBar.this.progressActivity
                        .onTitleProgressBarSetProgress((int) (AndroidTitleProgressBar.this
                                .getValue()
                                + AndroidTitleProgressBar.this.getMaxValue()
                                / portion));
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
            }
        }
    }

    class ShowTitleProgressBarRunnable implements Runnable
    {
        public void run()
        {
            try
            {
                AndroidTitleProgressBar.this.progressActivity
                        .onShowTitleProgressBar(
                                (int) AndroidTitleProgressBar.this.getMaxValue(), false);
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
            }
        }
    }

    class DismissTitleProgressBarRunnable implements Runnable
    {
        public void run()
        {
            try
            {
                AndroidTitleProgressBar.this.progressActivity
                        .onDismissTitleProgressBar();
            }
            catch (Exception e)
            {
                LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().RUN, e));
            }
        }
    }

}
