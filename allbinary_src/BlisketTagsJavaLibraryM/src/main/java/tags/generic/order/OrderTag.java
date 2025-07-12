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
package tags.generic.order;

import javax.servlet.jsp.JspTagException;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import tags.StoreValidationTransformTag;

public class OrderTag extends StoreValidationTransformTag
{
   //private String orderId;
   
   public OrderTag()
   {
   }
   
   /*
   public void setOrderId(String value)
   {
      this.orderId=value;
   }*/
   
   //         pageContext.getOut().print("Please Review the order below, and click on the Place Order button.<p>");
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Basic Order View");
         this.setObjectFile("views.generic.order.ValidationView");
         //this.propertiesHashMap.put(OrderData.ORDERID, this.orderId);
         
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
         AbResponseHandler.sendJspTagRedirect(this.pageContext, e);
         return SKIP_BODY;
      }
   }
   
}
