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

import jsinterop.annotations.JsType;

import org.allbinary.logic.string.StringMaker;
import org.allbinary.string.CommonLabels;
import org.allbinary.string.CommonSeps;
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;


@JsType
public class HighScores implements HighScoresInterface
{
    private BasicArrayList orderedHighScoresList = new BasicArrayListD();
    
    private final String name;     // = "Score"
    
    private final String heading;

    private final String columnOneHeading = "Name";
    private final String columnTwoHeading;
    
    @JsConstructor
    public HighScores(String name, String heading, String columnTwoHeading)
    {
        this.name = name;
        this.heading = heading;
        this.columnTwoHeading = columnTwoHeading;
    }
    
    @JsMethod
    public void setList(BasicArrayList orderedHighScoresList)
    {
        this.orderedHighScoresList = orderedHighScoresList;
    }

    @Override
    @JsMethod
    public BasicArrayList getList()
    {
        return this.orderedHighScoresList;
    }
    
    @JsMethod
    public String getName()
    {
        return this.name;
    }
    
    @Override
    @JsMethod
    public int getTotal()
    {
        return this.getList().size();
    }
    
    @Override
    @JsMethod
    public void addHighScore(HighScore newHighScore)
    {
        this.orderedHighScoresList.add(newHighScore);
    }

    @JsMethod
    public String getHeading()
    {
        return this.heading;
    }

    @JsMethod
    public String getColumnOneHeading()
    {
        return this.columnOneHeading;
    }

    @JsMethod
    public String getColumnTwoHeading()
    {
        return this.columnTwoHeading;
    }
    
    @Override
    @JsMethod
    public boolean isBestScore(HighScore newHighScore) throws Exception
    {
        return false;
    }
    
    @JsMethod
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
        stringBuffer.appendint(this.orderedHighScoresList.size());
        
        return stringBuffer.toString();
    }
}
