package org.allbinary.game.midlet;

import jsinterop.annotations.JsType;

import org.allbinary.game.state.GameState;
import org.allbinary.game.state.GameStateFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GameMidletStateFactory {
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();


	private static final GameMidletStateFactory INSTANCE = new GameMidletStateFactory();

    private GameState currentGameState = GameStateFactory.getInstance().NO_GAME_STATE;
    
	@JsConstructor
	private GameMidletStateFactory()
	{
		
	}
	
	@JsMethod
	public static final GameMidletStateFactory getInstance()
	{
		return GameMidletStateFactory.INSTANCE;
	}

	@JsMethod
	public GameState getCurrentGameState() {
		return this.currentGameState;
	}

	@JsMethod
	public void setCurrentGameState(GameState currentGameState) {
		this.logUtil.putF(new StringMaker().append("Current GameState: ").append(StringUtil.getInstance().toString(currentGameState)).toString(), this, "setCurrentGameState");
		this.currentGameState = currentGameState;
	}
	
}
