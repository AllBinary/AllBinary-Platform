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
package org.allbinary.game.testgamedemo.level;

import allbinary.game.ai.ArtificialIntelligenceInterfaceFactoryInterfaceFactory;
import allbinary.game.displayable.canvas.AllBinaryGameCanvas;
import allbinary.layer.AllBinaryLayer;
import allbinary.layer.LayerInterfaceFactory;
import allbinary.layer.LayerInterfaceVisitor;

public class TestGameDemoLevelBuilder implements LayerInterfaceVisitor
{
	private final AllBinaryGameCanvas gameCanvas;

    public TestGameDemoLevelBuilder(AllBinaryGameCanvas gameCanvas)
    		throws Exception
    {
    	this.gameCanvas = gameCanvas;

        // GPoint point = PointFactory.ZERO_ZERO;
        // this.layerPlacer = new ObamaStimulusLayerPlacer(this, point);
    }

    public static void init()
    {
        LayerInterfaceFactory layerInterfaceFactory = LayerInterfaceFactory.getInstance();

        layerInterfaceFactory.init();

        // layerInterfaceFactory.add(new RussianInfantryLayerFactory());

        ArtificialIntelligenceInterfaceFactoryInterfaceFactory artificialIntelligenceInterfaceFactoryInterfaceFactory = ArtificialIntelligenceInterfaceFactoryInterfaceFactory
                .getInstance();

        artificialIntelligenceInterfaceFactoryInterfaceFactory.clear();

        // artificialIntelligenceInterfaceFactoryInterfaceFactory.add(new
        // PacePatrolAIFactory());
    }

    //private BasicGameResources[] playerResourceArray = new BasicGameResources[1];
    //private Hashtable hashtable = new Hashtable();
    //private ArtificialIntelligenceInterfaceFactoryInterface artificialIntelligenceInterfaceFactoryInterface =
      //  new BoundBounceAIFactory();

    public void build() throws Exception
    {
    }

    public void visit(AllBinaryLayer layerInterface) throws Exception
    {
        layerInterface.setVisible(true);
        this.gameCanvas.getLayerManager().append(layerInterface);
    }
}