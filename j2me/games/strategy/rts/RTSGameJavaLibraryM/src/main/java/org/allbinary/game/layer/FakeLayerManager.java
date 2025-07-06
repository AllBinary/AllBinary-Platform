/*
 * AllBinary Open License Version 1
 * Copyright (c) 2002 AllBinary
 * 
 * By agreeing to this license you and any business entity you represent are
 * legally bound to the AllBinary Open License Version 1 legal agreement.
 * 
 * You may obtain the AllBinary Open License Version 1 legal agreement from
 * AllBinary or the root directory of AllBinary's AllBinary Platform repository.
 * 
 * Created By: Travis Berthelot
 */
package org.allbinary.game.layer;

import org.allbinary.game.GameInfo;
import org.allbinary.graphics.color.BasicColorFactory;
import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.map.GeographicMapCellType;
import org.allbinary.media.graphics.geography.map.GeographicMapCompositeInterface;

public class FakeLayerManager extends AllBinaryGameLayerManager
    implements GeographicMapCompositeInterface {

    private BasicGeographicMap[] geographicMapInterfaceArray;
    private GeographicMapCellType[] geographicMapCellTypeArray;

    public FakeLayerManager(GameInfo gameInfo) {
        super(BasicColorFactory.getInstance().BLACK, BasicColorFactory.getInstance().WHITE, gameInfo);
    }

    public BasicGeographicMap[] getGeographicMapInterface() {
        return geographicMapInterfaceArray;
    }

    public void setGeographicMapInterface(
        final BasicGeographicMap[] geographicMapInterfaceArray) {
        this.geographicMapInterfaceArray = geographicMapInterfaceArray;
        this.geographicMapCellTypeArray = new GeographicMapCellType[this.geographicMapInterfaceArray.length];
    }

    public GeographicMapCellType[] geographicMapCellTypeArray() {
        return this.geographicMapCellTypeArray;
    }

}
