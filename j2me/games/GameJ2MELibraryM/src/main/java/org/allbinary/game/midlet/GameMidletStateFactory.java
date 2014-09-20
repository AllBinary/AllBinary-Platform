package org.allbinary.game.midlet;

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.state.GameState;

public class GameMidletStateFactory {

	private static final GameMidletStateFactory INSTANCE = new GameMidletStateFactory();
	
    private GameState currentGameState = GameState.NO_GAME_STATE;
    
	private GameMidletStateFactory()
	{
		
	}
	
	public static final GameMidletStateFactory getInstance()
	{
		return INSTANCE;
	}

	public GameState getCurrentGameState() {
		return currentGameState;
	}

	public void setCurrentGameState(GameState currentGameState) {
		LogUtil.put(LogFactory.getInstance("Current GameState: " + currentGameState, this, "setCurrentGameState"));
		this.currentGameState = currentGameState;
	}
	
}
