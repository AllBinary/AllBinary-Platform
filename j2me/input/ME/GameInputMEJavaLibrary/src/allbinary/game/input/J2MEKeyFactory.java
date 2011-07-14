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
package allbinary.game.input;

public class J2MEKeyFactory
{
    private static final J2MEKeyFactory instance = new J2MEKeyFactory();

    public static final J2MEKeyFactory getInstance()
    {
        return instance;
    }

    private J2MEKeyFactory()
    {
    }

    public void init()
    {
        GameKey[] gameKeyArray = {
        GameKey.DOWN,
        GameKey.FIRE,
        GameKey.GAME_A,
        GameKey.GAME_B,
        GameKey.GAME_C,
        GameKey.GAME_D,
        GameKey.KEY_NUM0,
        GameKey.KEY_NUM1,
        GameKey.KEY_NUM2,
        GameKey.KEY_NUM3,
        GameKey.KEY_NUM4,
        GameKey.KEY_NUM5,
        GameKey.KEY_NUM6,
        GameKey.KEY_NUM7,
        GameKey.KEY_NUM8,
        GameKey.KEY_NUM9,
        GameKey.KEY_POUND,
        GameKey.KEY_STAR,
        GameKey.LEFT,
        GameKey.LEVEL_DOWN,
        GameKey.LEVEL_UP,
        GameKey.NONE,
        GameKey.RIGHT,
        GameKey.UP
        };

        for(int index = gameKeyArray.length; --index >= 0;)
        {
            Input.inputIntegerArray[gameKeyArray[index].getId()] = gameKeyArray[index];

            //PreLogUtil.put("Input ID: " + gameKeyArray[index].getId(), this, "init");
        }

        // KeyEvent.VK_F3
        //GameKey.LEVEL_DOWN = new GameKey(0x72, "Cheat Level Up");
        //GameKey.LEVEL_UP = new GameKey(0x73, "Cheat Level Down");
        //GameKey.LEVEL_DOWN = GameKey.NONE;
        //GameKey.LEVEL_UP = GameKey.NONE;
    }

    public boolean isSubmission(Input input)
    {
        if (input == GameKey.FIRE)
        {
            return true;
        }
        return false;
    }

    public boolean isDelete(Input input)
    {
        if (input == GameKey.GAME_D)
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
        if (input == GameKey.UP)
        {
            return true;
        }
        return false;
    }

    public boolean isDown(Input input)
    {
        if (input == GameKey.DOWN)
        {
            return true;
        }

        return false;
    }

    public boolean isLeft(Input input)
    {
        if (input == GameKey.LEFT)
        {
            return true;
        }
        return false;
    }

    public boolean isRight(Input input)
    {
        if (input == GameKey.RIGHT)
        {
            return true;
        }

        return false;
    }

    public boolean isEnter(Input input)
    {
        if (input == GameKey.FIRE)
        {
            return true;
        }

        return false;
    }
}
