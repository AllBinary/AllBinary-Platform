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
package admin.tags;

import abcs.logic.communication.http.request.AbResponseHandler;

import allbinary.business.entry.EntryData;
import allbinary.business.user.role.UserRoleData;

import admin.taghelpers.UserHelperFactory;
import javax.servlet.jsp.JspTagException;

public class UserTag extends TableTag
{
   private String role;
   private String enable;

   public UserTag()
   {
      this.setTagHelperFactory(new UserHelperFactory());
      this.setTagRequestHelperFactory(new UserHelperFactory());      
   }

   public void setRole(String value)
   {
      this.role = value;
      this.getPropertiesHashMap().put(UserRoleData.NAME, this.role);
   }

   public void setEnable(String value)
   {
      this.enable = value;
      this.getPropertiesHashMap().put(EntryData.getInstance().ENABLE, this.enable);
   }

   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.isEnabled())
         {
            return super.doStartTag();
         }
         return SKIP_BODY;
      }
      catch(Exception e)
      {
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
}
