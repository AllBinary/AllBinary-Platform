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

import jsinterop.annotations.JsType;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.string.CommonSeps;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsProperty;
import org.allbinary.logic.StdUtil;
import org.allbinary.util.ABHashtable;


@JsType
public class GameInfo 
{

    @JsProperty
    public static final GameInfo NONE = new GameInfo(GameTypeFactory.getInstance().NONE, GameMode.NONE, PlayerTypesFactory.getInstance().PLAYER_TYPE_ONE, -1, -1);
    
    @JsProperty
    protected final LogUtil logUtil = LogUtil.getInstance();

   @JsProperty
   public static final String LEVEL_NAME = "LEVEL";
   
   private final GameInfoData gameInfoData = GameInfoData.getInstance();

   private final GameType gameType;
   private final GameMode gameMode;
   private final PlayerType playerType;
   
   private int highestLevel;
   private int currentLevel;
   
   @JsConstructor
   public GameInfo(final GameType gameType, final GameMode gameMode, 
       final PlayerType playerType, int highestLevel, int currentLevel)
   {
       this.gameType = gameType;
       this.gameMode = gameMode;
       this.setHighestLevel(highestLevel);
       this.currentLevel = currentLevel;
       this.playerType = playerType;
   }
   
   @JsMethod
   public GameType getGameType()
   {
      return this.gameType;
   }

   @JsMethod
   public GameMode getGameMode()
   {
      return this.gameMode;
   }

   @JsMethod
   public PlayerType getPlayerType()
   {
       return this.playerType;
   }
   
   @JsMethod
   public int getHighestLevel()
   {
      return this.highestLevel;
   }

   @JsMethod
   public void setHighestLevel(final int highestLevel)
   {
       this.highestLevel = highestLevel;
   }
   
   @JsMethod
   public int getCurrentLevel()
   {
      return this.currentLevel;
   }

   private final String NEW_LEVEL = "New Level: ";
   private final String SET_CURRENT_LEVEL = "setCurrentLevel";

   @JsMethod
   public void setCurrentLevel(final int currentLevel)
   {
      this.currentLevel = currentLevel;
      this.logUtil.putF(new StringMaker().append(this.NEW_LEVEL).appendint(this.getCurrentLevel()).toString(), this, this.SET_CURRENT_LEVEL);
   }

   private final String NEXT = "Next ";
   private final String NEXT_GAME_LEVEL = "nextGameLevel";
   
   @JsMethod
   public void nextGameLevel()
   {
      if(this.getCurrentLevel() < this.getHighestLevel())
      {
          this.currentLevel++;
      }

      this.logUtil.putF(new StringMaker().append(this.NEXT).append(this.NEW_LEVEL).appendint(this.getCurrentLevel()).toString(), this, this.NEXT_GAME_LEVEL);
   }
   
   @JsMethod
   public void previousGameLevel()
   {
      if(this.getCurrentLevel() > 1)
      this.currentLevel--;
   }  
   
   @JsMethod
   public boolean isLastLevel()
   {
      if(this.getCurrentLevel() >= this.getHighestLevel())
      {
         return true;
      }
      else
      {
         return false;
      }
   }
   
   @JsMethod
   public ABHashtable toHashtable()
   {
       final ABHashtable hashtable = StdUtil.getInstance().createHashtable();

       hashtable.put(this.gameInfoData.GAME_TYPE, this.getGameType().toString());
       hashtable.put(this.gameInfoData.GAME_MODE, this.gameMode.toString());
       hashtable.put(this.gameInfoData.PLAYER_TYPE, this.playerType.toString());
       hashtable.put(this.gameInfoData.HIGHEST_LEVEL, Integer.toString(this.getHighestLevel()));
       hashtable.put(this.gameInfoData.CURRENT_LEVEL, Integer.toString(this.currentLevel));

       return hashtable;
   }
   
   @JsMethod
   public String toString()
   {
       final StringMaker stringBuffer = new StringMaker();

       final CommonSeps commonSeps = CommonSeps.getInstance();
       
       stringBuffer.append(this.gameInfoData.GAME_TYPE);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.getGameType().toString());

       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(this.gameInfoData.GAME_MODE);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.gameMode.toString());
       
       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(this.gameInfoData.PLAYER_TYPE);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(StringUtil.getInstance().toString(this.playerType));
       
       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(this.gameInfoData.HIGHEST_LEVEL);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.appendint(this.getHighestLevel());
       
       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(this.gameInfoData.CURRENT_LEVEL);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.appendint(this.currentLevel);
       
       return stringBuffer.toString();
   }
}
