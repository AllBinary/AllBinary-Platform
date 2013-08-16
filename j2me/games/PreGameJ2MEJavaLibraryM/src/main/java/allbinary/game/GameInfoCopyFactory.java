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
package allbinary.game;

public class GameInfoCopyFactory
{
    public static final GameInfo getInstance(GameInfo gameInfo, int maxLevel)
    {
        return new GameInfo(
                gameInfo.getGameType(), 
                gameInfo.getGameMode(), 
                gameInfo.getPlayerType(), 
                maxLevel, 
                gameInfo.getCurrentLevel()
                ); 
    }

    public static final GameInfo getInstance(GameInfo gameInfo)
    {
        return new GameInfo(
                gameInfo.getGameType(), 
                gameInfo.getGameMode(), 
                gameInfo.getPlayerType(), 
                gameInfo.getHighestLevel(), 
                gameInfo.getCurrentLevel()
                ); 
    }
}
