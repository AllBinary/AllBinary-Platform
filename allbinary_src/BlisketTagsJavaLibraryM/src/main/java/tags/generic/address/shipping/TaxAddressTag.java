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
package tags.generic.address.shipping;

import javax.servlet.jsp.JspTagException;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import org.allbinary.logic.system.security.licensing.LicensingException;
import tags.StoreValidationTransformTag;

public class TaxAddressTag extends StoreValidationTransformTag
{
   //private String value;
   
   public TaxAddressTag()
   {
   }
   /*
   public void setValue(String value)
   {
      this.value=value;
   }
   */
   public int doStartTag() throws JspTagException
   {
      try
      {
         //this.getPropertiesHashMap().put(StreetAddressData.INDEX,this.value);
         
         if(this.getCommand()!=null)
         {
            
            if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.VIEW)==0)
            {
               this.setName("Basic Shipping Address View");
               this.setObjectFile("views.generic.address.shipping.TaxValidationView");
            }
            else
               if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.NEW)==0)
               {
                  this.setName("Basic Shipping Address View");
                  this.setObjectFile("views.generic.address.shipping.NewValidationView");
               }
               else
                  if (this.getCommand().compareTo(this.commonStrings.INSERT)==0)
                  {
                     this.setName("Basic Shipping Address View");
                     this.setObjectFile("views.generic.address.shipping.AddTaxValidationView");
                  }
                  else
                     if (this.getCommand().compareTo(this.commonStrings.UPDATE)==0)
                     {
                        this.setName("Basic Shipping Address View");
                        this.setObjectFile("views.generic.address.shipping.UpdateTaxValidationView");
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
         AbResponseHandler.sendJspTagRedirect(
         this.pageContext,
         e);
         return SKIP_BODY;
      }
   }
}
