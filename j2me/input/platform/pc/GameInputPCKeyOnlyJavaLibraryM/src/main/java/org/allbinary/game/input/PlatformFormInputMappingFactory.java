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

import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class PlatformFormInputMappingFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private static final PlatformFormInputMappingFactory instance = 
        new PlatformFormInputMappingFactory();
    
    public static PlatformFormInputMappingFactory getInstance() {
        return instance;
    }
    
    private InputToGameKeyMapping SINGLETON = InputToGameKeyMapping.NULL_INPUT_TO_GAME_KEY_MAPPING;

    public InputToGameKeyMapping getOrCreate()
    {
        try
        {
            if (SINGLETON == InputToGameKeyMapping.NULL_INPUT_TO_GAME_KEY_MAPPING)
            {
                PCKeyFactory pcKeyFactory = PCKeyFactory.getInstance();

                final InputToGameKeyMapping inputToGameKeyMapping = new InputToGameKeyMapping();

                final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
                
        inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory.DPAD_UP);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory.DPAD_DOWN);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory.DPAD_LEFT);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory.DPAD_RIGHT);

        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory.ENTER);

                //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, androidKeyFactory.DPAD_CENTER);
                
                SINGLETON = inputToGameKeyMapping;
            }
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
        }
        return SINGLETON;
    }

}
