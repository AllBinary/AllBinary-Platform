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


public class UsersTag extends StoreValidationTransformTag
{
   private String role;
   
   public UsersTag()
   {
   }
   
   public void setRole(String value)
   {
      this.role=value;
   }

   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Generic Users View");
         this.setObjectFile("views.generic.user.UsersValidationView");
         
         if(this.getCommand()!=null)
         {
            this.getPropertiesHashMap().put(UserRoleData.NAME,this.role);
            
            if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.VIEW)==0)
            {
               
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
