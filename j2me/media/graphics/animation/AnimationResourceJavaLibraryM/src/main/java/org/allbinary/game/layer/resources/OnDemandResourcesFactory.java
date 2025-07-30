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
package org.allbinary.game.layer.resources;

public class OnDemandResourcesFactory
{
    public int size()
    {
        return 0;
    }
    
    public OnDemandResources remove(int layerId)
    {
        return OnDemandResources.NULL_ON_DEMAND_RESOURCES;
    }
}
