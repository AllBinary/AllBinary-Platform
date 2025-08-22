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
package org.allbinary.input.motion.button;

import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.InputFactory;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.string.CommonStrings;

public final class TouchButtonInput extends Input 
    implements GameKeyEventSourceInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    private final int TOUCH_BUTTON_SOURCE_ID = this.gameKeyEventFactory.TOUCH_BUTTON_SOURCE_ID;
    
    private GameKeyEvent gameKeyEvent = GameKeyEvent.NONE;

    public TouchButtonInput(int id, String name)
    {
        super(id, name);

        final InputFactory inputFactory = InputFactory.getInstance();
        inputFactory.add(this.getId(), this);
        
        //logUtil.put("Created", this, commonStrings.CONSTRUCTOR);
    }

    public void update(InputToGameKeyMapping inputToGameKeyMapping)
    {
        try
        {
            GameKey gameKey = inputToGameKeyMapping.getInstance(this.getId());
            this.gameKeyEvent = this.gameKeyEventFactory.getInstance(this, gameKey);
        }
        catch (Exception e)
        {
            final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e);
        }
    }

    @Override
    public int getSourceId()
    {
        return TOUCH_BUTTON_SOURCE_ID;
    }

    public GameKeyEvent getGameKeyEvent()
    {
        return gameKeyEvent;
    }
}
