/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package abcs.logic.system.security.licensing;

import org.allbinary.util.BasicArrayList;

/**
 *
 * @author user
 */
public class LockableFeatureFactory {
    
    private static final LockableFeatureFactory instance = new LockableFeatureFactory();

    /**
     * @return the instance
     */
    public static LockableFeatureFactory getInstance() {
        return instance;
    }
    
    private final BasicArrayList list = new BasicArrayList();

    /**
     * @return the list
     */
    public BasicArrayList getList() {
        return list;
    }
}
