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
package allbinary.game.score.displayable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.commands.GameCommandsFactory;
import allbinary.game.score.HighScore;
import allbinary.game.score.HighScoreNamePersistanceSingleton;
import allbinary.game.score.HighScores;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.color.BasicColorFactory;
import allbinary.graphics.paint.NullPaintable;
import allbinary.graphics.paint.Paintable;
import allbinary.graphics.paint.SimpleTextPaintable;
import allbinary.input.event.VirtualKeyboardEventHandler;

public class HighScoreTextBox extends CustomTextBox
// BasicTextBox
{
    public static final Command SUBMIT_TEXTBOX_COMMAND = 
        new Command("Submit", Command.SCREEN, 1);

    private final HighScores[] highScoresArray;
    private final HighScore highScore;

    private final Paintable pleaseWait = 
        new SimpleTextPaintable(CommonStrings.getInstance().PLEASE_WAIT, BasicColorFactory.getInstance().WHITE);
    
    private Paintable paintable = NullPaintable.getInstance();

    public HighScoreTextBox(CommandListener cmdListener, String name,
            HighScores[] highScoresArray, HighScore highScore,
            BasicColor backgrounBasicColor, BasicColor foregroundBasicColor) throws Exception
    {
        super(cmdListener, "New High Score Enter Name:", name, 12, TextField.ANY, 
                backgrounBasicColor, foregroundBasicColor);

        // LogUtil.put(LogFactory.getInstance("Score: " + score, this,
        // "compare"));

        this.highScoresArray = highScoresArray;
        this.highScore = highScore;
    }

    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        this.addCommand(GameCommandsFactory.getInstance().TOGGLE_KEYBOARD);
        this.addCommand(SUBMIT_TEXTBOX_COMMAND);

        this.setCommandListener(cmdListener);
    }

    public void open()
    {
        try
        {
            VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                VirtualKeyboardEventHandler.getInstance();

            virtualKeyboardEventHandler.fireEvent(
                    virtualKeyboardEventHandler.SHOW_EVENT);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "open", e));
        }

        this.paintable = NullPaintable.getInstance();
        super.open();
    }

    public void close() throws Exception
    {
        try
        {
            VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                VirtualKeyboardEventHandler.getInstance();
            
            virtualKeyboardEventHandler.fireEvent(virtualKeyboardEventHandler.HIDE_EVENT);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, "open", e));
        }

        this.paintable = this.pleaseWait;
        this.repaint();

        super.close();
        this.removeCommand(HighScoreTextBox.SUBMIT_TEXTBOX_COMMAND);
        this.saveHighScore();

        this.paintable = NullPaintable.getInstance();
        this.repaint();
    }

    private void update()
    {
        String name = this.getTextFieldItem().getString();
        HighScoreNamePersistanceSingleton.getInstance().save(name);
        this.highScore.setName(name);
    }

    public void saveHighScore()
    {
        this.update();

        for (int index = 0; index < this.highScoresArray.length; index++)
        {
            highScoresArray[index].add(this.highScore);
        }
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        this.paintable.paint(graphics);
    }
    
    public void submit()
    {
        CommandListener commandListener = 
            this.getCustomCommandListener();
        
        commandListener.commandAction(
                SUBMIT_TEXTBOX_COMMAND, this);
    }
}