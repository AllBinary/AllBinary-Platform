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
package tags.admin.storefront;

import javax.servlet.jsp.JspTagException;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.StoreValidationTransformTag;

public class StoreFrontTag extends StoreValidationTransformTag
{
   
   public StoreFrontTag()
   {
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            
            if (this.getCommand().compareTo(this.commonStrings.INSERT)==0)
            {
               this.setName("Add Validation StoreFront View");
               this.setObjectFile("views.admin.storefront.AddValidationStoreFrontView");
            }
            else
               if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.VIEW)==0)
               {
                  throw new Exception("View not implemented");
                  //this.setName("Validation User View");
                  //this.setObjectFile("views.admin.storefront.ViewValidationStoreFrontView");
               }
               else
                  if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.EDIT)==0)
                  {
                     this.setName("Edit Validation StoreFront View");
                     this.setObjectFile("views.admin.storefront.EditValidationStoreFrontView");
                  }
                  else
                     if (this.getCommand().compareTo(this.commonStrings.UPDATE)==0)
                     {                        
                        this.setName("Update Validation StoreFront View");
                        this.setObjectFile("views.admin.storefront.UpdateValidationStoreFrontView");
                     }
                     else
                        if (this.getCommand().compareTo(this.commonStrings.DELETE)==0)
                        {                           
                           this.setName("Delete Validation StoreFront View");
                           this.setObjectFile("views.admin.storefront.DeleteValidationStoreFrontView");
                        }
                        else
                           if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.BACKUP)==0)
                           {
                              throw new Exception("View not implemented");
                           }
                           else
                              if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.RESTORE)==0)
                              {
                                 throw new Exception("View not implemented");
                              }
                              else
                                 if (this.getCommand().compareTo(this.commonStrings.DROP)==0)
                                 {
                                    throw new Exception("View not implemented");
                                 }
                                 else
                                    if (this.getCommand().compareTo(this.commonStrings.CREATE)==0)
                                    {
                                       throw new Exception("View not implemented");
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
