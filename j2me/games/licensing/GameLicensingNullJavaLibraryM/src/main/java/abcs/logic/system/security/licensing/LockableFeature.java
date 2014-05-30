/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.system.security.licensing;

/**
 *
 * @author user
 */
public class LockableFeature {

    private final String name;
    
    public LockableFeature(String name)
    {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
