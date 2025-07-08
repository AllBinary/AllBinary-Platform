/*
 * AllBinary Open License Version 1
 * Copyright (c) 2022 AllBinary
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
package org.allbinary.game.behavior.topview;

import org.allbinary.direction.Direction;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.tracking.TrackingEventCircularStaticPool;
import org.allbinary.game.tracking.TrackingEventHandler;
import org.allbinary.graphics.displayable.DisplayInfoSingleton;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.BasicGeographicMapUtil;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.view.ViewPosition;

/**
 *
 * @author User
 */
public class PlayerTopViewCharacterBehavior extends TopViewCharacterBehavior {
    protected final LogUtil logUtil = LogUtil.getInstance();

    
    public void terrainEvent(final AllBinaryLayer layer, final Direction direction, final int x, final int y, final BasicGeographicMap[] geographicMapInterfaceArray,
            final GeographicMapCellPosition geographicMapCellPosition)
            throws Exception {

        TrackingEventHandler.getInstance().fireEvent(
                TrackingEventCircularStaticPool.getInstance().getInstance(layer));
    }
    
    public void terrainMove(final AllBinaryLayer layer, final BasicGeographicMap[] geographicMapInterfaceArray, final int x, final int y) {
        //logUtil.put(new StringMaker().append("GDGameLevelLevelBuilder Moving: x: ").append(x).append(" y: ").append(y).toString(), this, "move");

        // Don't move to far forward in screen if not close to edge of tilelayer
        //final ViewPosition viewPosition = layer.getViewPosition();
        //final int viewX = viewPosition.getX();

        //final DisplayInfoSingleton displayInfo = DisplayInfoSingleton.getInstance();
        final BasicGeographicMapUtil basicGeographicMapUtil = BasicGeographicMapUtil.getInstance();

        //basicGeographicMapUtil.move(geographicMapInterfaceArray, -x, -y);
        basicGeographicMapUtil.setPosition(geographicMapInterfaceArray, x, y);
        /*
        if (viewX < (displayInfo.getLastHalfWidth() >> 1) || viewX > displayInfo.getLastHalfWidth() + displayInfo.getLastHalfWidth() / 2) {
            
            // logUtil.put("View Position X: " +
            // this.getViewPosition().getX()).append(" 1/4 View" + spacialStrings +
            // DisplayInfoSingleton.getInstance()getLastHalfWidth() / 2, this, "move");
            if (this.isTiledLayerMoveable(terrainTiledLayer, x, y)) {
                logUtil.put(new StringMaker().append("Player and TileLayer move: x: ").append(x).append(" y: ").append(y).toString(), this, "move");
                basicGeographicMapUtil.move(geographicMapInterfaceArray, -x / 2, 0);
                this.moveIfOnScreen(layer, x, y);
            } else {
                logUtil.put("Player move", this, "move");
                this.moveIfOnScreen(layer, x, y);
            }
        } else {
            // if not at tilelayer edge then move tilelayer at currenct
            // x velocity
            if (this.isTiledLayerMoveable(terrainTiledLayer, x, y)) {
                logUtil.put("TileLayer moving", this, "move");
                basicGeographicMapUtil.move(geographicMapInterfaceArray, -x, 0);
                this.moveIfOnScreen(layer, x, y);
            } else {
                // at tilelayer edge move player not tile layer
                // when player gets back to the center then start moving
                // tilelayer again and not on tile layer edge
                logUtil.put("Player move towards TileLayer edge", this, "move");
                this.moveIfOnScreen(layer, x, y);
            }
        }
        */
    
    }

    private void moveIfOnScreen(final AllBinaryLayer layer, final int ax, final int ay)
    {
//        final int MAX_RIGHT = (DisplayInfoSingleton.getInstance().getLastWidth() - layer.getWidth());
//        final ViewPosition viewPosition = layer.getViewPosition();
//        final int viewX = viewPosition.getX();
//        if ((viewX > 0 || ax > 0) && (viewX < MAX_RIGHT || ax < 0))
//        {
//            logUtil.put(new StringMaker().append("ax: ").append(ax).append(CommonSeps.getInstance().SPACE).append(layer.getViewPosition().getX()).append(" < ").append(MAX_RIGHT).toString(), this, "moveIfOnScreen");
//            layer.move(ax, ay);
//        }
//        else
//        {
//            logUtil.put(new StringMaker().append("ax: ").append(ax).append(CommonSeps.getInstance().SPACE).append(layer.getViewPosition().getX()).append(" < ").append(MAX_RIGHT).toString(), this, "moveIfOnScreen");
//            layer.move(0, ay);
//        }
    }
    
    private boolean isTiledLayerMoveable(final AllBinaryTiledLayer terrainTiledLayer, final int x, final int y) {
        return (terrainTiledLayer.getX() + DisplayInfoSingleton.getInstance().getLastWidth() < terrainTiledLayer.getWidth() || x < 0) && (terrainTiledLayer.getX() > 0 || x > 0);
    }

}
