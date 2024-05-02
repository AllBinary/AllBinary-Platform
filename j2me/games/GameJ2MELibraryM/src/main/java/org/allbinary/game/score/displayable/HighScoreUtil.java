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


import org.allbinary.game.GameInfo;
import org.allbinary.game.score.HighScore;
import org.allbinary.game.score.HighScoreNamePersistanceSingleton;
import org.allbinary.game.score.HighScores;
import org.allbinary.graphics.displayable.MyCanvas;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class HighScoreUtil
{
    private final CommonStrings commonStrings = CommonStrings.getInstance();

    public static final Command SUBMIT_TEXTBOX_COMMAND = 
        new Command("Submit", Command.SCREEN, 1);

    private final HighScores[] highScoresArray;
    private final HighScore highScore;

    private final AbeClientInformationInterface abeClientInformation;
    private final GameInfo gameInfo;
    
    public HighScoreUtil(final AbeClientInformationInterface abeClientInformation, final GameInfo gameInfo, final CommandListener cmdListener, final String name,
            final HighScores[] highScoresArray, final HighScore highScore) throws Exception
    {
        this.highScoresArray = highScoresArray;
        this.highScore = highScore;
        this.abeClientInformation = abeClientInformation;
        this.gameInfo = gameInfo;
    }

    public void update(final String name)
    {
        HighScoreNamePersistanceSingleton.getInstance().save(abeClientInformation, gameInfo, name);
        this.highScore.setName(name);
    }

    public void saveHighScore()
    {
        LogUtil.put(LogFactory.getInstance(new StringMaker().append(commonStrings.START).append(this.highScore).toString(), this, "saveHighScore"));
        
        HighScores highScores;
        String highScoresAsString;
        for (int index = 0; index < this.highScoresArray.length; index++)
        {
            highScores = highScoresArray[index];
            highScores.addHighScore(this.highScore);
            highScoresAsString = highScores.toString();
            LogUtil.put(LogFactory.getInstance(new StringMaker().append("Adding Score: ").append(highScoresAsString).toString(), this, "saveHighScore"));
        }
    }
    
    public void submit(final MyCanvas myCanvas)
    {
        final CommandListener commandListener = myCanvas.getCustomCommandListener();
        commandListener.commandAction(SUBMIT_TEXTBOX_COMMAND, myCanvas);
    }
}