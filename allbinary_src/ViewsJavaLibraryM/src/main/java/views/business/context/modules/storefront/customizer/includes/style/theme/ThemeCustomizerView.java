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
package views.business.context.modules.storefront.customizer.includes.style.theme;




import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.data.tree.dom.DomNodeInterface;

import org.allbinary.logic.control.validate.ValidationInterface;

import org.allbinary.logic.visual.transform.info.TransformInfoInterface;


import views.business.context.modules.storefront.HttpStoreComponentView;

public class ThemeCustomizerView extends HttpStoreComponentView
{
   protected ValidationInterface validationInterface;
   
   public ThemeCustomizerView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);
   }
   
   public void addDomNodeInterfaces() throws Exception
   {
      this.addDomNodeInterface((DomNodeInterface) this.validationInterface);
   }      

   public String view() throws Exception
   {
      try
      {
         this.addDomNodeInterfaces();
         return super.view();
      }
      catch(Exception e)
      {
         //String error = "Failed to view Theme Template";
         if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
            org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(commonStrings.EXCEPTION, this, "view()", e));
         }
         throw e;
      }
   }
}
