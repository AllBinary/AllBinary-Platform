package org.allbinary.canvas;

import org.allbinary.logic.string.StringMaker;

public class GameStatisticsFactory extends BaseGameStatistics
{
    private static final GameStatisticsFactory instance = new GameStatisticsFactory();

    public static GameStatisticsFactory getInstance()
    {
        return instance;
    }
    
    private StringMaker stringBuffer = new StringMaker();
    
    public void add(String string)
    {
        if(this.stringBuffer.length() > 12000)
        {
            this.stringBuffer.delete(0, this.stringBuffer.length());
            this.stringBuffer.append("Old Stats Cleared");
        }
        this.stringBuffer.append(string);
    }
    
    public String toString()
    {
        return super.toString() + this.stringBuffer.toString();
    }
}
