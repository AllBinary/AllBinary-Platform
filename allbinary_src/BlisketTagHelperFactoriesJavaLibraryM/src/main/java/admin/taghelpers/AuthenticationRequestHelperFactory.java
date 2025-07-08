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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.logic.system.security.licensing.LicensingException;
import javax.servlet.http.HttpServletRequest;
import org.allbinary.string.CommonStrings;

public class AuthenticationRequestHelperFactory extends TagHelperFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();
   
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
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
            if (org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
            {
                logUtil.put(commonStrings.EXCEPTION, this, commonStrings.GET_INSTANCE, e);
            }
            return null;
        }
   }
}
