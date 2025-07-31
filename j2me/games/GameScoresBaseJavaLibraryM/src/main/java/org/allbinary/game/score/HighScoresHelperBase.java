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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonStrings;

/**
 *
 * @author User
 */
public class HighScoresHelperBase implements HighScoresResultsListener {
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected HighScores[] highScoresArrayP = 
        LastFetchHighScoresFactory.getInstance().highScoresArray;

    @Override    
    public void setHighScoresArray(final HighScores[] highScoresArrayP)
    {
        if (highScoresArrayP != null) {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(new StringMaker().append(commonStrings.START).append(highScoresArrayP.length).toString(), this, "setHighScoresArray");
        } else {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.START, this, "setHighScoresArray");
        }

        this.highScoresArrayP = highScoresArrayP;
    }
    
    public HighScores getNextHighScores()
    {
        return NullHighScoresSingletonFactory.getInstance();
    }
    
    public boolean isAnyHighScores() {
        throw new RuntimeException();
    }

    public void setSelectedHighScores(final HighScores selectedHighScores)
    {
    }

    public HighScores getSelectedHighScores()
    {
        return NullHighScoresSingletonFactory.getInstance();
    }
    
    public HighScores[] getHighScoresArray()
    {
        return this.highScoresArrayP;
    }

    public void selectHighScores() {
        
    }

}
