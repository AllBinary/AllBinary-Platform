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
package abcs.logic.system.security.licensing;

public class AbeClientInformationData
{
    private static final AbeClientInformationData instance = 
        new AbeClientInformationData();
    
    public static AbeClientInformationData getInstance()
    {
        return instance;
    }
    
   private AbeClientInformationData()
   {
   }

public final String KEY = "KEY";

   public final String NAME = "NAME";
   public final String VERSION = "VERSION";
   public final String SPECIALNAME = "SPECIALNAME";
   
   public final String LICENSEID = "LICENSEID";
   public final String LICENSE_TYPE = "LICENSETYPE";
   public final String PREVIOUSLICENSEID = "PREVIOUSLICENSEID";
   
   public final String OSNAME = "OSNAME";
   public final String OSARCH = "OSARCH";
   public final String OSVERSION = "OSVERSION";
   public final String OS = "OS";
   public final String HARDWARE = "HARDWARE";

   public final String LICENSESERVERS = "LICENSESERVERS";
   public final String NEWLICENSE = "NEWLICENSE";
   public final String ISNEW = "ISNEW";
   
   public final String SPECIAL = "SPECIAL";
}
