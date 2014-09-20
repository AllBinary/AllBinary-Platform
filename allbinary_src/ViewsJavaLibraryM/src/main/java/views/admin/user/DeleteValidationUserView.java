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

import org.allbinary.logic.communication.log.LogFactory;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.data.tables.user.UserEntityFactory;


import org.allbinary.business.user.username.UserName;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.communication.http.request.RequestParams;

import org.allbinary.logic.communication.log.LogUtil;

public class DeleteValidationUserView extends UserView 
   implements ValidationComponentInterface
{
   private String userName;
   
   public DeleteValidationUserView(
      TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      super(transformInfoInterface);
      
      HttpServletRequest httpServletRequest = 
         (HttpServletRequest) this.getPageContext().getRequest();
      
      HashMap hashMap = new RequestParams(httpServletRequest).toHashMap();
      this.userName = new UserName(hashMap).get();

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("User Name: " + this.userName,this,"Constructor()"));
      }
   }

   public Boolean isValid()
   {
      try
      {
         if(UserName.isValid(this.userName) == Boolean.TRUE)
         {
            this.user = UserEntityFactory.getInstance().getUser(this.userName);
            if(user == null)
            {
               return Boolean.FALSE;
            }
         }
         else
         {
            return Boolean.FALSE;
         }

         return Boolean.TRUE;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate form",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         if(UserName.isValid(this.userName) == Boolean.TRUE)
         {
            this.user = UserEntityFactory.getInstance().getUser(this.userName);            
            if(user==null) stringBuffer.append("User does not exist.<br />");
         }
         else 
         {
            stringBuffer.append("User Name is not valid.<br />");
         }

         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Validating Form";
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
