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
package org.allbinary.game.score;

import org.allbinary.game.score.displayable.HighScoresCanvas;

public class HighScoresCanvasChangeLevelInputProcessorFactory
implements HighScoresCanvasInputProcessorFactoryInterface
{
    @Override
    public HighScoresCanvasInputProcessor getInstance(HighScoresCanvas highScoresCanvas)
    {
        return new HighScoresCanvasLevelChangeInputProcessor(highScoresCanvas); 
    }
}
