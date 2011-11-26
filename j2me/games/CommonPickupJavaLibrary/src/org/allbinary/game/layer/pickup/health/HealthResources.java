/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.layer.pickup.health;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class HealthResources {

    private static final HealthResources SINGLETON = new HealthResources();
    
    public String RESOURCE = "health_drop_20_by_20.png";

    private HealthResources()
    {
    }

    public static HealthResources getInstance()
    {
        return SINGLETON;
    }
}
