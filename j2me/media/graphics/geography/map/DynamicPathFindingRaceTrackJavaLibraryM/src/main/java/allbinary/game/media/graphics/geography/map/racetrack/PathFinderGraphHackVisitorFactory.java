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
package allbinary.game.media.graphics.geography.map.racetrack;

import allbinary.media.graphics.geography.map.BasicGeographicMap;
import allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorFactoryInterface;
import allbinary.media.graphics.geography.pathfinding.PathFinderGraphVisitorInterface;

/**
 *
 * @author user
 */
public class PathFinderGraphHackVisitorFactory
implements PathFinderGraphVisitorFactoryInterface
{

    private final int edgeMinimum;
    private final int minPathWeight;
    private final int maxPathWeight;

    public PathFinderGraphHackVisitorFactory()
    {
        this(10, 8, 100000);
    }

    public PathFinderGraphHackVisitorFactory(
       int edgeMinimum, int minPathWeight, int maxPathWeight)
    {
        this.edgeMinimum = edgeMinimum;
        this.minPathWeight = minPathWeight;
        this.maxPathWeight = maxPathWeight;
    }

    public PathFinderGraphVisitorInterface getInstance(
        final BasicGeographicMap geographicMapInterface)
    {
        return new PathFinderGraphHackVisitor(
            geographicMapInterface,
            this.edgeMinimum, this.minPathWeight, this.maxPathWeight);
    }
}
