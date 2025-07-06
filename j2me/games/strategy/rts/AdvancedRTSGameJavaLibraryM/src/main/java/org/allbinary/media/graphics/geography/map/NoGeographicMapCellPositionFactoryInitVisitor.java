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
package org.allbinary.media.graphics.geography.map;

import org.allbinary.game.layer.AllBinaryTiledLayer;

/**
 *
 * @author User
 */
public class NoGeographicMapCellPositionFactoryInitVisitor 
    implements GeographicMapCellPositionFactoryInitVisitorInterface {
    
    public void visit(AllBinaryTiledLayer tiledLayer, GeographicMapCellPosition cellPosition)
           throws Exception
    {
    }
    
}
