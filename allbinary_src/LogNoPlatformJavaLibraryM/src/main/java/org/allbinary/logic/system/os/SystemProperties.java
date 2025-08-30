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

//NoPlatform
public class SystemProperties
{

    private static final SystemProperties instance = new SystemProperties();

    /**
     * @return the instance
     */
    public static SystemProperties getInstance() {
        return instance;
    }
    
     private SystemProperties()
     {         
     }

     /*
     public String getJavaVersion()
     {
        throw new RuntimeException();
     }
     
     public String getFileSep()
     {
        throw new RuntimeException();
     }
     
     public String getLineSep()
     {
         throw new RuntimeException();
     }
     
     public String getPathSep()
     {
         throw new RuntimeException();
     }
     
     public String getClassVersion()
     {
         throw new RuntimeException();
     }
     
     public String getVendor()
     {         
         throw new RuntimeException();
     }
     
     public String getVendorUrl()
     {
         throw new RuntimeException();
     }
     */
     
     public String getName()
     {
         throw new RuntimeException();
     }
     
     public String getArch()
     {
         throw new RuntimeException();
     }
     
     public String getVersion()
     {
         throw new RuntimeException();
     }
}