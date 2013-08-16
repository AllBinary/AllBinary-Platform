/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
