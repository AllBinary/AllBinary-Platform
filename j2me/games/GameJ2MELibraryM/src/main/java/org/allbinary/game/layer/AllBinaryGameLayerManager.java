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

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import org.allbinary.game.GameInfo;
import org.allbinary.game.input.NullPlayerGameInputComposite;
import org.allbinary.game.input.PlayerGameInputCompositeInterface;
import org.allbinary.graphics.ItemColorFactory;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.LayerProcessor;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.math.SmallIntegerSingletonFactory;
import org.allbinary.util.BasicArrayList;


@JsType
public class AllBinaryGameLayerManager extends AllBinaryLayerManager
{

    private static Object NULL_ALLBINARY_LAYER_MANAGER = NullUtil.getInstance().NULL_OBJECT;

    @JsMethod    
    public static AllBinaryGameLayerManager getNullInstance() {
        
        if(AllBinaryGameLayerManager.NULL_ALLBINARY_LAYER_MANAGER == NullUtil.getInstance().NULL_OBJECT) {
            AllBinaryGameLayerManager.NULL_ALLBINARY_LAYER_MANAGER = 
                new AllBinaryGameLayerManager(BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE, GameInfo.NONE);
        }

        return (AllBinaryGameLayerManager) AllBinaryGameLayerManager.NULL_ALLBINARY_LAYER_MANAGER;
    }
    
    @JsProperty
    public static final Integer ID = SmallIntegerSingletonFactory.getInstance().getAt(22);

    private BasicColor backgroundBasicColor;
    private BasicColor foregroundBasicColor;
    private GameInfo gameInfo;

    private PlayerGameInputCompositeInterface playerGameInputCompositeInterface = NullPlayerGameInputComposite.NULL_PLAYER_GAME_INPUT_COMPOSITE;

    @JsConstructor
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

    @JsMethod
    public AllBinaryLayerManager getLayerManager()
    {
        return this;
    }

    @JsMethod
    public void init()
    {
    }

    @JsMethod
    public BasicColor getBackgroundBasicColor()
    {
        return this.backgroundBasicColor;
    }

    @JsMethod
    public void setBackgroundBasicColor(final BasicColor backgroundBasicColor)
    {
        this.backgroundBasicColor = backgroundBasicColor;

        ItemColorFactory.getInstance().INVERT_PAINT = this.backgroundBasicColor.intValue();
    }

    @JsMethod
    public BasicColor getForegroundBasicColor()
    {
        return this.foregroundBasicColor;
    }

    @JsMethod
    public void setForegroundBasicColor(final BasicColor foregroundBasicColor)
    {
        this.foregroundBasicColor = foregroundBasicColor;

        ItemColorFactory.getInstance().PAINT = this.foregroundBasicColor.intValue();
    }

    @JsMethod
    public GameInfo getGameInfo()
    {
        return this.gameInfo;
    }

    @JsMethod
    public void setGameInfo(final GameInfo gameInfo)
    {
        this.gameInfo = gameInfo;
    }

    @JsMethod
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

    @JsMethod
    public void setPlayerGameInputCompositeInterface(
            final PlayerGameInputCompositeInterface playerGameInputCompositeInterface)
    {
        this.playerGameInputCompositeInterface = playerGameInputCompositeInterface;
    }

    @JsMethod
    public PlayerGameInputCompositeInterface getPlayerGameInputCompositeInterface()
    {
        return this.playerGameInputCompositeInterface;
    }
}
