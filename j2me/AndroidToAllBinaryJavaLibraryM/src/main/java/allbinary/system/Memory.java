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
package allbinary.system;

import abcs.logic.basic.string.StringMaker;

//TWB - Google fucked up they removed the class after 2.0
//import dalvik.system.VMRuntime;

public class Memory
{
    public static String getInfo()
    {
      //TWB - Google fucked up they removed the class after 2.0
        //VMRuntime vm = VMRuntime.getRuntime();

        StringMaker stringBuffer = new StringMaker();

        //stringBuffer.append("ExternalBytesAllocated: ");
        //stringBuffer.append(vm.getExternalBytesAllocated());
        //stringBuffer.append(" MinimumHeapSize: ");
        //stringBuffer.append(vm.getMinimumHeapSize());
        //stringBuffer.append(" TargetHeapUtilization: ");
        //stringBuffer.append(vm.getTargetHeapUtilization());

        //stringBuffer.append(" Memory: Max: ");
        //stringBuffer.append(Runtime.getRuntime().maxMemory());
        //stringBuffer.append(CommonStrings.getInstance().SPACE);
        //stringBuffer.append(CommonStrings.getInstance().TOTAL_LABEL);
        //stringBuffer.append(Runtime.getRuntime().totalMemory());
        //stringBuffer.append(" Free: ");
        //stringBuffer.append(Runtime.getRuntime().freeMemory());

        long maxUtilizedMemoryAvailable = 
            //(int) (vm.getTargetHeapUtilization() * Runtime.getRuntime().maxMemory());
            Runtime.getRuntime().maxMemory();
        long memoryUsed = 
            //(int) (vm.getExternalBytesAllocated() + Runtime.getRuntime().totalMemory());
            Runtime.getRuntime().totalMemory();
        stringBuffer.append("APK Memory: Available: ");
        stringBuffer.append(maxUtilizedMemoryAvailable);
        stringBuffer.append("/");
        stringBuffer.append(Runtime.getRuntime().maxMemory());
        stringBuffer.append(" Used: ");
        stringBuffer.append(memoryUsed);
        stringBuffer.append(" Free: ");
        stringBuffer.append(maxUtilizedMemoryAvailable - memoryUsed);
        stringBuffer.append("/");
        stringBuffer.append(Runtime.getRuntime().maxMemory() - memoryUsed);

        return stringBuffer.toString();
    }
}
