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
package org.allbinary.logic.communication.java.vm.stats;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.data.tables.TableMappingInterface;

public class JvmStatisticsMapping extends JvmStatistics implements TableMappingInterface
{
   public JvmStatisticsMapping()
   {
      super();
   }

   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      hashMap.put(JvmStatisticsData.FREE_MEMORY_BYTES,this.getFreeMemoryBytesString());
      hashMap.put(JvmStatisticsData.FREE_MEMORY_KILOBYTES,this.getFreeMemoryKilobytesString());
      hashMap.put(JvmStatisticsData.FREE_MEMORY_MEGABYTES,this.getFreeMemoryMegabytesString());
      
      hashMap.put(JvmStatisticsData.MAX_MEMORY_BYTES,this.getMaxMemoryBytesString());
      hashMap.put(JvmStatisticsData.MAX_MEMORY_KILOBYTES,this.getMaxMemoryKilobytesString());
      hashMap.put(JvmStatisticsData.MAX_MEMORY_MEGABYTES,this.getMaxMemoryMegabytesString());
      
      hashMap.put(JvmStatisticsData.NUMBER_OF_THREADS,this.getNumberOfThreadsString());
      
      hashMap.put(JvmStatisticsData.TOTAL_MEMORY_BYTES,this.getTotalMemoryBytesString());
      hashMap.put(JvmStatisticsData.TOTAL_MEMORY_KILOBYTES,this.getTotalMemoryKilobytesString());
      hashMap.put(JvmStatisticsData.TOTAL_MEMORY_MEGABYTES,this.getTotalMemoryMegabytesString());

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return JvmStatisticsData.NAME;
   }
   
   public Vector toVector() throws Exception
   {
      Vector vector = new Vector();
      
      vector.add(this.getFreeMemoryBytesString());
      vector.add(this.getFreeMemoryKilobytesString());
      vector.add(this.getFreeMemoryMegabytesString());
      
      vector.add(this.getMaxMemoryBytesString());
      vector.add(this.getMaxMemoryKilobytesString());
      vector.add(this.getMaxMemoryMegabytesString());
      
      vector.add(this.getNumberOfThreadsString());
      
      vector.add(this.getTotalMemoryBytesString());
      vector.add(this.getTotalMemoryKilobytesString());
      vector.add(this.getTotalMemoryMegabytesString());
      
      return vector;
   }
}
