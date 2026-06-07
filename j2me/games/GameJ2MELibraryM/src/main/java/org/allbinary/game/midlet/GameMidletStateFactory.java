package org.allbinary.game.midlet;

import org.allbinary.game.state.GameState;
import org.allbinary.game.state.GameStateFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;

public class GameMidletStateFactory {
    protected final LogUtil logUtil = LogUtil.getInstance();


	private static final GameMidletStateFactory INSTANCE = new GameMidletStateFactory();

    private GameState currentGameState = GameStateFactory.getInstance().NO_GAME_STATE;
    
	private GameMidletStateFactory()
	{
		
	}
	
	public static final GameMidletStateFactory getInstance()
	{
		return GameMidletStateFactory.INSTANCE;
	}

	public GameState getCurrentGameState() {
		return this.currentGameState;
	}

	public void setCurrentGameState(GameState currentGameState) {
		this.logUtil.putF(new StringMaker().append("Current GameState: ").append(StringUtil.getInstance().toString(currentGameState)).toString(), this, "setCurrentGameState");
		this.currentGameState = currentGameState;
	}
	
}
