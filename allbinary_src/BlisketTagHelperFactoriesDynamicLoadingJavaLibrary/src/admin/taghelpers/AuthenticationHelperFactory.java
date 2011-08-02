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
package admin.taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import abcs.logic.system.security.licensing.LicensingException;
import javax.servlet.http.HttpServletRequest;



public class AuthenticationHelperFactory implements TagHelperFactoryInterface
{
   private static final String CLASSNAME = "admin.taghelpers.AuthenticationHelper";
   private static final String FACTORYNAME = "AuthenticationHelperFactory";
   
   public AuthenticationHelperFactory()
   {
   }
   
   public Object getInstance(
      HashMap hashMap, PageContext pageContext) 
      throws LicensingException
   {
      return HelperFactory.getInstance(FACTORYNAME, CLASSNAME, hashMap, pageContext);
   }

   public Object getInstance(
      HashMap hashMap, HttpServletRequest httpServletRequest)
      throws LicensingException
   {
      return HelperFactory.getInstance(FACTORYNAME, CLASSNAME, hashMap, httpServletRequest);
   }
}
