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
package org.allbinary.game.layer.pickup;

import org.allbinary.util.BasicArrayList;

public class CountedPickedUpLayerInterfaceFactoryPool
{
    private static CountedPickedUpLayerInterfaceFactoryPool SINGLETON = new CountedPickedUpLayerInterfaceFactoryPool();
    
    private BasicArrayList list;
    
    private CountedPickedUpLayerInterfaceFactoryPool()
    {
        list = new BasicArrayList();
    }
    
    public BasicArrayList getList()
    {
            return list;
    }
    
    public static CountedPickedUpLayerInterfaceFactoryPool getInstance()
    {
        return SINGLETON;
    }
    
    public static void init()
    {
        SINGLETON = new CountedPickedUpLayerInterfaceFactoryPool();
    }
}
