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

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;

import org.allbinary.graphics.form.item.CustomGaugeItem;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.midlet.AllBinaryMidlet;

public class ProgressCanvas extends RunnableCanvas
    implements PaintableInterface
{
    protected AllBinaryMidlet allbinaryMidlet;

    private float value;
    private final float maxValue = 100;

    protected final CustomGaugeItem gauge;

    private final String TEXT = commonStrings.LOADING;
    private String text = TEXT;

    private boolean background = true;
    
    protected ProgressCanvas()
    {
        gauge = null;
    }
    
    protected ProgressCanvas(String title, BasicColor backgroundBasicColor, BasicColor foregroundBasicColor)
    {
        this.gauge = new CustomGaugeItem(StringUtil.getInstance().EMPTY_STRING, (int) maxValue, 0, 
                backgroundBasicColor, foregroundBasicColor);
    }

    public void init(AllBinaryMidlet gameMidlet)
    {
        this.allbinaryMidlet = gameMidlet;
    }
    
    public void update(Graphics graphics) throws Exception
    {
        
    }

    /*
    public boolean isInitialized()
    {
        if (this.allbinaryMidlet != null)
            return true;
        else
            return false;
    }
    */

    public void initCommands(CommandListener cmdListener)
    {
        // this.removeAllCommands();
        // this.addCommand(MYCOMMANDS.EXITCOMMAND);
        // this.addCommand(SELECTCOMMAND);
        // this.setCommandListener(cmdListener);
    }

    /*
     * public boolean isDone() { if (this.PROGRESS_SCREEN.getValue() <
     * this.PROGRESS_SCREEN .getMaxValue()) { return false; } else { return
     * true; } }
     */

    protected final float getMaxValue()
    {
        return this.maxValue;
    }

    public void start()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.START_METHOD_NAME));
        this.setBackground(true);
        this.gauge.setHeight(30);
        this.gauge.setLabel(commonStrings.PLEASE_WAIT);
        this.setText(TEXT);
        this.setValue(0);
        //this.setDisplayed(false);
    }
    
    private final MyFont myFont = MyFont.getInstance();
    
    private final String backgroundLabel = "Background AI Game Loading...";
    
    public void startBackground(boolean background)
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "startBackground"));
        this.setBackground(background);
        this.gauge.setHeight(myFont.DEFAULT_CHAR_HEIGHT + 2);
        this.gauge.setLabel(backgroundLabel);
        this.setText(TEXT);
        this.setValue(0);
    }
    
    public void endActual() {
        //getCommandListener()
        this.allbinaryMidlet.commandAction(GameCommandsFactory.getInstance().SHOW_GAME_CANVAS, null);
    }
        
    public void end()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.END_METHOD_NAME));
        this.gauge.setValue(this.getMaxValue());
        this.endActual();
    }

    public void endFromInitialLazyLoadingComplete()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, commonStrings.END_METHOD_NAME));
        this.gauge.setValue(this.getMaxValue());
    }
    
    protected final String ADD_PORTION = "addPortion";
    protected final String ADD_EARLY_PORTION = "addEarlyPortion";
    
    public void addEarlyPortion(int value, String text, int index)
    {
        //LogUtil.put(LogFactory.getInstance(this.text, this, ADD_EARLY_PORTION));
        //PreLogUtil.put(this.text, this, ADD_PORTION);
        
        this.setText(new StringMaker().append(text).append(SmallIntegerSingletonFactory.getInstance().getInstance(index)).toString());

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);
    }
    
    public void addPortion(int value, String text, int index)
    {
        this.setText(new StringMaker().append(text).append(SmallIntegerSingletonFactory.getInstance().getInstance(index)).toString());
        
        //commonStrings.START_LABEL).append(
        //LogUtil.put(LogFactory.getInstance(this.text, this, ADD_PORTION));
        PreLogUtil.put(this.text, this, ADD_PORTION);

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);

        //For Help calculating portion time and such
        //BaseRefreshHelper.process();
    }
    
    public void addPortion(int value, String text)
    {
        this.setText(text);
        
        //commonStrings.START_LABEL).append(
        //LogUtil.put(LogFactory.getInstance(text, this, ADD_PORTION));
        PreLogUtil.put(this.text, this, ADD_PORTION);

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);

        //For Help calculating portion time and such
        //BaseRefreshHelper.process();
    }

    protected void setValue(int value)
    {
        this.value = value;
        this.gauge.setValue(value);
    }

    public void paint(Graphics graphics)
    {
        // LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "paint"));
     
        this.gauge.paint(graphics, 0, 0);
    }

    public void paintThreed(Graphics graphics)
    {
    	
    }
    /*
    public void waitUntilDisplayed()
    {
        try
        {
            int index = 0;
            while ((!this.isDisplayed()) && index < 50)
            {
                LogUtil.put(LogFactory.getInstance(
                        "Waiting for it to be displayed", this,
                        "waitUntilDisplayed"));
                Thread.sleep(200);
                index++;
            }
            LogUtil.put(LogFactory.getInstance("Displayed", this,
                    "waitUntilDisplayed"));

        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this,
                    "waitUntilDisplayed", e));
        }
    }
    */

    protected float getValue()
    {
        return value;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
    
    protected void setBackground(boolean background)
    {
        this.background = background;
    }

    protected boolean isBackground()
    {
        return background;
    }
}
