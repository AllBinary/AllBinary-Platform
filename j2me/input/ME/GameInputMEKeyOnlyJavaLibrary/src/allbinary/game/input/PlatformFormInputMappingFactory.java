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

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.mapping.InputToGameKeyMapping;

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

        inputToGameKeyMapping.add(GameKey.UP, GameKey.UP);

        inputToGameKeyMapping.add(GameKey.DOWN, GameKey.DOWN);

        inputToGameKeyMapping.add(GameKey.LEFT, GameKey.LEFT);

        inputToGameKeyMapping.add(GameKey.RIGHT, GameKey.RIGHT);

        /*
        inputToGameKeyMapping.add(GameKey.UP, GameKey.KEY_NUM2);
        inputToGameKeyMapping.add(GameKey.LEFT, GameKey.KEY_NUM4);
        inputToGameKeyMapping.add(GameKey.RIGHT, GameKey.KEY_NUM6);
        inputToGameKeyMapping.add(GameKey.DOWN, GameKey.KEY_NUM8);
        */

        inputToGameKeyMapping.add(GameKey.KEY_NUM1, GameKey.GAME_A);

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
