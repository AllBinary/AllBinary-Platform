package org.allbinary.android.input.motion;

import org.allbinary.util.BasicArrayList;

import allbinary.game.input.analog.AnalogLocationInput;
import allbinary.game.input.analog.AnalogLocationInputFactory;
import allbinary.layer.AllBinaryLayerManager;

public class AllAnalogControllerVisitor
extends AnalogControllerVisitor
{
    public void process(AllBinaryLayerManager allbinaryLayerManager, AnalogLocationInputProcessor analogLocationInputProcessor)
    {
        BasicArrayList analogLocationInputList = AnalogLocationInputFactory.getInstance().getList();
        
        AnalogLocationInput analogLocationInput;
        for(int index = analogLocationInputList.size() - 1; index >= 0; index--)
        {
        	analogLocationInput = (AnalogLocationInput) analogLocationInputList.get(index);
        	
        	analogLocationInputProcessor.process(allbinaryLayerManager, analogLocationInput);
        }
    }
}
