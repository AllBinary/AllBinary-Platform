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
package org.allbinary.business.time.modified;

import org.allbinary.business.time.Time;

public class TimeLastModified extends Time
{
   public TimeLastModified(String time)
   {
      //super(long) for both constructors since Scala lets the constructors of a class call only one super constructor. Time(String) is new Long(time).
      super(Long.parseLong(time));
   }
   
   public TimeLastModified(long time)
   {
      super(time);
   }
}
