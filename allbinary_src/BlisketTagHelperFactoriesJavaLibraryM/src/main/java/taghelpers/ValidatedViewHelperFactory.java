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
package taghelpers;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import admin.taghelpers.TagHelperFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.system.security.licensing.LicensingException;


public class ValidatedViewHelperFactory extends TagHelperFactory
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public ValidatedViewHelperFactory()
   {
   }

   public Object getInstance(
      HashMap hashMap, PageContext pageContext) 
      throws Exception, LicensingException
   {
        try
        {
            return new ValidationViewHelper(hashMap, pageContext);
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
