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
package allbinary.business.user.modules.configuration;

import allbinary.business.time.created.TimeCreated;
import allbinary.business.time.modified.TimeLastModified;
import allbinary.logic.communication.smtp.configuration.user.UserEmailConfigurationInterface;

public interface UserConfigurationInterface
{
   public UserEmailConfigurationInterface getUserEmailConfigurationInterface();

   public void setUserEmailConfigurationInterface(
      UserEmailConfigurationInterface userEmailConfigurationInterface);
   
   public TimeCreated getTimeCreated();
   
   public TimeLastModified getTimeLastModified();

   public void setTimeCreated(TimeCreated timeCreated);

   public void setTimeLastModified(TimeLastModified timeLastModified);
}
