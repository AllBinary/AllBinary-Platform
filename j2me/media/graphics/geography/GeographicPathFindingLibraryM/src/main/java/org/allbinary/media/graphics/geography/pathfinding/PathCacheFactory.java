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
package org.allbinary.media.graphics.geography.pathfinding;

import java.util.Hashtable;

import org.allbinary.util.BasicArrayList;

public class PathCacheFactory
{
   private static PathCacheFactory pathFactory = new PathCacheFactory();
   
   private Hashtable hashtable = new Hashtable();

   private PathCacheFactory()
   {
   }

   public static PathCacheFactory getInstance()
   {
      return pathFactory;
   }

   public int getSize()
   {
      return this.hashtable.size();
   }
   
   public void add(Integer pathId, BasicArrayList list)
   {
      this.hashtable.put(pathId, list);
   }

   public void remove(Integer pathId)
   {
      this.hashtable.remove(pathId);
   }

   public void removeAll()
       throws Exception
   {
       this.hashtable.clear();
       if(this.hashtable.size() > 0)
       {
           throw new Exception("Did not clear");
       }
   }

   public BasicArrayList getInstance(
      Integer pathIdInteger)
      throws Exception
   {
       return (BasicArrayList) 
          this.hashtable.get(pathIdInteger);
   }
}