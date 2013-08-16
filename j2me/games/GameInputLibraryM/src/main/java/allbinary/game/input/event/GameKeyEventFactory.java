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
package allbinary.game.input.event;

import allbinary.game.input.GameKeyEventSourceInterface;
import allbinary.game.input.Input;
import allbinary.game.input.InputFactory;

public class GameKeyEventFactory
{
    private static final GameKeyEventFactory instance = new GameKeyEventFactory();

    public static GameKeyEventFactory getInstance()
    {
        return instance;
    }

    private final int MAX_SOURCES = 3;

    private GameKeyEvent[][] ARRAY = new GameKeyEvent[MAX_SOURCES][InputFactory.getInstance().MAX];
    //private GameKeyEvent[] ARRAY = new GameKeyEvent[MAX];

    private GameKeyEventFactory()
    {
    }

    public void init()
    {
        //LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().START, "GameKeyEventFactory", CommonStrings.getInstance().INIT));
        
        /*
        for (int index2 = 0; index2 < MAX; index2++)
        {
            ARRAY[index2] = new GameKeyEvent(null, index2);
        }
        */
        
        int size = InputFactory.getInstance().MAX;
        for (int index = MAX_SOURCES; --index >= 0;)
        {
            for (int index2 = size; --index2 >= 0;)
            {
                ARRAY[index][index2] = new GameKeyEvent(null, index, index2);
            }
        }
    }

    public GameKeyEvent getInstance(
            GameKeyEventSourceInterface object, int key)
    throws Exception
    {
        GameKeyEvent gameKeyEvent = ARRAY[object.getSourceId()][key];
        //GameKeyEvent gameKeyEvent = ARRAY[key];
        //gameKeyEvent.init(object);
        return gameKeyEvent;
    }

    public GameKeyEvent getInstance(
            GameKeyEventSourceInterface object, Input input)
    throws Exception
    {
        GameKeyEvent gameKeyEvent = ARRAY[object.getSourceId()][input.getId()];
        //GameKeyEvent gameKeyEvent = ARRAY[gameKey.getKey().intValue()];
        //gameKeyEvent.init(object);
        return gameKeyEvent;
    }

    /*
    public GameKeyEvent getInstance(GameKeyEventSourceInterface object,
            int originalKey, int gameActionKey, int key) throws Exception
    {
        GameKeyEvent gameKeyEvent = getInstance(object, key);

        gameKeyEvent.setOriginalKey(originalKey);
        gameKeyEvent.setGameActionKey(gameActionKey);

        return gameKeyEvent;
    }
    */
}
