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
package allbinary.game.init;

import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;

import abcs.logic.basic.NotImplemented;
import allbinary.game.resource.ResourceInitialization;
import allbinary.graphics.PointFactory;
import allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.logic.math.SmallIntegerSingletonFactory;
import allbinary.math.AngleFactory;

public class CommonGameInitialization 
extends BaseGameInitialization
{
    protected CommonGameInitialization(ResourceInitialization[] resourceInitializationArray,
            int portion)
    {
        super(resourceInitializationArray, portion);
    }

    public void init(CommandListener commandListener, int level) throws Exception
    {
        super.init(commandListener, level);

        ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();

        if (!this.isGameInitialized() && level == resourceLoadingLevelFactory.LOAD_ALL.getLevel())
        {
            //PreLogUtil.put("Game Init", this, CommonStrings.getInstance().INIT);

            this.setGameInitialized(true);

            ProgressCanvas progressCanvas = 
                ProgressCanvasFactory.getInstance();

            SmallIntegerSingletonFactory.getInstance().init();
            progressCanvas.addPortion(50, "Integers");

            PointFactory.getInstance().init();
            progressCanvas.addPortion(50, "Points");

            AngleFactory.getInstance();
            progressCanvas.addPortion(50, "Angles");

            this.initGame();

            this.resourceInitializationArray[GAME_RESOURCES].init();
        }

        super.resourceInitialization(level);
    }

    public void initGame()
    throws Exception
    {
        throw new Exception(NotImplemented.NAME);
    }
}
