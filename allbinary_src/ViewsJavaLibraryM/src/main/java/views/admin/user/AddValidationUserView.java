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
package views.admin.user;

import abcs.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;


import abcs.logic.communication.log.LogUtil;

import allbinary.business.user.NewUserFactory;
import allbinary.business.user.UserInterface;

import allbinary.data.tables.user.UserEntityFactory;


import allbinary.logic.control.validate.ValidationComponentInterface;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import views.business.context.HttpContextView;

public class AddValidationUserView extends HttpContextView
   implements ValidationComponentInterface
{
   protected UserInterface user;
   
   public AddValidationUserView(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
      
      this.user = NewUserFactory.getInstance(transformInfoInterface);
   }

   public Boolean isValid()
   {
      try
      {
         if(UserEntityFactory.getInstance().getUser(this.user.getUserName()) != null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("User already in existance.",this,"isValid()"));
            }
            return Boolean.FALSE;
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            return Boolean.FALSE;
         }

         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(UserEntityFactory.getInstance().getUser(this.user.getUserName())!=null)
         {
            if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
            {
               LogUtil.put(LogFactory.getInstance("User already exists",this,"validationInfo()"));
            }
            stringBuffer.append("The User Name you selected is already in use.<br/>  Please select another User Name.<br />");
            //stringBuffer.append("Unable to add since User Name already in use.<br />");
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            stringBuffer.append(this.user.validationInfo());
         }
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Getting Validation Info";
      }
   }

   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String view() throws Exception
   {
      return views.ValidationOnlyTempUtil.view(this);
   }
}
