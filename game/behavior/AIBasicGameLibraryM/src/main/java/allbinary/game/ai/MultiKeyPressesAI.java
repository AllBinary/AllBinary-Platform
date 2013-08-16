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
package allbinary.game.ai;

import java.util.Hashtable;

import allbinary.game.input.GameInput;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.time.GameTickTimeDelayHelperFactory;
import allbinary.time.TimeDelayHelper;

public class MultiKeyPressesAI extends KeyPressesAI
{
    private final TimeDelayHelper toggleTimeHelper = new TimeDelayHelper(600);

    private final GameTickTimeDelayHelperFactory gameTickTimeDelayHelperFactory = 
        GameTickTimeDelayHelperFactory.getInstance();
    
    public MultiKeyPressesAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
            throws Exception
    {
        super(hashtable, ownerLayerInterface, gameInput);

        this.update();
    }

    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
    }

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private void update() throws Exception
    {
        GameInput gameInput = this.getGameInput();
        
        if (isOn())
        {
            for (int index = 0; index < keys.length; index++)
            {
                int key = keys[index].intValue();
                if (key != -1)
                {
                    gameInput.add(gameKeyEventFactory.getInstance(this, key));
                }
            }
            // LogUtil.put(LogFactory.getInstance(, this, "update"));
        }
        else
        {
            for (int index = 0; index < keys.length; index++)
            {
                int key = keys[index].intValue();
                if (key != -1)
                {
                    gameInput.addForRemoval(gameKeyEventFactory.getInstance(this, key));
                }
            }
            // LogUtil.put(LogFactory.getInstance("remove", this, "processAI"));
        }
    }

    public void toggle() throws Exception
    {
        if (this.toggleTimeHelper.isTime(this.gameTickTimeDelayHelperFactory.getStartTime()))
        {
            super.toggle();
            this.update();
        }
    }

    public void disable() throws Exception
    {
        if (this.toggleTimeHelper.isTime(this.gameTickTimeDelayHelperFactory.getStartTime()))
        {
            super.disable();
            this.update();
        }
    }

    public void enable() throws Exception
    {
        if (this.toggleTimeHelper.isTime(this.gameTickTimeDelayHelperFactory.getStartTime()))
        {
            super.enable();
            this.update();
        }
    }
}