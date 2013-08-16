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

import org.allbinary.util.BasicArrayList;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;

public class GameInputProcessorComposite extends PlayerGameInput
{
    private final GameInputProcessorInterface gameInputProcessorInterface;
    
    public GameInputProcessorComposite(
            GameInputProcessorInterface gameInputProcessorInterface)
    {
        super(new BasicArrayList());
        
        this.gameInputProcessorInterface = gameInputProcessorInterface;
    }
    
    public synchronized void update()
    {
        try
        {
            BasicArrayList list = this.getGameKeyEventList();
            this.gameInputProcessorInterface.onInput(list);
        }
        catch(Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().UPDATE, e));
        }
    }
}
