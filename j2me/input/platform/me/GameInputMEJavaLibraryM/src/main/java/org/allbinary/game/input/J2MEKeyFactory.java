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

public class J2MEKeyFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private static final J2MEKeyFactory instance = new J2MEKeyFactory();

    public static final J2MEKeyFactory getInstance()
    {
        return J2MEKeyFactory.instance;
    }

    private J2MEKeyFactory()
    {
    }

    private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
    
    public void init()
    {
        GameKey[] gameKeyArray = {
        this.gameKeyFactory.DOWN,
        this.gameKeyFactory.FIRE,
        this.gameKeyFactory.GAME_A,
        this.gameKeyFactory.GAME_B,
        this.gameKeyFactory.GAME_C,
        this.gameKeyFactory.GAME_D,
        this.gameKeyFactory.KEY_NUM0,
        this.gameKeyFactory.KEY_NUM1,
        this.gameKeyFactory.KEY_NUM2,
        this.gameKeyFactory.KEY_NUM3,
        this.gameKeyFactory.KEY_NUM4,
        this.gameKeyFactory.KEY_NUM5,
        this.gameKeyFactory.KEY_NUM6,
        this.gameKeyFactory.KEY_NUM7,
        this.gameKeyFactory.KEY_NUM8,
        this.gameKeyFactory.KEY_NUM9,
        this.gameKeyFactory.KEY_POUND,
        this.gameKeyFactory.KEY_STAR,
        this.gameKeyFactory.LEFT,
        this.gameKeyFactory.LEVEL_DOWN,
        this.gameKeyFactory.LEVEL_UP,
        this.gameKeyFactory.NONE,
        this.gameKeyFactory.RIGHT,
        this.gameKeyFactory.UP
        };

        InputFactory inputFactory = InputFactory.getInstance();
        
        for(int index = gameKeyArray.length; --index >= 0;)
        {
            inputFactory.add(gameKeyArray[index].getId(), gameKeyArray[index]);

            //PreLogUtil.put("Input ID: " + gameKeyArray[index].getId(), this, this.commonStrings.INIT);
        }

        // KeyEvent.VK_F3
        //gameKeyFactory.LEVEL_DOWN = new GameKey(0x72, "Cheat Level Up");
        //gameKeyFactory.LEVEL_UP = new GameKey(0x73, "Cheat Level Down");
        //gameKeyFactory.LEVEL_DOWN = gameKeyFactory.NONE;
        //gameKeyFactory.LEVEL_UP = gameKeyFactory.NONE;
    }

    public boolean isSubmission(Input input)
    {
        if (input == this.gameKeyFactory.FIRE)
        {
            return true;
        }
        return false;
    }

    public boolean isDelete(Input input)
    {
        if (input == this.gameKeyFactory.GAME_D)
        {
            return true;
        }
        return false;
    }

    public boolean isBackSpace(Input input)
    {
        return false;
    }

    public boolean isUp(Input input)
    {
        if (input == this.gameKeyFactory.UP)
        {
            return true;
        }
        return false;
    }

    public boolean isDown(Input input)
    {
        if (input == this.gameKeyFactory.DOWN)
        {
            return true;
        }

        return false;
    }

    public boolean isLeft(Input input)
    {
        if (input == this.gameKeyFactory.LEFT)
        {
            return true;
        }
        return false;
    }

    public boolean isRight(Input input)
    {
        if (input == this.gameKeyFactory.RIGHT)
        {
            return true;
        }

        return false;
    }

    public boolean isEnter(Input input)
    {
        if (input == this.gameKeyFactory.FIRE)
        {
            return true;
        }

        return false;
    }
}
