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
package org.allbinary.game.input;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

import org.allbinary.util.BasicArrayList;

import org.allbinary.string.CommonStrings;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.game.configuration.feature.Features;
import org.allbinary.game.configuration.feature.InputFeatureFactory;
import org.allbinary.game.input.event.GameKeyEvent;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayerManager;
import org.allbinary.layer.event.ScrollMapEvent;
import org.allbinary.layer.event.ScrollMapEventHandler;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;

/**
 *
 * @author user
 */
public class ScrollMapPlayerGameInput
   extends PlayerGameInput
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    protected final GameInputProcessor[] inputProcessorArray = new GameInputProcessor[InputFactory.getInstance().MAX];

    private final int border = 150;
    
    private final BasicArrayList inputList;

    protected final boolean isSingleKeyProcessing =
       Features.getInstance().isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_REPEAT_PRESS) ||
       Features.getInstance().isFeature(InputFeatureFactory.getInstance().SINGLE_KEY_PRESS);

    private final BasicGeographicMap geographicMapInterface;

    private int maxBottom;
    private int maxRight;
    
    public ScrollMapPlayerGameInput(
       BasicGeographicMap geographicMapInterface, BasicArrayList list, int playerInputId)
    {
        super(list, playerInputId);

        this.initInputProcessors();
        
        this.geographicMapInterface = geographicMapInterface;
        this.inputList = list;
        
        this.init();
    }

    private final ScrollMapEvent scrollMapEvent = new ScrollMapEvent(this);
    
    private void move(int dx, int dy) throws Exception
    {
        AllBinaryTiledLayer terrainTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        
        terrainTiledLayer.move(-dx, -dy);
        
        scrollMapEvent.setDxDy(-dx, -dy);
        ScrollMapEventHandler.getInstance().fireEvent(scrollMapEvent);
    }

    public void init()
    {
        this.maxBottom = this.getSpecialHeight();
        this.maxRight = this.getSpecialWidth();
    }
    
    public void scrollMiddleX() throws Exception
    {
        AllBinaryTiledLayer terrainTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

      //PreLogUtil.put(diffX + CommonSeps.getInstance().SPACE + CommonSeps.getInstance().SPACE + terrainTiledLayer.getWidth() + CommonSeps.getInstance().SPACE + displayInfo.getLastWidth(), this, "scrollMiddleX");

        this.move(-terrainTiledLayer.getXP(), 0);
        
        if(terrainTiledLayer.getWidth() < displayInfo.getLastWidth())
        {
            int diffX = ((displayInfo.getLastWidth() - terrainTiledLayer.getWidth()) >> 1);
            this.move(-diffX, 0);
        }
        else
        {
            int diffX = ((terrainTiledLayer.getWidth() - displayInfo.getLastWidth()) >> 1);
            this.move(diffX, 0);
        }
    }

    //Graphics.HCENTER & Graphics.TOP
    public void scrollY(int anchor)
        throws Exception
    {
        AllBinaryTiledLayer terrainTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        if(anchor == Graphics.TOP)
        {
            logUtil.put("Top", this, "scrollY");
        }
        else
        if(anchor == Graphics.BOTTOM)
        {
            logUtil.put("Bottom", this, "scrollY");
            int diffY = (terrainTiledLayer.getHeight() - displayInfo.getLastHeight());
            this.move(0, diffY);
        }
        else
            if(anchor == Graphics.VCENTER)
        {
            logUtil.put("Center", this, "scrollY");
            //Already sees bottom but I want the map at the Center
            int diffY = (terrainTiledLayer.getHeight() - displayInfo.getLastHeight())/2;
            this.move(0, diffY);
        }
            else
            {
            throw new Exception("No Such Anchor Supported");
            }
    }

    protected void up() throws Exception
    {
        int y = geographicMapInterface.getAllBinaryTiledLayer().getYP() - 10;
        if(this.keepOnMapMinY(y))
        {
            this.move(0, -10);
        }
    }

    protected void down() throws Exception
    {
        int y = -geographicMapInterface.getAllBinaryTiledLayer().getYP() + 10;
        if(this.keepOnMapMaxY(y))
        {
            this.move(0, 10);
        }
    }
    
    protected void right() throws Exception
    {
        int x = geographicMapInterface.getAllBinaryTiledLayer().getYP() + 10;
        if(this.keepOnMapMinX(x))
        {
            this.move(10, 0);
        }
    }

    protected void left() throws Exception
    {
        int x = -geographicMapInterface.getAllBinaryTiledLayer().getYP() - 10;
        if(this.keepOnMapMaxX(x))
        {
            this.move(-10, 0);
        }
    }

    public void initInputProcessors()
    {     
        this.inputProcessorArray[Canvas.UP] = new ScrollMapUpGameInputProcessor(this);
        this.inputProcessorArray[Canvas.DOWN] = new ScrollMapDownGameInputProcessor(this);
        this.inputProcessorArray[Canvas.KEY_NUM9] = new ScrollMapRightGameInputProcessor(this);
        this.inputProcessorArray[Canvas.KEY_NUM7] = new ScrollMapLeftGameInputProcessor(this);

        GameInputProcessorUtil.init(this.inputProcessorArray);
    }

    public void processInput(int key)
    throws Exception
    {
        inputProcessorArray[key].process(null, (GameKeyEvent) null);
    }
    
    public void processInput(AllBinaryLayerManager layerManager)
    throws Exception
    {
        try
        {
            int size = inputList.size();
            int key = 0;

            for (int index = 0; index < size; index++)
            {
                GameKeyEvent gameKeyEvent = (GameKeyEvent) inputList.get(index);
                key = gameKeyEvent.getKey();

                this.processInput(key);
            }

            if (isSingleKeyProcessing)
            {
                this.clear();
            }
            else
            {
                this.update();
            }

        }
        catch (Exception e)
        {
            logUtil.put(commonStrings.EXCEPTION, this, gameInputStrings.PROCESS_INPUT, e);
        }
    }

    private int getSpecialHeight()
    {
        AllBinaryTiledLayer terrainTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        if(terrainTiledLayer.getHeight() > displayInfo.getLastHeight())
        {
            return terrainTiledLayer.getHeight() - displayInfo.getLastHeight();
        }
        else
        {
            return displayInfo.getLastHeight() - terrainTiledLayer.getHeight() + terrainTiledLayer.getCellHeight();
        }
    }

    private int getSpecialWidth()
    {
        AllBinaryTiledLayer terrainTiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();

        if(terrainTiledLayer.getWidth() > displayInfo.getLastWidth())
        {
            return terrainTiledLayer.getWidth() - displayInfo.getLastWidth();
        }
        else
        {
            return displayInfo.getLastWidth() - terrainTiledLayer.getWidth() + terrainTiledLayer.getCellWidth();
        }
    }

    public boolean keepOnMapMaxY(int newY)
    {
        /*
        AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
        
        //int mapBorderBufferHeight = terrainTiledLayer.getCellHeight();

        StringBuilder stringBuffer = new StringBuilder();
        
        stringBuffer.append("Y Bottom: > ");
        stringBuffer.append(maxBottom + border);

        stringBuffer.append(" X: ");
        stringBuffer.append(tiledLayer.getXP());
        stringBuffer.append(" Y: ");
        stringBuffer.append(tiledLayer.getYP());
        
        //logUtil.put(stringBuffer.toString(), this, "keepOnMapMaxY");
        PreLogUtil.put(stringBuffer.toString(), this, "keepOnMapMaxY");
        */

        int y = geographicMapInterface.getAllBinaryTiledLayer().getYP();
        if (y > maxBottom + border)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean keepOnMapMinY(int newY)
    {
        /*
        AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();

        //int mapBorderBufferHeight = tiledLayer.getCellHeight();

        StringBuilder stringBuffer = new StringBuilder();

        stringBuffer.append("Y Top: < ");
        
        stringBuffer.append(-border);
        //stringBuffer.append(mapBorderBufferHeight);
        
        stringBuffer.append(" X: ");
        stringBuffer.append(tiledLayer.getXP());
        stringBuffer.append(" Y: ");
        stringBuffer.append(tiledLayer.getYP());

        //logUtil.put(stringBuffer.toString(), this, "keepOnMapMinY");
        PreLogUtil.put(stringBuffer.toString(), this, "keepOnMapMinY");
        */

        int y = geographicMapInterface.getAllBinaryTiledLayer().getYP();
        if (y < -border)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean keepOnMapMaxX(int newX)
    {
        /*
        //int mapBorderBufferWidth = terrainTiledLayer.getCellWidth();

        AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();

        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("X Right: < ");
        
        stringBuffer.append(-maxRight - border);

        stringBuffer.append(" X: ");
        stringBuffer.append(tiledLayer.getXP());
        stringBuffer.append(" Y: ");
        stringBuffer.append(tiledLayer.getYP());

        //logUtil.put(stringBuffer.toString(), this, "keepOnMapMaxX");
        PreLogUtil.put(stringBuffer.toString(), this, "keepOnMapMaxX");
        */
        
        int x = geographicMapInterface.getAllBinaryTiledLayer().getXP();
        if (x < -maxRight - border)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean keepOnMapMinX(int newX)
    {
        /*
        AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();

        //int mapBorderBufferWidth = terrainTiledLayer.getCellWidth();

        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("X Left: > ");
        stringBuffer.append(border);
        //stringBuffer.append(mapBorderBufferWidth);

        stringBuffer.append(" X: ");
        stringBuffer.append(tiledLayer.getXP());
        stringBuffer.append(" Y: ");
        stringBuffer.append(tiledLayer.getYP());

        //logUtil.put(stringBuffer.toString(), this, "keepOnMapMinX");
        //PreLogUtil.put(stringBuffer.toString(), this, "keepOnMapMinX");
        */

        int x = geographicMapInterface.getAllBinaryTiledLayer().getXP();
        
        if (x > border)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
