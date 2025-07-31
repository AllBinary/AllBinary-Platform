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

import org.allbinary.util.CircularIndexUtil;

public class HighScoresHelper2 extends HighScoresHelperBase
{
    private final CircularIndexUtil circularIndexUtil = 
        CircularIndexUtil.getInstance(0, 0);
    
    private HighScores selectedHighScores = NullHighScoresSingletonFactory.getInstance();
    
    @Override
    public void setSelectedHighScores(final HighScores selectedHighScores)
    {
        this.selectedHighScores = selectedHighScores;
    }

    @Override
    public HighScores getSelectedHighScores()
    {
        return selectedHighScores;
    }

    @Override
    public void setHighScoresArray(final HighScores[] highScoresArray)
    {
        super.setHighScoresArray(highScoresArray);

        this.circularIndexUtil.setSize(this.highScoresArray.length);

        this.selectHighScores();
    }

    @Override
    public void selectHighScores()
    {

        if(this.highScoresArray.length == 0) {
            this.setSelectedHighScores(NullHighScoresSingletonFactory.getInstance());
            return;
        }

        this.circularIndexUtil.next();
        
        HighScores highScores = this.highScoresArray[this.circularIndexUtil.getIndex()];

        int index = 0;
        while (highScores.getTotal() < 1 && index < this.highScoresArray.length)
        {
            highScores = this.highScoresArray[this.circularIndexUtil.next()];
            index++;
        }

        this.setSelectedHighScores(highScores);
    }
    
}
