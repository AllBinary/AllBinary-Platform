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

import org.allbinary.media.graphics.geography.map.MultiGeographicMapBehavior;
import org.allbinary.game.layer.AllBinaryTiledLayer;
import org.allbinary.game.physics.acceleration.GravityUtil;
import org.allbinary.game.physics.velocity.VelocityProperties;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.string.CommonSeps;
import org.allbinary.logic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.topview.BasicTopViewGeographicMapCellTypeFactory;

/**
 *
 * @author User
 */
public class GeographicMapTopViewGameLayerBehavior1 extends GeographicMapTopViewLayerBehavior {
    protected final LogUtil logUtil = LogUtil.getInstance();


    private final GravityUtil gravityUtil = GravityUtil.getInstance();
    
    private final MultiGeographicMapBehavior geographicMapBehavior = new MultiGeographicMapBehavior();

    private final boolean autoStepBlocks;
    private final int offsetY;
    
    //public GeographicMapCellPosition blockGeographicMapCellPosition;

    private GeographicMapCellPosition previousGeographicMapCellPosition;

    public GeographicMapTopViewGameLayerBehavior1() {
        super(16 //32
        );
        this.autoStepBlocks = true;
        this.offsetY = 0;
    }
    
    public GeographicMapTopViewGameLayerBehavior1(final int maxGravityActionIndex, final boolean autoStepBlocks, final int offsetY) {
        super(maxGravityActionIndex);
        this.autoStepBlocks = autoStepBlocks;
        this.offsetY = offsetY;
    }
    
    @Override
    public void gravity(final VelocityProperties velocityProperties,
            final BasicGeographicMap[] geographicMapInterfaceArray, 
            final GeographicMapCellType[] geographicMapCellTypeArray, 
            final GeographicMapCellPosition geographicMapCellPosition)
            throws Exception {
        if (geographicMapCellPosition != null) {
            geographicMapBehavior.getCellTypeAt(geographicMapInterfaceArray, geographicMapCellTypeArray, geographicMapCellPosition);
            final boolean hasSolidBlock = this.hasSolidBlock(geographicMapInterfaceArray, geographicMapCellTypeArray);

            if (!hasSolidBlock) {
                //logUtil.put(new StringMaker().append("Not on Block: ").append(geographicMapCellPosition).append(" cellType: ").append(cellType).toString(), this, "gravity");

                gravityUtil.process(velocityProperties);

                velocityProperties.limitXYToForwardAndReverseMaxVelocity();
                this.gravity();
            } else {
                //logUtil.put(new StringMaker().append("On Block: ").append(geographicMapCellPosition).append(" cellType: ").append(cellType).toString(), this, "gravity");
            }
        }

        // If landing fails use this for debugging
        /*
         * if(jumpIndex != 0) {
         * this.getVelocityProperties().getVelocityYBasicDecimalP().add(800);
         * this.getVelocityProperties().limitMaxVelocity(); }
         */
    }

    public GeographicMapCellPosition getPosition(final BasicGeographicMap[] geographicMapInterfaceArray, final AllBinaryLayer layer, final int x, final int y) throws Exception {
        
//        final CommonSeps commonSeps = CommonSeps.getInstance();
//        logUtil.put(new StringMaker()
//                .append(layer.getXP()).append(commonSeps.COLON).append(-x).append(commonSeps.COLON).append(layer.getHalfWidth())
//                .append(layer.getYP()).append(commonSeps.COLON).append(-y).append(commonSeps.COLON).append(layer.getHalfHeight())
//                .toString(), this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
        final int xCellPosition = layer.getXP() + -x + layer.getWidth();
        final int yCellPosition = layer.getYP() + -y + layer.getHeight();

        return geographicMapInterfaceArray[0].getCellPositionAtNoThrow(xCellPosition, yCellPosition);
    }

    private GeographicMapCellPosition getLeftPosition(final BasicGeographicMap[] geographicMapInterfaceArray, final AllBinaryLayer layer) throws Exception {
        
        final int xCellPosition = layer.getXP();
        final int yCellPosition = layer.getYP() + layer.getHeight();

        return geographicMapInterfaceArray[0].getCellPositionAtNoThrow(xCellPosition, yCellPosition);        
    }

    private GeographicMapCellPosition getRightPosition(final BasicGeographicMap[] geographicMapInterfaceArray, final AllBinaryLayer layer) throws Exception {
        
        final int xCellPosition = layer.getXP() + layer.getWidth();
        final int yCellPosition = layer.getYP() + layer.getHeight();

        return geographicMapInterfaceArray[0].getCellPositionAtNoThrow(xCellPosition, yCellPosition);        
    }
    
