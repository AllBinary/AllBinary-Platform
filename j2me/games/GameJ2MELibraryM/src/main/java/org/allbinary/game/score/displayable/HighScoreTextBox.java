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
package org.allbinary.game.score.displayable;

import org.allbinary.graphics.form.item.CustomTextBox;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.logic.system.security.licensing.InApplicationPurchaseFactory;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.score.HighScore;
import org.allbinary.game.score.HighScoreNamePersistanceSingleton;
import org.allbinary.game.score.HighScores;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.SimpleTextPaintable;
import org.allbinary.input.event.VirtualKeyboardEventHandler;
import org.allbinary.logic.string.StringMaker;

public class HighScoreTextBox extends CustomTextBox
// BasicTextBox
{
    public static final Command SUBMIT_TEXTBOX_COMMAND = 
        new Command("Submit", Command.SCREEN, 1);

    private final HighScores[] highScoresArray;
    private final HighScore highScore;

    private final Paintable pleaseWait = 
        new SimpleTextPaintable(commonStrings.PLEASE_WAIT, BasicColorFactory.getInstance().WHITE);
    
    private Paintable paintable = NullPaintable.getInstance();

    public HighScoreTextBox(final CommandListener cmdListener, final String name,
            final HighScores[] highScoresArray, final HighScore highScore,
            final BasicColor backgrounBasicColor, final BasicColor foregroundBasicColor) throws Exception
    {
        super(cmdListener, "New High Score Enter Name:", name, 12, TextField.ANY, 
                backgrounBasicColor, foregroundBasicColor);

        // LogUtil.put(LogFactory.getInstance("Score: ").append(score, this,
        // "compare"));

        this.highScoresArray = highScoresArray;
        this.highScore = highScore;

        final OperatingSystemInterface operatingSystemInterface
                = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

        if (operatingSystemInterface.isOverScan()) {
            String username = InApplicationPurchaseFactory.getInstance().getUserName();
            if(username != null)
            {
                this.getTextFieldItem().setString(username);
            }
            else
            {
                this.getTextFieldItem().setString("NoGamerInfo");
            }
        }        
    }

    public void initCommands(CommandListener cmdListener)
    {
        this.removeAllCommands();

        final OperatingSystemInterface operatingSystemInterface
                = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

        if (!operatingSystemInterface.isOverScan()) {
            this.addCommand(GameCommandsFactory.getInstance().TOGGLE_KEYBOARD);
        }

        this.addCommand(SUBMIT_TEXTBOX_COMMAND);

        this.setCommandListener(cmdListener);
    }

    public void open()
    {
        try
        {
            final VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                VirtualKeyboardEventHandler.getInstance();

            virtualKeyboardEventHandler.fireEvent(
                    virtualKeyboardEventHandler.SHOW_EVENT);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "open", e));
        }

        this.paintable = NullPaintable.getInstance();
        super.open();
    }

    public void close() throws Exception
    {
        try
        {
            final VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                VirtualKeyboardEventHandler.getInstance();
            
            virtualKeyboardEventHandler.fireEvent(virtualKeyboardEventHandler.HIDE_EVENT);
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "open", e));
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
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "saveHighScore"));
        
        this.update();

        for (int index = 0; index < this.highScoresArray.length; index++)
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Adding HighScore to board: ").append(highScoresArray[index]).toString(), this, "saveHighScore"));
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