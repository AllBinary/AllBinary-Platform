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


import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringUtil;
import org.allbinary.logic.visual.transform.generator.TransformsGeneratorUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.TransformInfosData;
import views.business.context.modules.storefront.HttpStoreComponentView;

public class GeneratorStoreFrontContextView extends HttpStoreComponentView
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public GeneratorStoreFrontContextView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("View Name: " + transformInfoInterface.getName(), this, this.commonStrings.CONSTRUCTOR);
      }
   }

   protected String view(String group) throws Exception
   {
     String result = 
        TransformsGeneratorUtil.getInstance().generateComponentsFromObjectConfig(
            this.abeClientInformation,
           this.getTransformInfoInterface(), group);

     if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().JSPEXTRAOUTPUT))
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
         //String error = "Failed To Generate StoreFront Context";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
         }
         throw e;
      }
   }
}
