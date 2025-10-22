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
import org.allbinary.util.BasicArrayListUtil;

public class PathCacheFactory
{
   private static PathCacheFactory pathFactory = new PathCacheFactory();

   private final BasicArrayListUtil basicArrayListUtil = BasicArrayListUtil.getInstance();

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
   
   public void add(final Integer pathId, final BasicArrayList list)
   {
      this.hashtable.put(pathId, list);
   }

   public void remove(final Integer pathId)
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

   public BasicArrayList getInstance(final Integer pathIdInteger)
      throws Exception
   {
       Object listCanBeNull = this.hashtable.get(pathIdInteger);
       
       if(listCanBeNull == null) {
           listCanBeNull = basicArrayListUtil.getImmutableInstance();
       }

       return (BasicArrayList) listCanBeNull;
   }
}