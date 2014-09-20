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
package org.allbinary.game;

import org.allbinary.game.state.GameState;
import org.allbinary.game.state.GameStateFactory;

public class IntermissionFactory
{
    private static final IntermissionFactory instance = new IntermissionFactory();

    public static IntermissionFactory getInstance()
    {
        return instance;
    }
    
    public final GameState START_LEVEL_INTERMISSION_GAME_STATE = GameStateFactory
    .getInstance("START_LEVEL_INTERMISSION_GAME_STATE");

    public final GameState WAIT_LEVEL_INTERMISSION_GAME_STATE = GameStateFactory
    .getInstance("WAIT_LEVEL_INTERMISSION_GAME_STATE");
    
    public final GameState SHOW_RESULTS_LEVEL_INTERMISSION_GAME_STATE = GameStateFactory
    .getInstance("SHOW_RESULTS_LEVEL_INTERMISSION_GAME_STATE");
    
    public final GameState SHOW_HIGH_SCORE_LEVEL_INTERMISSION_GAME_STATE = GameStateFactory
    .getInstance("SHOW_HIGH_SCORE_LEVEL_INTERMISSION_GAME_STATE");
}