    @Override
    public GeographicMapCellPosition getGeographicMapCellPositionIfNotSolidBlockOrOffMap(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer, final int x, int y) throws Exception {
        
        final GeographicMapCellPosition geographicMapCellPosition = this.getPosition(geographicMapInterfaceArray, layer, x, y);
        
        //logUtil.put(geographicMapCellPosition.toString(), this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
        
        // If walking into a solid block
        //this.isJumpAction && 
        if (this.previousGeographicMapCellPosition != geographicMapCellPosition && geographicMapCellPosition != null) {

            final GeographicMapCellPosition possibleStepGeographicMapCellPosition = geographicMapCellPosition;

//            final GeographicMapCellPosition possibleStepGeographicMapCellPosition
//                    = geographicMapInterfaceArray[0].getGeographicMapCellPositionFactory().getInstance(
//                            geographicMapCellPosition.getColumn(),
//                            geographicMapCellPosition.getRow());
//
//            logUtil.put(possibleStepGeographicMapCellPosition.toString(), this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");

            final AllBinaryTiledLayer tiledLayer = geographicMapInterfaceArray[0].getAllBinaryTiledLayer();
            //logUtil.put(new StringMaker().append("tileLayer: ").append(tiledLayer.getXP()).append(CommonSeps.getInstance().COLON).append(tiledLayer.getYP()).toString(), this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
            //logUtil.put(new StringMaker().append("tileLayer: ").append(tiledLayer.getColumns()).append(CommonSeps.getInstance().COLON).append(tiledLayer.getRows()).toString(), this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");

            if(possibleStepGeographicMapCellPosition.getColumn() > 0 && possibleStepGeographicMapCellPosition.getRow() > 0 &&
                    possibleStepGeographicMapCellPosition.getColumn() < tiledLayer.getColumns() && 
                    possibleStepGeographicMapCellPosition.getRow() < tiledLayer.getRows()) {

            geographicMapBehavior.getCellTypeAt(geographicMapInterfaceArray, geographicMapCellTypeArray, possibleStepGeographicMapCellPosition);

            //logUtil.put("cellType: " + cellType, this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");

            final boolean hasSolidBlock = this.hasSolidBlock(geographicMapInterfaceArray, geographicMapCellTypeArray);
             //|| basicPlatormGeographicMapCellTypeFactory.JUMP_THRU_CELL_TYPE.isType(cellType)
                if (hasSolidBlock) {
                    
                    //logUtil.put("found cellType: " + cellType, this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
                    
//                    if (this.autoStepBlocks) {
//                        // int newY = this.getHeight() -
//                        // possibleStepGeographicMapCellPosition.getPoint().getYP().intValue();
//                        final int newY = possibleStepGeographicMapCellPosition.getPoint().getYP() - layer.getHeight();
//                        //logUtil.put("Stepping at: y: " + newY, this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
//                        // -possibleStepGeographicMapCellPosition.getPoint().getXP().intValue()
//                        layer.setPosition(layer.getXP(), newY, layer.getZP());
//                        this.previousGeographicMapCellPosition = possibleStepGeographicMapCellPosition;
//                        return possibleStepGeographicMapCellPosition;
//                    } else {
                        //logUtil.put("do not move", this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
                        //this.blockGeographicMapCellPosition = possibleStepGeographicMapCellPosition;
                        velocityProperties.getVelocityXBasicDecimalP().set(0);
                        this.previousGeographicMapCellPosition = null;
                        return null;
//                    }
                } else {
                    //logUtil.put("cellType: " + cellType, this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
                    this.previousGeographicMapCellPosition = possibleStepGeographicMapCellPosition;
                    return possibleStepGeographicMapCellPosition;
                }

            }

        }
        
        //logUtil.put("not moving", this, "getGeographicMapCellPositionIfNotSolidBlockOrOffMap");
        this.previousGeographicMapCellPosition = null;
        return null;
        //this.previousGeographicMapCellPosition = geographicMapCellPosition;
        //return geographicMapCellPosition;
    }

