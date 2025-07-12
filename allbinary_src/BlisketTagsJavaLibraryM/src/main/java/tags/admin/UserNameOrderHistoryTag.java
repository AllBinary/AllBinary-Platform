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
package tags.admin;

import javax.servlet.jsp.JspTagException;

import org.allbinary.logic.communication.http.request.AbResponseHandler;
import tags.StoreValidationTransformTag;

public class UserNameOrderHistoryTag extends StoreValidationTransformTag
{
   //private String id;
   private String status;
   //private String groupId;
   /*
   private String shipped;
   private String partiallyShipped;
   private String processing;
   private String preprocessing;
   private String cancelled;
    */
   
   public UserNameOrderHistoryTag()
   {
      super();
   }
   
/*
   public void setId(String id)
   {
      this.id=id;
   }
 */
   
   public void setStatus(String value)
   {
      this.status=value;
   }
   
  /*
   public void setGroupId(String value)
   {
      this.groupId=value;
   }
   */
   /*
   public void setShipped(String value)
   {
      this.shipped=value;
   }
    
   public void setPartiallyShipped(String value)
   {
      this.partiallyShipped=value;
   }
    
   public void setPreprocessing(String value)
   {
      this.preprocessing=value;
   }
    
   public void setProcessing(String value)
   {
      this.processing=value;
   }
    
   public void setCancelled(String value)
   {
      this.cancelled=value;
   }
    */
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         this.setName("Range Order History View");
         this.setObjectFile("views.admin.orderhistory.UserNameOrderHistoryView");
         
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

