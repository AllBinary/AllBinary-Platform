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
package org.allbinary.game.resource;

import java.util.Hashtable;

import org.allbinary.graphics.RelativeRelationship;
import org.allbinary.util.BasicArrayList;

public class ResourceRelativeRelationshipFactory 
    implements ResourceRelativeRelationshipFactoryInterface
{
   private final Hashtable hashtable = new Hashtable();
   
   private boolean initialized;
   
   @Override
   public void init(int level) throws Exception
   {
       this.setInitialized(true);
   }
   
    @Override
    public BasicArrayList getResourceRelativeRelationshipList(String resource) throws Exception
    {
        return (BasicArrayList) this.hashtable.get(resource);
    }
    
    @Override
    public void addResourceRelativeRelationship(String resource, RelativeRelationship hardPoint)
       throws Exception
    {
       BasicArrayList list = this.getResourceRelativeRelationshipList(resource);
       
       if(list == null)
       {
           list = new BasicArrayList();
       }
       list.add(hardPoint);
       this.hashtable.put(resource, list);
    }
    
    @Override
    public boolean isLoadingLevel(int level)
    {
        return false;
    }
    
    @Override
    public boolean isFeature()
    {
       return false;
    }

    public void setInitialized(boolean initialized)
    {
        this.initialized = initialized;
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
