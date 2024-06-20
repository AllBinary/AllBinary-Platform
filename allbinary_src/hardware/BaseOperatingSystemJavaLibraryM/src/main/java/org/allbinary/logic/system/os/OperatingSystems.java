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

public class OperatingSystems
{
    private static final OperatingSystems instance = new OperatingSystems();
    
   public final String LINUX = "Linux";
   public final String WINDOWS = "Windows";
   public final String SOLARIS = "Solaris";
   
   public final String WINDOWS2000 = "Windows 2000";
   public final String WINDOWS_NT = "Windows NT";
   public final String WINDOWS_10 = "Windows 10";
   public final String WINDOWS_11 = "Windows 11";
   public final String WINDOWS_VISTA = "Windows Vista";
   public final String WINDOWS_XP = "Windows XP";

   public final String ANDROID = LINUX;
   
   private OperatingSystems()
   {
   }
 
   private boolean unknownSpecificOSAllowed = true;
   
    public boolean isUnknownSpecificOSAllowed()
    {
        return unknownSpecificOSAllowed;
    }

    public void setUnknownSpecificOSAllowed(boolean aUnknownSpecificOSAllowed)
    {
        unknownSpecificOSAllowed = aUnknownSpecificOSAllowed;
    }

    public static OperatingSystems getInstance()
    {
        return instance;
    }
   
}
