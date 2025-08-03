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
package org.allbinary.game.ai;

import java.util.Hashtable;

import org.allbinary.game.input.GameInput;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.time.GameTickTimeDelayHelper;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class MultiKeyPressesAI extends KeyPressesAI
{
    private final TimeDelayHelper toggleTimeHelper = new TimeDelayHelper(600);

    private final GameTickTimeDelayHelper gameTickTimeDelayHelper = GameTickTimeDelayHelperFactory.getInstance();
    
    public MultiKeyPressesAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
            throws Exception
    {
        super(hashtable, ownerLayerInterface, gameInput);

        this.update();
    }

    @Override
    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
            throws Exception
    {
    }

    private final GameKeyEventFactory gameKeyEventFactory = GameKeyEventFactory.getInstance();
    
    private void update() throws Exception
    {
        GameInput gameInput = this.getGameInput();
        
        final Integer[] keys = this.keys;
        final int size = keys.length;
        if (isOn())
        {
            for (int index = 0; index < size; index++)
            {
                int key = keys[index].intValue();
                if (key != -1)
                {
                    gameInput.add(gameKeyEventFactory.getInstance(this, key));
                }
            }
            // logUtil.put(, this, "update");
        }
        else
        {
            for (int index = 0; index < size; index++)
            {
                int key = keys[index].intValue();
                if (key != -1)
                {
                    gameInput.addForRemoval(gameKeyEventFactory.getInstance(this, key));
                }
            }
            // logUtil.put("remove", this, commonStrings.PROCESS);
        }
    }

    @Override
    public void toggle() throws Exception
    {
        if (this.toggleTimeHelper.isTime(this.gameTickTimeDelayHelper.startTime))
        {
            super.toggle();
            this.update();
        }
    }

    @Override
    public void disable() throws Exception
    {
        if (this.toggleTimeHelper.isTime(this.gameTickTimeDelayHelper.startTime))
        {
            super.disable();
            this.update();
        }
    }

    @Override
    public void enable() throws Exception
    {
        if (this.toggleTimeHelper.isTime(this.gameTickTimeDelayHelper.startTime))
        {
            super.enable();
            this.update();
        }
    }
}