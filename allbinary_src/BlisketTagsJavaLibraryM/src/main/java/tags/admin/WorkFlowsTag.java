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

public class WorkFlowsTag extends StoreValidationTransformTag
{
   public WorkFlowsTag()
   {
   }
   
   public int doStartTag() throws JspTagException
   {
      try
      {
         if(this.getCommand()!=null)
         {
            if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.NEW)==0)
            {
               this.setName("New WorkFlow View");
               this.setObjectFile("views.admin.workflow.NewView");
            }
            else
               if (this.getCommand().compareTo(this.commonStrings.INSERT)==0)
               {
                  this.setName("Add Validation WorkFlow View");
                  this.setObjectFile("views.admin.workflow.AddValidationView");
               }
               else
                  if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.VIEW)==0)
                  {
                     this.setName("Validation WorkFlow View");
                     this.setObjectFile("views.admin.workflow.WorkflowsValidationView");
                  }
                  else
                     if (this.getCommand().compareTo(org.allbinary.globals.GLOBALS2.EDIT)==0)
                     {
                        this.setName("Validation WorkFlow View");
                        this.setObjectFile("views.admin.workflow.EditValidationView");
                     }
                     else
                        if (this.getCommand().compareTo(this.commonStrings.UPDATE)==0)
                        {
                           this.setName("Update Validation WorkFlow View");
                           this.setObjectFile("views.admin.workflow.UpdateValidationView");
                        }
                        else
                           if (this.getCommand().compareTo(this.commonStrings.DELETE)==0)
                           {
                              this.setName("Delete Validation WorkFlow View");
                              this.setObjectFile("views.admin.workflow.DeleteValidationView");
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
         AbResponseHandler.sendJspTagRedirect(
         this.pageContext,
         e);
         return SKIP_BODY;
      }
   }
}
