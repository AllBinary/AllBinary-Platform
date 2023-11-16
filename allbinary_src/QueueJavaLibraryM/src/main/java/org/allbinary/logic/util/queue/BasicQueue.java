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
package org.allbinary.logic.util.queue;

import java.util.Vector;

public class BasicQueue
{
   protected final Vector queueVector = new Vector();

   protected BasicQueue()
   {
   }

   protected synchronized boolean offer(Object object)
      throws Exception
   {
      this.queueVector.add(object);
      return true;
   }

   protected synchronized void remove(Object object)
   {
      this.queueVector.remove(object);
   }

   protected synchronized Object removeLast()
   {
      Object object = this.queueVector.lastElement();
      this.queueVector.remove(object);
      return object;
   }
}