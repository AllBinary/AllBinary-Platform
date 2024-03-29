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
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

import org.allbinary.game.GameInfo;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.logic.system.security.licensing.InApplicationPurchaseFactory;
import org.allbinary.game.commands.GameCommandsFactory;
import org.allbinary.game.score.HighScore;
import org.allbinary.game.score.HighScores;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.graphics.paint.NullPaintable;
import org.allbinary.graphics.paint.Paintable;
import org.allbinary.graphics.paint.SimpleTextPaintable;
import org.allbinary.input.event.VirtualKeyboardEventHandler;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class HighScoreTextBox extends CustomTextBox
// BasicTextBox
{
    private final VirtualKeyboardEventHandler virtualKeyboardEventHandler = 
                VirtualKeyboardEventHandler.getInstance();
    
    private final HighScoreUtil highScoreUtil;

    private final Paintable pleaseWait = 
        new SimpleTextPaintable(commonStrings.PLEASE_WAIT, BasicColorFactory.getInstance().WHITE);
    
    private Paintable paintable = NullPaintable.getInstance();

    public HighScoreTextBox(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo, 
        final CommandListener cmdListener, final String name,
        final HighScores[] highScoresArray, final HighScore highScore,
        final BasicColor backgrounBasicColor, final BasicColor foregroundBasicColor) throws Exception
    {
        super(cmdListener, "New High Score Enter Name:", name, 12, TextField.ANY, 
                backgrounBasicColor, foregroundBasicColor);

        highScoreUtil = new HighScoreUtil(abeClientInformation, gameInfo, cmdListener, name, highScoresArray, highScore);
        
        //LogUtil.put(LogFactory.getInstance("Score: ").append(score, this, "compare"));

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

        this.addCommand(this.highScoreUtil.SUBMIT_TEXTBOX_COMMAND);

        this.setCommandListener(cmdListener);
    }

    public void open()
    {
        this.virtualKeyboardEventHandler.open();

        this.paintable = NullPaintable.getInstance();
        super.open();
    }

    public void close() throws Exception
    {
        this.virtualKeyboardEventHandler.close();

        this.paintable = this.pleaseWait;
        this.repaint();

        super.close();
        this.removeCommand(this.highScoreUtil.SUBMIT_TEXTBOX_COMMAND);
        this.update();
        this.highScoreUtil.saveHighScore();

        this.paintable = NullPaintable.getInstance();
        this.repaint();
    }

    private void update()
    {
        final String name = this.getTextFieldItem().getString();
        this.highScoreUtil.update(name);
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics);
        this.paintable.paint(graphics);
    }
    
    public void saveHighScore()
    {
        this.highScoreUtil.saveHighScore();
    }
    
    public void submit()
    {
        this.highScoreUtil.submit(this);
    }
}