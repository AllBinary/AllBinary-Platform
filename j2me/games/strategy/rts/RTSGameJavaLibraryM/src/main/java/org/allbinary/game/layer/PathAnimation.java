/*
 * AllBinary Open License Version 1
 * Copyright (c) 2003 AllBinary
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

import javax.microedition.lcdui.Graphics;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.GPoint;
import org.allbinary.graphics.color.BasicColor;
import org.allbinary.graphics.color.BasicColorFactory;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellHistory;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.media.graphics.geography.map.SimpleGeographicMapCellPositionFactory;
import org.allbinary.util.BasicArrayList;

public class PathAnimation extends Animation
{
    protected final LogUtil logUtil = LogUtil.getInstance();

    private final LinePathAnimation linePathAnimation;
    private final PathFindingLayerInterface pathFindingLayer;
    
    protected AllBinaryGameLayerManager allBinaryGameLayerManagerP = AllBinaryGameLayerManager.NULL_ALLBINARY_LAYER_MANAGER;
    
    public PathAnimation(final PathFindingLayerInterface pathFindingLayer, final LinePathAnimation linePathAnimation)
    {
        this.pathFindingLayer = pathFindingLayer;
        this.linePathAnimation = linePathAnimation;
    }
    
    public void setAllBinaryGameLayerManager(final AllBinaryGameLayerManager allBinaryGameLayerManager) throws Exception {
        this.allBinaryGameLayerManagerP = allBinaryGameLayerManager;
    }
    
    @Override
    public void nextFrame() throws Exception
    {
    }
    
    private final BasicColor startColor = BasicColorFactory.getInstance().RED;
    private final BasicColor middleColor = BasicColorFactory.getInstance().BLUE;
    private final BasicColor endColor = BasicColorFactory.getInstance().GREEN;
    
    @Override
    public void paint(final Graphics graphics, final int x, final int y)
    {
        try
        {
            final WaypointBehaviorBase waypointBehaviorBase = this.pathFindingLayer.getWaypointBehavior();
            
            if(waypointBehaviorBase != null) {
                final GeographicMapCellHistory geographicMapCellHistory = 
                    waypointBehaviorBase.getCurrentGeographicMapCellHistory();

                final BasicArrayList list = geographicMapCellHistory.getTracked();

                final int size = list.size();
                if (size > 0) {

                    //final StringMaker stringMaker = new StringMaker();
                    //final CommonSeps commonSeps = CommonSeps.getInstance();
                    
                    final GeographicMapCompositeInterface geographicMapCompositeInterface = 
                        (GeographicMapCompositeInterface) this.allBinaryGameLayerManagerP;
                    final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];

                    final AllBinaryTiledLayer tiledLayer = geographicMapInterface.getAllBinaryTiledLayer();
                    GeographicMapCellPosition geographicMapCellPosition = SimpleGeographicMapCellPositionFactory.NULL_GEOGRAPHIC_MAP_CELL_POSITION;
                        //(GeographicMapCellPosition) list.get(list.size() - 1);

                    GPoint nextPoint;// = geographicMapCellPosition.getMidPoint();
                    GPoint point;

                    int start = size - 2;

                    for (int index = 0; index < size - 1; index++) {
                        if (index == start) {
                            this.setBasicColorP(this.startColor);
                            this.basicSetColorUtil.setBasicColorP(
                                graphics, this.getBasicColorP(), this.getColor());
                        } /*
                    else if (index == 0)
                    {
                        this.setBasicColorP(this.middleColor);
                        this.basicSetColorUtil.setBasicColorP(
                                graphics, this.getBasicColorP(), this.getColor());
                    }
                    else
                    {
                        this.setBasicColorP(this.endColor);
                        this.basicSetColorUtil.setBasicColorP(
                                graphics, this.getBasicColorP(), this.getColor());
                    }
                         */ else if (geographicMapCellHistory.isVisited(geographicMapCellPosition)) {
                            this.setBasicColorP(this.endColor);
                            this.basicSetColorUtil.setBasicColorP(
                                graphics, this.getBasicColorP(), this.getColor());
                        } else {
                            this.setBasicColorP(this.middleColor);
                            this.basicSetColorUtil.setBasicColorP(
                                graphics, this.getBasicColorP(), this.getColor());
                        }

                        geographicMapCellPosition = (GeographicMapCellPosition) list.get(index);
                        
                        //final RaceTrackGeographicMapCellType geographicMapCellType = (RaceTrackGeographicMapCellType) geographicMapInterface.getCellTypeAt(geographicMapCellPosition);

                        point = geographicMapCellPosition.getMidPoint();

                        geographicMapCellPosition = (GeographicMapCellPosition) list.get(index + 1);

                        //final RaceTrackGeographicMapCellType geographicMapCellType = (RaceTrackGeographicMapCellType) geographicMapInterface.getCellTypeAt(geographicMapCellPosition);
                        
                        nextPoint = geographicMapCellPosition.getMidPoint();

                        this.linePathAnimation.paint(graphics, point, nextPoint, tiledLayer);
                        //stringMaker.delete(0, stringMaker.length());
                        //graphics.drawString(stringMaker.append(geographicMapCellPosition.getColumn()).append(commonSeps.COMMA).append(geographicMapCellPosition.getRow()).toString(), point.getX(), point.getY(), 0);
                        //graphics.drawString(stringMaker
                            //.append(geographicMapCellPosition.getColumn()).append(commonSeps.COMMA).append(geographicMapCellPosition.getRow()).append(commonSeps.SEMICOLON)
                            //.append(geographicMapCellType.toString())
                            //.append(geographicMapCellType.getType())
                            //.append(commonSeps.SEMICOLON).append(geographicMapCellType.getTravelCost()).toString(), point.getX() - tiledLayer.getXP(), point.getY() - tiledLayer.getYP(), 0);
                    }
                }

            }
        }
        catch (Exception e)
        {
            logUtil.put("Cleared Target", this, "paint");
        }
    }
}
