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
