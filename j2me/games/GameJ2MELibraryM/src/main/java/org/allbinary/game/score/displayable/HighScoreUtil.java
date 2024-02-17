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

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.score.HighScore;
import org.allbinary.game.score.HighScoreNamePersistanceSingleton;
import org.allbinary.game.score.HighScores;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.string.StringMaker;

public class HighScoreUtil
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    public static final Command SUBMIT_TEXTBOX_COMMAND = 
        new Command("Submit", Command.SCREEN, 1);

    private final HighScores[] highScoresArray;
    private final HighScore highScore;

    public HighScoreUtil(final CommandListener cmdListener, final String name,
            final HighScores[] highScoresArray, final HighScore highScore) throws Exception
    {
        this.highScoresArray = highScoresArray;
        this.highScore = highScore;
    }

    public void update(final String name)
    {
        HighScoreNamePersistanceSingleton.getInstance().save(name);
        this.highScore.setName(name);
    }

    public void saveHighScore()
    {
        LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "saveHighScore"));
        
        for (int index = 0; index < this.highScoresArray.length; index++)
        {
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Adding HighScore to board: ").append(highScoresArray[index]).toString(), this, "saveHighScore"));
            highScoresArray[index].add(this.highScore);
        }
    }
    
    public void submit(final MyCanvas myCanvas)
    {
        final CommandListener commandListener = myCanvas.getCustomCommandListener();
        commandListener.commandAction(SUBMIT_TEXTBOX_COMMAND, myCanvas);
    }
}