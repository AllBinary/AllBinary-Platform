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

import org.allbinary.logic.string.CommonStrings;

public class NullHighScoresSingletonFactory
{
    private static final HighScores SINGLETON = new HighScores("Loading", "High Scores Not Loaded", 
            CommonStrings.getInstance().PERIODS);
    
    public static final HighScores getInstance()
    {
        return SINGLETON;
    }
}
