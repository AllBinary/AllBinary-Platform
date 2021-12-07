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
package tags.generic.advertisement;

import javax.servlet.jsp.JspTagException;

import tags.StoreValidationTransformTag;
import org.allbinary.logic.communication.http.request.AbResponseHandler;

public class AdvertisementTag extends StoreValidationTransformTag
{
   public AdvertisementTag()
   {
   }
      
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Basic Ad View");
         this.setObjectFile("views.generic.advertisement.ValidationView");
         if(this.getCommand()!=null)
         {
            if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS.VIEW)==0)
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
