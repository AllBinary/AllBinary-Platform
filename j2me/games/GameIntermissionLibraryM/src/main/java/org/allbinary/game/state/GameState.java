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
package org.allbinary.game.state;

public class GameState {

	private String name;
	//private int state;

	public static GameState NO_GAME_STATE = GameStateFactory.getInstance("NO_GAME_STATE");
	public static GameState PLAYING_GAME_STATE = GameStateFactory.getInstance("PLAYING_GAME_STATE");
	
	public GameState(String name, int state)
	{
		this.name = name;
		//this.state = state;
	}
	
	public String toString()
	{
		return this.name;
	}
}
