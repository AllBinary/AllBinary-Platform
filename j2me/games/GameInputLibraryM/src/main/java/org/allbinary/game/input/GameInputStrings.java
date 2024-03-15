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
package org.allbinary.game.input;

public class GameInputStrings
{
    private static final GameInputStrings instance = new GameInputStrings();

    public static GameInputStrings getInstance()
    {
        return instance;
    }
    
    public final String PROCESS_INPUT = "processInput";
    
    public final String KEY_PRESSED = "keyPressed";
    public final String KEY_RELEASED = "keyReleased";
    public final String KEY_REPEATED = "keyRepeated";
    
    public final String ON_PRESS_GAME_KEY = "onPressGameKeyEvent";
    public final String ON_DOWN_GAME_KEY = "onDownGameKeyEvent";
    public final String ON_UP_GAME_KEY = "onUpGameKeyEvent";

}
