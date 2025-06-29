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

    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
    throws Exception
    {
        // LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "processAI"));

        super.update();

        super.processAI(this.keyDirection);

        if (this.changedDirection)
        {
            // LogUtil.put(LogFactory.getInstance("Fire", this, "processAI"));
            // PreLogUtil.put("Fire", this, "processAI");
            super.processAI(Canvas.KEY_NUM1);

            this.updateRandomDistance();
            this.changedDirection = false;
        }
     }

    protected void nextDirection()
    {
        super.nextDirection();
        this.changedDirection = true;
    }
}