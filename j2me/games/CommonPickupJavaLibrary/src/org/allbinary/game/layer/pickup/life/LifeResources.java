/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.layer.pickup.life;

/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class LifeResources {

    private static final LifeResources SINGLETON = new LifeResources();
    
    public String RESOURCE = "life_drop_20_by_20.png";

    private LifeResources()
    {
    }

    public static LifeResources getInstance()
    {
        return SINGLETON;
    }
}
