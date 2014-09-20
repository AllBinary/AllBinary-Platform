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
package org.allbinary.logic.system.hardware.components.linux;

public class CpuInfo
{   
    private String name;    
    
    private CpuInfo(String name)
    {
        this.name = name;
    }
    
    public String toString()
    {
        return name;
    }
    
    public static final CpuInfo PROCESSOR = new CpuInfo ("processor");
    public static final CpuInfo VENDORID = new CpuInfo ("vendor_id");
    public static final CpuInfo CPUFAMILY = new CpuInfo ("cpu family");
    public static final CpuInfo MODEL = new CpuInfo ("model");
    public static final CpuInfo MODELNAME = new CpuInfo ("model name");
    public static final CpuInfo STEPPING = new CpuInfo ("stepping");
    public static final CpuInfo CPUMHZ = new CpuInfo ("cpu MHz");
    public static final CpuInfo CACHESIZE = new CpuInfo ("cache size");
    public static final CpuInfo FDIVBUG = new CpuInfo ("fdiv_bug");
    public static final CpuInfo HLTBUG = new CpuInfo ("hlt_bug");
    public static final CpuInfo F00FBUG = new CpuInfo ("f00f_bug");
    public static final CpuInfo COMABUG = new CpuInfo ("coma_bug");
    public static final CpuInfo FPU = new CpuInfo ("fpu");
    public static final CpuInfo FPUEXCEPTION = new CpuInfo ("fpu_exception");
    public static final CpuInfo CPUIDLEVEL = new CpuInfo ("cache size");
    public static final CpuInfo WP = new CpuInfo ("yes");
    public static final CpuInfo FLAGS = new CpuInfo ("flags");
    public static final CpuInfo BOGOMIPS = new CpuInfo ("bogomips");    
}