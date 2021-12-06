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




import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.info.TransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoHttpInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.TransformInfosData;


public class PreviewGeneratorStoreFrontContextView extends GeneratorStoreFrontContextView
{
   public PreviewGeneratorStoreFrontContextView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }

   public String view() throws Exception
   {
      try
      {
         TransformInfoHttpInterface httpTransformInfoInterface = 
           (TransformInfoHttpInterface) this.getTransformInfoInterface();
         
         httpTransformInfoInterface.getPropertiesHashMap().put(
                 TransformInfoData.getInstance().PARTIAL, 
                 CommonSeps.getInstance().SPACE + TransformInfosData.getInstance().PREVIEW);

         return this.view(TransformInfosData.getInstance().PREVIEW);
      }
      catch(Exception e)
      {
         String error = "Failed To View Store Template Compound Component";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         throw e;
      }
   }
}
