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
package views.generic.user;

import org.allbinary.logic.communication.log.LogFactory;
import org.w3c.dom.Node;
import org.w3c.dom.Document;


import org.allbinary.data.tables.user.UserEntityFactory;

import org.allbinary.business.user.username.UserName;


import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.communication.log.LogUtil;

public class EditValidationView extends UserView 
   implements ValidationComponentInterface
{
   public EditValidationView(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
   }

   public Boolean isValid()
   {
      try
      {
         if(UserName.getInstance().isValid(this.getWeblisketSession().getUserName()) == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("Failed to validate username",this,"isValid()"));
            }
            
            return Boolean.FALSE;
         }

         this.user = UserEntityFactory.getInstance().getUser(this.getWeblisketSession().getUserName());
         
         if(this.user==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("User does not exist",this,"isValid()"));
            }
            return Boolean.FALSE;
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("User exists but is invalid - Probably manually modified",this,"isValid()"));
            }
            return Boolean.FALSE;
         }
         
         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
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
         
         if(UserName.getInstance().isValid(this.getWeblisketSession().getUserName()) == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("Failed to validate username",this,"validationInfo()"));
            }
            stringBuffer.append("Session User Name is not valid - Your session data has been logged!");
         }

         if(this.user==null)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("User does not exist",this,"isValid()"));
            }
            stringBuffer.append("User profile does not exist - Your session data has been logged!");
         }
         
         if(this.user.isValid() == Boolean.FALSE)
         {
            if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
            {
               LogUtil.put(LogFactory.getInstance("User exists but is invalid - Probably manually modified",this,"isValid()"));
            }
            stringBuffer.append(this.user.validationInfo());
         }
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
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
   
}
