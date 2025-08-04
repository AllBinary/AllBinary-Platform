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
package org.allbinary.game.ai.scroller;

import javax.microedition.lcdui.Canvas;

import org.allbinary.game.ai.BasicAI;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.time.GameTickTimeDelayHelperFactory;
import org.allbinary.time.TimeDelayHelper;

public class TimedFireAI extends BasicAI
{
	public static final Integer TIME = SmallIntegerSingletonFactory.getInstance().getInstance(1);

        protected final TimeDelayHelper maxFireDelayTimeHelper = new TimeDelayHelper(0);

    private final int delay;
    
    public TimedFireAI(int delay, 
            AllBinaryLayer ownerLayerInterface,
            GameInput gameInput)
        throws Exception
    {
        super(ownerLayerInterface, gameInput);

        this.delay = delay;
    }
    
    @Override
    public void processAI(AllBinaryLayerManager allBinaryLayerManager) throws Exception
    {

        TimeFiredInterface timeFiredInterface = 
            (TimeFiredInterface) this.getOwnerLayerInterface();
        
        if (this.maxFireDelayTimeHelper.isTime(GameTickTimeDelayHelperFactory.getInstance().startTime) &&
                //Fire only if owner has not fired from a different behavoir/AI
                timeFiredInterface.getLastFireTime() + this.maxFireDelayTimeHelper.delay < this.maxFireDelayTimeHelper.getStartTime())
        {
            super.processAI(Canvas.KEY_NUM1);

            this.maxFireDelayTimeHelper.delay = delay;
        }
    }
}
