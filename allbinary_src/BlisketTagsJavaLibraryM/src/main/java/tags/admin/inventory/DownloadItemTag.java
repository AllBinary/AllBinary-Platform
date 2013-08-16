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
package tags.admin.inventory;

import abcs.logic.system.security.licensing.LicensingException;

import abcs.logic.communication.http.request.AbResponseHandler;
import javax.servlet.jsp.JspTagException;

import tags.StoreValidationTransformTag;

public class DownloadItemTag extends StoreValidationTransformTag
{
   public DownloadItemTag()
   {
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            if (this.getCommand().compareTo(allbinary.globals.GLOBALS.VIEW)==0)
            {
               //Probably use generic taglib
               throw new Exception("No Such View Command: " + this.getCommand());
            }
            else
               if (this.getCommand().compareTo(allbinary.globals.GLOBALS.INSERT)==0)
               {
                  this.setName("Add Inventory Item Validation View");
                  this.setObjectFile("views.admin.inventory.download.AddFileValidationView");
               }
               else
                  if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DELETE)==0)
                  {
                     this.setName("Delete Inventory Item Validation View");
                     this.setObjectFile("views.admin.inventory.download.DeleteFileValidationView");
                  }
                  else
                     if (this.getCommand().compareTo(allbinary.globals.GLOBALS.EDIT)==0)
                     {
                        this.setName("Edit Inventory Item Validation View");
                        this.setObjectFile("views.admin.inventory.download.EditValidationView");
                     }
                     else
                        if (this.getCommand().compareTo(allbinary.globals.GLOBALS.NEW)==0)
                        {
                           this.setName("New Inventory Item Validation View");
                           this.setObjectFile("views.admin.inventory.download.NewValidationView");
                        }
                        else
                           if (this.getCommand().compareTo(allbinary.globals.GLOBALS.UPDATE)==0)
                           {
                              this.setName("Update Inventory Item Validation View");
                              this.setObjectFile("views.admin.inventory.download.UpdateFileValidationView");
                           }
                           else
                           {
                              throw new Exception("No Such View Command: " + this.getCommand());
                           }
            return super.doStartTag();
         }
         throw new Exception("Command Null");
         
      }
      catch(LicensingException e)
      {
         AbResponseHandler.sendJspTagLicensingRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
   
}
