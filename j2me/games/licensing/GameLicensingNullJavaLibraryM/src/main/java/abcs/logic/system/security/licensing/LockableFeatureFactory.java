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
package abcs.logic.system.security.licensing;

import org.allbinary.util.BasicArrayList;

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
