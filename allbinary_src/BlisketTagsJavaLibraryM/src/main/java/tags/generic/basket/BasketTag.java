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
package tags.generic.basket;

import tags.StoreValidationTransformTag;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import javax.servlet.jsp.JspTagException;

public class BasketTag extends StoreValidationTransformTag
{
   public BasketTag()
   {
   }
      
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Basic Basket View");
         this.setObjectFile("views.generic.basket.ValidationView");
         if(this.getCommand()!=null)
         {
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
         AbResponseHandler.sendJspTagRedirect(
         this.pageContext,
         e);
         return SKIP_BODY;
      }
   }
   
}
