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

//iOS (Same as J2ME currently)
//ActualPlatform
public class GameStatisticsFactory extends BaseGameStatistics
{
    private static final GameStatisticsFactory instance = new GameStatisticsFactory();

    //ActualPlatform
    public static GameStatisticsFactory getInstance()
    {
        return instance;
    }
    
    private StringMaker stringBuffer = new StringMaker();
    
    @Override
    //ActualPlatform
    public void add(String string)
    {
        if(this.stringBuffer.length() > 12000)
        {
            this.stringBuffer.delete(0, this.stringBuffer.length());
            this.stringBuffer.append("Old Stats Cleared");
        }
        this.stringBuffer.append(string);
    }
    
    //ActualPlatform
    public String toString()
    {
        return new StringMaker().append(super.toString()).append(this.stringBuffer.toString()).toString();
    }
}
