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
package org.allbinary.logic.system.os;

//ActualPlatform
public class SystemProperties
{

    private static final SystemProperties instance = new SystemProperties();

    /**
     * @return the instance
     */
    //ActualPlatform
    public static SystemProperties getInstance() {
        return SystemProperties.instance;
    }
    
    private final String OS_NAME = "os.name";
    private final String OS_ARCH = "os.arch";
    private final String OS_VERSION = "os.version";
    
     private SystemProperties()
     {         
     }

     /*
     public String getJavaVersion()
     {
        return System.getProperty("java.version");
     }
     
     public String getFileSep()
     {
        return System.getProperty("file.separator");        
     }
     
     public String getLineSep()
     {
         return System.getProperty("line.separator");
     }
     
     public String getPathSep()
     {
         return System.getProperty("path.separator");
     }
     
     public String getClassVersion()
     {
         return System.getProperty("java.class.version");
     }
     
     public String getVendor()
     {         
         return System.getProperty("java.vendor");
     }
     
     public String getVendorUrl()
     {
         return System.getProperty("java.vendor.url");
     }
     */
     
     //ActualPlatform
     public String getName()
     {
         return System.getProperty(OS_NAME);
     }
     
     //ActualPlatform
     public String getArch()
     {
         return System.getProperty(OS_ARCH);
     }
     
     //ActualPlatform
     public String getVersion()
     {
         return System.getProperty(OS_VERSION);
     }

}