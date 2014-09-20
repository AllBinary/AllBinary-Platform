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

import tags.StoreValidationTransformTag;

import org.allbinary.logic.communication.http.request.AbResponseHandler;

import javax.servlet.jsp.JspTagException;

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
            if (this.getCommand().compareTo(allbinary.globals.GLOBALS.NEW)==0)
            {
               this.setName("New WorkFlow View");
               this.setObjectFile("views.admin.workflow.NewView");
            }
            else
               if (this.getCommand().compareTo(allbinary.globals.GLOBALS.INSERT)==0)
               {
                  this.setName("Add Validation WorkFlow View");
                  this.setObjectFile("views.admin.workflow.AddValidationView");
               }
               else
                  if (this.getCommand().compareTo(allbinary.globals.GLOBALS.VIEW)==0)
                  {
                     this.setName("Validation WorkFlow View");
                     this.setObjectFile("views.admin.workflow.WorkflowsValidationView");
                  }
                  else
                     if (this.getCommand().compareTo(allbinary.globals.GLOBALS.EDIT)==0)
                     {
                        this.setName("Validation WorkFlow View");
                        this.setObjectFile("views.admin.workflow.EditValidationView");
                     }
                     else
                        if (this.getCommand().compareTo(allbinary.globals.GLOBALS.UPDATE)==0)
                        {
                           this.setName("Update Validation WorkFlow View");
                           this.setObjectFile("views.admin.workflow.UpdateValidationView");
                        }
                        else
                           if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DELETE)==0)
                           {
                              this.setName("Delete Validation WorkFlow View");
                              this.setObjectFile("views.admin.workflow.DeleteValidationView");
                           }
                           else
                              if (this.getCommand().compareTo(allbinary.globals.GLOBALS.BACKUP)==0)
                              {
                                 throw new Exception("View not implemented");
                              }
                              else
                                 if (this.getCommand().compareTo(allbinary.globals.GLOBALS.RESTORE)==0)
                                 {
                                    throw new Exception("View not implemented");
                                 }
                                 else
                                    if (this.getCommand().compareTo(allbinary.globals.GLOBALS.DROP)==0)
                                    {
                                       throw new Exception("View not implemented");
                                    }
                                    else
                                       if (this.getCommand().compareTo(allbinary.globals.GLOBALS.CREATE)==0)
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
