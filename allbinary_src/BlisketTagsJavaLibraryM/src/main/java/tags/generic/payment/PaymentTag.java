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
package tags.generic.payment;

import javax.servlet.jsp.JspTagException;

import org.allbinary.business.user.commerce.money.payment.PaymentData;
import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.StoreValidationTransformTag;

public class PaymentTag extends StoreValidationTransformTag
{
   public PaymentTag()
   {
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Basic Payment View");
         this.setObjectFile("views.generic.payment.ValidationView");
         
         if(this.getCommand()!=null)
         {
            
            if (this.getCommand().compareTo(PaymentData.VIEW)==0 ||
            this.getCommand().compareTo(PaymentData.CHANGE)==0 ||
            this.getCommand().compareTo(PaymentData.ADDNEW)==0)
            {
               
            }
            else
               if (this.getCommand().compareTo(this.commonStrings.INSERT)==0)
               {
                  //pageContext.getOut().print("Payment Info Failed To Validate - All fields must contain data<p>");
               }
               else
                  if (this.getCommand().compareTo(this.commonStrings.DELETE)==0)
                  {
                     return this.EVAL_BODY_INCLUDE;
                  }
                  else
                     if (this.getCommand().compareTo(PaymentData.SELECT)==0)
                     {
                        return this.EVAL_BODY_INCLUDE;
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
