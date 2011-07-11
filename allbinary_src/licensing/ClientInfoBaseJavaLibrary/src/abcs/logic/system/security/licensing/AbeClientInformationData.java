/*
 *Copyright (c) 2002 All Binary
 *All Rights Reserved.
 *Don't Duplicate or Distributed.
 *Trade Secret Information
 *For Internal Use Only
 *Confidential
 *Unpublished
 *
 *Created By: Travis Berthelot
 *Date: 11/19/02
 *
 *
 *Modified By         When       ?
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
