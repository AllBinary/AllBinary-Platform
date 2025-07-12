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
package org.allbinary.business.user;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.allbinary.business.user.modules.User;
import org.allbinary.business.user.role.UserRoleData;
import org.allbinary.logic.communication.http.request.RequestParams;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class NewUserFactory
{
   private NewUserFactory()
   {
   }

   public static UserInterface getInstance() throws Exception
   {
      return new User();
   }
   
   //Uses the Role used in the tag not the one in the request for security
   //For adding new users use role property   
   public static UserInterface getInstance(TransformInfoInterface transformInfoInterface) 
      throws Exception
   {
      TransformInfoHttpInterface httpTransformInfoInterface = 
         (TransformInfoHttpInterface) transformInfoInterface;
      
      HttpServletRequest httpServletRequest = 
         (HttpServletRequest) httpTransformInfoInterface.getPageContext().getRequest();

      HashMap hashMap = new RequestParams(httpServletRequest).toHashMap();

      String role = (String) httpTransformInfoInterface.getPropertiesHashMap().get(
         UserRoleData.NAME.toString());

      hashMap.put(UserRoleData.NAME.toString(), role);
      
      return CreateUserFactory.getInstance(hashMap);
   }
   
   //Uses the Role used in the tag not the one in the request for security
   //Process a new user request where role is a tag property
   public static UserInterface getInstance(HttpServletRequest httpServletRequest, HashMap propertiesHashMap) throws Exception
   {
      if(propertiesHashMap!=null)
      {
         HashMap requestHashMap = new RequestParams(httpServletRequest).toHashMap();

         String roleString = (String) 
             propertiesHashMap.get(UserRoleData.NAME.toString());

         requestHashMap.put(UserRoleData.NAME.toString(), roleString);

         return CreateUserFactory.getInstance(requestHashMap);
      }
      else 
      {
         throw new Exception("Null HashMap");
      }
   }
}