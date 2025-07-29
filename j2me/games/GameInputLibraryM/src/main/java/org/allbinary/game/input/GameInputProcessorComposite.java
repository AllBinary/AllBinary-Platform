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

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.util.BasicArrayList;

public class GameInputProcessorComposite extends PlayerGameInput
{

    private final String name;
    private final GameInputProcessorInterface gameInputProcessorInterface;
    
    public GameInputProcessorComposite(String name,
            GameInputProcessorInterface gameInputProcessorInterface)
    {
        super(new BasicArrayList(), -1);
        
        this.name = name;
        this.gameInputProcessorInterface = gameInputProcessorInterface;
    }

    @Override
    public synchronized void update()
    {
        try
        {
            BasicArrayList list = this.getGameKeyEventList();
            this.gameInputProcessorInterface.onInput(list);
        }
        catch(Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, commonStrings.UPDATE, e);
        }
    }
    
    private final String NAME_LABEL = " GameInputProcessorInterface: ";
    
    public String toString()
    {
        return new StringMaker()
                .append(super.toString())
                .append(NAME_LABEL)
                .append(name)
                .toString();
    }
}
