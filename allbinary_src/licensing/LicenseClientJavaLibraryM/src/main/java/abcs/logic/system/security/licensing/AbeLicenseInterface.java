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

import org.allbinary.util.BasicArrayList;

//import abcs.logic.system.security.licensing*;

public interface AbeLicenseInterface
{    
   boolean hasKey();

   public String getKey(String keyName);
   //public void setKey(String keyValue);
   public String getLicenseId();   
   
   public BasicArrayList getServers();
   
   public String getSpecial();
   
   public LicenseType getLicenseType();
   
   public boolean isValid();
   
   public String toString();
}
