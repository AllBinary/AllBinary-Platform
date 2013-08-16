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

import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import abcs.logic.system.security.licensing.LicensingException;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationRequestHelperFactory implements TagHelperFactoryInterface
{   
   public AuthenticationRequestHelperFactory()
   {
   }
   
   public Object getInstance(
      HashMap hashMap, PageContext pageContext)
      throws LicensingException
   {
        try
        {
            return new AuthenticationRequestHelper(hashMap, pageContext);
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                String error = "Failed To Get Instance";

                LogUtil.put(LogFactory.getInstance(error, this, "getInstance(HashMap, PageContext)", e));
            }
            return null;
        }
   }

   public Object getInstance(
      HashMap hashMap, HttpServletRequest httpServletRequest)
      throws LicensingException
   {
        try
        {
            return new AuthenticationRequestHelper(hashMap, httpServletRequest);
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                String error = "Failed To Get Instance";

                LogUtil.put(LogFactory.getInstance(error, this, "getInstance(HashMap, httpServletRequest)", e));
            }
            return null;
        }
   }
}
