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

import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;

public class GameInfo 
{
   public static final String LEVEL_NAME = "LEVEL";
   
   private final GameInfoData gameInfoData = GameInfoData.getInstance();

   private final GameType gameType;
   private final GameMode gameMode;
   private final PlayerType playerType;
   
   private int highestLevel;
   private int currentLevel;
   
   public GameInfo(final GameType gameType, final GameMode gameMode, 
           final int highestLevel, final int currentLevel)
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

   public void setHighestLevel(final int highestLevel)
   {
       this.highestLevel = highestLevel;
   }
   
   public int getCurrentLevel()
   {
      return currentLevel;
   }

   private final String NEW_LEVEL = "New Level: ";
   private final String SET_CURRENT_LEVEL = "setCurrentLevel";

   public void setCurrentLevel(final int currentLevel)
   {
      this.currentLevel = currentLevel;
      LogUtil.put(LogFactory.getInstance(new StringMaker().append(NEW_LEVEL).append(this.getCurrentLevel()).toString(), this, SET_CURRENT_LEVEL));
   }

   private final String NEXT = "Next ";
   private final String NEXT_GAME_LEVEL = "nextGameLevel";
   
   public void nextGameLevel()
   {
      if(this.getCurrentLevel() < this.getHighestLevel())
      {
          this.currentLevel++;
      }

      LogUtil.put(LogFactory.getInstance(new StringMaker().append(NEXT).append(NEW_LEVEL).append(this.getCurrentLevel()).toString(), this, NEXT_GAME_LEVEL));
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
   
   public Hashtable toHashtable()
   {
       final Hashtable hashtable = new Hashtable();

       hashtable.put(this.gameInfoData.GAME_TYPE, this.getGameType().toString());
       hashtable.put(this.gameInfoData.GAME_MODE, this.gameMode.toString());
       hashtable.put(this.gameInfoData.PLAYER_TYPE, this.playerType.toString());
       hashtable.put(this.gameInfoData.HIGHEST_LEVEL, Integer.toString(this.getHighestLevel()));
       hashtable.put(this.gameInfoData.CURRENT_LEVEL, Integer.toString(this.currentLevel));

       return hashtable;
   }
   
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
       stringBuffer.append(this.getHighestLevel());
       
       stringBuffer.append(commonSeps.COMMA_SEP);
       
       stringBuffer.append(this.gameInfoData.CURRENT_LEVEL);
       stringBuffer.append(commonSeps.EQUALS);
       stringBuffer.append(this.currentLevel);
       
       return stringBuffer.toString();
   }
}
