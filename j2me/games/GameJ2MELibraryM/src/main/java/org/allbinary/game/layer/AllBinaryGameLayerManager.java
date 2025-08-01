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
package org.allbinary.game.layer;

//Should probably become composite of manager instead

import org.allbinary.game.GameInfo;
import org.allbinary.game.input.NullPlayerGameInputComposite;
import org.allbinary.game.input.PlayerGameInputCompositeInterface;
import org.allbinary.graphics.ItemColorFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerProcessor;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.util.BasicArrayList;

public class AllBinaryGameLayerManager extends AllBinaryLayerManager
{
    public static final AllBinaryGameLayerManager NULL_ALLBINARY_LAYER_MANAGER = new AllBinaryGameLayerManager(
        BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE, GameInfo.NONE);
    
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(22);

    private BasicColor backgroundBasicColor;
    private BasicColor foregroundBasicColor;
    private GameInfo gameInfo;

    private PlayerGameInputCompositeInterface playerGameInputCompositeInterface = NullPlayerGameInputComposite.NULL_PLAYER_GAME_INPUT_COMPOSITE;

    public AllBinaryGameLayerManager(final BasicColor backgroundBasicColor,
            final BasicColor foregroundBasicColor, final GameInfo gameInfo)
    {
        this.backgroundBasicColor = backgroundBasicColor;
        this.foregroundBasicColor = foregroundBasicColor;

        ItemColorFactory itemColorFactory = ItemColorFactory.getInstance();
        itemColorFactory.INVERT_PAINT = this.backgroundBasicColor.intValue();
        itemColorFactory.PAINT = this.foregroundBasicColor.intValue();

        this.gameInfo = gameInfo;
    }

    public AllBinaryLayerManager getLayerManager()
    {
        return this;
    }

    public void init()
    {
    }

    public BasicColor getBackgroundBasicColor()
    {
        return backgroundBasicColor;
    }

    public void setBackgroundBasicColor(final BasicColor backgroundBasicColor)
    {
        this.backgroundBasicColor = backgroundBasicColor;

        ItemColorFactory.getInstance().INVERT_PAINT = this.backgroundBasicColor.intValue();
    }

    public BasicColor getForegroundBasicColor()
    {
        return foregroundBasicColor;
    }

    public void setForegroundBasicColor(final BasicColor foregroundBasicColor)
    {
        this.foregroundBasicColor = foregroundBasicColor;

        ItemColorFactory.getInstance().PAINT = this.foregroundBasicColor.intValue();
    }

    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    public void setGameInfo(final GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
    }

    public void setLayerProcessorList(final BasicArrayList list)
    {
        final LayerProcessor[] layerProcessorInterfaceArray = new LayerProcessor[list.size()];

        int size = layerProcessorInterfaceArray.length;
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterfaceArray[index] = (LayerProcessor) list.objectArray[index];
        }

        this.setLayerProcessorArray(layerProcessorInterfaceArray);
    }

    public void setPlayerGameInputCompositeInterface(
            final PlayerGameInputCompositeInterface playerGameInputCompositeInterface)
    {
        this.playerGameInputCompositeInterface = playerGameInputCompositeInterface;
    }

    public PlayerGameInputCompositeInterface getPlayerGameInputCompositeInterface()
    {
        return playerGameInputCompositeInterface;
    }
}
