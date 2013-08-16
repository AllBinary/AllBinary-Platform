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
package allbinary.game.score;

import allbinary.game.GameInfo;

public class NoHighScoresFactory implements HighScoresFactoryInterface
{
    private static final NoHighScoresFactory instance = new NoHighScoresFactory();
    
    public static NoHighScoresFactory getInstance()
    {
        return instance;
    }
    
    private final HighScores[] NO_HIGH_SCORES = new HighScores[0];

    protected NoHighScoresFactory()
    {
        
    }
    
    public HighScores[] createHighScores(GameInfo gameInfo)
    {
        return NO_HIGH_SCORES;
    }
}
