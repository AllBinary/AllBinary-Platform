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

        this.set(gameKeyFactory.NONE);
        this.set(gameKeyFactory.UP);
        this.set(gameKeyFactory.DOWN);
        this.set(gameKeyFactory.LEFT);
        this.set(gameKeyFactory.RIGHT);
        
        this.set(gameKeyFactory.KEY_POUND);
        this.set(gameKeyFactory.KEY_STAR);
        this.set(gameKeyFactory.KEY_NUM0);
        this.set(gameKeyFactory.KEY_NUM1);
        this.set(gameKeyFactory.KEY_NUM2);
        this.set(gameKeyFactory.KEY_NUM3);
        this.set(gameKeyFactory.KEY_NUM4);
        this.set(gameKeyFactory.KEY_NUM5);
        this.set(gameKeyFactory.KEY_NUM6);
        this.set(gameKeyFactory.KEY_NUM7);
        this.set(gameKeyFactory.KEY_NUM8);
        this.set(gameKeyFactory.KEY_NUM9);
        
        this.set(gameKeyFactory.GAME_A);
        this.set(gameKeyFactory.GAME_B);
        this.set(gameKeyFactory.GAME_C);
        this.set(gameKeyFactory.GAME_D);
        
        this.set(gameKeyFactory.FIRE);
        
        this.set(gameKeyFactory.LEVEL_DOWN);
        this.set(gameKeyFactory.LEVEL_UP);
    }
    
    public GameKey getInstance(int key)
    {
        // logUtil.put("Getting GameKey: " + key, "GameKey", commonStrings.GET_INSTANCE);
        return intKeyToGameKey[key];
    }

    private void set(GameKey gameKey)
    {
        intKeyToGameKey[gameKey.getId()] = gameKey;
    }
    
    public static GameKeyMappingFactory getInstance()
    {
        return SINGLETON;
    }
}
