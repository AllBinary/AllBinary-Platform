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
package org.allbinary.game.media.graphics.geography.map.racetrack;

import org.allbinary.media.graphics.geography.map.BasicGeographicMap;
import org.allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorFactoryBase;
import org.allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorBase;

/**
 *
 * @author user
 */
public class SimplePathFinderGraphVisitorFactory
    extends PathFinderGraphVisitorFactoryBase {

    private final int edgeMinimum;
    private final int minPathWeight;
    private final int maxPathWeight;

    public SimplePathFinderGraphVisitorFactory()
    {
        this(10, 8, 100000);
    }

    public SimplePathFinderGraphVisitorFactory(
       int edgeMinimum, int minPathWeight, int maxPathWeight)
    {
        this.edgeMinimum = edgeMinimum;
        this.minPathWeight = minPathWeight;
        this.maxPathWeight = maxPathWeight;
    }

    public PathFinderGraphVisitorBase getInstance(
            BasicGeographicMap geographicMapInterface)
    {
        return new BasePathFinderGraphVisitor(
            geographicMapInterface,
            this.edgeMinimum, this.minPathWeight, this.maxPathWeight);
    }
}
