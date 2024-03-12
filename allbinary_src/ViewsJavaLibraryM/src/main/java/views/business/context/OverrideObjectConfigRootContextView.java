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
package views.business.context;

import org.w3c.dom.Document;

import views.business.context.modules.storefront.HttpStoreComponentView;
import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.logic.string.CommonSeps;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.visual.transform.TransformFactory;
import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.logic.visual.transform.info.GeneratorTransformInfoData;
import org.allbinary.logic.visual.transform.info.RootTransformInfoData;
import org.allbinary.logic.visual.transform.info.TransformInfoHttp;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigAndManipulatorFactory;
import org.allbinary.logic.visual.transform.info.objectConfig.TransformInfoObjectConfigInterface;
import org.allbinary.logic.visual.transform.template.TransformTemplateInterface;

public class OverrideObjectConfigRootContextView extends HttpStoreComponentView
{
   public OverrideObjectConfigRootContextView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("View Name: " + transformInfoInterface.getName(), this, "OverrideObjectRootContextView()"));
      }
   }

   //Overrides a stores ObjectConfig with the requested TransformInfoInterface's
   //ObjectConfig (Purpose is to generate some specific pages instead of the default)
   //Used for Payment Gateway specific Pages
   public String view() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("View Name: " + this.getTransformInfoInterface().getName(), this, "view()"));
         }

         TransformInfoInterface rootTransformInfoInterface = (TransformInfoInterface)
            new TransformInfoHttp((TransformInfoHttp) this.getTransformInfoInterface());

         StringBuffer stringBuffer = new StringBuffer();
         
         stringBuffer.append(this.getTransformInfoInterface().getStoreName());
         stringBuffer.append(CommonSeps.getInstance().SPACE);
         
         String nameStart = stringBuffer.toString();
         
         //Future Imp add Dom Node that does this logic for configurability
         //Removing code to data insertion and instead kicking off transforms
         //From Event Listeners
         if(this.getTransformInfoInterface().getName().indexOf(GeneratorTransformInfoData.NAME) > 0)
         {
            rootTransformInfoInterface.setName(
            		nameStart + GeneratorTransformInfoData.NAME);
         }
         else
         if(this.getTransformInfoInterface().getName().indexOf(RootTransformInfoData.NAME) > 0)
         {
            rootTransformInfoInterface.setName(
            		nameStart + RootTransformInfoData.NAME);
         }
         else
         {
            throw new Exception("Override Currently Not Supported");
         }

         Document overrideObjectConfigDocument = 
           this.getTransformInfoInterface().getObjectConfigInterface().toXmlDoc();

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Retrieved OverrideObjectConfig: " + DomDocumentHelper.toString(overrideObjectConfigDocument), this, "view()"));
         }

         TransformInterface rootComponentInterface = 
            TransformFactory.getInstance(this.abeClientInformation,
               rootTransformInfoInterface.getName(), rootTransformInfoInterface);

         TransformTemplateInterface transformTemplateInterface = 
            (TransformTemplateInterface) rootComponentInterface;

         //The view that the Root is pointing to as specified in its TransformInfoObjectConfig
         TransformInterface componentInterface = 
            TransformFactory.getInstance(this.abeClientInformation,
               transformTemplateInterface.getName(),
               rootComponentInterface.getTransformInfoInterface());

         TransformInfoObjectConfigInterface transformInfoObjectConfigInterface = 
            TransformInfoObjectConfigAndManipulatorFactory.getInstance().getInstance(
                this.abeClientInformation,
               componentInterface.getTransformInfoInterface(), 
               overrideObjectConfigDocument);

         componentInterface.getTransformInfoInterface().setObjectConfigInterface(
               transformInfoObjectConfigInterface);

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Set OverrideObjectConfig: " + componentInterface.getTransformInfoInterface().getObjectConfigInterface().toString(), this, "view()"));
         }
         
         return componentInterface.view();
      }
      catch(Exception e)
      {
         String error = "Failed To View";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "view()", e));
         }
         throw e;
      }
   }
}
