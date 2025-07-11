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
package org.allbinary.game.score;

import org.allbinary.game.GameInfo;
import org.allbinary.logic.communication.log.LogUtil;

public class NoHighScoresFactory extends HighScoresBase
{
    protected final LogUtil logUtil = LogUtil.getInstance();


    private static final NoHighScoresFactory instance = new NoHighScoresFactory();
    
    public static NoHighScoresFactory getInstance()
    {
        return instance;
    }
    
    public final HighScores[] NO_HIGH_SCORES = new HighScores[0];

    private NoHighScoresFactory()
    {
        
    }
    
    public void fetchHighScores(final GameInfo gameInfo, final HighScoresResultsListener highScoresResultsListener)
    {   
        logUtil.put("Getting No HighScores", this, "fetchHighScores");
        highScoresResultsListener.setHighScoresArray(NO_HIGH_SCORES);
    }
    
    public HighScoresHelperBase createHighScoresHelper() {
        return new HighScoresHelperBase();
    }

}
