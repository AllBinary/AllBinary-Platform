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
package org.allbinary.logic.control.contraints.display.browser;

import org.w3c.dom.Node;

public class DisplayInBrowserContraints 
   implements DisplayInBrowserContraintsInterface
{
   private boolean frameable;
   private boolean mountable;
   private boolean quickFrameable;
   private String mountedLink;
 
   public DisplayInBrowserContraints(Node node)
   {
/*
      this.frameable = 
         DomSearchHelper.getBooleanValueFromNode(
            DisplayInBrowserContraintsData.FRAMEABLE, 
            node.getChildNodes());
      Node node =
         DomSearchHelper.getNode(
            DisplayInBrowserContraintsData.FRAMEABLE, 
            node.getChildNodes());
      
      String frameableString = 

      this.frameable = new Boolean(frameableString);

      String mountableString = 
         DomSearchHelper.getNode(
            DisplayInBrowserContraintsData.MOUNTABLE, 
            node.getChildNodes());

      this.mountable = new Boolean(mountableString);

      String quickFrameableString = 
         DomSearchHelper.getNode(
            DisplayInBrowserContraintsData.QUICKFRAMEABLE, 
            node.getChildNodes());

      this.quickFrameable = new Boolean(quickFrameableString);

      this.mountedLink = 
         DomSearchHelper.getNode(
            DisplayInBrowserContraintsData.MOUNTABLELINK, 
            node.getChildNodes());
 */
   }

   public boolean isMountable()
   {
      return mountable;
   }

   public void setMountable(boolean mountable)
   {
      this.mountable = mountable;
   }

   public boolean isFrameable()
   {
      return frameable;
   }

   public void setFrameable(boolean frameable)
   {
      this.frameable = frameable;
   }

   public boolean isQuickFrameable()
   {
      return quickFrameable;
   }

   public void setQuickFrameable(boolean quickFrameable)
   {
      this.quickFrameable = quickFrameable;
   }

   public String getMountedLink()
   {
      return mountedLink;
   }

   public void setMountedLink(String mountedLink)
   {
      this.mountedLink = mountedLink;
   }
   
}
