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

import org.allbinary.game.input.analog.AnalogLocationInput;
import org.allbinary.game.input.analog.AnalogLocationInputFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.util.BasicArrayList;

public class AllAnalogControllerVisitor
extends AnalogControllerVisitor
{
    @Override
    public void process(final AllBinaryLayerManager allbinaryLayerManager, final AnalogLocationInputProcessor analogLocationInputProcessor)
    {
        final BasicArrayList analogLocationInputList = AnalogLocationInputFactory.getInstance().getList();
        
        AnalogLocationInput analogLocationInput;
        for(int index = analogLocationInputList.size() - 1; index >= 0; index--)
        {
        	analogLocationInput = (AnalogLocationInput) analogLocationInputList.get(index);
        	
        	analogLocationInputProcessor.process(allbinaryLayerManager, analogLocationInput);
        }
    }
}
