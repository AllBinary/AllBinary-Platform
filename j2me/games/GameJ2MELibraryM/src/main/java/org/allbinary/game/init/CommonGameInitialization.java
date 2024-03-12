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
package org.allbinary.game.init;

import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.resource.ResourceLoadingLevelFactory;

import org.allbinary.logic.string.CommonStrings;
import org.allbinary.game.resource.ResourceInitialization;
import org.allbinary.graphics.PointFactory;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvas;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.math.AngleFactory;

public class CommonGameInitialization 
extends BaseGameInitialization
{
    protected CommonGameInitialization(final ResourceInitialization[] resourceInitializationArray,
            int portion)
    {
        super(resourceInitializationArray, portion);
    }

    public void init(final AbeClientInformationInterface abeClientInformation, final CommandListener commandListener, final int level) throws Exception
    {
        super.init(abeClientInformation, commandListener, level);

        final ResourceLoadingLevelFactory resourceLoadingLevelFactory = 
            ResourceLoadingLevelFactory.getInstance();

        if (!this.isGameInitialized() && level == resourceLoadingLevelFactory.LOAD_ALL.getLevel())
        {
            //PreLogUtil.put("Game Init", this, commonStrings.INIT);

            this.setGameInitialized(true);

            final ProgressCanvas progressCanvas = 
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
        throw new Exception(CommonStrings.getInstance().NOT_IMPLEMENTED);
    }
}