    @Override
    public void moveAndLand(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final GeographicMapCellPosition geographicMapCellPosition, final VelocityProperties velocityProperties, final AllBinaryLayer layer, final int x, final int y) throws Exception {
        
        //final int x = velocityProperties.getVelocityXBasicDecimalP().getScaled();
        //final int y = velocityProperties.getVelocityYBasicDecimalP().getScaled();

        //logUtil.put(new StringMaker().append("x: ").append(x).append(" y: ").append(y).append(CommonSeps.getInstance().SPACE).append(layer.getViewPosition().getXP()).toString(), this, "moveAndLand");
        
        if (geographicMapCellPosition != null) {

            ((TopViewCharacterInterface) layer).terrainMove(geographicMapInterfaceArray, geographicMapCellTypeArray, x, y);

            //final String MOVE_AND_LAND = "moveAndLand";
            //logUtil.put(new StringMaker().append("Should Land at: ").append(this.gravityActionIndex).append(" y: ").append(y).toString(), this, MOVE_AND_LAND);
//            if (this.gravityActionIndex != 0 && y > 0 && geographicMapCellPosition != null) {
//
//                final GeographicMapCellType cellType = geographicMapBehavior.getCellTypeAt(geographicMapInterfaceArray, geographicMapCellPosition);
//
//                //logUtil.put(new StringMaker().append("Should Land at cellType: ").append(cellType).toString(), this, MOVE_AND_LAND);
//                if (geographicMapCellTypeFactory.BLOCK_CELL_TYPE.isType(cellType)) // ||
//                // this.getViewPositionY() > DisplayInfoSingleton.getInstance().getLastHeight() - 55)
//                {
//                    final int landY = geographicMapCellPosition.getPoint().getYP() - layer.getHeight() + offsetY;
//
//                    // logUtil.put("Cell Position: y: " + geographicMapCellPosition.getPoint().getXP().intValue(), this, "moveAndLand");
//                    // logUtil.put("Landing at: y: " + landY, this, "moveAndLand");
//                    layer.setPosition(layer.getXP(), landY, layer.getZP());
//                    //this.land(velocityProperties);
//                    //((TopViewCharacterInterface) layer).terrainLand();
//                }
//
//            }
//
//            ((TopViewCharacterInterface) layer).terrainEvent(x, y, geographicMapInterfaceArray, geographicMapCellPosition);
        } else {
            //logUtil.put("do not move", this, "moveAndLand");
        }
        
    }
    
    @Override
    public boolean move(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer, final int x, final int y) throws Exception {

        final GeographicMapCellPosition geographicMapCellPosition = this.getGeographicMapCellPositionIfNotSolidBlockOrOffMap(geographicMapInterfaceArray, geographicMapCellTypeArray, velocityProperties, layer, x, y);

        //this.gravity(velocityProperties, geographicMapInterfaceArray, geographicMapCellPosition);

        this.moveAndLand(geographicMapInterfaceArray, geographicMapCellTypeArray, geographicMapCellPosition, velocityProperties, layer, x, y);
        
        if(geographicMapCellPosition == null) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public void left(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer) throws Exception {

        final GeographicMapCellPosition geographicMapCellPosition = this.getLeftPosition(geographicMapInterfaceArray, layer);

        if (geographicMapCellPosition != null) {

            final GeographicMapCellPosition possibleStepGeographicMapCellPosition
                    = geographicMapInterfaceArray[0].getGeographicMapCellPositionFactory().getInstance(
                            geographicMapCellPosition.getColumn(),
                            geographicMapCellPosition.getRow() - 1);

            geographicMapBehavior.getCellTypeAt(geographicMapInterfaceArray, geographicMapCellTypeArray, possibleStepGeographicMapCellPosition);
            final boolean hasSolidBlock = this.hasSolidBlock(geographicMapInterfaceArray, geographicMapCellTypeArray);

            if (hasSolidBlock) {
                if (this.autoStepBlocks) {
                    ((TopViewCharacterInterface) layer).leftp();
                } else {
                    velocityProperties.getVelocityXBasicDecimalP().set(0);
                }
            } else {
                ((TopViewCharacterInterface) layer).leftp();
            }
        }
        
    }

    @Override
    public void right(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer) throws Exception {

        final GeographicMapCellPosition geographicMapCellPosition = this.getRightPosition(geographicMapInterfaceArray, layer);

        if (geographicMapCellPosition != null) {

            final GeographicMapCellPosition possibleStepGeographicMapCellPosition
                    = geographicMapInterfaceArray[0].getGeographicMapCellPositionFactory().getInstance(
                            geographicMapCellPosition.getColumn(),
                            geographicMapCellPosition.getRow() - 1);

            geographicMapBehavior.getCellTypeAt(geographicMapInterfaceArray, geographicMapCellTypeArray, possibleStepGeographicMapCellPosition);
            final boolean hasSolidBlock = this.hasSolidBlock(geographicMapInterfaceArray, geographicMapCellTypeArray);

            if (hasSolidBlock) {
                if (this.autoStepBlocks) {
                    ((TopViewCharacterInterface) layer).rightp();
                } else {
                    velocityProperties.getVelocityXBasicDecimalP().set(0);
                }
            } else {
                ((TopViewCharacterInterface) layer).rightp();
            }
        }
    }

}
