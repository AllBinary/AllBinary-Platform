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
package org.allbinary.game.input.event;

import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.logic.communication.log.LogUtil;

public class GameKeyEventFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final GameKeyEventFactory instance = new GameKeyEventFactory();

    public static GameKeyEventFactory getInstance()
    {
        return instance;
    }

    public final int TOUCH_BUTTON_SOURCE_ID = 2;
    public final int MOTION_GESTURE_SOURCE_ID = 3;
    
    private final int MAX_SOURCES = 4;

    //private final String SOURCE_ID = "sourceId";    
    
    private GameKeyEvent[][] ARRAY = new GameKeyEvent[MAX_SOURCES][InputFactory.getInstance().MAX];
    //private GameKeyEvent[] ARRAY = new GameKeyEvent[MAX];

    private GameKeyEventFactory()
    {
    }

    public void init()
    {
        //logUtil.put(commonStrings.START, "GameKeyEventFactory", commonStrings.INIT);
        
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
        //logUtil.put(commonStrings.START, this, SOURCE_ID + object.getSourceId());

        GameKeyEvent gameKeyEvent = ARRAY[object.getSourceId()][key];
        //GameKeyEvent gameKeyEvent = ARRAY[key];
        //gameKeyEvent.init(object);
        return gameKeyEvent;
    }

    public GameKeyEvent getInstance(
            GameKeyEventSourceInterface object, Input input)
    throws Exception
    {
        //logUtil.put(commonStrings.START_LABEL, this, SOURCE_ID + object.getSourceId());

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
