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


import allbinary.business.user.role.UserRole;
import allbinary.business.user.role.UserRoleData;

import allbinary.data.tables.user.UserEntityFactory;

import allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import allbinary.logic.visual.transform.info.TransformInfoInterface;

import allbinary.logic.control.validate.ValidationComponentInterface;

import abcs.logic.communication.log.LogUtil;
import allbinary.business.user.role.UserRoleB;

public class UsersValidationView extends UsersView 
   implements ValidationComponentInterface
{
   public UsersValidationView(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
      
      TransformInfoHttpInterface httpTransformInfoInterface = 
         (TransformInfoHttpInterface) this.getTransformInfoInterface();

      String role = (String) 
         httpTransformInfoInterface.getPropertiesHashMap().get(
            UserRoleData.NAME.toString());

      UserRole userRole = UserRoleB.getRole(role);

      this.userVector = 
         UserEntityFactory.getInstance().getUsersWithRole(userRole);
   }

   public Boolean isValid()
   {
      try
      {
         if(this.userVector.size()>0)
         {
            return Boolean.TRUE;
         }
         else
         {
            return Boolean.FALSE;
         }
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
         return "No Users";
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
   
}
