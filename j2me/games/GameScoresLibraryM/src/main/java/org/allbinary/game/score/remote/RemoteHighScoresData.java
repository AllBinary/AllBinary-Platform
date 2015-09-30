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
package org.allbinary.game.score.remote;

public class RemoteHighScoresData
{
    private static final RemoteHighScoresData SINGLETON = new RemoteHighScoresData();
    
    public static final RemoteHighScoresData getInstance()
    {
        return SINGLETON;
    }
    
    public final String HIGH_SCORES = "HIGH_SCORES";
    
    public String CUSTOMER_USER_NAME = "CUSTOMER_USER_NAME";
    public String DISPLAY_NAME = "DISPLAY_NAME";
    public String SCORE = "SCORE";
    public String GAME_INFO = "GAME_INFO";
    public String GAME_CONFIGURATION = "GAME_CONFIGURATION";
}
