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

import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;

public class PlatformFormInputMappingFactory
{
    private static final PlatformFormInputMappingFactory instance = 
        new PlatformFormInputMappingFactory();
    
    private static InputToGameKeyMapping SINGLETON = null;

    public static InputToGameKeyMapping getInstance()
    {
        try
        {
            if (SINGLETON == null)
            {
                InputToGameKeyMapping inputToGameKeyMapping = new InputToGameKeyMapping();

                final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
                
        inputToGameKeyMapping.add(gameKeyFactory.UP, gameKeyFactory.UP);

        inputToGameKeyMapping.add(gameKeyFactory.DOWN, gameKeyFactory.DOWN);

        inputToGameKeyMapping.add(gameKeyFactory.LEFT, gameKeyFactory.LEFT);

        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, gameKeyFactory.RIGHT);

        /*
        inputToGameKeyMapping.add(gameKeyFactory.UP, gameKeyFactory.KEY_NUM2);
        inputToGameKeyMapping.add(gameKeyFactory.LEFT, gameKeyFactory.KEY_NUM4);
        inputToGameKeyMapping.add(gameKeyFactory.RIGHT, gameKeyFactory.KEY_NUM6);
        inputToGameKeyMapping.add(gameKeyFactory.DOWN, gameKeyFactory.KEY_NUM8);
        */

        inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, gameKeyFactory.GAME_A);

                SINGLETON = inputToGameKeyMapping;
            }
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, instance, CommonStrings.getInstance().GET_INSTANCE, e));
        }
        return SINGLETON;
    }

}
