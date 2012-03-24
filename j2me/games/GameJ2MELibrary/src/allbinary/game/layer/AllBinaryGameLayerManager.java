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
package allbinary.game.layer;

//Should probably become composite of manager instead
import org.allbinary.graphics.ItemColorFactory;
import org.allbinary.util.BasicArrayList;

import allbinary.game.GameInfo;
import allbinary.game.input.PlayerGameInputCompositeInterface;
import allbinary.graphics.color.BasicColor;
import allbinary.layer.AllBinaryLayerManager;
import allbinary.layer.LayerProcessor;
import allbinary.logic.math.SmallIntegerSingletonFactory;

public class AllBinaryGameLayerManager extends AllBinaryLayerManager
{
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getInstance(22);

    private BasicColor backgroundBasicColor;
    private BasicColor foregroundBasicColor;
    private GameInfo gameInfo;

    private PlayerGameInputCompositeInterface playerGameInputCompositeInterface;

    public AllBinaryGameLayerManager(BasicColor backgroundBasicColor,
            BasicColor foregroundBasicColor, GameInfo gameInfo)
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

    public void setBackgroundBasicColor(BasicColor backgroundBasicColor)
    {
        this.backgroundBasicColor = backgroundBasicColor;

        ItemColorFactory.getInstance().INVERT_PAINT = this.backgroundBasicColor.intValue();
    }

    public BasicColor getForegroundBasicColor()
    {
        return foregroundBasicColor;
    }

    public void setForegroundBasicColor(BasicColor foregroundBasicColor)
    {
        this.foregroundBasicColor = foregroundBasicColor;

        ItemColorFactory.getInstance().PAINT = this.foregroundBasicColor.intValue();
    }

    public GameInfo getGameInfo()
    {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
    }

    public void setLayerProcessorList(BasicArrayList list)
    {
        LayerProcessor[] layerProcessorInterfaceArray = new LayerProcessor[list.size()];

        int size = layerProcessorInterfaceArray.length;
        for (int index = 0; index < size; index++)
        {
            layerProcessorInterfaceArray[index] = (LayerProcessor) list.objectArray[index];
        }

        this.setLayerProcessorArray(layerProcessorInterfaceArray);
    }

    public void setPlayerGameInputCompositeInterface(
            PlayerGameInputCompositeInterface playerGameInputCompositeInterface)
    {
        this.playerGameInputCompositeInterface = playerGameInputCompositeInterface;
    }

    public PlayerGameInputCompositeInterface getPlayerGameInputCompositeInterface()
    {
        return playerGameInputCompositeInterface;
    }
}
