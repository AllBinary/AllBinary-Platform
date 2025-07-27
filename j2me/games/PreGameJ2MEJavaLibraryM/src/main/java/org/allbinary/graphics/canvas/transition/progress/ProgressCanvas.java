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
import javax.microedition.lcdui.NullCanvas;

import org.allbinary.canvas.Processor;
import org.allbinary.canvas.RunnableCanvas;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.graphics.font.MyFont;
import org.allbinary.graphics.form.item.CustomGaugeItem;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.PaintableInterface;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.midlet.AllBinaryMidlet;
import org.allbinary.thread.PathFindingThreadPool;
import org.allbinary.thread.ThreadPool;

public class ProgressCanvas extends RunnableCanvas
    implements PaintableInterface
{
    //protected static final String END_FROM_INITIAL_LAZY_LOADING_COMPLETE = "endFromInitialLazyLoadingComplete";
    
    protected boolean hasPainted;
    private final BasicColor backgroundBasicColor;
    
    public final Paintable GAUGE_PAINTABLE = new Paintable() {
        public void paint(Graphics graphics) {
            paint2(graphics);
        }
    };
    
    protected AllBinaryMidlet allbinaryMidlet = AllBinaryMidlet.NULL_ALLBINARY_MIDLET;

    private float value;
    private final float maxValue = 100.0f;

    protected final CustomGaugeItem gauge;

    private final String TEXT = commonStrings.LOADING;
    private String text = TEXT;

    private boolean background = true;
    
    protected PaintableInterface paintable = GAUGE_PAINTABLE;
    
    public boolean inProgress = false;
    
    private final Processor IN_GAME_PROCESSOR = new Processor() {
        
        private final ThreadPool pathFindingThreadPool = PathFindingThreadPool.getInstance();
        
        public void process() throws Exception {
            pathFindingThreadPool.runAPriorityTask();
        }
    };

    public Processor inGameProcessor = Processor.getInstance();
    
    protected ProgressCanvas()
    {
        this.paintable = NullPaintable.getInstance();
        this.backgroundBasicColor = BasicColorFactory.getInstance().WHITE;
        this.gauge = CustomGaugeItem.NULL_GAUGE_ITEM;
    }
    
    protected ProgressCanvas(final String title, final BasicColor backgroundBasicColor, final BasicColor foregroundBasicColor)
    {
        this.backgroundBasicColor = backgroundBasicColor;
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

    @Override
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
        logUtil.put(commonStrings.START, this, commonStrings.START_METHOD_NAME);
        this.setBackground(true);
        this.gauge.setHeight(30);
        this.gauge.setLabel(commonStrings.PLEASE_WAIT);
        this.setText(TEXT);
        this.setValue(0);
        //this.setDisplayed(false);
        this.inGameProcessor = Processor.getInstance();
        this.paintable = GAUGE_PAINTABLE;
        this.inProgress = true;
    }
    
    private final String backgroundLabel = "Background AI Game Loading...";
    private final String START_BACKGROUND = "startBackground";
    
    public void startBackground(boolean background)
    {
        logUtil.put(commonStrings.START, this, START_BACKGROUND);
        final MyFont myFont = MyFont.getInstance();
        this.setBackground(background);
        this.gauge.setHeight(myFont.DEFAULT_CHAR_HEIGHT + 2);
        this.gauge.setLabel(backgroundLabel);
        this.setText(TEXT);
        this.setValue(0);
        this.inGameProcessor = Processor.getInstance();
        this.paintable = GAUGE_PAINTABLE;
    }
    
    public void endActual() {
        //getCommandListener()
        this.allbinaryMidlet.commandAction(GameCommandsFactory.getInstance().SHOW_GAME_CANVAS, NullCanvas.NULL_CANVAS);
        this.inProgress = false;
        this.inGame();
    }

    public void inGame() {
        this.inGameProcessor = IN_GAME_PROCESSOR;
    }

    public void end()
    {
        logUtil.put(commonStrings.START, this, commonStrings.END_METHOD_NAME);
        this.gauge.setValue(this.getMaxValue());
        this.endActual();
        this.paintable = NullPaintable.getInstance();
    }

    public void endFromInitialLazyLoadingComplete()
    {
        //logUtil.put(commonStrings.START, this, END_FROM_INITIAL_LAZY_LOADING_COMPLETE);
        this.gauge.setValue(this.getMaxValue());
        this.inGameProcessor = IN_GAME_PROCESSOR;
    }
    
    public void endIfPaintedSinceStart()
    {
    }
    
    protected final String ADD_PORTION = "addPortion";
    protected final String ADD_EARLY_PORTION = "addEarlyPortion";
    
    public void addEarlyPortion(int value, String text, int index)
    {
        //logUtil.put(this.text, this, ADD_EARLY_PORTION);
        //PreLogUtil.put(this.text, this, ADD_PORTION);
        
        this.setText(new StringMaker().append(text).append(SmallIntegerSingletonFactory.getInstance().getInstance(index).toString()).toString());

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);
    }
    
    public void addPortion(int value, String text, int index)
    {
        this.setText(new StringMaker().append(text).append(SmallIntegerSingletonFactory.getInstance().getInstance(index).toString()).toString());
        
        //commonStrings.START_LABEL).append(
        //logUtil.put(this.text, this, ADD_PORTION);
        PreLogUtil.put(this.text, this, ADD_PORTION);

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);

        //For Help calculating portion time and such
        //BaseRefreshHelper.process();
    }
    
    public void addPortion(int value, String text)
    {   
        //commonStrings.START_LABEL).append(
        //logUtil.put(text, this, ADD_PORTION);
        
        if(this.text != text) {
            PreLogUtil.put(text, this, ADD_PORTION);
        }
        
        this.setText(text);

        this.gauge.setValue(this.gauge.getValue() + this.getMaxValue() / value);

        //For Help calculating portion time and such
        //BaseRefreshHelper.process();
    }

    protected void setValue(int value)
    {
        this.value = (float) value;
        this.gauge.setValue((float) value);
    }

    @Override
    public void paint(Graphics graphics)
    {
        this.paintable.paint(graphics);
    }

    public void paint2(Graphics graphics)
    {
        final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
        graphics.setColor(backgroundBasicColor.intValue());
        graphics.fillRect(0, 0, displayInfoSingleton.getLastWidth(), displayInfoSingleton.getLastHeight());
        gauge.paint(graphics, 0, 0);
        hasPainted = true;
    }

    @Override
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
                logUtil.put(
                        "Waiting for it to be displayed", this,
                        "waitUntilDisplayed");
                Thread.sleep(200);
                index++;
            }
            logUtil.put("Displayed", this,
                    "waitUntilDisplayed");

        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this,
                    "waitUntilDisplayed", e);
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
