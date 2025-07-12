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
package org.allbinary.logic.visual.transform;

import javax.xml.transform.URIResolver;

import org.allbinary.data.tree.dom.BasicUriResolver;
import org.allbinary.data.tree.dom.StoreUriResolver;
import org.allbinary.logic.system.security.licensing.AbeClientInformationInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;

public class StoreTransformer extends BasicTransformer
{
   public StoreTransformer(final AbeClientInformationInterface abeClientInformation, final TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(abeClientInformation, transformInfoInterface);
      this.setURIResolver(
         (URIResolver) new StoreUriResolver(this.getTransformInfoInterface(), 
         (BasicUriResolver) this.getURIResolver()));
   }
}