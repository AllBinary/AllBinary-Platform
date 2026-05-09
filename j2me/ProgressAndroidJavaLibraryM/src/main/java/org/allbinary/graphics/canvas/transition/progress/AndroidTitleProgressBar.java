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

import org.allbinary.android.activity.NullProgressActivity;
import org.allbinary.android.activity.ProgressActivityInterface;
import org.allbinary.android.activity.SimpleProgressActivityInterface;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.logic.communication.log.LogUtil;

public class AndroidTitleProgressBar extends ProgressCanvas
{

    private ShowTitleProgressBarRunnable showTitleProgressBarRunnable = new ShowTitleProgressBarRunnable();

    private DismissTitleProgressBarRunnable dismissTitleProgressBarRunnable = new DismissTitleProgressBarRunnable();

    private TitleProgressBarPortionSetProgressRunnable progressDialogPortionSetProgressRunnable = new TitleProgressBarPortionSetProgressRunnable();

    private TitleProgressBarSetProgressRunnable progressDialogSetProgressRunnable = new TitleProgressBarSetProgressRunnable();

    //private MidletActivity midletActivity;
    private ProgressActivityInterface progressActivity = NullProgressActivity.NULL_PROGRESS_ACTIVITY;

    private int portion = 0;

    protected AndroidTitleProgressBar(final String title, final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    {
        super(title, backgroundBasicColor, foregroundBasicColor);
    }

//    public void init(SimpleProgressActivityInterface activity)
//    {
//      //  this.midletActivity = activity;
//    }

    public void init(ProgressActivityInterface activity)
    {
        this.progressActivity = activity;
    }

    public boolean isInitialized()
    {
        if (this.progressActivity != NullProgressActivity.NULL_PROGRESS_ACTIVITY) { // && this.midletActivity != null)
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void start()
    {
        try
        {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.START_METHOD_NAME);
            super.start();
            // this.midletActivity.startProgressActivity();
            this.progressActivity.runOnUiThread(this.showTitleProgressBarRunnable);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.START_METHOD_NAME, e);
        }

    }

    @Override
    public void end()
    {
        try
        {
            this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.END_METHOD_NAME);
            this.progressActivity.runOnUiThread(this.dismissTitleProgressBarRunnable);
            // this.progressActivity = null;
            super.end();
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, this.commonStrings.END_METHOD_NAME, e);
        }

    }

    @Override
    public void addPortion(int value, String text, int index)
    {
        try
        {
            //this.logUtil.putF(this.commonStrings.START, this, ADD_PORTION);

            super.addPortion(value, text, index);
            this.portion = value;
            this.progressActivity.runOnUiThread(this.progressDialogPortionSetProgressRunnable);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, ADD_PORTION, e);
        }
    }
    
    @Override
    public void addNormalPortion(int value, String text)
    {
        try
        {
            //this.logUtil.putF(this.commonStrings.START, this, ADD_PORTION);

            super.addNormalPortion(value, text);
            this.portion = value;
            this.progressActivity.runOnUiThread(this.progressDialogPortionSetProgressRunnable);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, ADD_PORTION, e);
        }
    }

    @Override
    protected void setValue(int value)
    {
        try
        {
            super.setValue(value);

            this.progressActivity.runOnUiThread(this.progressDialogSetProgressRunnable);
        }
        catch (Exception e)
        {
            this.logUtil.put(this.commonStrings.EXCEPTION, this, "setValue", e);
        }

    }

    public void waitUntilDisplayed()
    {

    }

    @Override
    public void paint(Graphics graphics)
    {
        // super.paint(graphics);
        // this.setDisplayed(true);
    }

    class TitleProgressBarSetProgressRunnable implements Runnable
    {
        @Override
        public void run()
        {
            final LogUtil logUtil = LogUtil.getInstance();
            try
            {
                final int value = (int) AndroidTitleProgressBar.this.getValue();
                AndroidTitleProgressBar.this.progressActivity.onTitleProgressBarSetProgress(value);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        }
    }

    class TitleProgressBarPortionSetProgressRunnable implements Runnable
    {
        @Override
        public void run()
        {
            final LogUtil logUtil = LogUtil.getInstance();
            try
            {
                final int value = (int) (AndroidTitleProgressBar.this.getValue() + AndroidTitleProgressBar.this.getMaxValue() / portion);
                AndroidTitleProgressBar.this.progressActivity.onTitleProgressBarSetProgress(value);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        }
    }

    class ShowTitleProgressBarRunnable implements Runnable
    {
        @Override
        public void run()
        {
            final LogUtil logUtil = LogUtil.getInstance();
            try
            {
                final int maxValue = (int) AndroidTitleProgressBar.this.getMaxValue();
                AndroidTitleProgressBar.this.progressActivity.onShowTitleProgressBar(maxValue, false);
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        }
    }

    class DismissTitleProgressBarRunnable implements Runnable
    {
        @Override
        public void run()
        {
            final LogUtil logUtil = LogUtil.getInstance();
            try
            {
                AndroidTitleProgressBar.this.progressActivity.onDismissTitleProgressBar();
            }
            catch (Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.RUN, e);
            }
        }
    }

}
