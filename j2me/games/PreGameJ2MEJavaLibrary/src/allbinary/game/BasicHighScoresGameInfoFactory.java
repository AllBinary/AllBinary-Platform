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

public class BasicHighScoresGameInfoFactory
{
    private static final BasicHighScoresGameInfoFactory SINGLETON = new BasicHighScoresGameInfoFactory();
    
    public static BasicHighScoresGameInfoFactory getInstance()
    {
        return SINGLETON;
    }
    
    /*
     * LOL - Bots do not get high scores - do not use
    public final GameInfo getInstance(GameInfo gameInfo) throws Exception
    {
        if(gameInfo.getGameMode() == GameMode.CLIENT)
        {
            if(gameInfo.getGameType() == GameType.BOT)
            {
                return this.BOT_CLIENT;
            }
            else
                if(gameInfo.getGameType() == GameType.MULTI_PLAYER)
                {
                    return this.MULTI_PLAYER_CLIENT;
                }
                else
                    if(gameInfo.getGameType() == GameType.SINGLE_PLAYER)
                    {
                        return this.SINGLE_PLAYER_CLIENT;
                    }
        }
        else
        {
            if(gameInfo.getGameType() == GameType.BOT)
            {
                return this.BOT_SERVER;
            }
            else
                if(gameInfo.getGameType() == GameType.MULTI_PLAYER)
                {
                    return this.MULTI_PLAYER_SERVER;
                }
                else
                    if(gameInfo.getGameType() == GameType.SINGLE_PLAYER)
                    {
                        return this.SINGLE_PLAYER_SERVER;
                    }
        }
        throw new Exception("No Such GameInfo Singleton");
    }
    */
    
    //public final GameInfo BOT_CLIENT = 
      //  new GameInfo(GameType.BOT, GameMode.CLIENT, 0, 0);
    //public final GameInfo BOT_SERVER = 
      //  new GameInfo(GameType.BOT, GameMode.CLIENT, 0, 0);
    
    //public final GameInfo MULTI_PLAYER_CLIENT = 
      //  new GameInfo(GameType.MULTI_PLAYER, GameMode.CLIENT, 0, 0);
    public final GameInfo MULTI_PLAYER_SERVER = 
        new GameInfo(GameTypeFactory.getInstance().MULTI_PLAYER, GameMode.SERVER, 0, 0);

    //public final GameInfo SINGLE_PLAYER_CLIENT = 
      //  new GameInfo(GameType.SINGLE_PLAYER, GameMode.CLIENT, 0, 0);
    public final GameInfo SINGLE_PLAYER_SERVER = 
        new GameInfo(GameTypeFactory.getInstance().SINGLE_PLAYER, GameMode.SERVER, 0, 0);
}
