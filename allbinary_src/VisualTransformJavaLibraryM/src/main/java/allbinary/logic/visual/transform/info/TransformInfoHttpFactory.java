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
package allbinary.logic.visual.transform.info;

import abcs.logic.basic.string.StringValidationUtil;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.business.context.modules.storefront.StoreFrontFactory;
import allbinary.business.context.modules.storefront.StoreFrontInterface;
import allbinary.logic.communication.http.request.session.WeblisketSession;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;

public class TransformInfoHttpFactory
{
   private TransformInfoHttpFactory()
   {
   }
      
   public static TransformInfoInterface getInstance(
      HashMap propertiesHashMap, PageContext pageContext) 
      throws Exception
   {
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
         String error = "Failed To Get Instance";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
         abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, "TransformInfoFactory",
               "getInstance(HashMap, PageContext)", e));
         }
         throw e;
      }
   }
}