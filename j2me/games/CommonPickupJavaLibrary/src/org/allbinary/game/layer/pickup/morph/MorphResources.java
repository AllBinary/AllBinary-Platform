/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.allbinary.game.layer.pickup.morph;


/**
 *
 * @author Berthelot, Travis
 * @version 1.0
 */
public class MorphResources {

    private static final MorphResources SINGLETON = new MorphResources();
    
    public String RESOURCE = "morph_drop_20_by_20.png";

    private MorphResources()
    {
    }

    public static MorphResources getInstance()
    {
        return SINGLETON;
    }
}
