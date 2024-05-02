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
public class HighScoresHelperBase implements HighScoresResultsListener {
    
    protected HighScores[] highScoresArray = 
        NoHighScoresFactory.getInstance().NO_HIGH_SCORES;
    
    public void setHighScoresArray(final HighScores[] highScoresArray)
    {
        //PreLogUtil.put(commonStrings.START_LABEL + highScoresArray.length, this, "setHighScoresArray");
        this.highScoresArray = highScoresArray;
    }
    
    public HighScores getNextHighScores()
    {
        return null;
    }
    
    public boolean isAnyHighScores() {
        throw new RuntimeException();
    }

    public void setSelectedHighScores(final HighScores selectedHighScores)
    {
    }

    public HighScores getSelectedHighScores()
    {
        return null;
    }
    
    public HighScores[] getHighScoresArray()
    {
        return this.highScoresArray;
    }

    public void selectHighScores() {
        
    }

}
