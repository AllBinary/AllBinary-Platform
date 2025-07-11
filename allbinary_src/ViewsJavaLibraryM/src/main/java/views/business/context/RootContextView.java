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

import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.string.StringValidationUtil;
import org.allbinary.logic.visual.transform.TransformFactory;
import org.allbinary.logic.visual.transform.TransformInterface;
import org.allbinary.logic.visual.transform.info.TransformInfo;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.template.TransformTemplateInterface;
import views.business.context.modules.storefront.HttpStoreComponentView;
import views.business.context.modules.storefront.customizer.template.objectConfig.NoTemplateTransformInfoObjectConfig;

public class RootContextView extends HttpStoreComponentView 
   implements TransformTemplateInterface //DomNodeInterface
{
    protected final LogUtil logUtil = LogUtil.getInstance();

   public RootContextView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         logUtil.put("View Name: " + transformInfoInterface.getName(), this, "RootContextView()");
      }
   }

   //Loads the view stored in the object config and views it
   public String view() throws Exception
   {
      try
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("View Name: " + this.getTransformInfoInterface().getName(), this, "view()");
         }

         String viewName = this.getName();

         TransformInterface componentInterface = TransformFactory.getInstance().getInstance(
                this.abeClientInformation, viewName, this.getTransformInfoInterface());

         return componentInterface.view();
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "view()", e);
         }
         throw e;
      }
   }   

   public String getName() throws Exception
   {
      try
      {
         NoTemplateTransformInfoObjectConfig objectConfig =
            new NoTemplateTransformInfoObjectConfig(
               this.getTransformInfoInterface(), 
               this.getTransformInfoInterface().getObjectConfigInterface().toXmlDoc());

         Vector componentsVector = objectConfig.getTransforms();
         if(componentsVector.size() == 0)
         {
            throw new Exception("Template View Not Set - No Components");
         }

         if(componentsVector.size() > 1)
         {
            throw new Exception("To Many Root Template Views: " + componentsVector.size());
         }

         TransformInfo transformInfoInterface = (TransformInfo) componentsVector.get(0);

         String viewName = transformInfoInterface.getName();

         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
         {
            logUtil.put("Root Template With View Name: " + viewName, this, "getName()");
         }

         if(StringValidationUtil.getInstance().isEmpty(viewName))
         {
            throw new Exception("Template View Not Set - No View Name");
         }

         return viewName;
      }
      catch(Exception e)
      {
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEWERROR))
         {
            logUtil.put(commonStrings.EXCEPTION, this, "getName()", e);
         }
         throw e;
      }
   }
   
}
