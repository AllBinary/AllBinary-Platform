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

import jsinterop.annotations.JsType;

import org.allbinary.logic.NullUtil;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;


@JsType
public class GameStateFactory
{
   private static Object instance = NullUtil.getInstance().NULL_OBJECT;

   @JsMethod
   public static GameStateFactory getInstance()
   {
      if(GameStateFactory.instance == NullUtil.getInstance().NULL_OBJECT)
      {
         GameStateFactory.instance = new GameStateFactory();
      }
      return (GameStateFactory) GameStateFactory.instance;
   }

   private int index = 0;
   
   @JsProperty
   public final GameState NO_GAME_STATE = this.createGameState("NO_GAME_STATE");
   @JsProperty
   public final GameState PLAYING_GAME_STATE = this.createGameState("PLAYING_GAME_STATE");
   @JsProperty
   public final GameState SHOW_END_RESULT_GAME_STATE = this.createGameState("SHOW_END_RESULT_GAME_STATE");
   @JsProperty
   public final GameState SHOW_HIGH_SCORE_GAME_STATE = this.createGameState("SHOW_HIGH_SCORE_GAME_STATE");
   
   @JsConstructor
   private GameStateFactory()
   {
   }
   
   @JsMethod
   public GameState createGameState(String name)
   {
      return new GameState(name, this.index++);
   }
}
