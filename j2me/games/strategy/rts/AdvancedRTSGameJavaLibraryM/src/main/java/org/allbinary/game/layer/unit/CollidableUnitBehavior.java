/*
 * AllBinary Open License Version 1
 * Copyright (c) 2006 AllBinary
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
package org.allbinary.game.layer.unit;

import javax.microedition.lcdui.Canvas;

import org.allbinary.game.layer.SteeringVisitor;
import org.allbinary.game.layer.AdvancedRTSGameLayer;
import org.allbinary.game.layer.CollidableCompositeLayer;
import org.allbinary.game.layer.CollidableRTSBehavior;
import org.allbinary.util.BasicArrayList;
import org.allbinary.game.input.event.GameKeyEventFactory;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.layer.TiledLayerUtil;
import org.allbinary.game.layer.geographic.map.LayerPartialCellPositionsUtil;
import org.allbinary.logic.NullUtil;
import org.allbinary.logic.java.bool.BooleanFactory;
import org.allbinary.math.AngleInfo;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;
import org.allbinary.media.graphics.geography.map.drop.DropCellPositionHistory;

public class CollidableUnitBehavior
extends CollidableRTSBehavior 
{

    private final LayerPartialCellPositionsUtil layerPartialCellPositionsUtil = LayerPartialCellPositionsUtil.getInstance();

    public CollidableUnitBehavior(final CollidableCompositeLayer ownerLayer, final boolean collidable)
    {
        super(ownerLayer, collidable);
    }
    
    //Collision with RTSLayers or other none damage game objects
    @Override
    protected void collideNone(final CollidableCompositeLayer collidableInterfaceCompositeInterface)
        throws Exception
    {
        //logUtil.put(CommonLabels.getInstance().START + collidableInterface.getName(), this, "collideNone");
        
        this.chase(collidableInterfaceCompositeInterface);        
        
        final AdvancedRTSGameLayer rtsLayer = (AdvancedRTSGameLayer) collidableInterfaceCompositeInterface;
        if (rtsLayer.getType() == UnitLayer.getStaticType())
        {
            this.collideUnit((UnitLayer) rtsLayer);
        }
    }

    private class SimpleSteeringVisitor extends SteeringVisitor
    {
        @Override
        public Object visit(final Object object)
        {
            try
            {
                if (this.getList().size() > 0)
                {
                    ///logUtil.put("steering", this, "visit");
                    
                    final CollidableCompositeLayer allbinaryLayer = 
                        (CollidableCompositeLayer) this.getList().get(0);
                    
                    boolean clear = CollidableUnitBehavior.this.steer(allbinaryLayer);

                    if (clear)
                    {
                        this.getList().clear();
                        return NullUtil.getInstance().NULL_OBJECT;
                    }

                    return BooleanFactory.getInstance().TRUE;
                }

                return NullUtil.getInstance().NULL_OBJECT;
            }
            catch(Exception e)
            {
                logUtil.put(commonStrings.EXCEPTION, this, "visit", e);
                return NullUtil.getInstance().NULL_OBJECT;
            }
        }

    }
    private final SimpleSteeringVisitor simpleSteeringVisitor = 
        new SimpleSteeringVisitor();
    
    private boolean steer(final CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {        
        //logUtil.put("Unit: " + collidableInterface.getName(), this, "chase");
    
        final UnitLayer ownerUnitLayer = (UnitLayer) this.ownerLayer;
        
        final UnitLayer unitLayer = (UnitLayer) collidableInterfaceCompositeInterface;
    
        final AngleInfo angleInfo2 = unitLayer.getRotationAnimationInterface().getAngleInfoP();
    
        //Since buildings don't move just turn to go around
        final AngleInfo angleInfo = ownerUnitLayer.getRotationAnimationInterface().getAngleInfoP();
        final int angle = angleInfo.getAngle() - angleInfo2.getAngle();
        
        //logUtil.put("Unit: " + collidableInterface.getName(), this, "chase");
        
        if (angle < 90 || angle > 270)
        {
            return true;
        } else if (angle >= 90 || angle <= 270)   
        {
            ownerUnitLayer.getGameKeyEventList().add(
                    GameKeyEventFactory.getInstance().getInstance(ownerUnitLayer, Canvas.RIGHT));

            return false;
        }
        throw new Exception("Error");
    }
    
    //Stops the normal turning for paths and target and changes to chase mode
    //This allows units to go around other units that it collides with
    private void chase(final CollidableCompositeLayer collidableInterfaceCompositeInterface)
    throws Exception
    {
        //logUtil.put(CommonLabels.getInstance().START + collidableInterface.getName(), this, "chase");

        final AdvancedRTSGameLayer rtsLayer = (AdvancedRTSGameLayer) collidableInterfaceCompositeInterface;
        if (rtsLayer.getType() == UnitLayer.getStaticType())
        {
            if(!this.simpleSteeringVisitor.getList().contains(collidableInterfaceCompositeInterface))
            {
                this.simpleSteeringVisitor.getList().add(collidableInterfaceCompositeInterface);
            }

            final UnitLayer ownerUnitLayer = (UnitLayer) this.ownerLayer;
            final BasicArrayList list = ownerUnitLayer.getUnitWaypointBehavior().getSteeringVisitorList();
            
            if(!list.contains(this.simpleSteeringVisitor))
            {
                list.add(this.simpleSteeringVisitor);
            }

        }
        //Does not currently happen.  Since building is checked in move()
        /*
        else
            if (rtsLayer.getType() == BuildingLayer.getStaticType())
        {
        }
        */
    }
    
    protected void collideUnit(final UnitLayer unitLayer) throws Exception
    {
        final UnitLayer ownerUnitLayer = (UnitLayer) this.ownerLayer;

        final BasicArrayList partialPositionList = UnitLayer.getPartialpositionlist();
        
        final GeographicMapCompositeInterface geographicMapCompositeInterface
            = (GeographicMapCompositeInterface) ownerUnitLayer.allBinaryGameLayerManagerP;
        final BasicGeographicMap geographicMapInterface = geographicMapCompositeInterface.getGeographicMapInterface()[0];
        
        final BasicGeographicMap basicGeographicMap = geographicMapInterface;
        
        // Random slight turning
//        final int randTurn = MyRandomFactory.getInstance().getAbsoluteNextInt(3); 
//        if (randTurn == 1) {
//            ownerUnitLayer.getGameKeyEventList().add(GameKeyEventFactory.getInstance().getInstance(ownerUnitLayer, Canvas.RIGHT));
//        } else if (randTurn == 2) {
//            ownerUnitLayer.getGameKeyEventList().add(GameKeyEventFactory.getInstance().getInstance(ownerUnitLayer, Canvas.LEFT));
//        }
        

        final DropCellPositionHistory dropCellPositionHistory = 
            DropCellPositionHistory.getInstance();

        final TiledLayerUtil tiledLayerUtil = TiledLayerUtil.getInstance();
        final AllBinaryTiledLayer tiledLayer = basicGeographicMap.getAllBinaryTiledLayer();

        if (this.ownerLayer.getXP() < unitLayer.getXP() &&
            this.ownerLayer.getX2() > unitLayer.getXP())
        {
            final int diff = this.ownerLayer.getWidth() + 1;

            this.layerPartialCellPositionsUtil.getAll(
                    basicGeographicMap, this.ownerLayer, -diff, 0, partialPositionList);
            
            if (!dropCellPositionHistory.anyCellPositionWithDrop(partialPositionList))
            {
                int x = unitLayer.getXP() - diff;
                int y = this.ownerLayer.getYP();

                x = tiledLayerUtil.keepOnMapX(tiledLayer, x, this.ownerLayer.getWidth());
                y = tiledLayerUtil.keepOnMapY(tiledLayer, y, this.ownerLayer.getHeight());

                this.ownerLayer.setPosition(x, y, this.ownerLayer.getZP());
            }

            this.layerPartialCellPositionsUtil.getAll(
                    basicGeographicMap,
                unitLayer, diff, 0,
                partialPositionList);

            if (!dropCellPositionHistory.anyCellPositionWithDrop(partialPositionList))
            {
                int x = unitLayer.getXP() + diff;
                int y = unitLayer.getYP();
                
                x = tiledLayerUtil.keepOnMapX(tiledLayer, x, unitLayer.getWidth());
                y = tiledLayerUtil.keepOnMapY(tiledLayer, y, unitLayer.getHeight());

                unitLayer.setPosition(x, y, unitLayer.getZP());
            }
        }

        if (this.ownerLayer.getYP() < unitLayer.getYP() &&
            this.ownerLayer.getY2() > unitLayer.getYP())
        {
            int diff = this.ownerLayer.getHeight() + 1;

            this.layerPartialCellPositionsUtil.getAll(
                    basicGeographicMap,
                this.ownerLayer, 0, -diff, partialPositionList);

            if (!dropCellPositionHistory.anyCellPositionWithDrop(partialPositionList))
            {
                int x = this.ownerLayer.getXP();
                int y = unitLayer.getYP() - diff;

                x = tiledLayerUtil.keepOnMapX(tiledLayer, x, this.ownerLayer.getWidth());
                y = tiledLayerUtil.keepOnMapY(tiledLayer, y, this.ownerLayer.getHeight());

                this.ownerLayer.setPosition(x, y, this.ownerLayer.getZP());
            }

            this.layerPartialCellPositionsUtil.getAll(
                    basicGeographicMap, unitLayer, 0, diff, partialPositionList);

            if (!dropCellPositionHistory.anyCellPositionWithDrop(partialPositionList))
            {
                int x = unitLayer.getXP();
                int y = unitLayer.getYP() + diff;

                x = tiledLayerUtil.keepOnMapX(tiledLayer, x, unitLayer.getWidth());
                y = tiledLayerUtil.keepOnMapY(tiledLayer, y, unitLayer.getHeight());

                unitLayer.setPosition(x, y, unitLayer.getZP());
            }
        }
    }
    
}
