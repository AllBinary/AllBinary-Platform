/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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

/**
 *
 * @author User
 */
public class LastFetchHighScoresFactory {
    
    private static final LastFetchHighScoresFactory instance = new LastFetchHighScoresFactory();

    /**
     * @return the instance
     */
    public static LastFetchHighScoresFactory getInstance() {
        return instance;
    }

    public HighScores[] highScoresArray = NoHighScoresFactory.getInstance().NO_HIGH_SCORES;

}
