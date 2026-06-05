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
import org.allbinary.input.motion.button.BasicTouchInputFactory;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public class PlatformFormInputMappingFactory
{

    private static Object instance = NullUtil.getInstance().NULL_OBJECT;
    
    public static PlatformFormInputMappingFactory getInstance() {
        
        if(PlatformFormInputMappingFactory.instance == NullUtil.getInstance().NULL_OBJECT) {
            PlatformFormInputMappingFactory.instance = new PlatformFormInputMappingFactory();
        }

        return (PlatformFormInputMappingFactory) PlatformFormInputMappingFactory.instance;
    }
 
    protected final LogUtil logUtil = LogUtil.getInstance();
    
    private InputToGameKeyMapping inputToGameKeyMapping = InputToGameKeyMapping.getNullInstance();

    public InputToGameKeyMapping getOrCreate()
    {
        try
        {
            if (this.inputToGameKeyMapping == InputToGameKeyMapping.getNullInstance())
            {
                PCKeyFactory pcKeyFactory = PCKeyFactory.getInstance();

                final InputToGameKeyMapping inputToGameKeyMapping = new InputToGameKeyMapping();

                final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
                final BasicTouchInputFactory basicTouchInputFactory = BasicTouchInputFactory.getInstance();
                
                inputToGameKeyMapping.add(gameKeyFactory.UP, pcKeyFactory.DPAD_UP);
                inputToGameKeyMapping.add(gameKeyFactory.DOWN, pcKeyFactory.DPAD_DOWN);
                inputToGameKeyMapping.add(gameKeyFactory.LEFT, pcKeyFactory.DPAD_LEFT);
                inputToGameKeyMapping.add(gameKeyFactory.RIGHT, pcKeyFactory.DPAD_RIGHT);

                inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, pcKeyFactory.ENTER);

                //inputToGameKeyMapping.add(gameKeyFactory.KEY_NUM1, androidKeyFactory.DPAD_CENTER);

                inputToGameKeyMapping.add(gameKeyFactory.UP, basicTouchInputFactory.UP);
                inputToGameKeyMapping.add(gameKeyFactory.LEFT, basicTouchInputFactory.LEFT);
                inputToGameKeyMapping.add(gameKeyFactory.RIGHT, basicTouchInputFactory.RIGHT);
                inputToGameKeyMapping.add(gameKeyFactory.DOWN, basicTouchInputFactory.DOWN);
                
                this.inputToGameKeyMapping = inputToGameKeyMapping;
            }
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            this.logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
        }
        return this.inputToGameKeyMapping;
    }

}
