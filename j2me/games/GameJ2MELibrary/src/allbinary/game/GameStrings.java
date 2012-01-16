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
package allbinary.game;

public class GameStrings
{
    private static final GameStrings instance = new GameStrings();

    public static GameStrings getInstance()
    {
        return instance;
    }
    
    public final String PROCESS_TICK = "processTick";
    public final String ON_GAME_INITIALIZED = "onGameInitialized";
    public final String UPDATESURFACE = "updateSurface";
    public final String ON_SET_DISPLAYABLE = "onSetDiplayable";
}
