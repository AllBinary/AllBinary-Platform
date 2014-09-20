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

import java.util.Hashtable;

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class GameInfo 
{
   public static final String LEVEL_NAME = "LEVEL";

   private final GameType gameType;
   private final GameMode gameMode;
   private final PlayerType playerType;
   
   private int highestLevel;
   private int currentLevel;
   
   public GameInfo(GameType gameType, GameMode gameMode, 
           int highestLevel, int currentLevel)
   {
       this.gameType = gameType;
       this.gameMode = gameMode;
       this.setHighestLevel(highestLevel);
       this.currentLevel = currentLevel;
       this.playerType = PlayerTypesFactory.getInstance().PLAYER_TYPE_ONE;
   }

   public GameInfo(GameType gameType, GameMode gameMode, PlayerType playerType, 
           int highestLevel, int currentLevel)
   {
       this.gameType = gameType;
       this.gameMode = gameMode;
       this.setHighestLevel(highestLevel);
       this.currentLevel = currentLevel;
       this.playerType = playerType;
   }
   
   public GameType getGameType()
   {
      return gameType;
   }

   public GameMode getGameMode()
   {
      return gameMode;
   }

   public PlayerType getPlayerType()
   {
       return playerType;
   }
   
   public int getHighestLevel()
   {
      return highestLevel;
   }

   public void setHighestLevel(int highestLevel)
   {
       this.highestLevel = highestLevel;
   }
   
   public int getCurrentLevel()
   {
      return currentLevel;
   }

   private final String NEW_LEVEL = "New Level: ";
   private final String SET_CURRENT_LEVEL = "setCurrentLevel";

   public void setCurrentLevel(int currentLevel)
   {
      this.currentLevel = currentLevel;
      LogUtil.put(LogFactory.getInstance(NEW_LEVEL + this.getCurrentLevel(), this, SET_CURRENT_LEVEL));
   }

   private final String NEXT = "Next ";
   private final String NEXT_GAME_LEVEL = "nextGameLevel";
   
   public void nextGameLevel()
   {
      if(this.getCurrentLevel() < this.getHighestLevel())
      {
          this.currentLevel++;
      }

      LogUtil.put(LogFactory.getInstance(NEXT + NEW_LEVEL + this.getCurrentLevel(), this, NEXT_GAME_LEVEL));
   }
   
   public void previousGameLevel()
   {
      if(this.getCurrentLevel() > 1)
      this.currentLevel--;
   }  
   
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

   private final String GAME_TYPE = "GAME_TYPE";
   private final String GAME_MODE = "GAME_MODE";
   private final String PLAYER_TYPE = "PLAYER_TYPE";
   private final String HIGHEST_LEVEL = "HIGHEST_LEVEL";
   private final String CURRENT_LEVEL = "CURRENT_LEVEL";
   
   public Hashtable toHashtable()
   {
       Hashtable hashtable = new Hashtable();

       hashtable.put(GAME_TYPE, this.getGameType().toString());
       hashtable.put(GAME_MODE, this.gameMode.toString());
       hashtable.put(PLAYER_TYPE, this.playerType.toString());
       hashtable.put(HIGHEST_LEVEL, Integer.toString(this.getHighestLevel()));
       hashtable.put(CURRENT_LEVEL, Integer.toString(this.currentLevel));

       return hashtable;
   }
   
   public String toString()
   {
       StringMaker stringBuffer = new StringMaker();

       CommonSeps commonSeps = CommonSeps.getInstance();
       
       stringBuffer.append(GAME_TYPE);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.getGameType().toString());

       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(GAME_MODE);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.gameMode.toString());
       
       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(PLAYER_TYPE);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.playerType);
       
       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(HIGHEST_LEVEL);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.getHighestLevel());
       
       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(CURRENT_LEVEL);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.currentLevel);
       
       return stringBuffer.toString();
   }
}
