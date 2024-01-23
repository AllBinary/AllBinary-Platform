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

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.input.GameKey;
import org.allbinary.game.input.GameKeyEventSourceInterface;
import org.allbinary.game.input.Input;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.input.mapping.InputToGameKeyMapping;

public final class TouchButtonInput extends Input 
    implements GameKeyEventSourceInterface
{
    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    private final int TOUCH_BUTTON_SOURCE_ID = this.gameKeyEventFactory.TOUCH_BUTTON_SOURCE_ID;
    
    private GameKeyEvent gameKeyEvent;

    public TouchButtonInput(int id, String name)
    {
        super(id, name);

        this.inputFactory.add(this.getId(), this);
        
        //LogUtil.put(LogFactory.getInstance("Created", this, commonStrings.CONSTRUCTOR));
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
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e));
        }
    }

    public int getSourceId()
    {
        return TOUCH_BUTTON_SOURCE_ID;
    }

    public GameKeyEvent getGameKeyEvent()
    {
        return gameKeyEvent;
    }
}
