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
package org.allbinary.logic.visual.transform.info;

import java.util.HashMap;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.string.CommonStrings;

public class TransformInfoHttpFactory
{
    //protected final LogUtil logUtil = LogUtil.getInstance();

   private TransformInfoHttpFactory()
   {
   }
      
   public static TransformInfoInterface getInstance(
      HashMap propertiesHashMap, PageContext pageContext) 
      throws Exception
   {
       final LogUtil logUtil = LogUtil.getInstance();
      try
      {
         WeblisketSession weblisketSession = new WeblisketSession(
            propertiesHashMap, pageContext);

         if(!StringValidationUtil.getInstance().isEmpty(weblisketSession.getStoreName()))
         {
            StoreFrontInterface storeFrontInterface =
               StoreFrontFactory.getInstance(weblisketSession.getStoreName());

            if(storeFrontInterface != null)
            {
               return (TransformInfoInterface) new TransformInfoHttpStore(
                     storeFrontInterface, propertiesHashMap, pageContext);
            }
            else
            {
               throw new Exception("No StoreFront with: " + weblisketSession.getStoreName());
            }
         }
         else
         {
               return (TransformInfoInterface) new TransformInfoHttpContext(
                  propertiesHashMap, pageContext);
         }
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERFACTORYERROR))
         {
             final CommonStrings commonStrings = CommonStrings.getInstance();
            logUtil.put(commonStrings.EXCEPTION, "TransformInfoFactory",commonStrings.GET_INSTANCE, e);
         }
         throw e;
      }
   }
}