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
package allbinary.input.motion.button;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.GameKey;
import allbinary.game.input.GameKeyEventSourceInterface;
import allbinary.game.input.Input;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.game.input.mapping.InputToGameKeyMapping;

public final class TouchButtonInput extends Input 
    implements GameKeyEventSourceInterface
{
    public static final int STANDARD_BUTTON_SIZE = 64;
    
    private GameKey gameKey;
    private GameKeyEvent gameKeyEvent;

    public TouchButtonInput(int id, String name)
    {
        super(id, name);

        this.inputFactory.add(this.getId(), this);
        
        //LogUtil.put(LogFactory.getInstance("Created", this, CommonStrings.getInstance().CONSTRUCTOR));
    }

    public void update(InputToGameKeyMapping inputToGameKeyMapping)
    {
        try
        {
            this.setGameKey(inputToGameKeyMapping.getInstance(this.getId()));
            this.setGameKeyEvent(GameKeyEventFactory.getInstance().getInstance(this, getGameKey()));
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }

    public int getSourceId()
    {
        return 2;
    }

    public void setGameKey(GameKey gameKey)
    {
        this.gameKey = gameKey;
    }

    public GameKey getGameKey()
    {
        return gameKey;
    }

    public void setGameKeyEvent(GameKeyEvent gameKeyEvent)
    {
        this.gameKeyEvent = gameKeyEvent;
    }

    public GameKeyEvent getGameKeyEvent()
    {
        return gameKeyEvent;
    }
}
