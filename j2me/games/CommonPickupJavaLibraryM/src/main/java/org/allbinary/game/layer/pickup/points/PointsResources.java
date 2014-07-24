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
package org.allbinary.game.layer.pickup.points;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class PointsResources {

    private static final PointsResources SINGLETON = new PointsResources();
    
    public String RESOURCE = "points_drop_20_by_20.png";
    public String RESOURCE_2 = "points_2_drop_20_by_20.png";
    public String RESOURCE_3 = "points_3_drop_20_by_20.png";
    public String RESOURCE_4 = "points_4_drop_20_by_20.png";

    private PointsResources()
    {
    }

    public static PointsResources getInstance()
    {
        return SINGLETON;
    }
}
