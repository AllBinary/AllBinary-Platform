package allbinary.game.midlet;

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.state.GameState;

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
