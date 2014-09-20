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

public class JvmStatisticsData
{
   private JvmStatisticsData()
   {
      super();
   }

   public static final String JVM_STATISTICS = "JVM_STATISTICS_";
   
   public static final String NAME = JVM_STATISTICS + "NAME";
   
   public static final String TOTAL_MEMORY_BYTES = JVM_STATISTICS + "TOTAL_MEMORY_BYTES";
   public static final String TOTAL_MEMORY_KILOBYTES = JVM_STATISTICS + "TOTAL_MEMORY_KILOBYTES";
   public static final String TOTAL_MEMORY_MEGABYTES = JVM_STATISTICS + "TOTAL_MEMORY_MEGABYTES";

   public static final String FREE_MEMORY_BYTES = JVM_STATISTICS + "FREE_MEMORY_BYTES";
   public static final String FREE_MEMORY_KILOBYTES = JVM_STATISTICS + "FREE_MEMORY_KILOBYTES";
   public static final String FREE_MEMORY_MEGABYTES = JVM_STATISTICS + "FREE_MEMORY_MEGABYTES";

   public static final String MAX_MEMORY_BYTES = JVM_STATISTICS + "MAX_MEMORY_BYTES";
   public static final String MAX_MEMORY_KILOBYTES = JVM_STATISTICS + "MAX_MEMORY_KILOBYTES";
   public static final String MAX_MEMORY_MEGABYTES = JVM_STATISTICS + "MAX_MEMORY_MEGABYTES";
   
   public static final String NUMBER_OF_THREADS = JVM_STATISTICS + "NUMBER_OF_THREADS";
}
