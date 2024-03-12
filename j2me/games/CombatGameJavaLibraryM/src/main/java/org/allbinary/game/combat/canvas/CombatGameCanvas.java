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
package org.allbinary.game.combat.canvas;

import javax.microedition.lcdui.CommandListener;

import org.allbinary.game.combat.destroy.DestroyedLayerProcessor;
import org.allbinary.game.combat.destroy.event.DestroyEventCircularStaticPool;
import org.allbinary.game.combat.destroy.event.DestroyedEventHandler;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.GameFeatureFactory;
import org.allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import org.allbinary.game.init.BasicBuildGameInitializerFactory;
import org.allbinary.game.layer.AllBinaryGameLayerManager;
import org.allbinary.game.layer.drop.DropLayerProcessor;
import org.allbinary.game.layer.identification.GroupLayerManagerListener;
import org.allbinary.game.score.HighScoresFactoryInterface;
import org.allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import org.allbinary.layer.BasicLayerProcessor;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;

public class CombatGameCanvas extends AllBinaryGameCanvas
{
    protected static BasicLayerProcessor[] basicLayerProcessor = new BasicLayerProcessor[0];

    public CombatGameCanvas(
            final CommandListener cmdListener,
            final AllBinaryGameLayerManager gameLayerManager,
            final HighScoresFactoryInterface highScoresFactoryInterface,
            final BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface,
            final boolean buffered) throws Exception
    {
        super(cmdListener, gameLayerManager,
                highScoresFactoryInterface,
                gameInitializationInterfaceFactoryInterface, buffered);
    }

    private void CombatGameCanvas_init()
    {
        DestroyEventCircularStaticPool.getInstance().init(this);
        ProgressCanvasFactory.getInstance().addPortion(50, "Destroy Events");

        // this.getLayerProcessorVector().insertElementAt(, 1);
        // this.getLayerProcessorVector().add(new TrackingLayerProcessor());
    }

    //@Override
    protected void init(final AbeClientInformationInterface abeClientInformation) throws Exception
    {
        this.CombatGameCanvas_init();
        
        super.init(abeClientInformation);
    }
    
    protected void initConfigurable(final int portion)
    {
        DestroyedLayerProcessor.init();

        ProgressCanvasFactory.getInstance().addPortion(portion, "Basic Processors");

        Features features = Features.getInstance();
        GameFeatureFactory gameFeatureFactory = GameFeatureFactory.getInstance();
        
        if (features.isFeature(gameFeatureFactory.DROPPED_ITEMS)
                && features.isFeature(gameFeatureFactory.DROPPED_ITEMS_FROM_DEATH))
        {
            basicLayerProcessor = new BasicLayerProcessor[2];

            basicLayerProcessor[0] = DestroyedLayerProcessor.getInstance();
            basicLayerProcessor[1] = DropLayerProcessor.getInstance();
        }
        else
        {
            basicLayerProcessor = new BasicLayerProcessor[1];

            basicLayerProcessor[0] = DestroyedLayerProcessor.getInstance();
        }
    }

    protected void processPlayingGame() throws Exception
    {
        super.processPlayingGame();

        for (int index = basicLayerProcessor.length; --index >= 0;)
        {
            basicLayerProcessor[index].process(this.gameLayerManager);
        }
    }
    
    protected void cleanupGame() throws Exception
    {
        super.cleanupGame();

        for (int index = basicLayerProcessor.length; --index >= 0;)
        {
            basicLayerProcessor[index].getList().clear();
        }
        
        GroupLayerManagerListener.getInstance().clear();
        GroupLayerManagerListener.getInstance().log();
        
        // TWB - somehow I need to add and remove listeners between levels
        // without
        // doing it outside the object like this        
        DestroyedEventHandler.getInstance().removeAllListeners();
        
        this.cleanupManager();
    }
    
    protected void cleanupManager()
    throws Exception
    {
    	this.gameLayerManager.cleanup();
    }
}