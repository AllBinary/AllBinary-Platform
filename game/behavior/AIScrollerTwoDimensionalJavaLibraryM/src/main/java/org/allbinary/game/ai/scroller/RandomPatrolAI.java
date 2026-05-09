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

import java.util.Hashtable;

import javax.microedition.lcdui.Canvas;

import org.allbinary.game.input.GameInput;
import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;

public class RandomPatrolAI extends BasePatrolAI
{
    private boolean changedDirection = true;
    
    public RandomPatrolAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
       throws Exception
    {
        super(hashtable, ownerLayerInterface, gameInput);

        this.updateRandomDistance();
    }

    private void updateRandomDistance()
    {
        this.currentDistance = MyRandomFactory.getInstance().getAbsoluteNextInt(this.maxDistance.intValue());
    }

    @Override
    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
    throws Exception
    {
        // this.logUtil.putF(this.commonStrings.START, this, this.commonStrings.PROCESS);

        super.update();

        super.processKeyAI(this.keyDirection);

        if (this.changedDirection)
        {
            // this.logUtil.putF("Fire", this, this.commonStrings.PROCESS);
            // PreLogUtil.put("Fire", this, this.commonStrings.PROCESS);
            super.processKeyAI(Canvas.KEY_NUM1);

            this.updateRandomDistance();
            this.changedDirection = false;
        }
     }

    @Override
    protected void nextDirection()
    {
        super.nextDirection();
        this.changedDirection = true;
    }
}