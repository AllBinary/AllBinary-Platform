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
package org.allbinary.logic.communication.smtp.configuration.server;

import java.util.HashMap;
import java.util.Vector;

import org.allbinary.data.tables.TableMappingInterface;

public class EmailServerConfigurationMapping implements TableMappingInterface
{
   private EmailServerConfigurationInterface emailServerConfigurationInterface;
   
   public EmailServerConfigurationMapping(
      EmailServerConfigurationInterface emailServerConfigurationInterface)
   {
      this.emailServerConfigurationInterface = emailServerConfigurationInterface;
   }
   
   public HashMap toHashMap()
   {
      HashMap hashMap = new HashMap();

      hashMap.put(EmailServerConfigurationData.SERVER, this.emailServerConfigurationInterface.getSmtpServer());
      hashMap.put(EmailServerConfigurationData.ACCOUNT, this.emailServerConfigurationInterface.getAccountName());
      hashMap.put(EmailServerConfigurationData.PASSWORD, this.emailServerConfigurationInterface.getPassword());

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return EmailServerConfigurationData.NAME;
   }
   
   public Vector toVector() throws Exception
   {
      Vector vector = new Vector();

      vector.add(this.emailServerConfigurationInterface.getSmtpServer());

      return vector;
   }   
}
