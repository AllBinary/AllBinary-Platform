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
import org.allbinary.util.BasicArrayList;
import org.allbinary.util.BasicArrayListD;

import org.allbinary.data.tables.TableMappingInterface;
import org.allbinary.logic.StdUtil;

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
      HashMap hashMap = StdUtil.getInstance().createHashMap();

      hashMap.put(EmailServerConfigurationData.SERVER, this.emailServerConfigurationInterface.getSmtpServer());
      hashMap.put(EmailServerConfigurationData.ACCOUNT, this.emailServerConfigurationInterface.getAccountName());
      hashMap.put(EmailServerConfigurationData.PASSWORD, this.emailServerConfigurationInterface.getPassword());

      return hashMap;
   }
   
   public Object getKey() throws Exception
   {
      return EmailServerConfigurationData.NAME;
   }
   
   public BasicArrayList toVector() throws Exception
   {
      BasicArrayList vector = new BasicArrayListD();

      vector.add(this.emailServerConfigurationInterface.getSmtpServer());

      return vector;
   }   
}
