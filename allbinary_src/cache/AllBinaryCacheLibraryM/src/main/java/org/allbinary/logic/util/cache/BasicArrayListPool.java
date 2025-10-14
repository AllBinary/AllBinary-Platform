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
package org.allbinary.logic.util.cache;

import org.allbinary.util.BasicArrayList;

public class BasicArrayListPool extends AbstractArrayListPool
{
  public BasicArrayListPool(CacheableInterfaceFactoryInterface cacheableInterfaceFactoryInterface) {
	  super(cacheableInterfaceFactoryInterface);
  }
    
  @Override
  public void clear()
  {
      this.buffers.clear();
  }
  
  @Override
  public CacheableInterface remove(Object key) throws Exception
  {
      int size = this.buffers.size();
      if(size > 0)
      {
          return (CacheableInterface) buffers.remove(size - 1);
      }
      else
      {
          return this.cacheableInterfaceFactoryInterface.getInstance(key);
      }
      
      /*
    if (pos < 0) {
      buffers.add(this.cacheableInterfaceFactoryInterface.getInstance(key));
      pos = 0;
    }

    return (CacheableInterface) buffers.remove(pos--);
    */
  }

  public void addAll(BasicArrayList usedList) throws Exception
  {
      for(int index = usedList.size(); --index >= 0;)
      {
          CacheableInterface object = (CacheableInterface) usedList.objectArray[index];
          this.add(object);
      }
      usedList.clear();
  }
  
  public void releaseUsedBackToPool(BasicArrayList usedList) throws Exception
  {
      for(int index = usedList.size(); --index >= 0;)
      {
          BasicArrayListCacheable list = (BasicArrayListCacheable) usedList.objectArray[index];
          if(list.size() == 0)
          {
              usedList.remove(index);
              this.add(list);
          }
      }
  }
}
