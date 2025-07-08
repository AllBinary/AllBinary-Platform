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
package org.allbinary.graphics.form;

import org.allbinary.canvas.Processor;
import org.allbinary.game.input.PlayerGameInputCompositeInterface;

public class InputProcessor extends Processor
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

    private final PlayerGameInputCompositeInterface playerGameInputCompositeInterface;
    
    public InputProcessor(PlayerGameInputCompositeInterface playerGameInputCompositeInterface)
    {
        this.playerGameInputCompositeInterface = playerGameInputCompositeInterface;
    }
    
    public void process() throws Exception
    {
        //PreLogUtil.put(commonStrings.START, this, commonStrings.PROCESS);
        
        this.playerGameInputCompositeInterface.getPlayerGameInput().update();
        // AllBinaryGameLayerManager gameLayerManager =
        // this.gameCanvas.getLayerManager();
        // this.processInput(gameLayerManager);
    }

}
