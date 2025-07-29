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

public class SingleAnalogControllerVisitor
    extends AnalogControllerVisitor {

    private final int playerInputId;

    public SingleAnalogControllerVisitor(final int playerInputId) {
        this.playerInputId = playerInputId;
    }

    @Override
    public void process(final AllBinaryLayerManager allbinaryLayerManager, final AnalogLocationInputProcessor analogLocationInputProcessor) {
        final AnalogLocationInput analogLocationInput = AnalogLocationInputFactory.getInstance().getInstance(playerInputId);
        analogLocationInputProcessor.process(allbinaryLayerManager, analogLocationInput);
    }
}
