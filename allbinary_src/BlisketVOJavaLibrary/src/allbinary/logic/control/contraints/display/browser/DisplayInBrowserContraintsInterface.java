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
package allbinary.logic.control.contraints.display.browser;

public interface DisplayInBrowserContraintsInterface
{
   public boolean isMountable();
   public boolean isFrameable();
   public boolean isQuickFrameable();
   
   public void setMountable(boolean canBeMounted);
   
   public void setFrameable(boolean canBeFramed);

   public void setQuickFrameable(boolean canBeQuickFramed);
   
   public void setMountedLink(String mountedLink);
   public String getMountedLink();
}
