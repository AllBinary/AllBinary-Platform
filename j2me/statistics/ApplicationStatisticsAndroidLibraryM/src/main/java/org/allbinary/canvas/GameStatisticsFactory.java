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
package org.allbinary.canvas;

import org.allbinary.logic.string.StringMaker;

public class GameStatisticsFactory extends AndroidGameStatistics
{
    private static final AndroidGameStatistics SINGLETON = new GameStatisticsFactory();
    
    public static final AndroidGameStatistics getInstance()
    {
        return SINGLETON;
    }
    
    private StringMaker stringBuffer = new StringMaker();
    
    @Override
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
        return new StringMaker().append(super.toString()).append(this.stringBuffer.toString()).toString();
    }
    
}
