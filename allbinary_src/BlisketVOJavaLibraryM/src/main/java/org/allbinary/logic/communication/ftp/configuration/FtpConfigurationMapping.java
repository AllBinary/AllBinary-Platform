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
package org.allbinary.logic.communication.ftp.configuration;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.data.tables.TableMappingInterface;

public class FtpConfigurationMapping implements TableMappingInterface
{
   private FtpConfigurationInterface ftpConfigurationInterface;
   
   public FtpConfigurationMapping(FtpConfigurationInterface ftpConfigurationInterface)
   {
      this.ftpConfigurationInterface = ftpConfigurationInterface;
   }

   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      hashMap.put(FtpConfigurationData.SERVER, this.ftpConfigurationInterface.getServer());
      hashMap.put(FtpConfigurationData.USERNAME, this.ftpConfigurationInterface.getUserName());
      hashMap.put(FtpConfigurationData.PASSWORD, this.ftpConfigurationInterface.getPassword());
      hashMap.put(FtpConfigurationData.PATH, this.ftpConfigurationInterface.getPath());

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return FtpConfigurationData.NAME;
   }
   
   public Vector toVector() throws Exception
   {
      Vector vector = new Vector();
      
      vector.add(this.ftpConfigurationInterface.getServer());
      vector.add(this.ftpConfigurationInterface.getUserName());
      vector.add(this.ftpConfigurationInterface.getPassword());
      vector.add(this.ftpConfigurationInterface.getPath());

      return vector;
   }
}
