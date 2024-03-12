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
package views.compound;

import java.io.InputStream;

import javax.xml.transform.URIResolver;

import org.allbinary.data.tree.dom.BasicUriResolver;
import org.allbinary.data.tree.dom.StoreUriResolver;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.system.security.licensing.ServiceClientInformationInterfaceFactory;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.visual.transform.AbTransformer;

import views.compound.objectConfig.CompoundContextTransformInfoObjectConfig;

public class CompoundTransform extends AbTransformer
{
    private final AbeClientInformationInterface abeClientInformation = 
        ServiceClientInformationInterfaceFactory.getInstance();
    
   public CompoundTransform(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      this.setURIResolver(
         (URIResolver) new StoreUriResolver(
            this.getTransformInfoInterface(), 
            (BasicUriResolver) this.getURIResolver()));

      CompoundContextTransformInfoObjectConfig objectConfig =
         new CompoundContextTransformInfoObjectConfig(
             this.abeClientInformation,
            this.getTransformInfoInterface(), 
            this.getTransformInfoInterface().getObjectConfigInterface().toXmlDoc());

      if(org.allbinary.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(org.allbinary.logic.communication.log.config.type.LogConfigTypeFactory.getInstance().VIEW))
      {
         LogUtil.put(LogFactory.getInstance("\nObjectConfig: \n" + objectConfig.toString(), this, "CompoundTransform("));
      }

      InputStream templateInputStream = objectConfig.createInputStream();
      this.setInputStream(templateInputStream);
   }
}
