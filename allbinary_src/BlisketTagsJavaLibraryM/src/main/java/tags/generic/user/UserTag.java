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
package tags.generic.user;

import javax.servlet.jsp.JspTagException;

import org.allbinary.business.user.role.UserRoleData;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import tags.StoreValidationTransformTag;

public class UserTag  extends StoreValidationTransformTag
{
   private String role;

   public UserTag()
   {
   }
   
   public void setRole(String value)
   {
      this.role = value;
      this.getPropertiesHashMap().put(UserRoleData.NAME,this.role);
   }

   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.EDIT + org.allbinary.globals.GLOBALS2.EDIT)==0)
            {
               this.setName("Edit Session Validation User View - i.e. User Profile Edit");
               this.setObjectFile("views.generic.user.EditValidationView");
            }
               if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.NEW)==0)
               {
                  this.setName("New User View");
                  this.setObjectFile("views.generic.user.NewUserView");
               }
               else
                  if (this.getCommand().compareTo(this.commonStrings.INSERT)==0)
                  {
                     this.setName("Add Validation User View");
                     this.setObjectFile("views.generic.user.AddValidationUserView");
                  }
                  else
                     if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.VIEW)==0)
                     {
                        this.setName("Validation User View");
                        this.setObjectFile("views.generic.user.ViewValidationUserView");
                     }
                     else
                        if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.EDIT)==0)
                        {
                           this.setName("Validation User View");
                           this.setObjectFile("views.generic.user.EditValidationUserView");
                        }
                        else
                           if (this.getCommand().compareTo(this.commonStrings.UPDATE)==0)
                           {
                              this.setName("Update Validation User View");
                              this.setObjectFile("views.generic.user.UpdateValidationUserView");
                           }
                           else
                              if (this.getCommand().compareTo(this.commonStrings.DELETE)==0)
                              {
                                 this.setName("Delete Validation User View");
                                 this.setObjectFile("views.generic.user.DeleteValidationUserView");
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
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
