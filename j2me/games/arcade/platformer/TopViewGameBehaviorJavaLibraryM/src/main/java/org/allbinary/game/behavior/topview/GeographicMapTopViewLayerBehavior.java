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

import org.allbinary.game.physics.velocity.VelocityProperties;
import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.topview.BasicTopViewGeographicMapCellTypeFactory;
import org.allbinary.util.BasicArrayList;

/**
 *
 * @author User
 */
public class GeographicMapTopViewLayerBehavior extends TopViewGameLayerBehavior {
    
    public GeographicMapTopViewLayerBehavior(final int maxGravityActionIndex) {
        super(maxGravityActionIndex);
    }

    public GeographicMapCellPosition getGeographicMapCellPositionIfNotSolidBlockOrOffMap(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer, final int x, int y) throws Exception {
        return null;
    }

    public GeographicMapCellPosition getGeographicMapCellPositionIfNotSolidBlockOrOffMap(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final GeographicMapCellPosition geographicMapCellPosition, final VelocityProperties velocityProperties, final AllBinaryLayer layer) throws Exception {
        return null;
    }

    public GeographicMapCellPosition getGeographicMapCellPositionIfNotSolidBlockOrOffMap(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final BasicArrayList geographicMapCellPositionList, final VelocityProperties velocityProperties, final AllBinaryLayer layer) throws Exception {
        return null;
    }
            
    public void gravity(final VelocityProperties velocityProperties, final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final GeographicMapCellPosition geographicMapCellPosition) throws Exception {
        
    }

    public void left(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer) throws Exception {
        
    }

    public boolean move(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer, final int x, final int y) throws Exception {
        return false;
    }

    public void moveAndLand(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final GeographicMapCellPosition geographicMapCellPosition, final VelocityProperties velocityProperties, final AllBinaryLayer layer, final int x, final int y) throws Exception {
        
    }

    public void right(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray, final VelocityProperties velocityProperties, final AllBinaryLayer layer) throws Exception {
        
    }

    public boolean hasSolidBlock(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray) {
        
        final int size = geographicMapInterfaceArray.length;
        
        BasicTopViewGeographicMapCellTypeFactory basicTopViewGeographicMapCellTypeFactory;
        for(int index = 0; index < size; index++) {
            basicTopViewGeographicMapCellTypeFactory = (BasicTopViewGeographicMapCellTypeFactory) geographicMapInterfaceArray[index].getGeographicMapCellTypeFactory();
            if(basicTopViewGeographicMapCellTypeFactory.BLOCK_CELL_TYPE.isType(geographicMapCellTypeArray[index])) {
                return true;
            }
        }

        return false;
    }

    public boolean isOffMap(final BasicGeographicMap[] geographicMapInterfaceArray, final GeographicMapCellType[] geographicMapCellTypeArray) {
        
        final int size = geographicMapInterfaceArray.length;
        
        BasicTopViewGeographicMapCellTypeFactory basicTopViewGeographicMapCellTypeFactory;
        for(int index = 0; index < size; index++) {
            basicTopViewGeographicMapCellTypeFactory = (BasicTopViewGeographicMapCellTypeFactory) geographicMapInterfaceArray[index].getGeographicMapCellTypeFactory();
            if(basicTopViewGeographicMapCellTypeFactory.OFF_MAP_CELL_TYPE.isType(geographicMapCellTypeArray[index])) {
                return true;
            }
        }

        return false;
    }
    
}
