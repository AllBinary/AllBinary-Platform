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

import org.allbinary.logic.string.CommonLabels;
import org.allbinary.util.BasicArrayList;

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.string.StringMaker;

public class HighScores implements HighScoresInterface
{
    private BasicArrayList orderedHighScoresList = new BasicArrayList();
    
    private final String name;     // = "Score"
    
    private final String heading;

    private final String columnOneHeading = "Name";
    private final String columnTwoHeading;
    
    public HighScores(String name, String heading, String columnTwoHeading)
    {
        this.name = name;
        this.heading = heading;
        this.columnTwoHeading = columnTwoHeading;
    }
    
    public void setList(BasicArrayList orderedHighScoresList)
    {
        this.orderedHighScoresList = orderedHighScoresList;
    }

    public BasicArrayList getList()
    {
        return orderedHighScoresList;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getTotal()
    {
        return this.getList().size();
    }
    
    public void add(HighScore newHighScore)
    {
        this.orderedHighScoresList.add(newHighScore);
    }

    public String getHeading()
    {
        return heading;
    }

    public String getColumnOneHeading()
    {
        return columnOneHeading;
    }

    public String getColumnTwoHeading()
    {
        return columnTwoHeading;
    }
    
    public boolean isBestScore(HighScore newHighScore) throws Exception
    {
        return false;
    }
    
    public String toString()
    {
        final StringMaker stringBuffer = new StringMaker();
        
        stringBuffer.append("HighScores: ");
        //stringBuffer.append(this.name);
        stringBuffer.append(this.heading);
        //stringBuffer.append(this.columnOneHeading);
        //stringBuffer.append(this.columnTwoHeading);
        
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(CommonLabels.getInstance().TOTAL_LABEL);
        stringBuffer.append(this.orderedHighScoresList.size());
        
        return stringBuffer.toString();
    }
}
