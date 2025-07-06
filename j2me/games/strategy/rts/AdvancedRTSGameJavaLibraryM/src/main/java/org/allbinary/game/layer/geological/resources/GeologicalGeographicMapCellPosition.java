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
package org.allbinary.game.layer.geological.resources;

import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

/**
 *
 * @author user
 */
public class GeologicalGeographicMapCellPosition extends GeographicMapCellPosition
{
   private final GeologicalResource geologicalResource;

   public GeologicalGeographicMapCellPosition(final int column, final int row, final int columns, final int rows, final int width, final int height, final GeologicalResource geologicalResource)
           throws Exception
   {
       super(column, row, columns, rows, width, height);

       this.geologicalResource = geologicalResource;
   }

    /**
     * @return the geologicalResource
     */
    public GeologicalResource getGeologicalResource()
    {
        return geologicalResource;
    }
}
