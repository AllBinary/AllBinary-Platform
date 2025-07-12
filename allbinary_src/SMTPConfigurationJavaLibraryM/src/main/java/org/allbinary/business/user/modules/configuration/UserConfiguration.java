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
package org.allbinary.business.user.modules.configuration;

import java.util.HashMap;

import org.allbinary.business.entry.EntryData;
import org.allbinary.business.time.created.TimeCreated;
import org.allbinary.business.time.modified.TimeLastModified;
import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfiguration;
import org.allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationInterface;

public class UserConfiguration implements UserConfigurationInterface
{
   //private FtpConfigurationInterface ftpConfigurationInterface;
   private UserEmailConfigurationInterface userEmailConfigurationInterface;
   
   private TimeCreated timeCreated;
   private TimeLastModified timeLastModified;

   public UserConfiguration()
   {
      this.userEmailConfigurationInterface = (UserEmailConfigurationInterface)
         new UserEmailConfiguration();

      this.timeCreated = new TimeCreated(0);

      this.timeLastModified = new TimeLastModified(0);
   }

   public UserConfiguration(HashMap hashMap)
   {
      //this.ftpConfigurationInterface = (FtpConfigurationInterface)
        // new FtpConfiguration(hashMap);

      this.userEmailConfigurationInterface = (UserEmailConfigurationInterface)
         new UserEmailConfiguration(hashMap);

      this.timeCreated = 
         new TimeCreated((String) hashMap.get(EntryData.getInstance().TIMECREATED));

      this.timeLastModified = 
         new TimeLastModified((String) hashMap.get(EntryData.getInstance().LASTMODIFIED));
   }

   /*
   public FtpConfigurationInterface getFtpConfigurationInterface()
   {
      return this.ftpConfigurationInterface;
   }

   public void setFtpConfigurationInterface(
      FtpConfigurationInterface ftpConfigurationInterface)
   {
      this.ftpConfigurationInterface = ftpConfigurationInterface;
   }
   */
   
   public UserEmailConfigurationInterface getUserEmailConfigurationInterface()
   {
      return this.userEmailConfigurationInterface;
   }

   public void setUserEmailConfigurationInterface(
      UserEmailConfigurationInterface userEmailConfigurationInterface)
   {
      this.userEmailConfigurationInterface = userEmailConfigurationInterface;
   }
   
   public TimeCreated getTimeCreated()
   {
      return this.timeCreated;
   }
   
   public TimeLastModified getTimeLastModified()
   {
      return this.timeLastModified;
   }
   
   public void setTimeCreated(TimeCreated timeCreated)
   {
      this.timeCreated = timeCreated;
   }
   
   public void setTimeLastModified(TimeLastModified timeLastModified)
   {
      this.timeLastModified = timeLastModified;
   }   
}
