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

import org.allbinary.logic.system.security.licensing.LicensingException;

import tags.StoreValidationTransformTag;

import org.allbinary.logic.communication.http.request.AbResponseHandler;

import javax.servlet.jsp.JspTagException;

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
            
            if (this.getCommand().compareTo(allbinary.globals.GLOBALS.INSERT)==0)
            {
               this.setName("Add Validation StoreFront View");
               this.setObjectFile("views.admin.storefront.AddValidationStoreFrontView");
            }
            else
               if (this.getCommand().compareTo(allbinary.globals.GLOBALS.VIEW)==0)
               {
                  throw new Exception("View not implemented");
                  //this.setName("Validation User View");
                  //this.setObjectFile("views.admin.storefront.ViewValidationStoreFrontView");
               }
               else
                  if (this.getCommand().compareTo(allbinary.globals.GLOBALS.EDIT)==0)
                  {
                     this.setName("Edit Validation StoreFront View");
                     this.setObjectFile("views.admin.storefront.EditValidationStoreFrontView");
                  }
                  else
                     if (this.getCommand().compareTo(allbinary.globals.GLOBALS.UPDATE)==0)
                     {                        
                        this.setName("Update Validation StoreFront View");
                        this.setObjectFile("views.admin.storefront.UpdateValidationStoreFrontView");
                     }
                     else
                        if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DELETE)==0)
                        {                           
                           this.setName("Delete Validation StoreFront View");
                           this.setObjectFile("views.admin.storefront.DeleteValidationStoreFrontView");
                        }
                        else
                           if (this.getCommand().compareTo(allbinary.globals.GLOBALS.BACKUP)==0)
                           {
                              throw new Exception("View not implemented");
                           }
                           else
                              if (this.getCommand().compareTo(allbinary.globals.GLOBALS.RESTORE)==0)
                              {
                                 throw new Exception("View not implemented");
                              }
                              else
                                 if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DROP)==0)
                                 {
                                    throw new Exception("View not implemented");
                                 }
                                 else
                                    if (this.getCommand().compareTo(allbinary.globals.GLOBALS.CREATE)==0)
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
