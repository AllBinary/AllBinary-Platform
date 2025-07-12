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
package org.allbinary.logic.system.security.licensing;

public class LockedUtil
{
    private static final LockedUtil instance = new LockedUtil();

    public static LockedUtil getInstance()
    {
        return instance;
    }
 
    public boolean isLockedFeature()
    {
        //Demo
        //return true;
        //Paid
        return false;
    }
    
    public boolean isLockedFeature(LockableFeature lockableFeatrue)
    {
        //Demo
        //return true;
        //Paid
        return false;
    }    
}
