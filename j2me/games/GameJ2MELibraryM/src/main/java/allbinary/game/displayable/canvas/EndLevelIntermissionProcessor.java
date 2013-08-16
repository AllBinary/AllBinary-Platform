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
package allbinary.game.displayable.canvas;

import allbinary.canvas.Processor;

public class EndLevelIntermissionProcessor extends Processor
{
    private AllBinaryGameCanvas gameCanvas;
    
    public EndLevelIntermissionProcessor(AllBinaryGameCanvas gameCanvas)
    {
        this.gameCanvas = gameCanvas;
    }
    
    public void process() throws Exception
    {
        this.gameCanvas.processEndLevelIntermissionGameState();
    }
}
