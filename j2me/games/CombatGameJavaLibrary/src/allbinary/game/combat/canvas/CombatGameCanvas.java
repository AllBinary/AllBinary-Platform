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
package allbinary.game.combat.canvas;

import javax.microedition.lcdui.CommandListener;

import allbinary.game.combat.destroy.DestroyedLayerProcessor;
import allbinary.game.combat.destroy.event.DestroyEventCircularStaticPool;
import allbinary.game.combat.destroy.event.DestroyedEventHandler;
import allbinary.game.configuration.feature.Features;
import allbinary.game.configuration.feature.GameFeatureFactory;
import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.game.init.BasicBuildGameInitializerFactory;
import allbinary.game.layer.AllBinaryGameLayerManager;
import allbinary.game.layer.drop.DropLayerProcessor;
import allbinary.game.layer.identification.GroupLayerManagerListener;
import allbinary.game.score.HighScoresFactoryInterface;
import allbinary.graphics.canvas.transition.progress.ProgressCanvasFactory;
import allbinary.layer.BasicLayerProcessor;

public class CombatGameCanvas extends AllBinaryGameCanvas
{
    protected static BasicLayerProcessor[] basicLayerProcessor = new BasicLayerProcessor[0];

    public CombatGameCanvas(
            CommandListener cmdListener,
            AllBinaryGameLayerManager gameLayerManager,
            HighScoresFactoryInterface highScoresFactoryInterface,
            BasicBuildGameInitializerFactory gameInitializationInterfaceFactoryInterface,
            boolean buffered) throws Exception
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

    protected void init() throws Exception
    {
        this.CombatGameCanvas_init();
        
        super.init();
    }
    
    protected void initConfigurable(int portion)
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

        AllBinaryGameLayerManager layerManager = this.getLayerManager();

        for (int index = basicLayerProcessor.length; --index >= 0;)
        {
            basicLayerProcessor[index].process(layerManager);
        }
    }
    
    protected void cleanupGame() throws Exception
    {
        super.cleanupGame();

        GroupLayerManagerListener.getInstance().clear();
        GroupLayerManagerListener.getInstance().log();
        
        // TWB - somehow I need to add and remove listeners between levels
        // without
        // doing it outside the object like this        
        DestroyedEventHandler.getInstance().removeAllListeners();
    }
}