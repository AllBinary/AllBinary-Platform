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
package org.allbinary.game.input;


public class GameKeyMappingFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final GameKeyMappingFactory SINGLETON = new GameKeyMappingFactory();
    
    private GameKey[] intKeyToGameKey = new GameKey[InputFactory.getInstance().MAX];
    
    private GameKeyMappingFactory()
    {
        final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();

        this.setGameKey(gameKeyFactory.NONE);
        this.setGameKey(gameKeyFactory.UP);
        this.setGameKey(gameKeyFactory.DOWN);
        this.setGameKey(gameKeyFactory.LEFT);
        this.setGameKey(gameKeyFactory.RIGHT);
        
        this.setGameKey(gameKeyFactory.KEY_POUND);
        this.setGameKey(gameKeyFactory.KEY_STAR);
        this.setGameKey(gameKeyFactory.KEY_NUM0);
        this.setGameKey(gameKeyFactory.KEY_NUM1);
        this.setGameKey(gameKeyFactory.KEY_NUM2);
        this.setGameKey(gameKeyFactory.KEY_NUM3);
        this.setGameKey(gameKeyFactory.KEY_NUM4);
        this.setGameKey(gameKeyFactory.KEY_NUM5);
        this.setGameKey(gameKeyFactory.KEY_NUM6);
        this.setGameKey(gameKeyFactory.KEY_NUM7);
        this.setGameKey(gameKeyFactory.KEY_NUM8);
        this.setGameKey(gameKeyFactory.KEY_NUM9);
        
        this.setGameKey(gameKeyFactory.GAME_A);
        this.setGameKey(gameKeyFactory.GAME_B);
        this.setGameKey(gameKeyFactory.GAME_C);
        this.setGameKey(gameKeyFactory.GAME_D);
        
        this.setGameKey(gameKeyFactory.FIRE);
        
        this.setGameKey(gameKeyFactory.LEVEL_DOWN);
        this.setGameKey(gameKeyFactory.LEVEL_UP);
    }
    
    public GameKey getGameKey(int key)
    {
        // this.logUtil.putF("Getting GameKey: " + key, "GameKey", commonStrings.GET_INSTANCE);
        return this.intKeyToGameKey[key];
    }

    private void setGameKey(GameKey gameKey)
    {
        this.intKeyToGameKey[gameKey.getId()] = gameKey;
    }
    
    public static GameKeyMappingFactory getInstance()
    {
        return SINGLETON;
    }
}
