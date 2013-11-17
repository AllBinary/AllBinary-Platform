package org.allbinary.android.input.motion;

import allbinary.game.input.analog.AnalogLocationInput;
import allbinary.game.input.analog.AnalogLocationInputFactory;
import allbinary.layer.AllBinaryLayerManager;

public class SingleAnalogControllerVisitor
extends AnalogControllerVisitor
{
	private final int playerInputId;
	
	public SingleAnalogControllerVisitor(int playerInputId)
	{
		this.playerInputId = playerInputId;
	}
	
    public void process(AllBinaryLayerManager allbinaryLayerManager, AnalogLocationInputProcessor analogLocationInputProcessor)
    {
    	AnalogLocationInput analogLocationInput = AnalogLocationInputFactory.getInstance().getInstance(playerInputId);
       	analogLocationInputProcessor.process(
       			allbinaryLayerManager, analogLocationInput);
    }
}
