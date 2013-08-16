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
package allbinary.business.user.modules.admin.configuration;

import allbinary.business.context.configuration.ContextConfigurationInterface;

public interface AdminConfigurationInterface
{
   public void write() throws Exception;
   
   public ContextConfigurationInterface getContextConfigurationInterface();
   public void setContextConfigurationInterface(ContextConfigurationInterface contextConfigurationInterface);
}
