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
package org.allbinary.input.motion.button;

import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListUtil;

/**
 *
 * @author user
 */
public class BaseTouchInput 
{    
    protected BaseTouchInput()
    {
        
    }
    
    private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();
    
    /**
     * @return the list
     */
    public BasicArrayList getList()
    {
        return basicArrayListUtil.getImmutableInstance();
    }
    
    public String toString()
    {
        return this.getClass().getName();
    }
}
