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
package allbinary.graphics.form;

import allbinary.canvas.Processor;
import allbinary.game.input.PlayerGameInputCompositeInterface;

public class InputProcessor extends Processor
{
    private final PlayerGameInputCompositeInterface playerGameInputCompositeInterface;
    
    public InputProcessor(PlayerGameInputCompositeInterface playerGameInputCompositeInterface)
    {
        this.playerGameInputCompositeInterface = playerGameInputCompositeInterface;
    }
    
    public void process() throws Exception
    {
        //PreLogUtil.put(CommonStrings.getInstance().START, this, CommonStrings.getInstance().PROCESS);
        
        this.playerGameInputCompositeInterface.getPlayerGameInput().update();
        // AllBinaryGameLayerManager gameLayerManager =
        // this.gameCanvas.getLayerManager();
        // this.processInput(gameLayerManager);
    }

}
