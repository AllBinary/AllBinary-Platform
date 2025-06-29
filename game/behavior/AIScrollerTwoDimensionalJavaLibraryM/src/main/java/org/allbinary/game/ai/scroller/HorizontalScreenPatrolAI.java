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

import org.allbinary.game.ai.BasicAI;
import org.allbinary.game.input.GameInput;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.rand.MyRandomFactory;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.media.audio.PlayerQueue;
import org.allbinary.media.audio.SecondaryPlayerQueueFactory;
import org.allbinary.media.audio.Sound;
import org.allbinary.time.TimeDelayHelper;

public class HorizontalScreenPatrolAI
extends BasicAI
{
    //private boolean changedDirection = true;

    protected int keyDirection = Canvas.LEFT;

    private final DisplayInfoSingleton displayInfoSingleton = DisplayInfoSingleton.getInstance();
    
    private int firingX = displayInfoSingleton.getLastHalfWidth();
    private int firedIndex = 0;
    private final int MAX_FIRE = 28;
    
    private final TimeDelayHelper timeDelayHelper = new TimeDelayHelper(240);

    private final PlayerQueue secondaryPlayerQueue = SecondaryPlayerQueueFactory.getInstance();
    
    private final Sound sound;
    
    public static final Integer SOUND = SmallIntegerSingletonFactory.getInstance().getInstance(433);
    
    public HorizontalScreenPatrolAI(Hashtable hashtable,
            AllBinaryLayer ownerLayerInterface, GameInput gameInput)
       throws Exception
    {
        super(ownerLayerInterface, gameInput);
        
        this.sound = (Sound) hashtable.get(SOUND);
        //SleighBells4Sound.getInstance()
        if(this.sound == null)
        {
            throw new Exception("No Sound Provided!!");
        }
    }

    //Current go left always and fire when towards the middle
    public void processAI(AllBinaryLayerManager allBinaryLayerManager)
    throws Exception
    {
        //LogUtil.put(LogFactory.getInstance(commonStrings.START, this, "processAI"));

        super.processAI(this.keyDirection);

		if (timeDelayHelper.isTime())
		{
			AllBinaryLayer layerInterface = this.getOwnerLayerInterface();

			int currentOwnerLayerX = layerInterface.getX();

			// if(this.firedIndex > 0)
			// {
			// PreLogUtil.put("Fire: " + currentOwnerLayerX + " < " +
			// this.firingX + " > " + -layerInterface.getWidth() + " index: " +
			// this.firedIndex, this, "processAI");
			// }

			if (currentOwnerLayerX < -layerInterface.getWidth())
			{
				int y = MyRandomFactory.getInstance().getAbsoluteNextIntAllowZero(50);

				// PreLogUtil.put(PositionStrings.getInstance().Y_LABEL + y, this, "processAI");

				layerInterface.setPosition(displayInfoSingleton.getLastWidth() + layerInterface.getWidth() + 50, y, layerInterface.getZ());

				firedIndex = 0;
				this.firingX = displayInfoSingleton.getLastHalfWidth()
						+ MyRandomFactory.getInstance().getNextInt(
								displayInfoSingleton.getLastHalfWidth());
				
				secondaryPlayerQueue.add(this.sound);
			}

			if (currentOwnerLayerX < this.firingX
					&& currentOwnerLayerX > -layerInterface.getWidth())
			// if (this.changedDirection)
			{
				// PreLogUtil.put("Fire: " + currentOwnerLayerX +"<" + this.firingX + " index: " + this.firedIndex, this, "processAI");
				super.processAI(Canvas.KEY_NUM1);

				// this.changedDirection = false;

				int FIRE = (((AllBinaryGameLayerManager) allBinaryLayerManager).getGameInfo().getCurrentLevel() + 12) >> 2;

				if (FIRE > MAX_FIRE) 
				{
					FIRE = MAX_FIRE;
				}

				if (firedIndex > FIRE)
				{
					firedIndex = 0;
					this.firingX = Integer.MIN_VALUE;
				}
				firedIndex++;
			}
		}
     }

    protected void nextDirection()
    {
        //super.nextDirection();
        //this.changedDirection = true;
    }
}