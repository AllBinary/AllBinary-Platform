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

public class HighScoresHelper extends HighScoresHelperBase
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final CircularIndexUtil circularIndexUtil = 
        CircularIndexUtil.getInstance(0, 0);

    public void setHighScoresArray(final HighScores[] highScores)
    {
        super.setHighScoresArray(highScores);

        this.circularIndexUtil.setSize(this.highScoresArray.length);
    }

    int lastIndex = -1;
    
    public HighScores getNextHighScores()
    {        
        HighScores highScores = this.highScoresArray[this.circularIndexUtil.getIndex()];

        //PreLogUtil.put(commonStrings.START_LABEL + highScores.toString(), this, "getSelectHighScores");
        
        //just in case infinite loop
        int index = 0;

        while ((highScores.getTotal() < 1 || lastIndex == this.circularIndexUtil.getIndex()) && 
                index < this.highScoresArray.length)
        {
            this.circularIndexUtil.next();
            highScores = this.highScoresArray[this.circularIndexUtil.getIndex()];
            
            //PreLogUtil.put("Selecting: " + highScores.toString(), this, "getSelectHighScores");
            
            index++;
        }

        lastIndex = this.circularIndexUtil.getIndex();
        
        //PreLogUtil.put(highScores.toString(), this, "getSelectHighScores");
        
        return highScores;
    }
    
    public boolean isAnyHighScores()
    {
        if(highScoresArray.length < 1)
        {
            //PreLogUtil.put("No Scores", this, "isAnyHighScores");
            return false;
        }
        
        boolean anyScores = false;
        
        HighScores highScores;
        
        //PreLogUtil.put("Searching Scores", this, "isAnyHighScores");
        
        for(int index = highScoresArray.length - 1; index >= 0; index--)
        {
            highScores = highScoresArray[index];
            
            if(highScores.getTotal() > 0)
            {
                //PreLogUtil.put("Found: " + highScores.toString(), this, "isAnyHighScores");
                anyScores = true;
            }
        }

        return anyScores;
    }
}
