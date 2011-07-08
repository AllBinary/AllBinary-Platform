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
package views.business.context.modules.storefront.generator;


import abcs.logic.basic.string.StringUtil;
import abcs.logic.communication.log.LogFactory;

import allbinary.logic.visual.transform.info.TransformInfosData;

import allbinary.logic.visual.transform.generator.TransformsGeneratorUtil;

import allbinary.logic.visual.transform.info.TransformInfoInterface;

import abcs.logic.communication.log.LogUtil;

import views.business.context.modules.storefront.HttpStoreComponentView;

public class GeneratorStoreFrontContextView extends HttpStoreComponentView
{
   public GeneratorStoreFrontContextView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "Constructor()"));
      }
   }

   protected String view(String group) throws Exception
   {
     String result = 
        TransformsGeneratorUtil.generateComponentsFromObjectConfig(
           this.getTransformInfoInterface(), group);

     if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.JSPEXTRAOUTPUT))
     {
        return result;
     }
     else
     {
        return StringUtil.getInstance().EMPTY_STRING;
     }
   }

   public String view() throws Exception
   {
      try
      {
         return this.view(TransformInfosData.getInstance().ALL);
      }
      catch(Exception e)
      {
         //String error = "Failed To View Store Template Compound Component";
         String error = "Failed To Generate StoreFront Context";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         throw e;
      }
   }
}
