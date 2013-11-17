package org.allbinary.input;

import org.allbinary.android.input.motion.AnalogLocationInputProcessor;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.input.GameInputProcessor;
import allbinary.game.input.GameKeyEventSourceInterface;
import allbinary.game.input.GameKeyFactory;
import allbinary.game.input.analog.AnalogLocationInput;
import allbinary.game.input.event.GameKeyEvent;
import allbinary.game.input.event.GameKeyEventFactory;
import allbinary.graphics.CustomGPoint;
import allbinary.layer.AllBinaryLayerManager;

public class DirectionalAnalogLocationInputProcessor 
extends AnalogLocationInputProcessor
implements GameKeyEventSourceInterface
{
	private final GameInputProcessor[] inputProcessorArray;
	
	private final GameKeyFactory gameKeyFactory = GameKeyFactory.getInstance();
	
	private GameKeyEvent leftGameKeyEvent;
	private GameKeyEvent rightGameKeyEvent;
	private GameKeyEvent upGameKeyEvent;
	private GameKeyEvent downGameKeyEvent;
	
	public DirectionalAnalogLocationInputProcessor(GameInputProcessor[] inputProcessorArray)
	{
		this.inputProcessorArray = inputProcessorArray;
		
		try
		{
			this.leftGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.LEFT);
			this.rightGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.RIGHT);
			this.upGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.UP);
			this.downGameKeyEvent = GameKeyEventFactory.getInstance().getInstance(this, gameKeyFactory.DOWN);
		}
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().CONSTRUCTOR, e));
        }		
	}
	
	public void process(AllBinaryLayerManager allbinaryLayerManager, AnalogLocationInput analogLocationInput)
	{
		try
		{
	        CustomGPoint customGPoint;
	    	customGPoint = analogLocationInput.getCustomGPoint();
	    	
	    	int x = customGPoint.getX();
	    	int y = customGPoint.getY();
	    	
	    	if(x < 0)
	    	{
	    		inputProcessorArray[this.leftGameKeyEvent.getKey()].process(allbinaryLayerManager, this.leftGameKeyEvent, x);
	    	}
	    	else
	    		if(x > 0)
	    		{
	    			inputProcessorArray[this.rightGameKeyEvent.getKey()].process(allbinaryLayerManager, this.rightGameKeyEvent, x);
	    		}

	    	if(y < 0)
	    	{
	    		inputProcessorArray[this.downGameKeyEvent.getKey()].process(allbinaryLayerManager, this.downGameKeyEvent, y);
	    	}
	    	else
	    		if(y > 0)
	    		{
	    			inputProcessorArray[this.upGameKeyEvent.getKey()].process(allbinaryLayerManager, this.upGameKeyEvent, y);
	    		}			
		}
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Unable to process analog input", this, CommonStrings.getInstance().PROCESS, e));
        }		
	}
	
	public int getSourceId()
	{
		return 0;
	}
}
